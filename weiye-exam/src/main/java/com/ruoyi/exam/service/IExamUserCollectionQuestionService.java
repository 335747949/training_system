package com.ruoyi.exam.service;

import com.ruoyi.exam.domain.ExamUserCollectionQuestion;
import java.util.List;

import com.ruoyi.exam.domain.ExamUserCollectionQuestionVO;
import com.ruoyi.framework.web.base.AbstractBaseService;
import com.ruoyi.system.domain.SysUser;

/**
 * 我的收藏 服务层
 * 
 * @author zhujj
 * @date 2019-01-16
 */
public interface IExamUserCollectionQuestionService extends AbstractBaseService<ExamUserCollectionQuestion>
{
	/**
     * 查询我的收藏分页列表
     *
     * @param examUserCollectionQuestion 我的收藏信息
     * @return 我的收藏集合
     */
	public List<ExamUserCollectionQuestionVO> selectExamUserCollectionQuestionPage(ExamUserCollectionQuestionVO examUserCollectionQuestion);
    /**
     * 查询我的收藏列表
     *
     * @param examUserCollectionQuestion 我的收藏信息
     * @return 我的收藏集合
     */
    public List<ExamUserCollectionQuestionVO> selectExamUserCollectionQuestionList(ExamUserCollectionQuestionVO examUserCollectionQuestion);

    /**
     * 插入收藏题库
     * @param questionId
     * @param sysUser
     * @return
     */
    int insertSelectiveBySelf(Integer questionId, SysUser sysUser);
}
