package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.ExamUserCollectionQuestion;
import java.util.List;

import com.ruoyi.exam.domain.ExamUserCollectionQuestionVO;
import com.ruoyi.framework.web.base.MyMapper;

/**
 * 我的收藏 数据层
 * 
 * @author zhujj
 * @date 2019-01-16
 */
public interface ExamUserCollectionQuestionMapper  extends MyMapper<ExamUserCollectionQuestion>
{

	/**
     * 查询我的收藏列表
     * 
     * @param examUserCollectionQuestion 我的收藏信息
     * @return 我的收藏集合
     */
	public List<ExamUserCollectionQuestionVO> selectExamUserCollectionQuestionList(ExamUserCollectionQuestionVO examUserCollectionQuestion);
	/**
	 * 查询我的收藏列表详情（包含题目选项）
	 *
	 * @param examUserCollectionQuestion 我的收藏信息
	 * @return 我的收藏集合
	 */
	public List<ExamUserCollectionQuestionVO> selectExamUserCollectionQuestionDetailList(ExamUserCollectionQuestionVO examUserCollectionQuestion);

}