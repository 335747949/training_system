package com.ruoyi.exam.service;

import com.ruoyi.exam.domain.ExamUserExamination;
import java.util.List;
import java.util.Map;

import com.ruoyi.exam.domain.ExamUserExaminationVO;
import com.ruoyi.exam.domain.ExcelUserExamination;
import com.ruoyi.framework.web.base.AbstractBaseService;
/**
 * 我的考试记录 服务层
 * 
 * @author zhujj
 * @date 2019-01-12
 */
public interface IExamUserExaminationService extends AbstractBaseService<ExamUserExamination>
{
	/**
     * 查询我的考试记录分页列表
     *
     * @param examUserExamination 我的考试记录信息
     * @return 我的考试记录集合
     */
	public List<ExamUserExamination> selectExamUserExaminationPage(ExamUserExamination examUserExamination);
    /**
     * 查询我的考试记录列表
     *
     * @param examUserExamination 我的考试记录信息
     * @return 我的考试记录集合
     */
    public List<ExamUserExamination> selectExamUserExaminationList(ExamUserExamination examUserExamination);

    /**
     * 查询用户上一次的考试
     * @param examUserExamination
     * @return
     */
    List<ExamUserExamination> selectLastOne(ExamUserExamination examUserExamination);

    void insertOne(ExamUserExamination insert);

    int updateOneSelectiveById(ExamUserExamination examUserExamination);

    List<ExamUserExaminationVO> selectMyExamUserExamination(ExamUserExaminationVO bean);

    List<ExamUserExaminationVO> selectMyExamUserExamination(Map<String,Object> map);

    //查询考试记录详情
    ExamUserExaminationVO selectDetailById(Integer id);

    List<ExcelUserExamination> selectMyExamUserExaminationForExcel(Map<String, Object> map);
}
