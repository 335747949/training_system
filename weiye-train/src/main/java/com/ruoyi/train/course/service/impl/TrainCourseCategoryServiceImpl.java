package com.ruoyi.train.course.service.impl;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.train.course.domain.TrainCourseCategory;
import com.ruoyi.train.course.mapper.TrainCourseCategoryMapper;
import com.ruoyi.train.course.service.ITrainCourseCategoryService;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课程分类管理 服务实现
 *
 * @author ruoyi
 */
@Service
public class TrainCourseCategoryServiceImpl extends AbstractBaseServiceImpl<TrainCourseCategoryMapper, TrainCourseCategory> implements ITrainCourseCategoryService {

    @Autowired
    private TrainCourseCategoryMapper trainCourseCategoryMapper;

    /**
     * 查询课程分类管理数据
     *
     * @return 课程分类信息集合
     */
    @Override
    @DataScope(tableAlias = "d")
    public List<TrainCourseCategory> selectCategoryList(TrainCourseCategory category) {
        return trainCourseCategoryMapper.selectCategoryList( category );
    }

    @Override
    @DataScope(tableAlias = "d")
    public List<TrainCourseCategory> selectAllCategoryList(TrainCourseCategory category) {
        return trainCourseCategoryMapper.selectAllCategoryList( category );
    }

    /**
     * 查询课程分类管理树
     *
     * @return 所有课程分类信息
     */
    @Override
    public List<Map<String, Object>> selectCategoryTree() {
        List<TrainCourseCategory> deptList = selectCategoryList( new TrainCourseCategory() );
        List<Map<String, Object>> trees = getTrees( deptList, false, null );
        return trees;
    }


    /**
     * 对象转课程分类树
     *
     * @param deptList     课程分类列表
     * @param isCheck      是否需要选中
     * @param roleCategoryList 角色已存在菜单列表
     * @return
     */
    public List<Map<String, Object>> getTrees(List<TrainCourseCategory> deptList, boolean isCheck, List<String> roleCategoryList) {

        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        for (TrainCourseCategory dept : deptList) {
            if (UserConstants.DEPT_NORMAL.equals( dept.getDelFlag() )) {
                Map<String, Object> deptMap = new HashMap<String, Object>();
                deptMap.put( "id", dept.getId() );
                deptMap.put( "pId", dept.getParentId() );
                deptMap.put( "name", dept.getName() );
                deptMap.put( "title", dept.getName() );
                if (isCheck) {
                    deptMap.put( "checked", roleCategoryList.contains( dept.getId() + dept.getName() ) );
                } else {
                    deptMap.put( "checked", false );
                }
                trees.add( deptMap );
            }
        }
        return trees;
    }

    /**
     * 查询课程分类人数
     *
     * @param parentId 课程分类ID
     * @return 结果
     */
    @Override
    public int selectCategoryCount(Long parentId) {
        TrainCourseCategory dept = new TrainCourseCategory();
        dept.setParentId( parentId );
        return trainCourseCategoryMapper.selectCategoryCount( dept );
    }

    /**
     * 查询课程分类是否存在用户
     *
     * @param deptId 课程分类ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkCategoryExistCourse(Long deptId) {
        int result = trainCourseCategoryMapper.checkCategoryExistCourse( deptId );
        return result > 0 ? true : false;
    }

    /**
     * 删除课程分类管理信息
     *
     * @param deptId 课程分类ID
     * @return 结果
     */
    @Override
    public int deleteCategoryById(Long deptId) {
        return trainCourseCategoryMapper.deleteCategoryById( deptId );
    }

    /**
     * 新增保存课程分类信息
     *
     * @param dept 课程分类信息
     * @return 结果
     */
    @Override
    public int insertCategory(TrainCourseCategory dept) {
        TrainCourseCategory info = trainCourseCategoryMapper.selectCategoryById( dept.getParentId() );
        dept.setParentIds( info.getParentIds() + "," + dept.getParentId() );
        return trainCourseCategoryMapper.insertCategory( dept );
    }

    /**
     * 修改保存课程分类信息
     *
     * @param dept 课程分类信息
     * @return 结果
     */
    @Override
    public int updateCategory(TrainCourseCategory dept) {
        TrainCourseCategory info = trainCourseCategoryMapper.selectCategoryById( dept.getParentId() );
        if (StringUtils.isNotNull( info )) {
            String ancestors = info.getParentIds() + "," + dept.getParentId();
            dept.setParentIds( ancestors );
            updateCategoryChildren( dept.getId(), ancestors );
        }
        return trainCourseCategoryMapper.updateCategory( dept );
    }

    /**
     * 修改子元素关系
     *
     * @param deptId    课程分类ID
     * @param ancestors 元素列表
     */
    public void updateCategoryChildren(Long deptId, String ancestors) {
        TrainCourseCategory dept = new TrainCourseCategory();
        dept.setParentId( deptId );
        List<TrainCourseCategory> childrens = trainCourseCategoryMapper.selectCategoryList( dept );
        for (TrainCourseCategory children : childrens) {
            children.setParentIds( ancestors + "," + dept.getParentId() );
        }
        if (childrens.size() > 0) {
            trainCourseCategoryMapper.updateCategoryChildren( childrens );
        }
    }

    /**
     * 根据课程分类ID查询信息
     *
     * @param deptId 课程分类ID
     * @return 课程分类信息
     */
    @Override
    public TrainCourseCategory selectCategoryById(Long deptId) {
        return trainCourseCategoryMapper.selectCategoryById( deptId );
    }

    /**
     * 校验课程分类名称是否唯一
     *
     * @param dept 课程分类信息
     * @return 结果
     */
    @Override
    public String checkCategoryNameUnique(TrainCourseCategory dept) {
        Long deptId = StringUtils.isNull( dept.getId() ) ? -1L : dept.getId();
        TrainCourseCategory info = trainCourseCategoryMapper.checkCategoryNameUnique( dept.getName(), dept.getParentId() );
        if (StringUtils.isNotNull( info ) && info.getId().longValue() != deptId.longValue()) {
            return UserConstants.DEPT_NAME_NOT_UNIQUE;
        }
        return UserConstants.DEPT_NAME_UNIQUE;
    }
}
