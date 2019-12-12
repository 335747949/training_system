package com.ruoyi.exam.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.exam.domain.ExamQuestionCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.exam.mapper.ExamPaperCategoryMapper;
import com.ruoyi.exam.domain.ExamPaperCategory;
import com.ruoyi.exam.service.IExamPaperCategoryService;
import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
/**
 * 试卷分类 服务层实现
 * 
 * @author zhujj
 * @date 2018-12-11
 */
@Service
public class ExamPaperCategoryServiceImpl extends AbstractBaseServiceImpl<ExamPaperCategoryMapper,ExamPaperCategory> implements IExamPaperCategoryService
{
	@Autowired
	private ExamPaperCategoryMapper examPaperCategoryMapper;

	
	/**
     * 查询试卷分类列表
     * 
     * @param examPaperCategory 试卷分类信息
     * @return 试卷分类集合
     */
	@Override
	public List<ExamPaperCategory> selectExamPaperCategoryList(ExamPaperCategory examPaperCategory)
	{
        return examPaperCategoryMapper.selectExamPaperCategoryList(examPaperCategory);
	}

    @Override
    public List<Map<String, Object>> selectDeptTree() {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<ExamPaperCategory> deptList = selectExamPaperCategoryList(new ExamPaperCategory());
        trees = getTrees(deptList, false, null);
        return trees;
    }

    private List<Map<String, Object>> getTrees(List<ExamPaperCategory> deptList, boolean isCheck, List<String> roleDeptList) {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        for (ExamPaperCategory dept : deptList) {

            Map<String, Object> deptMap = new HashMap<String, Object>();
            deptMap.put("id", dept.getId());
            deptMap.put("pId", dept.getParentId());
            deptMap.put("name", dept.getName());
            deptMap.put("title", dept.getName());
            if (isCheck) {
                deptMap.put("checked", roleDeptList.contains(dept.getId() + dept.getName()));
            } else {
                deptMap.put("checked", false);
            }
            trees.add(deptMap);
        }
        return trees;
    }

    /**
     * 查询试卷分类分页列表
     *
     * @param examPaperCategory 试卷分类信息
     * @return 试卷分类集合
     */
    @Override
    public List<ExamPaperCategory> selectExamPaperCategoryPage(ExamPaperCategory examPaperCategory)
    {
        startPage();
        return examPaperCategoryMapper.selectExamPaperCategoryList(examPaperCategory);
    }

}
