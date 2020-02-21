package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.ExamApiUserErrorExaminationVO;
import com.ruoyi.exam.domain.ExamUserErrorQuestion;
import com.ruoyi.exam.domain.ExamUserErrorQuestionVO;
import com.ruoyi.framework.web.base.MyMapper;

import java.util.List;

/**
 * 我的错题 数据层
 *
 * @author zhujj
 * @date 2019-01-10
 */
public interface ExamUserErrorQuestionMapper  extends MyMapper<ExamUserErrorQuestion>
{

	/**
	 * 查询我的错题列表
	 *
	 * @param examUserErrorQuestion 我的错题信息
	 * @return 我的错题集合
	 */
	public List<ExamUserErrorQuestion> selectExamUserErrorQuestionList(ExamUserErrorQuestion examUserErrorQuestion);

	/**
	 * 查询错题列表
	 * @param examUserErrorQuestion
	 * @return
	 */
	List<ExamUserErrorQuestionVO> selectExamUserErrorQuestionDetailPage(ExamUserErrorQuestion examUserErrorQuestion);

	/**
	 * 查询错题列表（包含选项）
	 * @param examUserErrorQuestion
	 * @return
	 */
	List<ExamUserErrorQuestionVO> selectExamUserErrorQuestionDetailList(ExamUserErrorQuestion examUserErrorQuestion);

	/**
	 * 查询存在错题的模拟考试的列表
	 * @param userId
	 * @return
	 */
	List<ExamApiUserErrorExaminationVO> selectErrorQuestionExaminationList(Integer userId);

}