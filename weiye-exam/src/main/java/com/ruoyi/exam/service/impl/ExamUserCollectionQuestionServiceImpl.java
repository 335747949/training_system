package com.ruoyi.exam.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.exam.domain.ExamUserCollectionQuestionVO;
import com.ruoyi.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.exam.mapper.ExamUserCollectionQuestionMapper;
import com.ruoyi.exam.domain.ExamUserCollectionQuestion;
import com.ruoyi.exam.service.IExamUserCollectionQuestionService;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
/**
 * 我的收藏 服务层实现
 * 
 * @author zhujj
 * @date 2019-01-16
 */
@Service
public class ExamUserCollectionQuestionServiceImpl extends AbstractBaseServiceImpl<ExamUserCollectionQuestionMapper,ExamUserCollectionQuestion> implements IExamUserCollectionQuestionService
{
	@Autowired
	private ExamUserCollectionQuestionMapper examUserCollectionQuestionMapper;

    /**
     * 查询我的收藏分页列表
     *
     * @param examUserCollectionQuestion 我的收藏信息
     * @return 我的收藏集合
     */
    @Override
    public List<ExamUserCollectionQuestionVO> selectExamUserCollectionQuestionPage(ExamUserCollectionQuestionVO examUserCollectionQuestion)
    {
        startPage();
        return examUserCollectionQuestionMapper.selectExamUserCollectionQuestionList(examUserCollectionQuestion);
    }
	/**
     * 查询我的收藏列表(包含题目选项)
     * 
     * @param examUserCollectionQuestion 我的收藏信息
     * @return 我的收藏集合
     */
	@Override
	public List<ExamUserCollectionQuestionVO> selectExamUserCollectionQuestionList(ExamUserCollectionQuestionVO examUserCollectionQuestion)
	{
        return examUserCollectionQuestionMapper.selectExamUserCollectionQuestionDetailList(examUserCollectionQuestion);
	}

    @Override
    public int insertSelectiveBySelf(Integer questionId, SysUser sysUser) {
        ExamUserCollectionQuestion examUserCollectionQuestion = new ExamUserCollectionQuestion();
        examUserCollectionQuestion.setExamQuestionId( questionId );
        examUserCollectionQuestion.setVipUserId( sysUser.getUserId().intValue() );
        List<ExamUserCollectionQuestion> db = examUserCollectionQuestionMapper.select(examUserCollectionQuestion);
        if(db.size()>0){
            return 0;
        }
        examUserCollectionQuestion.setCreateBy( sysUser.getLoginName() );
        examUserCollectionQuestion.setCreateDate( new Date() );
        examUserCollectionQuestion.setDelFlag( "0" );
        examUserCollectionQuestion.setUpdateBy( sysUser.getLoginName() );
        examUserCollectionQuestion.setUpdateDate( new Date() );
	    return examUserCollectionQuestionMapper.insertSelective(examUserCollectionQuestion);
    }



}
