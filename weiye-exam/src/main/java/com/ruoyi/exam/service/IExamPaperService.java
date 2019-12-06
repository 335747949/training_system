package com.ruoyi.exam.service;

import com.ruoyi.exam.domain.ExamPaper;
import java.util.List;

import com.ruoyi.exam.domain.ExamQuestionVO;
import com.ruoyi.framework.web.base.AbstractBaseService;
/**
 * 试卷 服务层
 * 
 * @author zhujj
 * @date 2018-12-16
 */
public interface IExamPaperService extends AbstractBaseService<ExamPaper>
{
	/**
     * 查询试卷分页列表
     *
     * @param examPaper 试卷信息
     * @return 试卷集合
     */
	public List<ExamPaper> selectExamPaperPage(ExamPaper examPaper);
    /**
     * 查询试卷列表
     *
     * @param examPaper 试卷信息
     * @return 试卷集合
     */
    public List<ExamPaper> selectExamPaperList(ExamPaper examPaper);


    List<ExamPaper> selectListByCategory(ExamPaper examPaper);

    List<ExamQuestionVO> selectQuestionAndItemByPaperId(Integer examPaperId);
}
