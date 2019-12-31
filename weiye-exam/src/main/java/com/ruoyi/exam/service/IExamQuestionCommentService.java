package com.ruoyi.exam.service;

import com.ruoyi.exam.domain.ExamQuestionComment;
import java.util.List;
import com.ruoyi.framework.web.base.AbstractBaseService;
/**
 * 问题点评 服务层
 * 
 * @author zhujj
 * @date 2018-12-07
 */
public interface IExamQuestionCommentService extends AbstractBaseService<ExamQuestionComment>
{
	/**
     * 查询问题点评分页列表
     *
     * @param examQuestionComment 问题点评信息
     * @return 问题点评集合
     */
	public List<ExamQuestionComment> selectExamQuestionCommentPage(ExamQuestionComment examQuestionComment);
    /**
     * 查询问题点评列表
     *
     * @param examQuestionComment 问题点评信息
     * @return 问题点评集合
     */
    public List<ExamQuestionComment> selectExamQuestionCommentList(ExamQuestionComment examQuestionComment);

	
}
