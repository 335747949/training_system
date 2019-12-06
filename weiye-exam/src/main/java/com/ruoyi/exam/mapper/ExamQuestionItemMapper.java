package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.ExamQuestionItem;
import java.util.List;
import com.ruoyi.framework.web.base.MyMapper;

/**
 * 问题选项 数据层
 * 
 * @author zhujj
 * @date 2018-12-06
 */
public interface ExamQuestionItemMapper  extends MyMapper<ExamQuestionItem>
{
	/**
     * 查询问题选项信息
     *
     * @param id 问题选项ID
     * @return 问题选项信息
     */
	public ExamQuestionItem selectExamQuestionItemById(String id);

	/**
     * 查询问题选项列表
     * 
     * @param examQuestionItem 问题选项信息
     * @return 问题选项集合
     */
	public List<ExamQuestionItem> selectExamQuestionItemList(ExamQuestionItem examQuestionItem);
	
	/**
     * 新增问题选项
     *
     * @param examQuestionItem 问题选项信息
     * @return 结果
     */
	public int insertExamQuestionItem(ExamQuestionItem examQuestionItem);

	/**
     * 修改问题选项
     *
     * @param examQuestionItem 问题选项信息
     * @return 结果
     */
	public int updateExamQuestionItem(ExamQuestionItem examQuestionItem);

	/**
     * 删除问题选项
     *
     * @param id 问题选项ID
     * @return 结果
     */
	public int deleteExamQuestionItemById(String id);

	/**
     * 批量删除问题选项
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteExamQuestionItemByIds(String[] ids);

    void deleteByQuestionIds(String[] ids);
}