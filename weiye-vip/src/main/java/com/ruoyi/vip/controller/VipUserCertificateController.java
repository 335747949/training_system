package com.ruoyi.vip.controller;

import java.util.List;

import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.vip.domain.vo.VipUserCertificateVO;
import org.apache.ibatis.annotations.Param;
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
import com.ruoyi.vip.domain.VipUserCertificate;
import com.ruoyi.vip.service.IVipUserCertificateService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.ExcelUtil;

/**
 * 证书管理 信息操作处理
 *
 * @author zhujj
 * @date 2019-01-15
 */
@Controller
@RequestMapping("/vip/vipUserCertificate")
public class VipUserCertificateController extends BaseController
{
	private String prefix = "vip/vipUserCertificate";
	@Autowired
	private ISysUserService userService;
	@Autowired
	private IVipUserCertificateService vipUserCertificateService;

	@RequiresPermissions("vip:vipUserCertificate:view")
	@GetMapping()
	public String vipUserCertificate()
	{
		return prefix + "/vipUserCertificate";
	}

	/**
	 * 查询证书管理列表
	 */
	@RequiresPermissions("vip:vipUserCertificate:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(VipUserCertificateVO vipUserCertificate)
	{
		List<VipUserCertificateVO> list = vipUserCertificateService.selectVipUserCertificatePage(vipUserCertificate);
		return getDataTable(list);
	}


	/**
	 * 导出证书管理列表
	 */
	@RequiresPermissions("vip:vipUserCertificate:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(VipUserCertificateVO vipUserCertificate)
	{
		List<ExcelVipUserCertification> list = vipUserCertificateService.selectVipUserCertificateListFroExcel(vipUserCertificate);
		ExcelUtil<ExcelVipUserCertification> util = new ExcelUtil<ExcelVipUserCertification>(ExcelVipUserCertification.class);
		return util.exportExcel(list, "vipUserCertificate");
	}

	/**
	 * 新增证书管理
	 */
	@GetMapping("/add")
	public String add(@Param("vipUserId") Long  vipUserId, ModelMap mmap)
	{
		SysUser sysUser = userService.selectUserById(vipUserId);
		mmap.put("user", sysUser);
		return prefix + "/add";
	}

	/**
	 * 新增保存证书管理
	 */
	@RequiresPermissions("vip:vipUserCertificate:add")
	@Log(title = "证书管理", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(VipUserCertificate vipUserCertificate)
	{
		vipUserCertificate.setDelFlag( "0" );
		return toAjax(vipUserCertificateService.insert(vipUserCertificate));
	}

	/**
	 * 修改证书管理
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		VipUserCertificate vipUserCertificate = vipUserCertificateService.selectById(id);
		SysUser sysUser = userService.selectUserById(vipUserCertificate.getVipUserId().longValue());
		mmap.put("vipUserCertificate", vipUserCertificate);
		mmap.put("user", sysUser);
		return prefix + "/edit";
	}

	/**
	 * 修改保存证书管理
	 */
	@RequiresPermissions("vip:vipUserCertificate:edit")
	@Log(title = "证书管理", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(VipUserCertificate vipUserCertificate)
	{
		return toAjax(vipUserCertificateService.updateSelectiveById(vipUserCertificate));
	}

	/**
	 * 删除证书管理
	 */
	@RequiresPermissions("vip:vipUserCertificate:remove")
	@Log(title = "证书管理", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		return toAjax(vipUserCertificateService.deleteByIds(ids));
	}

}
