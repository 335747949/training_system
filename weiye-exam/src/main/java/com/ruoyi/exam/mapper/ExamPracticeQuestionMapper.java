package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.ExamPracticeQuestion;
import java.util.List;

import com.ruoyi.exam.domain.ExamPracticeQuestionVO;
import com.ruoyi.framework.web.base.MyMapper;

/**
 * 试卷题目 数据层
 * 
 * @author zhujj
 * @date 2019-01-01
 */
public interface ExamPracticeQuestionMapper  extends MyMapper<ExamPracticeQuestion>
{

	/**
     * 查询试卷题目列表
     * 
     * @param examPracticeQuestion 试卷题目信息
     * @return 试卷题目集合
     */
	public List<ExamPracticeQuestionVO> selectExamPracticeQuestionList(ExamPracticeQuestion examPracticeQuestion);
	
}