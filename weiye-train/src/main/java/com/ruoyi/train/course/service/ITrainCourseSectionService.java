package com.ruoyi.train.course.service;

import com.ruoyi.framework.web.base.AbstractBaseService;
import com.ruoyi.train.course.domain.TrainCourseSection;
import com.ruoyi.train.course.domain.TrainCourseSectionVO;
import com.ruoyi.train.course.domain.vo.ApiTrainCourseSectionVO;

import java.util.List;

/**
 * 课程章节 服务层
 * 
 * @author zhujj
 * @date 2018-12-23
 */
public interface ITrainCourseSectionService extends AbstractBaseService<TrainCourseSection>
{
	/**
     * 查询课程章节分页列表
     *
     * @param trainCourseSection 课程章节信息
     * @return 课程章节集合
     */
	public List<TrainCourseSectionVO> selectTrainCourseSectionPage(TrainCourseSection trainCourseSection);
    /**
     * 查询课程章节列表
     *
     * @param trainCourseSection 课程章节信息
     * @return 课程章节集合
     */
    public List<TrainCourseSection> selectTrainCourseSectionList(TrainCourseSection trainCourseSection);

    /**
     * 查询课程章节VO列表
     *
     * @param trainCourseSection 课程章节信息
     * @return 课程章节集合
     */
    List<TrainCourseSectionVO> selectTrainCourseSectionVOList(TrainCourseSection trainCourseSection);

    /**
     *
     * @param id
     * @return
     */
    TrainCourseSectionVO selectTrainCourseVOById(Integer id);

    /**
     *
     * @param courseId
     * @return
     */
    List<ApiTrainCourseSectionVO> apiSelectTrainCourseVOTreeByCourseId(Integer courseId);

	
}
