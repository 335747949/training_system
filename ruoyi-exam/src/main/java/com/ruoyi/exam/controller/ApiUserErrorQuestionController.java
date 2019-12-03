package com.ruoyi.exam.controller;

import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.exam.domain.*;
import com.ruoyi.exam.service.*;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by flower on 2019/1/9.
 */
@Api("错题接口")
@RestController
@RequestMapping("/api")
public class ApiUserErrorQuestionController extends BaseController {

    @Autowired
    private IExamPracticeService examPracticeService;

    @Autowired
    private IExamPracticeQuestionService examPracticeQuestionService;

    @Autowired
    private IExamQuestionService examQuestionService;

    @Autowired
    private IExamUserErrorQuestionService examUserErrorQuestionService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IExamUserCollectionQuestionService examUserCollectionQuestionService;

    /**
     * 保存错题记录
     *
     * @param questionIds
     * @return
     * @description 练习时答错题就保存到错题记录中
     * 传入问题id
     */
    @PostMapping("/v1/practice/answer")
    public AjaxResult answer(@RequestBody List<String> questionIds) {
        for (String questionId : questionIds) {
            SysUser sysUser = sysUserService.selectUserByLoginName(JwtUtil.getLoginName());

            int insert = examUserErrorQuestionService.insertError(questionId,sysUser );
        }
        AjaxResult success = success("插入错题本成功");
        return success;
    }

    /**
     * 查询我的错题列表
     *
     * @return
     */
    @GetMapping("/v1/practice/error")
    public AjaxResult queryError() {
        ExamUserErrorQuestion examUserErrorQuestion = new ExamUserErrorQuestion();
        SysUser sysUser = sysUserService.selectUserByLoginName( JwtUtil.getLoginName() );
        examUserErrorQuestion.setVipUserId(sysUser.getUserId().intValue());
        List<ExamUserErrorQuestionVO> list = examUserErrorQuestionService.selectExamUserErrorQuestionDetailPage(examUserErrorQuestion);
        AjaxResult success = success("查询成功");
        success.put("data", list);
        return success;
    }
    /**
     * 查询我的错题列表(不分页)
     *
     * @return
     */
    @GetMapping("/v1/question/error/list")
    public AjaxResult queryErrorList() {
        ExamUserErrorQuestion examUserErrorQuestion = new ExamUserErrorQuestion();
        SysUser sysUser = sysUserService.selectUserByLoginName( JwtUtil.getLoginName() );
        examUserErrorQuestion.setVipUserId(sysUser.getUserId().intValue());
        List<ExamUserErrorQuestionVO> list = examUserErrorQuestionService.selectExamUserErrorQuestionDetailList(examUserErrorQuestion);
        AjaxResult success = success("查询成功");
        success.put("data", list);
        return success;
    }
    /**
     * 查询问题详情
     *
     * @param id
     * @return
     */
    @GetMapping("/v1/question/{id}")
    public AjaxResult queryQuestion(@PathVariable("id") String id) {
        ExamQuestionVO result = examQuestionService.selectQuestionDetail(id);
        AjaxResult success = success("查询成功");
        success.put("data", result);
        return success;
    }
    /**
     * 删除错题记录
     *
     * @param id
     * @return
     */
    @GetMapping("/v1/question/error/delete/{id}")
    public AjaxResult deleteErrorQuestion(@PathVariable("id") String id) {
        examUserErrorQuestionService.deleteById(id);
        AjaxResult success = success("删除成功");
        return success;
    }

}
