package com.ruoyi.exam.controller;

import java.util.List;
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
import com.ruoyi.exam.domain.ExamQuestionComment;
import com.ruoyi.exam.service.IExamQuestionCommentService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.ExcelUtil;

/**
 * 问题点评 信息操作处理
 * 
 * @author zhujj
 * @date 2018-12-07
 */
@Controller
@RequestMapping("/exam/examQuestionComment")
public class ExamQuestionCommentController extends BaseController
{
    private String prefix = "exam/examQuestionComment";
	
	@Autowired
	private IExamQuestionCommentService examQuestionCommentService;
	
	@RequiresPermissions("exam:examQuestionComment:view")
	@GetMapping()
	public String examQuestionComment()
	{
	    return prefix + "/examQuestionComment";
	}
	
	/**
	 * 查询问题点评列表
	 */
	@RequiresPermissions("exam:examQuestionComment:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ExamQuestionComment examQuestionComment)
	{
        List<ExamQuestionComment> list = examQuestionCommentService.selectExamQuestionCommentPage(examQuestionComment);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出问题点评列表
	 */
	@RequiresPermissions("exam:examQuestionComment:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ExamQuestionComment examQuestionComment)
    {
    	List<ExamQuestionComment> list = examQuestionCommentService.selectExamQuestionCommentList(examQuestionComment);
        ExcelUtil<ExamQuestionComment> util = new ExcelUtil<ExamQuestionComment>(ExamQuestionComment.class);
        return util.exportExcel(list, "examQuestionComment");
    }
	
	/**
	 * 新增问题点评
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存问题点评
	 */
	@RequiresPermissions("exam:examQuestionComment:add")
	@Log(title = "问题点评", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ExamQuestionComment examQuestionComment)
	{		
		return toAjax(examQuestionCommentService.insert(examQuestionComment));
	}

	/**
	 * 修改问题点评
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		ExamQuestionComment examQuestionComment = examQuestionCommentService.selectById(id);
		mmap.put("examQuestionComment", examQuestionComment);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存问题点评
	 */
	@RequiresPermissions("exam:examQuestionComment:edit")
	@Log(title = "问题点评", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ExamQuestionComment examQuestionComment)
	{		
		return toAjax(examQuestionCommentService.updateById(examQuestionComment));
	}
	
	/**
	 * 删除问题点评
	 */
	@RequiresPermissions("exam:examQuestionComment:remove")
	@Log(title = "问题点评", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(examQuestionCommentService.deleteByIds(ids));
	}
	
}
