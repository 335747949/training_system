package com.ruoyi.exam.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.exam.mapper.ExamUserExaminationQuestionMapper;
import com.ruoyi.exam.domain.ExamUserExaminationQuestion;
import com.ruoyi.exam.service.IExamUserExaminationQuestionService;
import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
/**
 * 我参与过的考试试题 服务层实现
 * 
 * @author zhujj
 * @date 2019-01-13
 */
@Service
public class ExamUserExaminationQuestionServiceImpl extends AbstractBaseServiceImpl<ExamUserExaminationQuestionMapper,ExamUserExaminationQuestion> implements IExamUserExaminationQuestionService
{
	@Autowired
	private ExamUserExaminationQuestionMapper examUserExaminationQuestionMapper;

	
	/**
     * 查询我参与过的考试试题列表
     * 
     * @param examUserExaminationQuestion 我参与过的考试试题信息
     * @return 我参与过的考试试题集合
     */
	@Override
	public List<ExamUserExaminationQuestion> selectExamUserExaminationQuestionList(ExamUserExaminationQuestion examUserExaminationQuestion)
	{
        return examUserExaminationQuestionMapper.selectExamUserExaminationQuestionList(examUserExaminationQuestion);
	}

    @Override
    public int insertOne(ExamUserExaminationQuestion item) {
        return examUserExaminationQuestionMapper.insert(item);
    }

    /**
     * 查询我参与过的考试试题分页列表
     *
     * @param examUserExaminationQuestion 我参与过的考试试题信息
     * @return 我参与过的考试试题集合
     */
    @Override
    public List<ExamUserExaminationQuestion> selectExamUserExaminationQuestionPage(ExamUserExaminationQuestion examUserExaminationQuestion)
    {
        startPage();
        return examUserExaminationQuestionMapper.selectExamUserExaminationQuestionList(examUserExaminationQuestion);
    }

}
