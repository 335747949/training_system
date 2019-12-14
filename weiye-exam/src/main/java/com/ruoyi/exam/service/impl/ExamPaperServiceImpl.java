package com.ruoyi.exam.service.impl;

import java.util.List;

import com.ruoyi.exam.domain.ExamQuestionVO;
import com.ruoyi.exam.mapper.ExamPaperQuestionMapper;
import com.ruoyi.exam.mapper.ExamQuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.exam.mapper.ExamPaperMapper;
import com.ruoyi.exam.domain.ExamPaper;
import com.ruoyi.exam.service.IExamPaperService;
import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
/**
 * 试卷 服务层实现
 * 
 * @author zhujj
 * @date 2018-12-16
 */
@Service
public class ExamPaperServiceImpl extends AbstractBaseServiceImpl<ExamPaperMapper,ExamPaper> implements IExamPaperService
{
	@Autowired
	private ExamPaperMapper examPaperMapper;

	@Autowired
    private ExamQuestionMapper examQuestionMapper;

	
	/**
     * 查询试卷列表
     * 
     * @param examPaper 试卷信息
     * @return 试卷集合
     */
	@Override
	public List<ExamPaper> selectExamPaperList(ExamPaper examPaper)
	{
        return examPaperMapper.selectExamPaperList(examPaper);
	}

    @Override
    public List<ExamPaper> selectListByCategory(ExamPaper examPaper) {
        startPage();
        return examPaperMapper.selectListByCategory(examPaper);
    }

    @Override
    public List<ExamQuestionVO> selectQuestionAndItemByPaperId(Integer examPaperId) {
        List<ExamQuestionVO> list = examQuestionMapper.selectQuestionListByPaperId(examPaperId);
	    return list;
    }

    /**
     * 查询试卷分页列表
     *
     * @param examPaper 试卷信息
     * @return 试卷集合
     */
    @Override
    public List<ExamPaper> selectExamPaperPage(ExamPaper examPaper)
    {
        startPage();
        return examPaperMapper.selectExamPaperList(examPaper);
    }

}
