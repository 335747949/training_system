package com.ruoyi.train.course.mapper;


import com.ruoyi.framework.web.base.MyMapper;
import com.ruoyi.train.course.domain.TrainCourseSection;

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
	
}