package com.ruoyi.train.course.controller;


import java.util.List;

import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.train.course.domain.TrainCourseUser;
import com.ruoyi.train.course.service.ITrainCourseUserService;
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
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.ExcelUtil;

/**
 * 课程使用对象 信息操作处理
 * 
 * @author zhujj
 * @date 2018-12-23
 */
@Controller
@RequestMapping("/train/trainCourseUser")
public class TrainCourseUserController extends BaseController
{
    private String prefix = "train/trainCourseUser";
	
	@Autowired
	private ITrainCourseUserService trainCourseUserService;
	
	@RequiresPermissions("train:trainCourseUser:view")
	@GetMapping()
	public String trainCourseUser()
	{
	    return prefix + "/trainCourseUser";
	}
	
	/**
	 * 查询课程使用对象列表
	 */
	@RequiresPermissions("train:trainCourseUser:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TrainCourseUser trainCourseUser)
	{
        List<TrainCourseUser> list = trainCourseUserService.selectTrainCourseUserPage(trainCourseUser);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出课程使用对象列表
	 */
	@RequiresPermissions("train:trainCourseUser:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TrainCourseUser trainCourseUser)
    {
    	List<TrainCourseUser> list = trainCourseUserService.selectTrainCourseUserList(trainCourseUser);
        ExcelUtil<TrainCourseUser> util = new ExcelUtil<TrainCourseUser>(TrainCourseUser.class);
        return util.exportExcel(list, "trainCourseUser");
    }
	
	/**
	 * 新增课程使用对象
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存课程使用对象
	 */
	@RequiresPermissions("train:trainCourseUser:add")
	@Log(title = "课程使用对象", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TrainCourseUser trainCourseUser)
	{		
		return toAjax(trainCourseUserService.insert(trainCourseUser));
	}

	/**
	 * 修改课程使用对象
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		TrainCourseUser trainCourseUser = trainCourseUserService.selectById(id);
		mmap.put("trainCourseUser", trainCourseUser);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存课程使用对象
	 */
	@RequiresPermissions("train:trainCourseUser:edit")
	@Log(title = "课程使用对象", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TrainCourseUser trainCourseUser)
	{		
		return toAjax(trainCourseUserService.updateById(trainCourseUser));
	}
	
	/**
	 * 删除课程使用对象
	 */
	@RequiresPermissions("train:trainCourseUser:remove")
	@Log(title = "课程使用对象", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(trainCourseUserService.deleteByIds(ids));
	}
	
}
