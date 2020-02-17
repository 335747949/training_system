package com.ruoyi.train.course.controller;

import java.util.List;

import com.ruoyi.system.domain.SysUser;
import com.ruoyi.train.course.domain.Banner;
import com.ruoyi.train.course.service.IBannerService;
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
 * banner 信息操作处理
 * 
 * @author zhujj
 * @date 2020-02-04
 */
@Controller
@RequestMapping("/train/course/banner")
public class BannerController extends BaseController
{
    private String prefix = "train/course/banner";
	
	@Autowired
	private IBannerService bannerService;
	
	@RequiresPermissions("train:banner:view")
	@GetMapping()
	public String banner()
	{
	    return prefix + "/banner";
	}
	
	/**
	 * 查询banner列表
	 */
	@RequiresPermissions("train:banner:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Banner banner)
	{
        List<Banner> list = bannerService.selectBannerPage(banner);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出banner列表
	 */
	@RequiresPermissions("train:banner:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Banner banner)
    {
    	List<Banner> list = bannerService.selectBannerList(banner);
        ExcelUtil<Banner> util = new ExcelUtil<Banner>(Banner.class);
        return util.exportExcel(list, "banner");
    }
	
	/**
	 * 新增banner
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存banner
	 */
	@RequiresPermissions("train:banner:add")
	@Log(title = "banner", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Banner banner)
	{
		banner.setDelFlag(0);
		return toAjax(bannerService.insert(banner));
	}

	/**
	 * 修改banner
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Banner banner = bannerService.selectById(id);
		mmap.put("banner", banner);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存banner
	 */
	@RequiresPermissions("train:banner:edit")
	@Log(title = "banner", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Banner banner)
	{		
		return toAjax(bannerService.updateById(banner));
	}
	
	/**
	 * 删除banner
	 */
	@RequiresPermissions("train:banner:remove")
	@Log(title = "banner", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(bannerService.deleteByIds(ids));
	}

	/**
	 * 校验banner名称
	 */
	@PostMapping("/checkBannerNameUnique")
	@ResponseBody
	public String checkBannerNameUnique(Banner banner) {
		return bannerService.checkBannerNameUnique( banner );
	}
	
}
