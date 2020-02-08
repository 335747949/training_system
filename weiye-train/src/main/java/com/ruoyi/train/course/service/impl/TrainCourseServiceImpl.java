package com.ruoyi.train.course.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.constant.ExamConstants;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
import com.ruoyi.train.course.domain.TrainCourse;
import com.ruoyi.train.course.domain.TrainCourseVO;
import com.ruoyi.train.course.domain.vo.ApiCourseListByCategoryVO;
import com.ruoyi.train.course.mapper.TrainCourseMapper;
import com.ruoyi.train.course.service.ITrainCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public String checkNameUnique(String name, Integer trainCourseCategoryId) {
        List<TrainCourse> trainCourseList = trainCourseMapper.selectByNameAndcategoryId(name, trainCourseCategoryId);
        if (CollectionUtils.isEmpty(trainCourseList)) {
            return ExamConstants.TRAIN_COURSE_NAME_UNIQUE;
        }
        return ExamConstants.TRAIN_COURSE_NAME_NOT_UNIQUE;
    }


    /**
     * 根据课程分类分页查询课程列表
     * v1.1.0
     * @param apiCourseListByCategoryVO
     * @return
     */
    @Override
    public PageInfo<TrainCourseVO> selectTrainCourseListByCategory(ApiCourseListByCategoryVO apiCourseListByCategoryVO){
        StringBuffer buffer = new StringBuffer();
        if (!StringUtils.isEmpty(apiCourseListByCategoryVO.getCategoryId1()) && !"-1".equals(apiCourseListByCategoryVO.getCategoryId1())) {
            buffer.append("0").append(",").append(apiCourseListByCategoryVO.getCategoryId1());
        }
        if (!StringUtils.isEmpty(apiCourseListByCategoryVO.getCategoryId2()) && !"-1".equals(apiCourseListByCategoryVO.getCategoryId2())) {
            buffer.append(",").append(apiCourseListByCategoryVO.getCategoryId2());
        }
        // 三级分类选择全部时，只按一级二级分类进行查询
        if ("-1".equals(apiCourseListByCategoryVO.getCategoryId3())) {
            apiCourseListByCategoryVO.setCategoryId3(null);
        }
        PageHelper.startPage(apiCourseListByCategoryVO.getPageNum(), apiCourseListByCategoryVO.getPageSize());
        List<TrainCourseVO> list = trainCourseMapper.selectTrainCourseListByCategory(buffer.toString(), apiCourseListByCategoryVO.getCategoryId3());
        return new PageInfo<>(list);
    }

    @Override
    public List<TrainCourseVO> selectGoodsCourses() {
        return trainCourseMapper.selectGoodsCourses();
    }

    @Override
    public List<TrainCourseVO> selectNewCourses() {
        return trainCourseMapper.selectNewCourses();
    }

    @Override
    public Map<String, Object> selectMoreTrainCourses(TrainCourseVO trainCourseVO) {
        Page page = startPage();
        List<TrainCourseVO> trainCourseVOList = trainCourseMapper.selectTrainCourseList(trainCourseVO);
        Map<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("list",trainCourseVOList);
        return map;
    }


}
