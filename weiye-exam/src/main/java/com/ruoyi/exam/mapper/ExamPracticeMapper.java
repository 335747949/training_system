package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.ExamPractice;
import java.util.List;

import com.ruoyi.exam.domain.ExamPracticeVO;
import com.ruoyi.framework.web.base.MyMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 练习 数据层
 * 
 * @author zhujj
 * @date 2018-12-28
 */
public interface ExamPracticeMapper  extends MyMapper<ExamPractice>
{

	/**
     * 查询练习列表
     * 
     * @param examPractice 练习信息
     * @return 练习集合
     */
	public List<ExamPractice> selectExamPracticeList(ExamPractice examPractice);

	/**
	 * 接口查询练习题列表
	 * @param examPractice
	 * @return
	 */
	List<ExamPracticeVO> selectListFromWeb(ExamPractice examPractice);

    int update(ExamPractice examPractice);

    ExamPracticeVO selectExamPracticeById(@Param("id") Integer id);
}