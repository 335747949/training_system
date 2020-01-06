package com.ruoyi.exam.service;

import com.ruoyi.exam.domain.ExamPractice;
import java.util.List;

import com.ruoyi.exam.domain.ExamPracticeQuestionVO;
import com.ruoyi.exam.domain.ExamPracticeVO;
import com.ruoyi.framework.web.base.AbstractBaseService;
/**
 * 练习 服务层
 * 
 * @author zhujj
 * @date 2018-12-28
 */
public interface IExamPracticeService extends AbstractBaseService<ExamPractice>
{
	/**
     * 查询练习分页列表
     *
     * @param examPractice 练习信息
     * @return 练习集合
     */
	public List<ExamPractice> selectExamPracticePage(ExamPractice examPractice);
    /**
     * 查询练习列表
     *
     * @param examPractice 练习信息
     * @return 练习集合
     */
    public List<ExamPractice> selectExamPracticeList(ExamPractice examPractice);


    /**
     * 前台页面调用API
     * @param examPractice
     * @return
     */
    List<ExamPracticeVO> selectListFromWeb(ExamPractice examPractice);

    int update(ExamPractice examPractice);

    ExamPracticeVO selectExamPracticeById(Integer id);

    /**
     * 练习名称是否唯一
     * @param name
     * @return
     */
    String checkNameUnique(String name);
}
