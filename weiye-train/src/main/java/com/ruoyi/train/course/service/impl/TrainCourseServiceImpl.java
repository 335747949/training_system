package com.ruoyi.train.course.service.impl;

import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
import com.ruoyi.train.course.domain.TrainCourse;
import com.ruoyi.train.course.domain.TrainCourseVO;
import com.ruoyi.train.course.mapper.TrainCourseMapper;
import com.ruoyi.train.course.service.ITrainCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 课程 服务层实现
 *
 * @author zhujj
 * @date 2018-12-23
 */
@Service
public class TrainCourseServiceImpl extends AbstractBaseServiceImpl<TrainCourseMapper,TrainCourse> implements ITrainCourseService
{
    @Autowired
    private TrainCourseMapper trainCourseMapper;


    /**
     * 查询课程列表
     *
     * @param trainCourse 课程信息
     * @return 课程集合
     */
    @Override
    public List<TrainCourseVO> selectTrainCourseList(TrainCourseVO trainCourse)
    {
        return trainCourseMapper.selectTrainCourseList(trainCourse);
    }

    @Override
    public List<TrainCourse> selectTrainCourseListForExcel(TrainCourseVO trainCourse) {
        return trainCourseMapper.selectTrainCourseListForExcel(trainCourse);
    }

    /**
     * 查询课程分页列表
     *
     * @param trainCourse 课程信息
     * @return 课程集合
     */
    @Override
    public List<TrainCourseVO> selectTrainCoursePage(TrainCourseVO trainCourse)
    {
        startPage();
        return trainCourseMapper.selectTrainCourseList(trainCourse);
    }

}
