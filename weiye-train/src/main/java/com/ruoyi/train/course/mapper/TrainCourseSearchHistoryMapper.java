package com.ruoyi.train.course.mapper;

import com.ruoyi.train.course.domain.TrainCourseSearchHistory;
import java.util.List;
import com.ruoyi.framework.web.base.MyMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户课程搜索记录 数据层
 * 
 * @author zhujj
 * @date 2020-02-06
 */
public interface TrainCourseSearchHistoryMapper  extends MyMapper<TrainCourseSearchHistory>
{

	/**
     * 查询用户课程搜索记录列表
     * 
     * @param trainCourseSearchHistory 用户课程搜索记录信息
     * @return 用户课程搜索记录集合
     */
	public List<TrainCourseSearchHistory> selectTrainCourseSearchHistoryList(TrainCourseSearchHistory trainCourseSearchHistory);

	List<TrainCourseSearchHistory> searchHistory(@Param("userId") String userId);
}