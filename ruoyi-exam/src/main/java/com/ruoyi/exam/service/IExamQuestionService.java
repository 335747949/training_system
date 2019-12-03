package com.ruoyi.exam.service;

import com.ruoyi.exam.domain.ExamQuestion;

import java.util.List;
import java.util.Map;

import com.ruoyi.exam.domain.ExamQuestionVO;
import com.ruoyi.exam.domain.ExcelQuestion;
import com.ruoyi.framework.web.base.AbstractBaseService;
/**
 * 问题 服务层
 * 
 * @author zhujj
 * @date 2018-12-06
 */
public interface IExamQuestionService extends AbstractBaseService<ExamQuestion>
{
	/**
     * 查询问题信息
     *
     * @param id 问题ID
     * @return 问题信息
     */
	public ExamQuestion selectExamQuestionById(String id);

	/**
     * 查询问题列表
     *
     * @param examQuestion 问题信息
     * @return 问题集合
     */
	public List<ExamQuestion> selectExamQuestionList(ExamQuestion examQuestion);

	/**
     * 新增问题
     *
     * @param examQuestion 问题信息
     * @return 结果
     */
	public int insertExamQuestion(ExamQuestion examQuestion);

	/**
     * 修改问题
     *
     * @param examQuestion 问题信息
     * @return 结果
     */
	public int updateExamQuestion(ExamQuestion examQuestion);

	/**
     * 删除问题信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteExamQuestionByIds(String ids);

	int insertQuestion(ExamQuestion examQuestion, String[] number, String[] content);

    int updateQuestion(ExamQuestion examQuestion, String[] number, String[] content);

	List<ExamQuestion> selectByIds(List<String> ids);

	List<ExamQuestion> selectQuestionList(ExamQuestion examQuestion);

    List<ExamQuestion> selectListBycategory(ExamQuestion examQuestion);

	List<ExamQuestionVO> selectQuestionListByPracticeId(Map<String, Object> map);

    ExamQuestionVO selectQuestionDetail(String questionId);

    List<ExcelQuestion> selectExamQuestionListForExcel(ExamQuestion examQuestion);
}
