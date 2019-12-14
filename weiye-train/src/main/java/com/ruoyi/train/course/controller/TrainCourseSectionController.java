package com.ruoyi.train.course.controller;

import java.util.List;

import com.ruoyi.train.course.domain.TrainCourse;
import com.ruoyi.train.course.domain.TrainCourseSection;
import com.ruoyi.train.course.service.ITrainCourseSectionService;
import com.ruoyi.train.course.service.ITrainCourseService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.ExcelUtil;

/**
 * 课程章节 信息操作处理
 *
 * @author zhujj
 * @date 2018-12-23
 */
@Controller
@RequestMapping("/train/trainCourseSection")
public class TrainCourseSectionController extends BaseController
{
	private String prefix = "train/course/trainCourseSection";

	@Autowired
	private ITrainCourseSectionService trainCourseSectionService;

	@Autowired
	private ITrainCourseService trainCourseService;
	@RequiresPermissions("train:trainCourseSection:view")
	@GetMapping()
	public String trainCourseSection(String trainCourseId, ModelMap mmap)
	{
		mmap.put("trainCourseId", trainCourseId);
		return prefix + "/trainCourseSection";
	}

	/**
	 * 查询课程章节列表
	 */
	@RequiresPermissions("train:trainCourseSection:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TrainCourseSection trainCourseSection)
	{
		List<TrainCourseSection> list = trainCourseSectionService.selectTrainCourseSectionPage(trainCourseSection);
		return getDataTable(list);
	}


	/**
	 * 导出课程章节列表
	 */
	@RequiresPermissions("train:trainCourseSection:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(TrainCourseSection trainCourseSection)
	{
		List<TrainCourseSection> list = trainCourseSectionService.selectTrainCourseSectionList(trainCourseSection);
		ExcelUtil<TrainCourseSection> util = new ExcelUtil<TrainCourseSection>(TrainCourseSection.class);
		return util.exportExcel(list, "trainCourseSection");
	}

	/**
	 * 新增课程章节
	 */
	@GetMapping("/add/{id}")
	public String add(@PathVariable("id") Integer id, ModelMap mmap)
	{
		TrainCourse trainCourse = trainCourseService.selectById( id );
		mmap.put( "trainCourse", trainCourse );
		return prefix + "/add";
	}

	/**
	 * 新增保存课程章节
	 */
	@RequiresPermissions("train:trainCourseSection:add")
	@Log(title = "课程章节", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TrainCourseSection trainCourseSection)
	{
		return toAjax(trainCourseSectionService.insertSelective(trainCourseSection));
	}

	/**
	 * 修改课程章节
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		TrainCourseSection trainCourseSection = trainCourseSectionService.selectById(id);

		TrainCourse trainCourse = trainCourseService.selectById( trainCourseSection.getTrainCourseId() );
		mmap.put( "trainCourse", trainCourse );
		mmap.put("trainCourseSection", trainCourseSection);
		return prefix + "/edit";
	}

	/**
	 * 修改保存课程章节
	 */
	@RequiresPermissions("train:trainCourseSection:edit")
	@Log(title = "课程章节", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TrainCourseSection trainCourseSection)
	{
		return toAjax(trainCourseSectionService.updateById(trainCourseSection));
	}

	/**
	 * 删除课程章节
	 */
	@RequiresPermissions("train:trainCourseSection:remove")
	@Log(title = "课程章节", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		return toAjax(trainCourseSectionService.deleteByIds(ids));
	}

}
