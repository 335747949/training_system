package com.ruoyi.train.course.service.impl;

import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
import com.ruoyi.train.course.domain.TrainCourseSection;
import com.ruoyi.train.course.mapper.TrainCourseSectionMapper;
import com.ruoyi.train.course.service.ITrainCourseSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 课程章节 服务层实现
 * 
 * @author zhujj
 * @date 2018-12-23
 */
@Service
public class TrainCourseSectionServiceImpl extends AbstractBaseServiceImpl<TrainCourseSectionMapper,TrainCourseSection> implements ITrainCourseSectionService
{
	@Autowired
	private TrainCourseSectionMapper trainCourseSectionMapper;

	
	/**
     * 查询课程章节列表
     * 
     * @param trainCourseSection 课程章节信息
     * @return 课程章节集合
     */
	@Override
	public List<TrainCourseSection> selectTrainCourseSectionList(TrainCourseSection trainCourseSection)
	{
        return trainCourseSectionMapper.selectTrainCourseSectionList(trainCourseSection);
	}
    /**
     * 查询课程章节分页列表
     *
     * @param trainCourseSection 课程章节信息
     * @return 课程章节集合
     */
    @Override
    public List<TrainCourseSection> selectTrainCourseSectionPage(TrainCourseSection trainCourseSection)
    {
        startPage();
        return trainCourseSectionMapper.selectTrainCourseSectionList(trainCourseSection);
    }

}
