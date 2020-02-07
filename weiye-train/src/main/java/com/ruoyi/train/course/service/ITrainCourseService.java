package com.ruoyi.train.course.service;

import com.github.pagehelper.PageInfo;
import com.ruoyi.framework.web.base.AbstractBaseService;
import com.ruoyi.train.course.domain.TrainCourse;
import com.ruoyi.train.course.domain.TrainCourseVO;
import com.ruoyi.train.course.domain.vo.ApiCourseListByCategoryVO;

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


    /**
     * 根据课程分类分页查询课程列表
     * v1.1.0
     * @param apiCourseListByCategoryVO
     * @return
     */
    PageInfo<TrainCourseVO> selectTrainCourseListByCategory(ApiCourseListByCategoryVO apiCourseListByCategoryVO);
}
