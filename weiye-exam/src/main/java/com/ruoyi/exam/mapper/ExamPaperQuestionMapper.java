package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.ExamPaperQuestion;
import java.util.List;

import com.ruoyi.exam.domain.ExamPaperQuestionVO;
import com.ruoyi.framework.web.base.MyMapper;

/**
 * 试卷题目 数据层
 * 
 * @author zhujj
 * @date 2018-12-17
 */
public interface ExamPaperQuestionMapper  extends MyMapper<ExamPaperQuestion>
{

	/**
     * 查询试卷题目列表
     * 
     * @param examPaperQuestion 试卷题目信息
     * @return 试卷题目集合
     */
	public List<ExamPaperQuestionVO> selectExamPaperQuestionList(ExamPaperQuestion examPaperQuestion);

	List<ExamPaperQuestion> selectquestionByIds(List<String> ids);
}