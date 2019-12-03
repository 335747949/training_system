package com.ruoyi.exam.service.impl;

import java.util.List;

import com.ruoyi.exam.domain.ExamPracticeQuestionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.exam.mapper.ExamPracticeQuestionMapper;
import com.ruoyi.exam.domain.ExamPracticeQuestion;
import com.ruoyi.exam.service.IExamPracticeQuestionService;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
/**
 * 试卷题目 服务层实现
 * 
 * @author zhujj
 * @date 2019-01-01
 */
@Service
public class ExamPracticeQuestionServiceImpl extends AbstractBaseServiceImpl<ExamPracticeQuestionMapper,ExamPracticeQuestion> implements IExamPracticeQuestionService
{
	@Autowired
	private ExamPracticeQuestionMapper examPracticeQuestionMapper;

	
	/**
     * 查询试卷题目列表
     * 
     * @param examPracticeQuestion 试卷题目信息
     * @return 试卷题目集合
     */
	@Override
	public List<ExamPracticeQuestionVO> selectExamPracticeQuestionList(ExamPracticeQuestion examPracticeQuestion)
	{
        return examPracticeQuestionMapper.selectExamPracticeQuestionList(examPracticeQuestion);
	}

    @Override
    public List<ExamPracticeQuestionVO> selectQuestionForPracticeId(Integer id) {
        ExamPracticeQuestion examPracticeQuestion = new ExamPracticeQuestion();
        examPracticeQuestion.setExamPracticeId(id);
        List<ExamPracticeQuestionVO> result = examPracticeQuestionMapper.selectExamPracticeQuestionList(examPracticeQuestion);
        return result;
    }

    /**
     * 查询试卷题目分页列表
     *
     * @param examPracticeQuestion 试卷题目信息
     * @return 试卷题目集合
     */
    @Override
    public List<ExamPracticeQuestionVO> selectExamPracticeQuestionPage(ExamPracticeQuestion examPracticeQuestion)
    {
        startPage();
        return examPracticeQuestionMapper.selectExamPracticeQuestionList(examPracticeQuestion);
    }

}
