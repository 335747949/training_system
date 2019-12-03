package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.ExamExaminationResultVo;
import com.ruoyi.exam.domain.ExamExaminationUser;
import java.util.List;
import com.ruoyi.framework.web.base.MyMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 考试对象 数据层
 * 
 * @author zhujj
 * @date 2019-01-13
 */
public interface ExamExaminationUserMapper  extends MyMapper<ExamExaminationUser>
{

	/**
     * 查询考试对象列表
     * 
     * @param examExaminationUser 考试对象信息
     * @return 考试对象集合
     */
	List<ExamExaminationUser> selectExamExaminationUserList(ExamExaminationUser examExaminationUser);

	List<ExamExaminationResultVo> selectExamExaminationResultByExamId(@Param("examId") Integer examId);
}