package com.ruoyi.train.course.mapper;


import com.ruoyi.framework.web.base.MyMapper;
import com.ruoyi.train.course.domain.TrainCourseSection;
import com.ruoyi.train.course.domain.TrainCourseSectionVO;
import com.ruoyi.train.course.domain.vo.ApiTrainCourseSectionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课程章节 数据层
 * 
 * @author zhujj
 * @date 2018-12-23
 */
public interface TrainCourseSectionMapper  extends MyMapper<TrainCourseSection>
{

	/**
     * 查询课程章节列表
     * 
     * @param trainCourseSection 课程章节信息
     * @return 课程章节集合
     */
	public List<TrainCourseSection> selectTrainCourseSectionList(TrainCourseSection trainCourseSection);

	/**
     * 查询课程章节列表
     *
     * @param trainCourseSection 课程章节信息
     * @return 课程章节集合
     */
	public List<TrainCourseSectionVO> selectTrainCourseSectionVOList(TrainCourseSection trainCourseSection);

	/**
	 * pc端查询课程内容列表
	 *
	 * @param trainCourseSection 课程章节信息
	 * @return 课程章节集合
	 */
	public List<TrainCourseSectionVO> selectTrainCourseSectionVOList2(TrainCourseSection trainCourseSection);

	/**
	 *
	 * @param id
	 * @return
	 */
	TrainCourseSectionVO selectTrainCourseVOById(@Param("id") Integer id);

	/**
	 *
	 * @param courseId
	 * @return
	 */
	List<ApiTrainCourseSectionVO> apiSelectTrainCourseVOListByCourseId(@Param("courseId") Integer courseId);
}