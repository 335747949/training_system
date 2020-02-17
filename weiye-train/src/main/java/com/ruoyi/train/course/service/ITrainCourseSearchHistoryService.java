package com.ruoyi.train.course.service;

import java.util.List;
import com.ruoyi.framework.web.base.AbstractBaseService;
import com.ruoyi.train.course.domain.TrainCourseSearchHistory;

/**
 * 用户课程搜索记录 服务层
 * 
 * @author zhujj
 * @date 2020-02-06
 */
public interface ITrainCourseSearchHistoryService extends AbstractBaseService<TrainCourseSearchHistory>
{
	/**
     * 查询用户课程搜索记录分页列表
     *
     * @param trainCourseSearchHistory 用户课程搜索记录信息
     * @return 用户课程搜索记录集合
     */
	public List<TrainCourseSearchHistory> selectTrainCourseSearchHistoryPage(TrainCourseSearchHistory trainCourseSearchHistory);
    /**
     * 查询用户课程搜索记录列表
     *
     * @param trainCourseSearchHistory 用户课程搜索记录信息
     * @return 用户课程搜索记录集合
     */
    public List<TrainCourseSearchHistory> selectTrainCourseSearchHistoryList(TrainCourseSearchHistory trainCourseSearchHistory);


    /**
     * 查询用户搜索记录，最近20个
     * @param userId
     * @return
     */
    List<TrainCourseSearchHistory> searchHistory(String userId);
}
