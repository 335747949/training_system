package com.ruoyi.exam.service;

import com.ruoyi.exam.domain.*;

import java.util.List;
import java.util.Map;

import com.ruoyi.framework.web.base.AbstractBaseService;
import com.ruoyi.system.domain.SysUser;

/**
 * 考试 服务层
 * 
 * @author zhujj
 * @date 2018-12-24
 */
public interface IExamExaminationService extends AbstractBaseService<ExamExamination>
{
	/**
     * 查询考试分页列表
     *
     * @param examExamination 考试信息
     * @return 考试集合
     */
	public List<ExamExamination> selectExamExaminationPage(ExamExamination examExamination);
    /**
     * 查询考试列表
     *
     * @param examExamination 考试信息
     * @return 考试集合
     */
    public List<ExamExamination> selectExamExaminationList(ExamExamination examExamination);

    /**
     * web查询考试列表
     * @param map
     * @return
     */
    List<ExamExamination> selectListFromWeb(Map<String, Object> map);

    /**
     * 查询可以报名的列表
     * @param map
     * @return
     */
    List<ExamExamination> selectEnterNameListFromWeb(Map<String, Object> map);

    Map<String,Object> queryExaminationQuestion(ExamExamination examExamination,ExamUserExamination examUserExamination);

    Integer finshExamination(List<ExamUserExaminationQuestion> examUserExaminationQuestion, SysUser user, Integer examUserExaminationId, Integer examinationId, Integer paperId);

    Integer countScore(List<ExamUserExaminationQuestionVO> examUserExaminationQuestion, Integer examinationId);

    List<ExamExaminationResultVo> selectExamExaminationResultById(Integer examId);

    int update(ExamExamination examExamination);
}
