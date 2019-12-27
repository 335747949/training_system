package com.ruoyi.train.course.service;

import com.ruoyi.train.course.domain.TrainCourseCategory;
import com.ruoyi.framework.web.base.AbstractBaseService;

import java.util.List;
import java.util.Map;

/**
 * 课程分类管理 服务层
 *
 * @author ruoyi
 */
public interface ITrainCourseCategoryService extends AbstractBaseService<TrainCourseCategory>
{
    /**
     * 查询课程分类管理数据
     *
     * @param category 课程分类信息
     * @return 课程分类信息集合
     */
    public List<TrainCourseCategory> selectCategoryList(TrainCourseCategory category);

    /**
     * 查询课程分类管理树
     *
     * @return 所有课程分类信息
     */
    public List<Map<String, Object>> selectCategoryTree();



    /**
     * 查询课程分类人数
     *
     * @param parentId 父课程分类ID
     * @return 结果
     */
    public int selectCategoryCount(Long parentId);

    /**
     * 查询课程分类是否存在用户
     *
     * @param id 课程分类ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkCategoryExistCourse(Long id);

    /**
     * 删除课程分类管理信息
     *
     * @param id 课程分类ID
     * @return 结果
     */
    public int deleteCategoryById(Long id);

    /**
     * 新增保存课程分类信息
     *
     * @param category 课程分类信息
     * @return 结果
     */
    public int insertCategory(TrainCourseCategory category);

    /**
     * 修改保存课程分类信息
     *
     * @param category 课程分类信息
     * @return 结果
     */
    public int updateCategory(TrainCourseCategory category);

    /**
     * 根据课程分类ID查询信息
     *
     * @param id 课程分类ID
     * @return 课程分类信息
     */
    public TrainCourseCategory selectCategoryById(Long id);

    /**
     * 校验课程分类名称是否唯一
     *
     * @param category 课程分类信息
     * @return 结果
     */
    public String checkCategoryNameUnique(TrainCourseCategory category);

    List<TrainCourseCategory> selectAllCategoryList(TrainCourseCategory category);

    int updateCategoryByParentId(Long id, String updateBy);
}
