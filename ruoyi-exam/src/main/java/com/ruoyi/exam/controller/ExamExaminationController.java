package com.ruoyi.exam.controller;

import java.util.Date;
import java.util.List;

import com.ruoyi.framework.web.util.ShiroUtils;
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
import com.ruoyi.exam.domain.ExamExamination;
import com.ruoyi.exam.service.IExamExaminationService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.ExcelUtil;

/**
 * 考试 信息操作处理
 * 
 * @author zhujj
 * @date 2018-12-24
 */
@Controller
@RequestMapping("/exam/examExamination")
public class ExamExaminationController extends BaseController
{
    private String prefix = "exam/examExamination";
	
	@Autowired
	private IExamExaminationService examExaminationService;
	
	@RequiresPermissions("exam:examExamination:view")
	@GetMapping()
	public String examExamination()
	{
	    return prefix + "/examExamination";
	}
	
	/**
	 * 查询考试列表
	 */
	@RequiresPermissions("exam:examExamination:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ExamExamination examExamination)
	{
        List<ExamExamination> list = examExaminationService.selectExamExaminationPage(examExamination);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出考试列表
	 */
	@RequiresPermissions("exam:examExamination:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ExamExamination examExamination)
    {
    	List<ExamExamination> list = examExaminationService.selectExamExaminationList(examExamination);
        ExcelUtil<ExamExamination> util = new ExcelUtil<ExamExamination>(ExamExamination.class);
        return util.exportExcel(list, "examExamination");
    }
	
	/**
	 * 新增考试
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存考试
	 */
	@RequiresPermissions("exam:examExamination:add")
	@Log(title = "考试", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ExamExamination examExamination)
	{
		examExamination.setDelFlag("0");
		examExamination.setCreateDate(new Date());
		examExamination.setCreateBy(ShiroUtils.getLoginName());
		return toAjax(examExaminationService.insert(examExamination));
	}

	/**
	 * 修改考试
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		ExamExamination examExamination = examExaminationService.selectById(id);
		mmap.put("examExamination", examExamination);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存考试
	 */
	@RequiresPermissions("exam:examExamination:edit")
	@Log(title = "考试", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ExamExamination examExamination)
	{
		examExamination.setDelFlag("0");
		examExamination.setUpdateDate(new Date());
		examExamination.setUpdateBy(ShiroUtils.getLoginName());
		return toAjax(examExaminationService.updateSelectiveById(examExamination));
	}
	
	/**
	 * 删除考试
	 */
	@RequiresPermissions("exam:examExamination:remove")
	@Log(title = "考试", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(examExaminationService.deleteByIds(ids));
	}
	
}
