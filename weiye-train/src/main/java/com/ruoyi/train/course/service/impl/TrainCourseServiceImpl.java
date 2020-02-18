package com.ruoyi.train.course.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.constant.ExamConstants;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
import com.ruoyi.train.course.domain.TrainCourse;
import com.ruoyi.train.course.domain.TrainCourseVO;
import com.ruoyi.train.course.domain.vo.ApiCourseListByCategoryVO;
import com.ruoyi.train.course.mapper.TrainCourseCategoryMapper;
import com.ruoyi.train.course.mapper.TrainCourseMapper;
import com.ruoyi.train.course.service.ITrainCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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

    @Autowired
    private TrainCourseCategoryMapper trainCourseCategoryMapper;

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
//        暂时启用以下代码 2020.2.14  v1.1.0
//        // 三级分类选择全部时，只按一级二级分类进行查询
//        if ("-1".equals(apiCourseListByCategoryVO.getCategoryId3())) {
//            apiCourseListByCategoryVO.setCategoryId3(null);
//        }
//        PageHelper.startPage(apiCourseListByCategoryVO.getPageNum(), apiCourseListByCategoryVO.getPageSize());
//        List<TrainCourseVO> list = trainCourseMapper.selectTrainCourseListByCategory(buildCategoryParentIds(apiCourseListByCategoryVO), apiCourseListByCategoryVO.getCategoryId3());

        TrainCourseVO trainCourseVO = new TrainCourseVO();
        // 用户端仅展示公开课程
        trainCourseVO.setState("1");
        trainCourseVO.setDelFlag("0");
        trainCourseVO.setTrainCourseCategoryId(getTrainCourseVOCategoryId(apiCourseListByCategoryVO));
        PageHelper.startPage(apiCourseListByCategoryVO.getPageNum(), apiCourseListByCategoryVO.getPageSize());
        List<TrainCourseVO> list = trainCourseMapper.ApiSelectTrainCourseList(trainCourseVO);
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
    /**
     * 此方法暂时弃用 2020.2.14 v1.1.0
     * @param apiCourseListByCategoryVO
     * @return
     */
    private String buildCategoryParentIds (ApiCourseListByCategoryVO apiCourseListByCategoryVO) {
        StringBuffer buffer = new StringBuffer();
        if (!StringUtils.isEmpty(apiCourseListByCategoryVO.getCategoryId1()) && !"-1".equals(apiCourseListByCategoryVO.getCategoryId1())) {
            buffer.append("0").append(",").append("100").append(",").append(apiCourseListByCategoryVO.getCategoryId1());
        }
        if (!StringUtils.isEmpty(apiCourseListByCategoryVO.getCategoryId2()) && !"-1".equals(apiCourseListByCategoryVO.getCategoryId2())) {
            buffer.append(",").append(apiCourseListByCategoryVO.getCategoryId2());
        }
        return buffer.toString();
    }

    /**
     * 组装参数，供按分类查询课程使用
     * @param apiCourseListByCategoryVO
     * @return
     */
    private Integer getTrainCourseVOCategoryId(ApiCourseListByCategoryVO apiCourseListByCategoryVO) {
        Integer trainCourseVOCategoryId = Integer.parseInt(apiCourseListByCategoryVO.getCategoryId3());
        if ("-1".equals(apiCourseListByCategoryVO.getCategoryId3())) {
            trainCourseVOCategoryId = Integer.parseInt(apiCourseListByCategoryVO.getCategoryId2());
        }
        if ("-1".equals(apiCourseListByCategoryVO.getCategoryId2())) {
            trainCourseVOCategoryId = Integer.parseInt(apiCourseListByCategoryVO.getCategoryId1());
        }
        if ("-1".equals(apiCourseListByCategoryVO.getCategoryId1())) {
            trainCourseVOCategoryId = null;
        }
        return trainCourseVOCategoryId;
    }

    /**
     * 小程序api根据课程id推荐相关课程
     * @param courseId 课程id
     * @param size 需要推荐的课程数量
     * @return
     */
    @Override
    public List<TrainCourseVO> recommendCourseByCategory(Integer courseId, Integer size) {
        // 出参，推荐的六个课程
        HashMap<Integer,TrainCourseVO> resultMap = new HashMap<>();
        // 查询此课程的分类
        TrainCourse condition = new TrainCourse();
        condition.setId(courseId);
        TrainCourse trainCourse = trainCourseMapper.selectByPrimaryKey(condition);
        // 查询相同分类下的size个推荐课程，排除本课程
        TrainCourseVO trainCourseVOCondition = new TrainCourseVO();
        // 用户端仅展示公开课程
        trainCourseVOCondition.setState("1");
        trainCourseVOCondition.setDelFlag("0");
        trainCourseVOCondition.setTrainCourseCategoryId(trainCourse.getTrainCourseCategoryId());
        PageHelper.startPage(1, size + 1);
        List<TrainCourseVO> list3 = trainCourseMapper.ApiSelectTrainCourseList(trainCourseVOCondition);
        // 第一次去重并组装出参
        for (TrainCourseVO trainCourseVO: list3) {
            // 相同课程不再推荐
            if (!courseId.equals(trainCourseVO.getId()) && resultMap.size() < size) {
                resultMap.put(trainCourseVO.getId(), trainCourseVO);
            }
            if (resultMap.size() == size) {
                return new ArrayList(resultMap.values());
            }
        }

        // 不够推荐数量size个时,再从全部课程中随机推荐课程
        trainCourseVOCondition.setTrainCourseCategoryId(null);
        PageHelper.startPage(1, size + 1);
        List<TrainCourseVO> list = trainCourseMapper.ApiSelectTrainCourseList(trainCourseVOCondition);
        // 第二次去重并组装出参
        for (TrainCourseVO trainCourseVO: list) {
            // 相同课程不在推荐
            if (!courseId.equals(trainCourseVO.getId()) && resultMap.size() < size) {
                resultMap.put(trainCourseVO.getId(), trainCourseVO);
            }
            if (resultMap.size() == size) {
                return new ArrayList(resultMap.values());
            }
        }
        // 返回
        return new ArrayList(resultMap.values());
    }

}
