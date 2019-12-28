package com.ruoyi.train.course.controller;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.train.course.domain.TrainCourse;
import com.ruoyi.train.course.domain.TrainCourseCategory;
import com.ruoyi.train.course.domain.TrainCourseVO;
import com.ruoyi.train.course.service.ITrainCourseCategoryService;
import com.ruoyi.train.course.service.ITrainCourseService;
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
 * 课程 信息操作处理
 *
 * @author zhujj
 * @date 2018-12-23
 */
@Controller
@RequestMapping("/train/trainCourse")
public class TrainCourseController extends BaseController
{
	private String prefix = "train/course/trainCourse";
	@Autowired
	private ITrainCourseService trainCourseService;

	@Autowired
	private ITrainCourseCategoryService trainCourseCategoryService;
	@RequiresPermissions("train:trainCourse:view")
	@GetMapping()
	public String trainCourse()
	{
		return prefix + "/trainCourse";
	}

	/**
	 * 查询课程列表
	 */
	@RequiresPermissions("train:trainCourse:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TrainCourseVO trainCourse)
	{
		List<TrainCourseVO> list = trainCourseService.selectTrainCoursePage(trainCourse);
		return getDataTable(list);
	}


	/**
	 * 导出课程列表
	 */
	@RequiresPermissions("train:trainCourse:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(TrainCourseVO trainCourse)
	{
		List<TrainCourse> list = trainCourseService.selectTrainCourseListForExcel(trainCourse);
		ExcelUtil<TrainCourse> util = new ExcelUtil<TrainCourse>(TrainCourse.class);
		return util.exportExcel(list, "trainCourse");
	}

	/**
	 * 新增课程
	 */
	@GetMapping("/add")
	public String add()
	{
		return prefix + "/add";
	}

	/**
	 * 新增保存课程
	 */
	@RequiresPermissions("train:trainCourse:add")
	@Log(title = "课程", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TrainCourse trainCourse)
	{
		trainCourse.setPrice(BigDecimal.ZERO);
		trainCourse.setDelFlag("0");
		return toAjax(trainCourseService.insert(trainCourse));
	}

	/**
	 * 修改课程
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		TrainCourse trainCourse = trainCourseService.selectById(id);
		TrainCourseCategory courseCategory = trainCourseCategoryService.selectCategoryById( (long) trainCourse.getTrainCourseCategoryId() );
		mmap.put("trainCourse", trainCourse);
		mmap.put("category", courseCategory);
		return prefix + "/edit";
	}

	/**
	 * 修改保存课程
	 */
	@RequiresPermissions("train:trainCourse:edit")
	@Log(title = "课程", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TrainCourse trainCourse)
	{
		return toAjax(trainCourseService.updateSelectiveById(trainCourse));
	}

	/**
	 * 删除课程
	 */
	@RequiresPermissions("train:trainCourse:remove")
	@Log(title = "课程", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		return toAjax(trainCourseService.deleteByIds(ids));
	}

}
