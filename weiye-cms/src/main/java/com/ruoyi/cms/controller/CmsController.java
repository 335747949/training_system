package com.ruoyi.cms.controller;

import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.exam.domain.ExamPractice;
import com.ruoyi.exam.service.IExamPracticeService;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.train.course.domain.TrainCourse;
import com.ruoyi.train.course.domain.TrainCourseCategory;
import com.ruoyi.train.course.domain.TrainCourseSection;
import com.ruoyi.train.course.domain.TrainCourseVO;
import com.ruoyi.train.course.service.ITrainCourseCategoryService;
import com.ruoyi.train.course.service.ITrainCourseSectionService;
import com.ruoyi.train.course.service.ITrainCourseService;
import com.ruoyi.train.course.service.ITrainCourseUserService;
import com.ruoyi.vip.domain.VipUserCourseSection;
import com.ruoyi.vip.domain.vo.VipUserCourseSectionVO;
import com.ruoyi.vip.service.IVipUserCourseSectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 通用请求处理
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/web")
public class CmsController  extends BaseController {
    private static final Logger log = LoggerFactory.getLogger( CmsController.class );

    private String prefix = "web";

    @Autowired
    private ITrainCourseService trainCourseService;

    @Autowired
    private ITrainCourseUserService trainCourseUserService;

    @Autowired
    private ITrainCourseCategoryService trainCourseCategoryService;

    @Autowired
    private ITrainCourseSectionService trainCourseSectionService;

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private IExamPracticeService examPracticeService;

    @Autowired
    private ISysConfigService configService;

    @RequestMapping({"", "/index", "/index.html"})
    @GetMapping()
    public String index(String parentIds, ModelMap map) {
        SysUser user = ShiroUtils.getSysUser();
        if (ObjectUtils.isEmpty(user) || !UserConstants.USER_VIP.equals(user.getUserType())){
            return prefix + "/user/login";
        }
        TrainCourseVO trainCourseVO = new TrainCourseVO();
        //三级分类列表根据parentIds判断
        TrainCourseCategory courseCategory = new TrainCourseCategory();
        courseCategory.setParentId( (long) 100 );
        List<TrainCourseCategory> courseCategories1 = trainCourseCategoryService.selectCategoryList( courseCategory );
        List<TrainCourseCategory> courseCategories2 = new ArrayList<>();
        List<TrainCourseCategory> courseCategories3 = new ArrayList<>();
        if (StrUtil.isNotBlank( parentIds ) && parentIds.split( "," ).length >= 3) {//二级分类
            Long parentId = new Long( parentIds.split( "," )[2] );
            courseCategory.setParentId( parentId );
            courseCategories2 = trainCourseCategoryService.selectCategoryList( courseCategory );

            map.put( "selectCategoryId1", parentId );
        }
        if (StrUtil.isNotBlank( parentIds ) && parentIds.split( "," ).length >= 4) {//三级分类
            Long parentId = new Long( parentIds.split( "," )[3] );
            courseCategory.setParentId( parentId );
            courseCategories3 = trainCourseCategoryService.selectCategoryList( courseCategory );
            //当前选中的分类id
            map.put( "selectCategoryId2", parentId );
            Long parentId1 = new Long( parentIds.split( "," )[2] );
            map.put( "selectCategoryId1", parentId1 );
        }
        if (StrUtil.isNotBlank( parentIds )) {
            trainCourseVO.setTrainCourseCategoryId( new Integer( parentIds.split( "," )[parentIds.split( "," ).length - 1] ) );
            //当前选中的分类id
            map.put( "selectCategoryId", parentIds.split( "," )[parentIds.split( "," ).length - 1] );
        }

        // 用户端仅展示公开课程
        trainCourseVO.setState("1");
        List<TrainCourseVO> list = trainCourseService.selectTrainCoursePage( trainCourseVO );
        map.put( "trainCourse", list );
        map.put( "user", ShiroUtils.getSysUser() );
        //三级分类列表
        map.put( "courseCategories1", courseCategories1 );
        map.put( "courseCategories2", courseCategories2 );
        map.put( "courseCategories3", courseCategories3 );
        return prefix + "/index";
    }

    @RequestMapping("/course/courseInfo.html/{id}")
    @GetMapping()
    public String courseInfo(@PathVariable("id") Integer id, ModelMap map) {
        SysUser user = ShiroUtils.getSysUser();
        if (ObjectUtils.isEmpty(user) || !UserConstants.USER_VIP.equals(user.getUserType())){
            return prefix + "/user/login";
        }
        TrainCourse trainCourse = trainCourseService.selectById( id );
        TrainCourseSection trainCourseSection = new TrainCourseSection();
        trainCourseSection.setTrainCourseId( id );
        List<TrainCourseSection> trainCourseSections = trainCourseSectionService.selectTrainCourseSectionList( trainCourseSection );
        ExamPractice examPractice = new ExamPractice();
        examPractice.setTrainCourseId( id );
        List<ExamPractice> examPractices = examPracticeService.selectExamPracticeList( examPractice );

        boolean courseAuth=false;
        String s = configService.selectConfigByKey( "course.days" );
        if (ShiroUtils.getSysUser() != null) {
            courseAuth = trainCourseUserService.authority( ShiroUtils.getSysUser().getUserId(), id );
        }
        map.put( "courseAuth",courseAuth);
        map.put( "user", ShiroUtils.getSysUser() );
        map.put( "courseDays", s );
        map.put( "trainCourse", trainCourse );
        map.put( "trainCourseSections", trainCourseSections );
        map.put( "examPractices", examPractices );
        return prefix + "/course/courseInfo";
    }

    @RequestMapping("/course/courseSections.html/{id}")
    @GetMapping()
    public String courseSections(@PathVariable("id") Integer id, ModelMap map) {
        SysUser user = ShiroUtils.getSysUser();
        if (ObjectUtils.isEmpty(user) || !UserConstants.USER_VIP.equals(user.getUserType())){
            return prefix + "/user/login";
        }
            TrainCourseSection tcs = trainCourseSectionService.selectById( id );
        TrainCourseSection trainCourseSection = new TrainCourseSection();
        trainCourseSection.setTrainCourseId( tcs.getTrainCourseId() );
        List<TrainCourseSection> trainCourseSections = trainCourseSectionService.selectTrainCourseSectionList( trainCourseSection );
        ExamPractice examPractice = new ExamPractice();
        examPractice.setTrainCourseId( id );
        map.put( "trainCourseSection", tcs );
        map.put( "trainCourseSections", trainCourseSections );
        map.put( "user", ShiroUtils.getSysUser() );
        return prefix + "/course/courseSections";
    }

    @Autowired
    private IVipUserCourseSectionService vipUserCourseSectionService;
    @PostMapping("/user/course/study")
    @ResponseBody
    public AjaxResult courseSections(VipUserCourseSection trainCourseSection, ModelMap map) {
        SysUser sysUser = ShiroUtils.getSysUser();
        trainCourseSection.setVipUserId( sysUser.getUserId().intValue() );
        vipUserCourseSectionService.updateStudy(trainCourseSection);

        return success("增加学习进度");
    }
}
