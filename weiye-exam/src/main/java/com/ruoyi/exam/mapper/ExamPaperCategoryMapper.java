package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.ExamPaperCategory;
import java.util.List;
import com.ruoyi.framework.web.base.MyMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 试卷分类 数据层
 * 
 * @author zhujj
 * @date 2018-12-11
 */
public interface ExamPaperCategoryMapper  extends MyMapper<ExamPaperCategory>
{

	/**
     * 查询试卷分类列表
     * 
     * @param examPaperCategory 试卷分类信息
     * @return 试卷分类集合
     */
	public List<ExamPaperCategory> selectExamPaperCategoryList(ExamPaperCategory examPaperCategory);


	List<ExamPaperCategory> selectByNameAndPid(@Param("name") String name, @Param("parentId") Integer parentId);
}