package com.ruoyi.train.course.service.impl;

import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
import com.ruoyi.train.course.domain.TrainCourseSectionCourseware;
import com.ruoyi.train.course.mapper.TrainCourseSectionCoursewareMapper;
import com.ruoyi.train.course.service.ITrainCourseSectionCoursewareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 章节课件 服务层实现
 * 
 * @author zhujj
 * @date 2018-12-23
 */
@Service
public class TrainCourseSectionCoursewareServiceImpl extends AbstractBaseServiceImpl<TrainCourseSectionCoursewareMapper,TrainCourseSectionCourseware> implements ITrainCourseSectionCoursewareService
{
	@Autowired
	private TrainCourseSectionCoursewareMapper trainCourseSectionCoursewareMapper;

	
	/**
     * 查询章节课件列表
     * 
     * @param trainCourseSectionCourseware 章节课件信息
     * @return 章节课件集合
     */
	@Override
	public List<TrainCourseSectionCourseware> selectTrainCourseSectionCoursewareList(TrainCourseSectionCourseware trainCourseSectionCourseware)
	{
        return trainCourseSectionCoursewareMapper.selectTrainCourseSectionCoursewareList(trainCourseSectionCourseware);
	}
    /**
     * 查询章节课件分页列表
     *
     * @param trainCourseSectionCourseware 章节课件信息
     * @return 章节课件集合
     */
    @Override
    public List<TrainCourseSectionCourseware> selectTrainCourseSectionCoursewarePage(TrainCourseSectionCourseware trainCourseSectionCourseware)
    {
        startPage();
        return trainCourseSectionCoursewareMapper.selectTrainCourseSectionCoursewareList(trainCourseSectionCourseware);
    }

}
