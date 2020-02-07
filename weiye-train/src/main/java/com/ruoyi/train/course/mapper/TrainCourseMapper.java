package com.ruoyi.train.course.mapper;


import com.ruoyi.framework.web.base.MyMapper;
import com.ruoyi.train.course.domain.TrainCourse;
import com.ruoyi.train.course.domain.TrainCourseVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课程 数据层
 * 
 * @author zhujj
 * @date 2018-12-23
 */
public interface TrainCourseMapper  extends MyMapper<TrainCourse>
{

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
    List<TrainCourse> selectByNameAndcategoryId(@Param("name") String name, @Param("trainCourseCategoryId") Integer trainCourseCategoryId);

	/**
	 * 根据课程分类模糊查询课程列表
	 * @param parentIds
	 * @return
	 */
	List<TrainCourseVO> selectTrainCourseListByCategory(@Param("parentIds") String parentIds, @Param("courseCategoryId") String courseCategoryId);
}