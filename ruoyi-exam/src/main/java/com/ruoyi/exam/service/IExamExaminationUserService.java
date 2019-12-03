package com.ruoyi.exam.service;

import com.ruoyi.exam.domain.ExamExaminationResultVo;
import com.ruoyi.exam.domain.ExamExaminationUser;
import java.util.List;
import com.ruoyi.framework.web.base.AbstractBaseService;
/**
 * 考试对象 服务层
 * 
 * @author zhujj
 * @date 2019-01-13
 */
public interface IExamExaminationUserService extends AbstractBaseService<ExamExaminationUser>
{
	/**
     * 查询考试对象分页列表
     *
     * @param examExaminationUser 考试对象信息
     * @return 考试对象集合
     */
	public List<ExamExaminationUser> selectExamExaminationUserPage(ExamExaminationUser examExaminationUser);
    /**
     * 查询考试对象列表
     *
     * @param examExaminationUser 考试对象信息
     * @return 考试对象集合
     */
    public List<ExamExaminationUser> selectExamExaminationUserList(ExamExaminationUser examExaminationUser);


    int insertOne(ExamExaminationUser examExaminationUser);

    List<ExamExaminationResultVo> selectExamExaminationResultByExamId(Integer examId);
}
