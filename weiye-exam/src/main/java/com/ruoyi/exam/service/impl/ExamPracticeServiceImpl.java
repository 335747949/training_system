package com.ruoyi.exam.service.impl;

import java.util.List;

import com.ruoyi.exam.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.exam.mapper.ExamPracticeMapper;
import com.ruoyi.exam.service.IExamPracticeService;
import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
/**
 * 练习 服务层实现
 * 
 * @author zhujj
 * @date 2018-12-28
 */
@Service
public class ExamPracticeServiceImpl extends AbstractBaseServiceImpl<ExamPracticeMapper,ExamPractice> implements IExamPracticeService
{
	@Autowired
	private ExamPracticeMapper examPracticeMapper;

	
	/**
     * 查询练习列表
     * 
     * @param examPractice 练习信息
     * @return 练习集合
     */
	@Override
	public List<ExamPractice> selectExamPracticeList(ExamPractice examPractice)
	{
        return examPracticeMapper.selectExamPracticeList(examPractice);
	}

    @Override
    public List<ExamPracticeVO> selectListFromWeb(ExamPractice examPractice) {
        startPage();
        return examPracticeMapper.selectListFromWeb(examPractice);
    }


    /**
     * 查询练习分页列表
     *
     * @param examPractice 练习信息
     * @return 练习集合
     */
    @Override
    public List<ExamPractice> selectExamPracticePage(ExamPractice examPractice)
    {
        startPage();
        return examPracticeMapper.selectExamPracticeList(examPractice);
    }

}
