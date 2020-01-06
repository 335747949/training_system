package com.ruoyi.exam.service;

import com.ruoyi.exam.domain.ExamPaperCategory;
import java.util.List;
import java.util.Map;

import com.ruoyi.framework.web.base.AbstractBaseService;
/**
 * 试卷分类 服务层
 * 
 * @author zhujj
 * @date 2018-12-11
 */
public interface IExamPaperCategoryService extends AbstractBaseService<ExamPaperCategory>
{
	/**
     * 查询试卷分类分页列表
     *
     * @param examPaperCategory 试卷分类信息
     * @return 试卷分类集合
     */
	public List<ExamPaperCategory> selectExamPaperCategoryPage(ExamPaperCategory examPaperCategory);
    /**
     * 查询试卷分类列表
     *
     * @param examPaperCategory 试卷分类信息
     * @return 试卷分类集合
     */
    public List<ExamPaperCategory> selectExamPaperCategoryList(ExamPaperCategory examPaperCategory);


    List<Map<String,Object>> selectDeptTree();

    /**
     *
     * @param name
     * @param parentId
     * @return
     */
    String checkNameUnique(String name, Integer parentId);
}
