package com.ruoyi.exam.controller;

import java.util.List;

import com.ruoyi.exam.domain.ExamUserCollectionQuestionVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.exam.domain.ExamUserCollectionQuestion;
import com.ruoyi.exam.service.IExamUserCollectionQuestionService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.ExcelUtil;

/**
 * 我的收藏 信息操作处理
 * 
 * @author zhujj
 * @date 2019-01-16
 */
@Controller
@RequestMapping("/exam/examUserCollectionQuestion")
public class ExamUserCollectionQuestionController extends BaseController
{
    private String prefix = "exam/examUserCollectionQuestion";
	
	@Autowired
	private IExamUserCollectionQuestionService examUserCollectionQuestionService;
	
	@RequiresPermissions("exam:examUserCollectionQuestion:view")
	@GetMapping()
	public String examUserCollectionQuestion()
	{
	    return prefix + "/examUserCollectionQuestion";
	}
	
	/**
	 * 查询我的收藏列表
	 */
	@RequiresPermissions("exam:examUserCollectionQuestion:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ExamUserCollectionQuestionVO examUserCollectionQuestion)
	{
        List<ExamUserCollectionQuestionVO> list = examUserCollectionQuestionService.selectExamUserCollectionQuestionPage(examUserCollectionQuestion);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出我的收藏列表
	 */
	@RequiresPermissions("exam:examUserCollectionQuestion:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ExamUserCollectionQuestionVO examUserCollectionQuestion)
    {
    	List<ExamUserCollectionQuestionVO> list = examUserCollectionQuestionService.selectExamUserCollectionQuestionList(examUserCollectionQuestion);
        ExcelUtil<ExamUserCollectionQuestionVO> util = new ExcelUtil<ExamUserCollectionQuestionVO>(ExamUserCollectionQuestionVO.class);
        return util.exportExcel(list, "examUserCollectionQuestion");
    }
	
	/**
	 * 新增我的收藏
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存我的收藏
	 */
	@RequiresPermissions("exam:examUserCollectionQuestion:add")
	@Log(title = "我的收藏", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ExamUserCollectionQuestion examUserCollectionQuestion)
	{		
		return toAjax(examUserCollectionQuestionService.insert(examUserCollectionQuestion));
	}

	/**
	 * 修改我的收藏
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		ExamUserCollectionQuestion examUserCollectionQuestion = examUserCollectionQuestionService.selectById(id);
		mmap.put("examUserCollectionQuestion", examUserCollectionQuestion);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存我的收藏
	 */
	@RequiresPermissions("exam:examUserCollectionQuestion:edit")
	@Log(title = "我的收藏", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ExamUserCollectionQuestion examUserCollectionQuestion)
	{		
		return toAjax(examUserCollectionQuestionService.updateById(examUserCollectionQuestion));
	}
	
	/**
	 * 删除我的收藏
	 */
	@RequiresPermissions("exam:examUserCollectionQuestion:remove")
	@Log(title = "我的收藏", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(examUserCollectionQuestionService.deleteByIds(ids));
	}
	
}
