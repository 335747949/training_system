package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.ExamUserExaminationQuestion;
import java.util.List;
import com.ruoyi.framework.web.base.MyMapper;

/**
 * 我参与过的考试试题 数据层
 * 
 * @author zhujj
 * @date 2019-01-13
 */
public interface ExamUserExaminationQuestionMapper  extends MyMapper<ExamUserExaminationQuestion>
{

	/**
     * 查询我参与过的考试试题列表
     * 
     * @param examUserExaminationQuestion 我参与过的考试试题信息
     * @return 我参与过的考试试题集合
     */
	public List<ExamUserExaminationQuestion> selectExamUserExaminationQuestionList(ExamUserExaminationQuestion examUserExaminationQuestion);
	
}