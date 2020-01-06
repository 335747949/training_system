package com.ruoyi.train.course.mapper;

import com.ruoyi.train.course.domain.TrainCourseCategory;
import com.ruoyi.framework.web.base.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课程分类管理 数据层
 *
 * @author ruoyi
 */
public interface TrainCourseCategoryMapper extends MyMapper<TrainCourseCategory>
{
    /**
     * 查询课程分类人数
     *
     * @param category 课程分类信息
     * @return 结果
     */
    public int selectCategoryCount(TrainCourseCategory category);

    /**
     * 查询课程分类是否存在用户
     *
     * @param id 课程分类ID
     * @return 结果
     */
    public int checkCategoryExistCourse(Long id);

    /**
     * 查询课程分类管理数据
     *
     * @param category 课程分类信息
     * @return 课程分类信息集合
     */
    public List<TrainCourseCategory> selectCategoryList(TrainCourseCategory category);


    /**
     * 查询课程分类管理数据
     *
     * @param category 课程分类信息
     * @return 课程分类信息集合
     */
    public List<TrainCourseCategory> selectAllCategoryList(TrainCourseCategory category);

    /**
     * 删除课程分类管理信息
     *
     * @param id 课程分类ID
     * @return 结果
     */
    public int deleteCategoryById(Long id);

    /**
     * 新增课程分类信息
     *
     * @param category 课程分类信息
     * @return 结果
     */
    public int insertCategory(TrainCourseCategory category);

    /**
     * 修改课程分类信息
     *
     * @param category 课程分类信息
     * @return 结果
     */
    public int updateCategory(TrainCourseCategory category);

    /**
     * 修改子元素关系
     *
     * @param ids 子元素
     * @return 结果
     */
    public int updateCategoryChildren(@Param("ids") List<TrainCourseCategory> ids);

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
     * @param name 课程分类名称
     * @param parentId 父课程分类ID
     * @return 结果
     */
    public TrainCourseCategory checkCategoryNameUnique(@Param("name") String name, @Param("parentId") Long parentId);

    /**
     * 根据角色ID查询课程分类
     *
     * @param roleId 角色ID
     * @return 课程分类列表
     */
    public List<String> selectRoleCategoryTree(Long roleId);

    int updateCategoryByParentId(@Param("id") Long id, @Param("updateBy")String updateBy);

    /**
     *
     * @param name
     * @param parentId
     * @return
     */
    List<TrainCourseCategory> selectByNameAndParentId(@Param("name") String name, @Param("parentId") Long parentId);
}
