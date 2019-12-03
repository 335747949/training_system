package com.ruoyi.exam.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.exam.mapper.ExamQuestionCommentMapper;
import com.ruoyi.exam.domain.ExamQuestionComment;
import com.ruoyi.exam.service.IExamQuestionCommentService;
import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
/**
 * 问题点评 服务层实现
 * 
 * @author zhujj
 * @date 2018-12-07
 */
@Service
public class ExamQuestionCommentServiceImpl extends AbstractBaseServiceImpl<ExamQuestionCommentMapper,ExamQuestionComment> implements IExamQuestionCommentService
{
	@Autowired
	private ExamQuestionCommentMapper examQuestionCommentMapper;

	
	/**
     * 查询问题点评列表
     * 
     * @param examQuestionComment 问题点评信息
     * @return 问题点评集合
     */
	@Override
	public List<ExamQuestionComment> selectExamQuestionCommentList(ExamQuestionComment examQuestionComment)
	{
        return examQuestionCommentMapper.selectExamQuestionCommentList(examQuestionComment);
	}
    /**
     * 查询问题点评分页列表
     *
     * @param examQuestionComment 问题点评信息
     * @return 问题点评集合
     */
    @Override
    public List<ExamQuestionComment> selectExamQuestionCommentPage(ExamQuestionComment examQuestionComment)
    {
        startPage();
        return examQuestionCommentMapper.selectExamQuestionCommentList(examQuestionComment);
    }

}
