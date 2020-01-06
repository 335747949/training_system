package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.ExamPaper;
import java.util.List;

import com.ruoyi.exam.domain.ExamPaperVO;
import com.ruoyi.framework.web.base.MyMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 试卷 数据层
 * 
 * @author zhujj
 * @date 2018-12-16
 */
public interface ExamPaperMapper  extends MyMapper<ExamPaper>
{

	/**
     * 查询试卷列表
     * 
     * @param examPaper 试卷信息
     * @return 试卷集合
     */
	public List<ExamPaper> selectExamPaperList(ExamPaper examPaper);

    List<ExamPaper> selectListByCategory(ExamPaper examPaper);

	/**
	 *
	 * @param name
	 * @return
	 */
	List<ExamPaper> selectByNameAndType(@Param("name") String name, @Param("type") String type,  @Param("categoryId") Integer examPaperCategoryId);
}