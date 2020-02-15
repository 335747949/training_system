package com.ruoyi.exam.controller.api;

import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.exam.domain.ExamApiUserErrorExaminationVO;
import com.ruoyi.exam.domain.ExamQuestionVO;
import com.ruoyi.exam.domain.ExamUserErrorQuestion;
import com.ruoyi.exam.domain.ExamUserErrorQuestionVO;
import com.ruoyi.exam.service.*;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.exception.user.AuthExpireException;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by flower on 2019/1/9.
 */
@Api("错题接口")
@RestController
@RequestMapping("/api/v1")
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
     * @param questionList 错题列表，其中需错题id和考试id
     * @return
     * @description 练习时答错题就保存到错题记录中
     * 传入问题id
     */
    @PostMapping("/error/question/add")
    public AjaxResult answer(@RequestBody List<Map<String, String>> questionList) {
        SysUser sysUser = sysUserService.selectUserByLoginName(JwtUtil.getLoginName(), UserConstants.USER_VIP);
        for (Map<String, String> map : questionList) {
            int insert = examUserErrorQuestionService.insertError(map.get("questionId"), map.get("examinationId"), sysUser );
        }
        AjaxResult success = success("API接口插入错题本成功!");
        return success;
    }

    /**
     * 查询存在错题的模拟考试的列表
     *
     * @return
     */
    @GetMapping("/error/question/examination/list")
    public AjaxResult queryPracticeList() {
        SysUser sysUser = sysUserService.selectUserByLoginName( JwtUtil.getLoginName(),UserConstants.USER_VIP );
        // 无效用户
        if (null == sysUser) {
            throw new AuthExpireException();
        }
        List<ExamApiUserErrorExaminationVO> list = examUserErrorQuestionService.selectErrorQuestionExaminationList(sysUser.getUserId().intValue());
        AjaxResult success = success("查询成功");
        success.put("data", list);
        return success;
    }


    /**
     * 查询模拟考试下的错题列表(不分页)
     *
     * @return
     */
    @GetMapping("/error/question/examination/{id}")
    public AjaxResult queryErrorQuestionList(@PathVariable("id") Integer examinationId) {
        ExamUserErrorQuestion examUserErrorQuestion = new ExamUserErrorQuestion();
        SysUser sysUser = sysUserService.selectUserByLoginName( JwtUtil.getLoginName(),UserConstants.USER_VIP );
        // 无效用户
        if (null == sysUser) {
            throw new AuthExpireException();
        }
        examUserErrorQuestion.setVipUserId(sysUser.getUserId().intValue());
        examUserErrorQuestion.setExaminationId(examinationId);
        List<ExamUserErrorQuestionVO> list = examUserErrorQuestionService.selectExamUserErrorQuestionDetailList(examUserErrorQuestion);
        AjaxResult success = success("模拟考试错题查询成功");
        success.put("data", list);
        return success;
    }

    /**
     * 根据question_id查询问题详情
     *
     * @param id
     * @return
     */
    @GetMapping("/question/{id}")
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
    @GetMapping("/error/question/delete/{id}")
    public AjaxResult deleteErrorQuestion(@PathVariable("id") String id) {
        SysUser sysUser = sysUserService.selectUserByLoginName(JwtUtil.getLoginName(), UserConstants.USER_VIP);
        // 无效用户
        if (null == sysUser) {
            throw new AuthExpireException();
        }
        examUserErrorQuestionService.deleteById(id);
        AjaxResult success = success("删除成功");
        return success;
    }

}
