package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.ExamPaperTypeNumber;
import java.util.List;
import com.ruoyi.framework.web.base.MyMapper;

/**
 * 随机试卷题型数量 数据层
 * 
 * @author zhujj
 * @date 2019-01-16
 */
public interface ExamPaperTypeNumberMapper  extends MyMapper<ExamPaperTypeNumber>
{

	/**
     * 查询随机试卷题型数量列表
     * 
     * @param examPaperTypeNumber 随机试卷题型数量信息
     * @return 随机试卷题型数量集合
     */
	public List<ExamPaperTypeNumber> selectExamPaperTypeNumberList(ExamPaperTypeNumber examPaperTypeNumber);
	
}