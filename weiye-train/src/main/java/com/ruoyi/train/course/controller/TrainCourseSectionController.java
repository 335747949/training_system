package com.ruoyi.train.course.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ExcelUtil;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.train.course.domain.TrainCourse;
import com.ruoyi.train.course.domain.TrainCourseSection;
import com.ruoyi.train.course.domain.TrainCourseSectionVO;
import com.ruoyi.train.course.service.ITrainCourseDirectoryService;
import com.ruoyi.train.course.service.ITrainCourseSectionService;
import com.ruoyi.train.course.service.ITrainCourseService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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
	private ITrainCourseDirectoryService trainCourseDirectoryService;

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
		List<TrainCourseSectionVO> list = trainCourseSectionService.selectTrainCourseSectionPage(trainCourseSection);
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
	@Transactional(rollbackFor = Exception.class)
	public AjaxResult addSave(TrainCourseSectionVO trainCourseSectionVO)
	{
		// 添加章节是校验是否已添加父章节，大章节无需校验
		if (100 != trainCourseSectionVO.getDirectoryParentId()) {
			TrainCourseSection condition = new TrainCourseSection();
			condition.setTrainCourseId(trainCourseSectionVO.getTrainCourseId());
			condition.setDirectoryId(trainCourseSectionVO.getDirectoryParentId());
			condition.setDelFlag("0");
			List<TrainCourseSection> list = trainCourseSectionService.selectList(condition);
			if (list.isEmpty()) {
				return error("请先添加父章节课程内容！");
			}
		} else {
			trainCourseSectionVO.setCourseware("");
		}
		// 相同章节重复性校验
		TrainCourseSection condition = new TrainCourseSection();
		condition.setTrainCourseId(trainCourseSectionVO.getTrainCourseId());
		condition.setDirectoryId(trainCourseSectionVO.getDirectoryId());
		condition.setDelFlag("0");
		List<TrainCourseSection> list = trainCourseSectionService.selectList(condition);
		if (!list.isEmpty()) {
			return error("此目录下已添加课程内容，请重新选择目录！");
		}
		//
		String courseware = trainCourseSectionVO.getCourseware();
		String[] coursewares = courseware.split(",");
		List<String> coursewareList = Arrays.asList(coursewares);
		int orderNum = trainCourseSectionVO.getOrderNum();
		String courseName = trainCourseSectionVO.getName();
		int count = 1;
		if (coursewareList.size()>1){
			for (String item : coursewares){
				TrainCourseSection courseSection = new TrainCourseSection();
				BeanUtils.copyProperties(trainCourseSectionVO,courseSection);
				courseSection.setOrderNum(orderNum);
				courseSection.setCourseware(item);
				courseSection.setName(courseName + "("+ count +")");
				trainCourseSectionService.insertSelective(courseSection);
				orderNum++;
				count++;
			}
			return AjaxResult.success(courseware.length());
		}else {
			TrainCourseSection trainCourseSection = new TrainCourseSection();
			BeanUtils.copyProperties(trainCourseSectionVO,trainCourseSection);
			return toAjax(trainCourseSectionService.insertSelective(trainCourseSection));
		}

	}

	/**
	 * 修改课程章节
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		TrainCourseSectionVO trainCourseSectionVO = trainCourseSectionService.selectTrainCourseVOById(id);

		TrainCourse trainCourse = trainCourseService.selectById( trainCourseSectionVO.getTrainCourseId() );
		mmap.put( "trainCourse", trainCourse );
		mmap.put("trainCourseSection", trainCourseSectionVO);
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
