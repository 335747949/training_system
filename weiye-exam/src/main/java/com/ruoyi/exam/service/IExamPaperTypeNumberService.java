package com.ruoyi.exam.service;

import com.ruoyi.exam.domain.ExamPaperTypeNumber;
import java.util.List;
import com.ruoyi.framework.web.base.AbstractBaseService;
/**
 * 随机试卷题型数量 服务层
 * 
 * @author zhujj
 * @date 2019-01-16
 */
public interface IExamPaperTypeNumberService extends AbstractBaseService<ExamPaperTypeNumber>
{
	/**
     * 查询随机试卷题型数量分页列表
     *
     * @param examPaperTypeNumber 随机试卷题型数量信息
     * @return 随机试卷题型数量集合
     */
	public List<ExamPaperTypeNumber> selectExamPaperTypeNumberPage(ExamPaperTypeNumber examPaperTypeNumber);
    /**
     * 查询随机试卷题型数量列表
     *
     * @param examPaperTypeNumber 随机试卷题型数量信息
     * @return 随机试卷题型数量集合
     */
    public List<ExamPaperTypeNumber> selectExamPaperTypeNumberList(ExamPaperTypeNumber examPaperTypeNumber);

	
}
