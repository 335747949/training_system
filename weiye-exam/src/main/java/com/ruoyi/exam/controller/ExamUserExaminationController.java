package com.ruoyi.exam.controller;

import java.util.List;
import java.util.Map;

import cn.hutool.core.util.StrUtil;
import com.ruoyi.exam.domain.ExamUserExaminationQuestionVO;
import com.ruoyi.exam.domain.ExamUserExaminationVO;
import com.ruoyi.exam.domain.ExcelUserExamination;
import com.ruoyi.framework.web.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.exam.domain.ExamUserExamination;
import com.ruoyi.exam.service.IExamUserExaminationService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.ExcelUtil;

/**
 * 我的考试记录 信息操作处理
 * 
 * @author zhujj
 * @date 2019-01-12
 */
@Controller
@RequestMapping("/exam/examUserExamination")
public class ExamUserExaminationController extends BaseController
{
    private String prefix = "exam/examUserExamination";
	
	@Autowired
	private IExamUserExaminationService examUserExaminationService;
	
	@RequiresPermissions("exam:examUserExamination:view")
	@GetMapping()
	public String examUserExamination()
	{
	    return prefix + "/examUserExamination";
	}
	
	/**
	 * 查询我的考试记录列表
	 */
	@RequiresPermissions("exam:examUserExamination:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam  Map<String,Object> map)
	{
        List<ExamUserExaminationVO> list = examUserExaminationService.selectMyExamUserExamination(map);
		return getDataTable(list);
	}

	@RequiresPermissions("exam:examUserExamination:view")
	@GetMapping("/info/{id}")
	public String info(@PathVariable("id") Integer id, ModelMap map)
	{
		map.put( "user", ShiroUtils.getLoginName() );
		ExamUserExaminationVO data = examUserExaminationService.selectDetailById( id );
		List<ExamUserExaminationQuestionVO> questions = data.getExamUserExaminationQuestions();
		int right = 0;
		int error = 0;
		int nullAnswer = 0;
		for (ExamUserExaminationQuestionVO question : questions) {
			if(StrUtil.isBlank(question.getUserAnswer())){
				nullAnswer++;
			}else if(question.getUserAnswer().equals(question.getAnswer())){
				right++;
			}else{
				error++;
			}
		}
		map.put( "data", data );
		map.put( "right", right );
		map.put( "error", error );
		map.put( "nullAnswer", nullAnswer );
		return prefix + "/info";
	}
	
	
	/**
	 * 导出我的考试记录列表
	 */
	@RequiresPermissions("exam:examUserExamination:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestParam  Map<String,Object> map)
    {
		List<ExcelUserExamination> list = examUserExaminationService.selectMyExamUserExaminationForExcel(map);
        ExcelUtil<ExcelUserExamination> util = new ExcelUtil<ExcelUserExamination>(ExcelUserExamination.class);
        return util.exportExcel(list, "_考试记录");
    }
	
	/**
	 * 新增我的考试记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存我的考试记录
	 */
	@RequiresPermissions("exam:examUserExamination:add")
	@Log(title = "我的考试记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ExamUserExamination examUserExamination)
	{		
		return toAjax(examUserExaminationService.insert(examUserExamination));
	}

	/**
	 * 修改我的考试记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		ExamUserExamination examUserExamination = examUserExaminationService.selectById(id);
		mmap.put("examUserExamination", examUserExamination);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存我的考试记录
	 */
	@RequiresPermissions("exam:examUserExamination:edit")
	@Log(title = "我的考试记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ExamUserExamination examUserExamination)
	{		
		return toAjax(examUserExaminationService.updateById(examUserExamination));
	}
	
	/**
	 * 删除我的考试记录
	 */
	@RequiresPermissions("exam:examUserExamination:remove")
	@Log(title = "我的考试记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(examUserExaminationService.deleteByIds(ids));
	}
	
}
