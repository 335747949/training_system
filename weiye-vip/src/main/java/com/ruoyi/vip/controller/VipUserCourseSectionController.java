package com.ruoyi.vip.controller;

import java.util.List;

import com.ruoyi.vip.domain.vo.VipUserCourseSectionVO;
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
import com.ruoyi.vip.domain.VipUserCourseSection;
import com.ruoyi.vip.service.IVipUserCourseSectionService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.ExcelUtil;

/**
 * 我的课程学习 信息操作处理
 *
 * @author zhujj
 * @date 2019-01-15
 */
@Controller
@RequestMapping("/vip/vipUserCourseSection")
public class VipUserCourseSectionController extends BaseController
{
	private String prefix = "vip/vipUserCourseSection";

	@Autowired
	private IVipUserCourseSectionService vipUserCourseSectionService;

	@RequiresPermissions("vip:vipUserCourseSection:view")
	@GetMapping()
	public String vipUserCourseSection()
	{
		return prefix + "/vipUserCourseSection";
	}

	/**
	 * 查询我的课程学习列表
	 */
	@RequiresPermissions("vip:vipUserCourseSection:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(VipUserCourseSectionVO vipUserCourseSection)
	{
		List<VipUserCourseSectionVO> list = vipUserCourseSectionService.selectVipUserCourseSectionPage(vipUserCourseSection);
		return getDataTable(list);
	}


	/**
	 * 导出我的课程学习列表
	 */
	@RequiresPermissions("vip:vipUserCourseSection:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(VipUserCourseSectionVO vipUserCourseSection)
	{
		List<VipUserCourseSectionVO> list = vipUserCourseSectionService.selectVipUserCourseSectionList(vipUserCourseSection);
		ExcelUtil<VipUserCourseSectionVO> util = new ExcelUtil<VipUserCourseSectionVO>(VipUserCourseSectionVO.class);
		return util.exportExcel(list, "_学习进度");
	}

	/**
	 * 新增我的课程学习
	 */
	@GetMapping("/add")
	public String add()
	{
		return prefix + "/add";
	}

	/**
	 * 新增保存我的课程学习
	 */
	@RequiresPermissions("vip:vipUserCourseSection:add")
	@Log(title = "我的课程学习", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(VipUserCourseSection vipUserCourseSection)
	{	vipUserCourseSection.setDelFlag( "0" );
		return toAjax(vipUserCourseSectionService.insert(vipUserCourseSection));
	}

	/**
	 * 修改我的课程学习
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		VipUserCourseSection vipUserCourseSection = vipUserCourseSectionService.selectById(id);
		mmap.put("vipUserCourseSection", vipUserCourseSection);
		return prefix + "/edit";
	}

	/**
	 * 修改保存我的课程学习
	 */
	@RequiresPermissions("vip:vipUserCourseSection:edit")
	@Log(title = "我的课程学习", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(VipUserCourseSection vipUserCourseSection)
	{
		return toAjax(vipUserCourseSectionService.updateSelectiveById(vipUserCourseSection));
	}

	/**
	 * 删除我的课程学习
	 */
	@RequiresPermissions("vip:vipUserCourseSection:remove")
	@Log(title = "我的课程学习", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		return toAjax(vipUserCourseSectionService.deleteByIds(ids));
	}

}
