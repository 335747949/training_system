package com.ruoyi.exam.service;

import com.ruoyi.exam.domain.ExamQuestionCategory;
import java.util.List;
import java.util.Map;

import com.ruoyi.framework.web.base.AbstractBaseService;
/**
 * 试题分类 服务层
 * 
 * @author zhujj
 * @date 2018-12-06
 */
public interface IExamQuestionCategoryService extends AbstractBaseService<ExamQuestionCategory>
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
     * 删除试题分类信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteExamQuestionCategoryByIds(String ids);

    List<Map<String,Object>> selectDeptTree();
}
