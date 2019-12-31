package com.ruoyi.exam.service.impl;

import java.util.List;

import com.ruoyi.exam.domain.ExamExaminationResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.exam.mapper.ExamExaminationUserMapper;
import com.ruoyi.exam.domain.ExamExaminationUser;
import com.ruoyi.exam.service.IExamExaminationUserService;
import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
/**
 * 考试对象 服务层实现
 * 
 * @author zhujj
 * @date 2019-01-13
 */
@Service
public class ExamExaminationUserServiceImpl extends AbstractBaseServiceImpl<ExamExaminationUserMapper,ExamExaminationUser> implements IExamExaminationUserService
{
	@Autowired
	private ExamExaminationUserMapper examExaminationUserMapper;

	
	/**
     * 查询考试对象列表
     * 
     * @param examExaminationUser 考试对象信息
     * @return 考试对象集合
     */
	@Override
	public List<ExamExaminationUser> selectExamExaminationUserList(ExamExaminationUser examExaminationUser)
	{
        return examExaminationUserMapper.selectExamExaminationUserList(examExaminationUser);
	}

    @Override
    public int insertOne(ExamExaminationUser examExaminationUser) {
        return examExaminationUserMapper.insert(examExaminationUser);
    }

    /**
     * 查询考试对象分页列表
     *
     * @param examExaminationUser 考试对象信息
     * @return 考试对象集合
     */
    @Override
    public List<ExamExaminationUser> selectExamExaminationUserPage(ExamExaminationUser examExaminationUser)
    {
        startPage();
        return examExaminationUserMapper.selectExamExaminationUserList(examExaminationUser);
    }


    @Override
    public List<ExamExaminationResultVo> selectExamExaminationResultByExamId(Integer examId) {
        startPage();
        return examExaminationUserMapper.selectExamExaminationResultByExamId(examId);
    }
}
