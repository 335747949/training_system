package com.ruoyi.train.course.service;

import com.ruoyi.framework.web.base.AbstractBaseService;
import com.ruoyi.train.course.domain.TrainCourseDirectory;

import java.util.List;
import java.util.Map;

/**
 * 课程目录管理 服务层
 *
 * @author ruoyi
 */
public interface ITrainCourseDirectoryService extends AbstractBaseService<TrainCourseDirectory>
{
    /**
     * 查询课程目录管理数据
     *
     * @param directory 课程目录信息
     * @return 课程目录信息集合
     */
    public List<TrainCourseDirectory> selectDirectoryList(TrainCourseDirectory directory);

    /**
     * 查询课程目录管理树
     *
     * @return 所有课程目录信息
     */
    public List<Map<String, Object>> selectDirectoryTree();



    /**
     * 查询课程目录人数
     *
     * @param parentId 父课程目录ID
     * @return 结果
     */
    public int selectDirectoryCount(Long parentId);

    /**
     * 查询课程目录是否存在用户
     *
     * @param id 课程目录ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkDirectoryExistCourse(Long id);

    /**
     * 删除课程目录管理信息
     *
     * @param id 课程目录ID
     * @return 结果
     */
    public int deleteDirectoryById(Long id);

    /**
     * 新增保存课程目录信息
     *
     * @param directory 课程目录信息
     * @return 结果
     */
    public int insertDirectory(TrainCourseDirectory directory);

    /**
     * 修改保存课程目录信息
     *
     * @param directory 课程目录信息
     * @return 结果
     */
    public int updateDirectory(TrainCourseDirectory directory);

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
     * @param directory 课程目录信息
     * @return 结果
     */
    public String checkDirectoryNameUnique(TrainCourseDirectory directory);

    List<TrainCourseDirectory> selectAllDirectoryList(TrainCourseDirectory directory);

    int updateDirectoryByParentId(Long id, String updateBy);

    /**
     *
     * @param name
     * @param parentId
     * @return
     */
    String checkNameUnique(String name, Long parentId);

//    /**
//     * api查询课程目录树
//     * v1.1.0
//     * @return
//     */
//    List<ApiCourseCategoryVO> selectCategoryTreeList();
}
