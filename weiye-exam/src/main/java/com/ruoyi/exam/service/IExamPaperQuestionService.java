package com.ruoyi.exam.service;

import com.ruoyi.exam.domain.ExamPaperQuestion;
import java.util.List;

import com.ruoyi.exam.domain.ExamPaperQuestionVO;
import com.ruoyi.framework.web.base.AbstractBaseService;
/**
 * 试卷题目 服务层
 * 
 * @author zhujj
 * @date 2018-12-17
 */
public interface IExamPaperQuestionService extends AbstractBaseService<ExamPaperQuestion>
{
	/**
     * 查询试卷题目分页列表
     *
     * @param examPaperQuestion 试卷题目信息
     * @return 试卷题目集合
     */
	public List<ExamPaperQuestionVO> selectExamPaperQuestionPage(ExamPaperQuestion examPaperQuestion);
    /**
     * 查询试卷题目列表
     *
     * @param examPaperQuestion 试卷题目信息
     * @return 试卷题目集合
     */
    public List<ExamPaperQuestionVO> selectExamPaperQuestionList(ExamPaperQuestion examPaperQuestion);


    int saveQuestion(String paperId, String[] questionId);

    List<String> selectQuestionIdsForPaperId(Integer id);

    List<ExamPaperQuestionVO> selectQuestionForPaperId(Integer id);

    List<ExamPaperQuestion> selectquestionByIds(List<String> ids);

}
