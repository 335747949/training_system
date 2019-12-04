package com.ruoyi.train.course.controller;

import cn.hutool.json.JSONObject;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.train.course.domain.*;
import com.ruoyi.train.course.service.ITrainCourseCategoryService;
import com.ruoyi.train.course.service.ITrainCourseSectionService;
import com.ruoyi.train.course.service.ITrainCourseService;
import com.ruoyi.train.course.service.ITrainCourseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程 信息操作处理
 *
 * @author zhujj
 * @date 2018-12-23
 */
@RestController
@RequestMapping("/api/v1")
public class ApiTrainCourseController extends BaseController {
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
    private ISysConfigService configService;
    /**
     * 查询课程列表
     */
    @GetMapping("/trainCourse/category")
    public AjaxResult list(TrainCourseCategory category) {
        List<TrainCourseCategory> trainCourseCategories = trainCourseCategoryService.selectCategoryList( category );
        AjaxResult success = success( "查询成功" );
        success.put( "data", trainCourseCategories );
        return success;
    }

    /**
     * 查询课程列表
     */
    @GetMapping("/trainCourse/list")
    public AjaxResult list(TrainCourseVO trainCourse) {
        List<TrainCourseVO> list = trainCourseService.selectTrainCoursePage( trainCourse );
        AjaxResult success = success( "查询成功" );
        success.put( "data", list );
        success.put("total",new PageInfo(list).getTotal());
        return success;
    }

    /**
     * 查询我的课程列表
     */
    @GetMapping("/trainCourse/myList")
    public AjaxResult myList(TrainCourseVO trainCourse) {
        SysUser sysUser = sysUserService.selectUserByLoginName( JwtUtil.getLoginName(), UserConstants.USER_VIP );
        trainCourse.setUserId( sysUser.getUserId().intValue() );
        List<TrainCourseVO> list = trainCourseService.selectTrainCoursePage( trainCourse );
        AjaxResult success = success( "查询成功" );
        success.put( "data", list );
        return success;
    }

    /**
     * 查询课程详情
     */
    @GetMapping("/trainCourse/{id}")
    public AjaxResult get(@PathVariable("id") Integer id) {
        TrainCourse trainCourse = trainCourseService.selectById( id );
        boolean courseAuth = false;
        String courseDays = configService.selectConfigByKey( "course.days" );
        String loginName = JwtUtil.getLoginName();
        if (StringUtils.isNotEmpty( loginName )) {
            SysUser sysUser = sysUserService.selectUserByLoginName( loginName,UserConstants.USER_VIP );
            if (sysUser != null) {
                courseAuth = trainCourseUserService.authority( sysUser.getUserId(), id );
            }
        }
        AjaxResult success = success( "查询成功" );
        success.put( "data", trainCourse );
        success.put( "courseAuth", courseAuth );
        success.put( "courseDays", courseDays );
        return success;
    }

    /**
     * 查询课程章节列表详情
     */
    @GetMapping("/trainCourse/{id}/section")
    public AjaxResult trainCourseSection(@PathVariable("id") Integer id) {
        TrainCourseSection trainCourseSection = new TrainCourseSection();
        trainCourseSection.setTrainCourseId( id );
        List<TrainCourseSection> trainCourseSections = trainCourseSectionService.selectTrainCourseSectionList( trainCourseSection );

        AjaxResult success = success( "查询成功" );
        success.put( "data", trainCourseSections );
        return success;
    }

    /**
     * 查询课程章节列表详情
     */
    @GetMapping("/trainCourse/section/{id}")
    public AjaxResult trainCourseSectionInfo(@PathVariable("id") Integer id) {
        TrainCourseSection trainCourseSections = trainCourseSectionService.selectById( id );

        AjaxResult success = success( "查询成功" );
        success.put( "data", trainCourseSections );
        return success;
    }

}
