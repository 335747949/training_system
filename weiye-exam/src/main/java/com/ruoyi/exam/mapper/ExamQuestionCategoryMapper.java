package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.ExamQuestionCategory;
import java.util.List;
import com.ruoyi.framework.web.base.MyMapper;

/**
 * 试题分类 数据层
 * 
 * @author zhujj
 * @date 2018-12-06
 */
public interface ExamQuestionCategoryMapper  extends MyMapper<ExamQuestionCategory>
{
	/**
     * 查询试题分类信息
     *
     * @param id 试题分类ID
     * @return 试题分类信息
     */
	public ExamQuestionCategory selectExamQuestionCategoryById(String id);

	/**
     * 查询试题分类列表
     * 
     * @param examQuestionCategory 试题分类信息
     * @return 试题分类集合
     */
	public List<ExamQuestionCategory> selectExamQuestionCategoryList(ExamQuestionCategory examQuestionCategory);
	
	/**
     * 新增试题分类
     *
     * @param examQuestionCategory 试题分类信息
     * @return 结果
     */
	public int insertExamQuestionCategory(ExamQuestionCategory examQuestionCategory);

	/**
     * 修改试题分类
     *
     * @param examQuestionCategory 试题分类信息
     * @return 结果
     */
	public int updateExamQuestionCategory(ExamQuestionCategory examQuestionCategory);

	/**
     * 删除试题分类
     *
     * @param id 试题分类ID
     * @return 结果
     */
	public int deleteExamQuestionCategoryById(String id);

	/**
     * 批量删除试题分类
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteExamQuestionCategoryByIds(String[] ids);

}