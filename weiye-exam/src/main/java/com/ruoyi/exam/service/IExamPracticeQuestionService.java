package com.ruoyi.exam.service;

import com.ruoyi.exam.domain.ExamPracticeQuestion;
import java.util.List;

import com.ruoyi.exam.domain.ExamPracticeQuestionVO;
import com.ruoyi.framework.web.base.AbstractBaseService;
/**
 * 试卷题目 服务层
 * 
 * @author zhujj
 * @date 2019-01-01
 */
public interface IExamPracticeQuestionService extends AbstractBaseService<ExamPracticeQuestion>
{
	/**
     * 查询试卷题目分页列表
     *
     * @param examPracticeQuestion 试卷题目信息
     * @return 试卷题目集合
     */
	public List<ExamPracticeQuestionVO> selectExamPracticeQuestionPage(ExamPracticeQuestion examPracticeQuestion);
    /**
     * 查询试卷题目列表
     *
     * @param examPracticeQuestion 试卷题目信息
     * @return 试卷题目集合
     */
    public List<ExamPracticeQuestionVO> selectExamPracticeQuestionList(ExamPracticeQuestion examPracticeQuestion);


    List<ExamPracticeQuestionVO> selectQuestionForPracticeId(Integer id);
}
