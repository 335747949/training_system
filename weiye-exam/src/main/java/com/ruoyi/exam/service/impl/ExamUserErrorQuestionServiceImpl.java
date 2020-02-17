package com.ruoyi.exam.service.impl;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.exam.domain.ExamApiUserErrorExaminationVO;
import com.ruoyi.exam.domain.ExamUserErrorQuestion;
import com.ruoyi.exam.domain.ExamUserErrorQuestionVO;
import com.ruoyi.exam.mapper.ExamUserErrorQuestionMapper;
import com.ruoyi.exam.service.IExamUserErrorQuestionService;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
import com.ruoyi.system.domain.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
/**
 * 我的错题 服务层实现
 *
 * @author zhujj
 * @date 2019-01-10
 */
@Service
public class ExamUserErrorQuestionServiceImpl extends AbstractBaseServiceImpl<ExamUserErrorQuestionMapper,ExamUserErrorQuestion> implements IExamUserErrorQuestionService
{
    @Autowired
    private ExamUserErrorQuestionMapper examUserErrorQuestionMapper;
    @Autowired
    private IExamUserErrorQuestionService examUserErrorQuestionService;


    /**
     * 查询我的错题列表
     *
     * @param examUserErrorQuestion 我的错题信息
     * @return 我的错题集合
     */
    @Override
    public List<ExamUserErrorQuestion> selectExamUserErrorQuestionList(ExamUserErrorQuestion examUserErrorQuestion)
    {
        return examUserErrorQuestionMapper.selectExamUserErrorQuestionList(examUserErrorQuestion);
    }

    @Override
    public List<ExamUserErrorQuestionVO> selectExamUserErrorQuestionDetailPage(ExamUserErrorQuestion examUserErrorQuestion) {
        startPage();
        return examUserErrorQuestionMapper.selectExamUserErrorQuestionDetailPage(examUserErrorQuestion);
    }
    @Override
    public List<ExamUserErrorQuestionVO> selectExamUserErrorQuestionDetailList(ExamUserErrorQuestion examUserErrorQuestion) {
        return examUserErrorQuestionMapper.selectExamUserErrorQuestionDetailList(examUserErrorQuestion);
    }

    @Override
    public int insertError(String questionId, String examinationId, SysUser sysUser) {

        ExamUserErrorQuestion examUserErrorQuestion = new ExamUserErrorQuestion();
        examUserErrorQuestion.setExamQuestionId(Integer.parseInt(questionId));
        if (!StringUtils.isEmpty(examinationId)) {
            examUserErrorQuestion.setExaminationId(Integer.parseInt(examinationId));
        }
        examUserErrorQuestion.setVipUserId(sysUser.getUserId().intValue());
        List<ExamUserErrorQuestion> db = examUserErrorQuestionService.selectList(examUserErrorQuestion);
        if(db.size()>0){
            return 0;
        }
        examUserErrorQuestion.setCreateBy(sysUser.getLoginName());
        examUserErrorQuestion.setCreateDate(new Date());
        examUserErrorQuestion.setDelFlag("0");
        examUserErrorQuestion.setUpdateBy(sysUser.getLoginName());
        examUserErrorQuestion.setUpdateDate(new Date());

        return  examUserErrorQuestionMapper.insert( examUserErrorQuestion );
    }

    /**
     * 查询我的错题分页列表
     *
     * @param examUserErrorQuestion 我的错题信息
     * @return 我的错题集合
     */
    @Override
    public List<ExamUserErrorQuestion> selectExamUserErrorQuestionPage(ExamUserErrorQuestion examUserErrorQuestion)
    {
        startPage();
        return examUserErrorQuestionMapper.selectExamUserErrorQuestionList(examUserErrorQuestion);
    }

    /**
     * 查询存在错题的模拟考试的列表
     * @param userId
     * @return
     */
    @Override
    public List<ExamApiUserErrorExaminationVO> selectErrorQuestionExaminationList(@Param("userId") Integer userId) {
        return examUserErrorQuestionMapper.selectErrorQuestionExaminationList(userId);
    }
}
