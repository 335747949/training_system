package com.ruoyi.train.course.service;

import com.ruoyi.framework.web.base.AbstractBaseService;
import com.ruoyi.train.course.domain.TrainCourse;
import com.ruoyi.train.course.domain.TrainCourseVO;

import java.util.List;

/**
 * 课程 服务层
 * 
 * @author zhujj
 * @date 2018-12-23
 */
public interface ITrainCourseService extends AbstractBaseService<TrainCourse>
{
	/**
     * 查询课程分页列表
     *
     * @param trainCourse 课程信息
     * @return 课程集合
     */
	public List<TrainCourseVO> selectTrainCoursePage(TrainCourseVO trainCourse);
    /**
     * 查询课程列表
     *
     * @param trainCourse 课程信息
     * @return 课程集合
     */
    public List<TrainCourseVO> selectTrainCourseList(TrainCourseVO trainCourse);


    List<TrainCourse> selectTrainCourseListForExcel(TrainCourseVO trainCourse);

    /**
     *
     * @param name
     * @param trainCourseCategoryId
     * @return
     */
    String checkNameUnique(String name, Integer trainCourseCategoryId);
}
