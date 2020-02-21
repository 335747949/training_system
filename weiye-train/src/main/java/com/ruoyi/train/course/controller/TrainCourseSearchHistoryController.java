package com.ruoyi.train.course.controller;

import java.util.List;

import com.ruoyi.train.course.domain.TrainCourseSearchHistory;
import com.ruoyi.train.course.service.ITrainCourseSearchHistoryService;
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
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.ExcelUtil;

/**
 * 用户课程搜索记录 信息操作处理
 * 
 * @author zhujj
 * @date 2020-02-06
 */
@Controller
@RequestMapping("/vip/trainCourseSearchHistory")
public class TrainCourseSearchHistoryController extends BaseController
{
    private String prefix = "vip/trainCourseSearchHistory";
	
	@Autowired
	private ITrainCourseSearchHistoryService trainCourseSearchHistoryService;
	
	@RequiresPermissions("vip:trainCourseSearchHistory:view")
	@GetMapping()
	public String trainCourseSearchHistory()
	{
	    return prefix + "/trainCourseSearchHistory";
	}
	
	/**
	 * 查询用户课程搜索记录列表
	 */
	@RequiresPermissions("vip:trainCourseSearchHistory:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TrainCourseSearchHistory trainCourseSearchHistory)
	{
        List<TrainCourseSearchHistory> list = trainCourseSearchHistoryService.selectTrainCourseSearchHistoryPage(trainCourseSearchHistory);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户课程搜索记录列表
	 */
	@RequiresPermissions("vip:trainCourseSearchHistory:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TrainCourseSearchHistory trainCourseSearchHistory)
    {
    	List<TrainCourseSearchHistory> list = trainCourseSearchHistoryService.selectTrainCourseSearchHistoryList(trainCourseSearchHistory);
        ExcelUtil<TrainCourseSearchHistory> util = new ExcelUtil<TrainCourseSearchHistory>(TrainCourseSearchHistory.class);
        return util.exportExcel(list, "trainCourseSearchHistory");
    }
	
	/**
	 * 新增用户课程搜索记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存用户课程搜索记录
	 */
	@RequiresPermissions("vip:trainCourseSearchHistory:add")
	@Log(title = "用户课程搜索记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TrainCourseSearchHistory trainCourseSearchHistory)
	{		
		return toAjax(trainCourseSearchHistoryService.insert(trainCourseSearchHistory));
	}

	/**
	 * 修改用户课程搜索记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		TrainCourseSearchHistory trainCourseSearchHistory = trainCourseSearchHistoryService.selectById(id);
		mmap.put("trainCourseSearchHistory", trainCourseSearchHistory);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存用户课程搜索记录
	 */
	@RequiresPermissions("vip:trainCourseSearchHistory:edit")
	@Log(title = "用户课程搜索记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TrainCourseSearchHistory trainCourseSearchHistory)
	{		
		return toAjax(trainCourseSearchHistoryService.updateById(trainCourseSearchHistory));
	}
	
	/**
	 * 删除用户课程搜索记录
	 */
	@RequiresPermissions("vip:trainCourseSearchHistory:remove")
	@Log(title = "用户课程搜索记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(trainCourseSearchHistoryService.deleteByIds(ids));
	}
	
}
