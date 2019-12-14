package com.ruoyi.train.course.service;

import com.ruoyi.framework.web.base.AbstractBaseService;
import com.ruoyi.train.course.domain.TrainCourseUser;

import java.util.List;

/**
 * 课程使用对象 服务层
 * 
 * @author zhujj
 * @date 2018-12-23
 */
public interface ITrainCourseUserService extends AbstractBaseService<TrainCourseUser>
{
	/**
     * 查询课程使用对象分页列表
     *
     * @param trainCourseUser 课程使用对象信息
     * @return 课程使用对象集合
     */
	public List<TrainCourseUser> selectTrainCourseUserPage(TrainCourseUser trainCourseUser);
    /**
     * 查询课程使用对象列表
     *
     * @param trainCourseUser 课程使用对象信息
     * @return 课程使用对象集合
     */
    public List<TrainCourseUser> selectTrainCourseUserList(TrainCourseUser trainCourseUser);

    /**
     * 判断是否有权限
     * @param userId 会员id
     * @param trainCourseId 课程id
     */
    boolean authority(Long userId, Integer trainCourseId);
}
