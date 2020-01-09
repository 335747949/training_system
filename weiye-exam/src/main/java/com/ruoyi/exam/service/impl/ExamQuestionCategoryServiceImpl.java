package com.ruoyi.exam.service.impl;

import com.ruoyi.common.constant.ExamConstants;
import com.ruoyi.common.support.Convert;
import com.ruoyi.exam.domain.ExamQuestionCategory;
import com.ruoyi.exam.mapper.ExamQuestionCategoryMapper;
import com.ruoyi.exam.service.IExamQuestionCategoryService;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 试题分类 服务层实现
 *
 * @author zhujj
 * @date 2018-12-06
 */
@Service
public class ExamQuestionCategoryServiceImpl extends AbstractBaseServiceImpl<ExamQuestionCategoryMapper, ExamQuestionCategory> implements IExamQuestionCategoryService {
    @Autowired
    private ExamQuestionCategoryMapper examQuestionCategoryMapper;

    /**
     * 查询试题分类信息
     *
     * @param id 试题分类ID
     * @return 试题分类信息
     */
    @Override
    public ExamQuestionCategory selectExamQuestionCategoryById(String id) {
        return examQuestionCategoryMapper.selectExamQuestionCategoryById(id);
    }

    /**
     * 查询试题分类列表
     *
     * @param examQuestionCategory 试题分类信息
     * @return 试题分类集合
     */
    @Override
    public List<ExamQuestionCategory> selectExamQuestionCategoryList(ExamQuestionCategory examQuestionCategory) {
        startPage();
        return examQuestionCategoryMapper.selectExamQuestionCategoryList(examQuestionCategory);
    }

    /**
     * 新增试题分类
     *
     * @param examQuestionCategory 试题分类信息
     * @return 结果
     */
    @Override
    public int insertExamQuestionCategory(ExamQuestionCategory examQuestionCategory) {
        return examQuestionCategoryMapper.insertExamQuestionCategory(examQuestionCategory);
    }

    /**
     * 修改试题分类
     *
     * @param examQuestionCategory 试题分类信息
     * @return 结果
     */
    @Override
    public int updateExamQuestionCategory(ExamQuestionCategory examQuestionCategory) {
        return examQuestionCategoryMapper.updateExamQuestionCategory(examQuestionCategory);
    }

    /**
     * 删除试题分类对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteExamQuestionCategoryByIds(String ids) {
        return examQuestionCategoryMapper.deleteExamQuestionCategoryByIds(Convert.toStrArray(ids));
    }

    @Override
    public List<Map<String, Object>> selectDeptTree() {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<ExamQuestionCategory> deptList = selectExamQuestionCategoryList(new ExamQuestionCategory());
        trees = getTrees(deptList, false, null);
        return trees;
    }

    private List<Map<String, Object>> getTrees(List<ExamQuestionCategory> deptList, boolean isCheck, List<String> roleDeptList) {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        for (ExamQuestionCategory dept : deptList) {

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
     *
     * @param name
     * @param parentId
     * @return
     */
    @Override
    public String checkNameUnique(String name, Long parentId) {
        List<ExamQuestionCategory> examQuestionCategoryList = examQuestionCategoryMapper.selectByNameAndParentId(name, parentId);
        if (CollectionUtils.isEmpty(examQuestionCategoryList)) {
            return ExamConstants.EXAM_CATEGORY_NAME_UNIQUE;
        }
        return ExamConstants.EXAM_CATEGORY_NAME_NOT_UNIQUE;
    }
}
