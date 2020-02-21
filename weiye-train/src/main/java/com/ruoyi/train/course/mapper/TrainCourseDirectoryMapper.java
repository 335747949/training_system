package com.ruoyi.train.course.mapper;

import com.ruoyi.framework.web.base.MyMapper;
import com.ruoyi.train.course.domain.TrainCourseDirectory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课程目录管理 数据层
 *
 * @author ruoyi
 */
public interface TrainCourseDirectoryMapper extends MyMapper<TrainCourseDirectory>
{
    /**
     * 查询课程目录人数
     *
     * @param directory 课程目录信息
     * @return 结果
     */
    public int selectDirectoryCount(TrainCourseDirectory directory);

    /**
     * 查询课程目录是否存在用户
     *
     * @param id 课程目录ID
     * @return 结果
     */
    public int checkDirectoryExistCourse(Long id);

    /**
     * 查询课程目录管理数据
     *
     * @param category 课程目录信息
     * @return 课程目录信息集合
     */
    public List<TrainCourseDirectory> selectDirectoryList(TrainCourseDirectory category);


    /**
     * 查询课程目录管理数据
     *
     * @param category 课程目录信息
     * @return 课程目录信息集合
     */
    public List<TrainCourseDirectory> selectAllDirectoryList(TrainCourseDirectory category);

    /**
     * 删除课程目录管理信息
     *
     * @param id 课程目录ID
     * @return 结果
     */
    public int deleteDirectoryById(Long id);

    /**
     * 新增课程目录信息
     *
     * @param directory 课程目录信息
     * @return 结果
     */
    public int insertDirectory(TrainCourseDirectory directory);

    /**
     * 修改课程目录信息
     *
     * @param directory 课程目录信息
     * @return 结果
     */
    public int updateDirectory(TrainCourseDirectory directory);

    /**
     * 修改子元素关系
     *
     * @param ids 子元素
     * @return 结果
     */
    public int updateDirectoryChildren(@Param("ids") List<TrainCourseDirectory> ids);

    /**
     * 根据课程目录ID查询信息
     *
     * @param id 课程目录ID
     * @return 课程目录信息
     */
    public TrainCourseDirectory selectDirectoryById(Long id);

    /**
     * 校验课程目录名称是否唯一
     *
     * @param name 课程目录名称
     * @param parentId 父课程目录ID
     * @return 结果
     */
    public TrainCourseDirectory checkDirectoryNameUnique(@Param("name") String name, @Param("parentId") Long parentId);

    /**
     * 根据角色ID查询课程目录
     *
     * @param roleId 角色ID
     * @return 课程目录列表
     */
    public List<String> selectRoleDirectoryTree(Long roleId);

    int updateDirectoryByParentId(@Param("id") Long id, @Param("updateBy") String updateBy);

    /**
     *
     * @param name
     * @param parentId
     * @return
     */
    List<TrainCourseDirectory> selectByNameAndParentId(@Param("name") String name, @Param("parentId") Long parentId);
}
