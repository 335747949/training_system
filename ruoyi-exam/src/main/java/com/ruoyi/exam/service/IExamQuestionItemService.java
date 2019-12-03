package com.ruoyi.exam.service;

import com.ruoyi.exam.domain.ExamQuestionItem;
import java.util.List;
import com.ruoyi.framework.web.base.AbstractBaseService;
/**
 * 问题选项 服务层
 * 
 * @author zhujj
 * @date 2018-12-07
 */
public interface IExamQuestionItemService extends AbstractBaseService<ExamQuestionItem>
{
	/**
     * 查询问题选项分页列表
     *
     * @param examQuestionItem 问题选项信息
     * @return 问题选项集合
     */
	public List<ExamQuestionItem> selectExamQuestionItemPage(ExamQuestionItem examQuestionItem);
    /**
     * 查询问题选项列表
     *
     * @param examQuestionItem 问题选项信息
     * @return 问题选项集合
     */
    public List<ExamQuestionItem> selectExamQuestionItemList(ExamQuestionItem examQuestionItem);


    void deleteByQuestionIds(String ids);
}
