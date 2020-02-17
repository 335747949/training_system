package com.ruoyi.train.course.service.impl;

import java.util.List;

import com.ruoyi.train.course.domain.TrainCourseSearchHistory;
import com.ruoyi.train.course.mapper.TrainCourseSearchHistoryMapper;
import com.ruoyi.train.course.service.ITrainCourseSearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
/**
 * 用户课程搜索记录 服务层实现
 * 
 * @author zhujj
 * @date 2020-02-06
 */
@Service
public class TrainCourseSearchHistoryServiceImpl extends AbstractBaseServiceImpl<TrainCourseSearchHistoryMapper, TrainCourseSearchHistory> implements ITrainCourseSearchHistoryService
{
	@Autowired
	private TrainCourseSearchHistoryMapper trainCourseSearchHistoryMapper;

	
	/**
     * 查询用户课程搜索记录列表
     * 
     * @param trainCourseSearchHistory 用户课程搜索记录信息
     * @return 用户课程搜索记录集合
     */
	@Override
	public List<TrainCourseSearchHistory> selectTrainCourseSearchHistoryList(TrainCourseSearchHistory trainCourseSearchHistory)
	{
        return trainCourseSearchHistoryMapper.selectTrainCourseSearchHistoryList(trainCourseSearchHistory);
	}

    @Override
    public List<TrainCourseSearchHistory> searchHistory(String userId) {
        return trainCourseSearchHistoryMapper.searchHistory(userId);
    }

    /**
     * 查询用户课程搜索记录分页列表
     *
     * @param trainCourseSearchHistory 用户课程搜索记录信息
     * @return 用户课程搜索记录集合
     */
    @Override
    public List<TrainCourseSearchHistory> selectTrainCourseSearchHistoryPage(TrainCourseSearchHistory trainCourseSearchHistory)
    {
        startPage();
        return trainCourseSearchHistoryMapper.selectTrainCourseSearchHistoryList(trainCourseSearchHistory);
    }

}
