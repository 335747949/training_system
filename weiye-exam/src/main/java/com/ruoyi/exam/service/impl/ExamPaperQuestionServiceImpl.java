package com.ruoyi.exam.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.exam.domain.ExamPaperQuestionVO;
import com.ruoyi.framework.web.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.exam.mapper.ExamPaperQuestionMapper;
import com.ruoyi.exam.domain.ExamPaperQuestion;
import com.ruoyi.exam.service.IExamPaperQuestionService;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
/**
 * 试卷题目 服务层实现
 * 
 * @author zhujj
 * @date 2018-12-17
 */
@Service
public class ExamPaperQuestionServiceImpl extends AbstractBaseServiceImpl<ExamPaperQuestionMapper,ExamPaperQuestion> implements IExamPaperQuestionService
{
	@Autowired
	private ExamPaperQuestionMapper examPaperQuestionMapper;


	
	/**
     * 查询试卷题目列表
     * 
     * @param examPaperQuestion 试卷题目信息
     * @return 试卷题目集合
     */
	@Override
	public List<ExamPaperQuestionVO> selectExamPaperQuestionList(ExamPaperQuestion examPaperQuestion)
	{
        return examPaperQuestionMapper.selectExamPaperQuestionList(examPaperQuestion);
	}

    @Override
    public int saveQuestion(String paperId, String[] questionId) {
        ExamPaperQuestion ea = new ExamPaperQuestion();
        ea.setExamPaperId(Integer.parseInt(paperId));
        examPaperQuestionMapper.delete(ea);
        List<ExamPaperQuestion> list = new ArrayList();
        if(questionId!=null && questionId.length>0){
            for (String s : questionId) {
                ExamPaperQuestion examPaperQuestion = new ExamPaperQuestion();
                examPaperQuestion.setExamPaperId(Integer.parseInt(paperId));
                examPaperQuestion.setExamQuestionId(Integer.parseInt(s));
                examPaperQuestion.setDelFlag("0");
                examPaperQuestion.setCreateBy(ShiroUtils.getLoginName());
                examPaperQuestion.setCreateDate(new Date());
                list.add(examPaperQuestion);

            }
            if(list.size()>0){
                examPaperQuestionMapper.insertList(list);
            }
        }

        return 1;
    }

    @Override
    public List<String> selectQuestionIdsForPaperId(Integer id) {
        ExamPaperQuestion examPaperQuestion = new ExamPaperQuestion();
        examPaperQuestion.setExamPaperId(id);
        List<ExamPaperQuestionVO> examPaperQuestions = examPaperQuestionMapper.selectExamPaperQuestionList(examPaperQuestion);
        List<String> ids = new ArrayList<>();
        for (ExamPaperQuestionVO paperQuestion : examPaperQuestions) {
            ids.add(paperQuestion.getExamQuestionId().toString());

        }
        return ids;
    }

    @Override
    public List<ExamPaperQuestionVO> selectQuestionForPaperId(Integer id) {
        ExamPaperQuestion examPaperQuestion = new ExamPaperQuestion();
        examPaperQuestion.setExamPaperId(id);
        List<ExamPaperQuestionVO> examPaperQuestions = examPaperQuestionMapper.selectExamPaperQuestionList(examPaperQuestion);
	    return examPaperQuestions;
    }

    @Override
    public List<ExamPaperQuestion> selectquestionByIds(List<String> ids) {
        return examPaperQuestionMapper.selectquestionByIds(ids);
    }



    /**
     * 查询试卷题目分页列表
     *
     * @param examPaperQuestion 试卷题目信息
     * @return 试卷题目集合
     */
    @Override
    public List<ExamPaperQuestionVO> selectExamPaperQuestionPage(ExamPaperQuestion examPaperQuestion)
    {
        startPage();
        return examPaperQuestionMapper.selectExamPaperQuestionList(examPaperQuestion);
    }

}
