package com.ruoyi.exam.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.exam.mapper.ExamQuestionItemMapper;
import com.ruoyi.exam.domain.ExamQuestionItem;
import com.ruoyi.exam.service.IExamQuestionItemService;
import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
/**
 * 问题选项 服务层实现
 * 
 * @author zhujj
 * @date 2018-12-07
 */
@Service
public class ExamQuestionItemServiceImpl extends AbstractBaseServiceImpl<ExamQuestionItemMapper,ExamQuestionItem> implements IExamQuestionItemService
{
	@Autowired
	private ExamQuestionItemMapper examQuestionItemMapper;

	
	/**
     * 查询问题选项列表
     * 
     * @param examQuestionItem 问题选项信息
     * @return 问题选项集合
     */
	@Override
	public List<ExamQuestionItem> selectExamQuestionItemList(ExamQuestionItem examQuestionItem)
	{
        return examQuestionItemMapper.selectExamQuestionItemList(examQuestionItem);
	}

    @Override
    public void deleteByQuestionIds(String ids) {
        String[] split = ids.split(",");
        examQuestionItemMapper.deleteByQuestionIds(split);
    }

    /**
     * 查询问题选项分页列表
     *
     * @param examQuestionItem 问题选项信息
     * @return 问题选项集合
     */
    @Override
    public List<ExamQuestionItem> selectExamQuestionItemPage(ExamQuestionItem examQuestionItem)
    {
        startPage();
        return examQuestionItemMapper.selectExamQuestionItemList(examQuestionItem);
    }

}
