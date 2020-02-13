package com.ruoyi.train.course.service.impl;

import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
import com.ruoyi.train.course.domain.TrainCourseSection;
import com.ruoyi.train.course.domain.TrainCourseSectionVO;
import com.ruoyi.train.course.domain.vo.ApiTrainCourseSectionVO;
import com.ruoyi.train.course.mapper.TrainCourseSectionMapper;
import com.ruoyi.train.course.service.ITrainCourseSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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
    public List<TrainCourseSectionVO> selectTrainCourseSectionPage(TrainCourseSection trainCourseSection)
    {
        startPage();
        return trainCourseSectionMapper.selectTrainCourseSectionVOList(trainCourseSection);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public TrainCourseSectionVO selectTrainCourseVOById(Integer id) {
        return trainCourseSectionMapper.selectTrainCourseVOById(id);
    }

    /**
     * 按课程目录关系对课程内容进行树形关系组件
     * 1、课程目录关系暂时支持二级
     * 2、课程内容的顺序按关联的课程目录顺序排序
     * @param courseId
     * @return
     */
    @Override
    public List<ApiTrainCourseSectionVO> apiSelectTrainCourseVOTreeByCourseId(Integer courseId) {
        List<ApiTrainCourseSectionVO> apiTrainCourseSectionVOList = trainCourseSectionMapper.apiSelectTrainCourseVOListByCourseId(courseId);
        // 一级目录父id默认为100
        List<ApiTrainCourseSectionVO> CourseSectionVOList1 = getChildrenByPid(apiTrainCourseSectionVOList, 100);
        // 二级目录
        for (ApiTrainCourseSectionVO courseSectionVO1 : CourseSectionVOList1) {
            List<ApiTrainCourseSectionVO> CourseSectionVOList2 = getChildrenByPid(apiTrainCourseSectionVOList, courseSectionVO1.getDirectoryId());
            courseSectionVO1.setChildren(CourseSectionVOList2);
        }
        return CourseSectionVOList1;
    }

    private List<ApiTrainCourseSectionVO> getChildrenByPid(List<ApiTrainCourseSectionVO> list, Integer pid) {
        List<ApiTrainCourseSectionVO> children = new ArrayList<>();
        for (ApiTrainCourseSectionVO apiTrainCourseSectionVO : list) {
            if (pid.equals(apiTrainCourseSectionVO.getDirectoryParentId())) {
                children.add(apiTrainCourseSectionVO);
            }
        }
        Collections.sort(children);
        return children;
    }
}
