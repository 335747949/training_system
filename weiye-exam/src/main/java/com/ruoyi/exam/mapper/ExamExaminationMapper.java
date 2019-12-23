package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.ExamExamination;
import java.util.List;
import java.util.Map;

import com.ruoyi.exam.domain.ExamExaminationVO;
import com.ruoyi.framework.web.base.MyMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 考试 数据层
 * 
 * @author zhujj
 * @date 2018-12-24
 */
public interface ExamExaminationMapper  extends MyMapper<ExamExamination>
{

	/**
     * 查询考试列表
     * 
     * @param examExamination 考试信息
     * @return 考试集合
     */
	public List<ExamExamination> selectExamExaminationList(ExamExamination examExamination);

	List<ExamExamination> selectListFromWeb(Map<String, Object> map);

    List<ExamExamination> selectSignUpListFromWeb(Map<String, Object> map);

    int update(ExamExamination examExamination);

    ExamExaminationVO selectExamExaminationById(@Param("id") Integer id);

    int countExamQuestion(@Param("id") Integer id);
}