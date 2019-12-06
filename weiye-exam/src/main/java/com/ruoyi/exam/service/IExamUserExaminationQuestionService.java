package com.ruoyi.exam.service;

import com.ruoyi.exam.domain.ExamUserExaminationQuestion;
import java.util.List;
import com.ruoyi.framework.web.base.AbstractBaseService;
/**
 * 我参与过的考试试题 服务层
 * 
 * @author zhujj
 * @date 2019-01-13
 */
public interface IExamUserExaminationQuestionService extends AbstractBaseService<ExamUserExaminationQuestion>
{
	/**
     * 查询我参与过的考试试题分页列表
     *
     * @param examUserExaminationQuestion 我参与过的考试试题信息
     * @return 我参与过的考试试题集合
     */
	public List<ExamUserExaminationQuestion> selectExamUserExaminationQuestionPage(ExamUserExaminationQuestion examUserExaminationQuestion);
    /**
     * 查询我参与过的考试试题列表
     *
     * @param examUserExaminationQuestion 我参与过的考试试题信息
     * @return 我参与过的考试试题集合
     */
    public List<ExamUserExaminationQuestion> selectExamUserExaminationQuestionList(ExamUserExaminationQuestion examUserExaminationQuestion);


    int insertOne(ExamUserExaminationQuestion item);
}
