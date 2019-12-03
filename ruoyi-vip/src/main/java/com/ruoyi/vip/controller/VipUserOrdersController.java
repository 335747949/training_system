package com.ruoyi.vip.controller;

import java.util.List;

import com.ruoyi.vip.domain.vo.VipUserOrdersVO;
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
import com.ruoyi.vip.domain.VipUserOrders;
import com.ruoyi.vip.service.IVipUserOrdersService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.ExcelUtil;

/**
 * 我的订单 信息操作处理
 * 
 * @author zhujj
 * @date 2019-01-25
 */
@Controller
@RequestMapping("/vip/vipUserOrders")
public class VipUserOrdersController extends BaseController
{
    private String prefix = "vip/vipUserOrders";
	
	@Autowired
	private IVipUserOrdersService vipUserOrdersService;
	
	@RequiresPermissions("vip:vipUserOrders:view")
	@GetMapping()
	public String vipUserOrders()
	{
	    return prefix + "/vipUserOrders";
	}
	
	/**
	 * 查询我的订单列表
	 */
	@RequiresPermissions("vip:vipUserOrders:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(VipUserOrdersVO vipUserOrders)
	{
        List<VipUserOrdersVO> list = vipUserOrdersService.selectVipUserOrdersPage(vipUserOrders);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出我的订单列表
	 */
	@RequiresPermissions("vip:vipUserOrders:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(VipUserOrdersVO vipUserOrders)
    {
    	List<VipUserOrdersVO> list = vipUserOrdersService.selectVipUserOrdersList(vipUserOrders);
        ExcelUtil<VipUserOrdersVO> util = new ExcelUtil<VipUserOrdersVO>(VipUserOrdersVO.class);
        return util.exportExcel(list, "vipUserOrders");
    }
	
	/**
	 * 新增我的订单
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存我的订单
	 */
	@RequiresPermissions("vip:vipUserOrders:add")
	@Log(title = "我的订单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(VipUserOrders vipUserOrders)
	{		
		return toAjax(vipUserOrdersService.insert(vipUserOrders));
	}

	/**
	 * 修改我的订单
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		VipUserOrders vipUserOrders = vipUserOrdersService.selectById(id);
		mmap.put("vipUserOrders", vipUserOrders);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存我的订单
	 */
	@RequiresPermissions("vip:vipUserOrders:edit")
	@Log(title = "我的订单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(VipUserOrders vipUserOrders)
	{		
		return toAjax(vipUserOrdersService.updateById(vipUserOrders));
	}
	
	/**
	 * 删除我的订单
	 */
	@RequiresPermissions("vip:vipUserOrders:remove")
	@Log(title = "我的订单", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(vipUserOrdersService.deleteByIds(ids));
	}
	
}
