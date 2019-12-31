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
import com.ruoyi.exam.domain.ExamQuestionItem;
import com.ruoyi.exam.service.IExamQuestionItemService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.ExcelUtil;

/**
 * 问题选项 信息操作处理
 * 
 * @author zhujj
 * @date 2018-12-07
 */
@Controller
@RequestMapping("/exam/examQuestionItem")
public class ExamQuestionItemController extends BaseController
{
    private String prefix = "exam/examQuestionItem";
	
	@Autowired
	private IExamQuestionItemService examQuestionItemService;
	
	@RequiresPermissions("exam:examQuestionItem:view")
	@GetMapping()
	public String examQuestionItem()
	{
	    return prefix + "/examQuestionItem";
	}
	
	/**
	 * 查询问题选项列表
	 */
	@RequiresPermissions("exam:examQuestionItem:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ExamQuestionItem examQuestionItem)
	{
        List<ExamQuestionItem> list = examQuestionItemService.selectExamQuestionItemPage(examQuestionItem);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出问题选项列表
	 */
	@RequiresPermissions("exam:examQuestionItem:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ExamQuestionItem examQuestionItem)
    {
    	List<ExamQuestionItem> list = examQuestionItemService.selectExamQuestionItemList(examQuestionItem);
        ExcelUtil<ExamQuestionItem> util = new ExcelUtil<ExamQuestionItem>(ExamQuestionItem.class);
        return util.exportExcel(list, "examQuestionItem");
    }
	
	/**
	 * 新增问题选项
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存问题选项
	 */
	@RequiresPermissions("exam:examQuestionItem:add")
	@Log(title = "问题选项", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ExamQuestionItem examQuestionItem)
	{		
		return toAjax(examQuestionItemService.insert(examQuestionItem));
	}

	/**
	 * 修改问题选项
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		ExamQuestionItem examQuestionItem = examQuestionItemService.selectById(id);
		mmap.put("examQuestionItem", examQuestionItem);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存问题选项
	 */
	@RequiresPermissions("exam:examQuestionItem:edit")
	@Log(title = "问题选项", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ExamQuestionItem examQuestionItem)
	{		
		return toAjax(examQuestionItemService.updateById(examQuestionItem));
	}
	
	/**
	 * 删除问题选项
	 */
	@RequiresPermissions("exam:examQuestionItem:remove")
	@Log(title = "问题选项", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(examQuestionItemService.deleteByIds(ids));
	}
	
}
