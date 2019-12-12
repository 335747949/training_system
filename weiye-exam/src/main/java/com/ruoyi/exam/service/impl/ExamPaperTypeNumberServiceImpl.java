package com.ruoyi.exam.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.exam.mapper.ExamPaperTypeNumberMapper;
import com.ruoyi.exam.domain.ExamPaperTypeNumber;
import com.ruoyi.exam.service.IExamPaperTypeNumberService;
import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
/**
 * 随机试卷题型数量 服务层实现
 * 
 * @author zhujj
 * @date 2019-01-16
 */
@Service
public class ExamPaperTypeNumberServiceImpl extends AbstractBaseServiceImpl<ExamPaperTypeNumberMapper,ExamPaperTypeNumber> implements IExamPaperTypeNumberService
{
	@Autowired
	private ExamPaperTypeNumberMapper examPaperTypeNumberMapper;

	
	/**
     * 查询随机试卷题型数量列表
     * 
     * @param examPaperTypeNumber 随机试卷题型数量信息
     * @return 随机试卷题型数量集合
     */
	@Override
	public List<ExamPaperTypeNumber> selectExamPaperTypeNumberList(ExamPaperTypeNumber examPaperTypeNumber)
	{
        return examPaperTypeNumberMapper.selectExamPaperTypeNumberList(examPaperTypeNumber);
	}
    /**
     * 查询随机试卷题型数量分页列表
     *
     * @param examPaperTypeNumber 随机试卷题型数量信息
     * @return 随机试卷题型数量集合
     */
    @Override
    public List<ExamPaperTypeNumber> selectExamPaperTypeNumberPage(ExamPaperTypeNumber examPaperTypeNumber)
    {
        startPage();
        return examPaperTypeNumberMapper.selectExamPaperTypeNumberList(examPaperTypeNumber);
    }

}
