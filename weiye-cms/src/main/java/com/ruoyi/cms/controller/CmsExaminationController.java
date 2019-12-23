package com.ruoyi.cms.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.exam.domain.*;
import com.ruoyi.exam.service.*;
import com.ruoyi.exam.service.impl.ExamExaminationServiceImpl;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.web.exception.base.BaseException;
import com.ruoyi.framework.web.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by flower on 2019/1/21.
 */
@Controller
@RequestMapping("/web")
public class CmsExaminationController {
    private String prefix = "web/examination/";

    @Autowired
    private IExamExaminationService examExaminationService;

    @Autowired
    private IExamPaperService examPaperService;

    @Autowired
    private IExamUserExaminationService examUserExaminationService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IExamExaminationUserService examExaminationUserService;

    @Autowired
    private IExamUserExaminationQuestionService examUserExaminationQuestionService;

    @Autowired
    private IExamQuestionService examQuestionService;

    @Autowired
    private IExamPaperQuestionService examPaperQuestionService;

    @Autowired
    private IExamPaperTypeNumberService examPaperTypeNumberService;

    @RequestMapping("/examination")
    @GetMapping()
    public String list(ModelMap map) {
        SysUser user = ShiroUtils.getSysUser();
        if (ObjectUtils.isEmpty(user) || !UserConstants.USER_VIP.equals(user.getUserType())){
            return "web/user/login";
        }
        map.put( "user", user);
        return prefix + "list";
    }

    /**
     * 考试列表
     * @param examExamination
     * @return
     */
    @RequestMapping("/examination/list")
    @GetMapping()
    @ResponseBody
    public AjaxResult list(ExamExamination examExamination) {
        SysUser sysUser = ShiroUtils.getSysUser();
        if (ObjectUtils.isEmpty(sysUser) || !UserConstants.USER_VIP.equals(sysUser.getUserType())){
            AjaxResult fail = AjaxResult.error("请登录");
            return fail;
        }
        Map<String, Object> map = new HashMap<>();
        map.put( "ination", examExamination );
        map.put( "userId", sysUser.getUserId() );
        List<ExamExamination> list = examExaminationService.selectListFromWeb( map );

        List<ExamExamination> resultList = new ArrayList<>();
        for (ExamExamination exam : list) {
           int count = examExaminationService.countExamQuestion(exam.getId());
           if (count > 0){
               resultList.add(exam);
            }
        }

        AjaxResult success = AjaxResult.success( "查询成功" );
        success.put( "data", resultList );
        success.put("total",new PageInfo(resultList).getTotal());
        return success;
    }

    /**
     * 报名列表
     * @param examExamination
     * @return
     */
    @RequestMapping("/examination/signuplist")
    @GetMapping()
    @ResponseBody
    public AjaxResult signupist(ExamExamination examExamination) {
        SysUser sysUser = ShiroUtils.getSysUser();
        if (ObjectUtils.isEmpty(sysUser) || !UserConstants.USER_VIP.equals(sysUser.getUserType())){
            AjaxResult fail = AjaxResult.error("请登录");
            return fail;
        }
        Map<String, Object> map = new HashMap<>();
        map.put( "ination", examExamination );
        map.put( "userId", sysUser.getUserId() );
        List<ExamExamination> list = examExaminationService.selectSignUpListFromWeb( map );

        List<ExamExamination> resultList = new ArrayList<>();
        for (ExamExamination exam : list) {
            int count = examExaminationService.countExamQuestion(exam.getId());
            if (count > 0){
                resultList.add(exam);
            }
        }
        AjaxResult success = AjaxResult.success( "查询成功" );
        success.put("total",new PageInfo(resultList).getTotal());
        success.put( "data", resultList );
        return success;
    }


    /**
     * 报名
     * @param id
     * @return
     */
    @RequestMapping("/examination/signup/{id}")
    @GetMapping()
    @ResponseBody
    public AjaxResult signupist(@PathVariable Integer id) {
        SysUser sysUser = ShiroUtils.getSysUser();
        if (ObjectUtils.isEmpty(sysUser) || !UserConstants.USER_VIP.equals(sysUser.getUserType())){
            AjaxResult fail = AjaxResult.error("请登录");
            return fail;
        }
        Long userId = sysUser.getUserId();
        ExamExaminationUser examExaminationUser = new ExamExaminationUser();
        examExaminationUser.setVipUserId( Integer.parseInt( userId.toString() ) );
        examExaminationUser.setDelFlag( "0" );
        examExaminationUser.setCreateDate( new Date() );
        examExaminationUser.setCreateBy( sysUser.getLoginName() );
        examExaminationUser.setExamExaminationId(id);
        examExaminationUserService.insertOne( examExaminationUser );

        AjaxResult success = AjaxResult.success( "报名成功" );
        return success;
    }



    /**
     * 开始考试
     * @param id
     * @param mmap
     * @return
     */
    @RequestMapping("/examination/start/{id}")
    @GetMapping()
    public String start(@PathVariable String id, ModelMap mmap) {
        SysUser user = ShiroUtils.getSysUser();
        if (ObjectUtils.isEmpty(user) || !UserConstants.USER_VIP.equals(user.getUserType())){
            return "web/user/login";
        }

        ExamExamination examExamination = examExaminationService.selectById(id);

        ExamUserExamination eue = new ExamUserExamination();
        Map<String,Object> result = examExaminationService.queryExaminationQuestion(examExamination,eue);
        if (!ObjectUtils.isEmpty(result.get("fail"))){
            String message = result.get("fail").toString();
            return prefix+"notice";
        }

        List<ExamQuestionVO> data = (List<ExamQuestionVO>) result.get("data");

        Integer examPaperId = examExamination.getExamPaperId();

        mmap.put( "data", data );
        mmap.put( "examUserExaminationId", eue.getId()==null?-1:eue.getId());
        mmap.put( "userExaminationTime", eue.getId()==null?0:examUserExaminationService.selectById(eue.getId()).getCreateDate().getTime());
        mmap.put( "examExamination", examExamination );
        mmap.put("paperId", examPaperId);
        return prefix+"detail";
    }

    /**
     * 交卷
     * @param examUserExaminationQuestion
     * @param examUserExaminationId
     * @param examinationId
     * @param paperId
     * @param map
     * @return
     */
    @RequestMapping("/examination/finish/{examUserExaminationId}/{examinationId}/{paperId}")
    @ResponseBody
    public AjaxResult finish(@RequestBody List<ExamUserExaminationQuestion> examUserExaminationQuestion,
                             @PathVariable Integer examUserExaminationId, @PathVariable Integer examinationId, @PathVariable Integer paperId,ModelMap map) {
        SysUser sysUser = ShiroUtils.getSysUser();
        if (ObjectUtils.isEmpty(sysUser) || !UserConstants.USER_VIP.equals(sysUser.getUserType())){
            AjaxResult fail = AjaxResult.error("请登录");
            return fail;
        }
        //交卷然后返回考试记录id
        Integer id = examExaminationService.finshExamination(examUserExaminationQuestion,sysUser,examUserExaminationId,examinationId,paperId);
        ExamUserExaminationVO data = examUserExaminationService.selectDetailById( id );
        AjaxResult success = AjaxResult.success( "交卷成功" );
        success.put("id",id);
        return success;
    }
}
