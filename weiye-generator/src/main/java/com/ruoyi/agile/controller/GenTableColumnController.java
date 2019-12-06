package com.ruoyi.agile.controller;

import java.util.List;
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
import com.ruoyi.agile.domain.GenTableColumn;
import com.ruoyi.agile.service.IGenTableColumnService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.ExcelUtil;

/**
 * 代码生成列 信息操作处理
 * 
 * @author zhujj
 * @date 2018-11-29
 */
@Controller
@RequestMapping("/agile/genTableColumn")
public class GenTableColumnController extends BaseController
{
    private String prefix = "agile/genTableColumn";
	
	@Autowired
	private IGenTableColumnService genTableColumnService;
	
	@RequiresPermissions("agile:genTableColumn:view")
	@GetMapping()
	public String genTableColumn()
	{
	    return prefix + "/genTableColumn";
	}
	
	/**
	 * 查询代码生成列列表
	 */
	@RequiresPermissions("agile:genTableColumn:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(GenTableColumn genTableColumn)
	{
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnList(genTableColumn);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出代码生成列列表
	 */
	@RequiresPermissions("agile:genTableColumn:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GenTableColumn genTableColumn)
    {
    	List<GenTableColumn> list = genTableColumnService.selectGenTableColumnList(genTableColumn);
        ExcelUtil<GenTableColumn> util = new ExcelUtil<GenTableColumn>(GenTableColumn.class);
        return util.exportExcel(list, "genTableColumn");
    }
	
	/**
	 * 新增代码生成列
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存代码生成列
	 */
	@RequiresPermissions("agile:genTableColumn:add")
	@Log(title = "代码生成列", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(GenTableColumn genTableColumn)
	{		
		return toAjax(genTableColumnService.insertGenTableColumn(genTableColumn));
	}

	/**
	 * 修改代码生成列
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		GenTableColumn genTableColumn = genTableColumnService.selectGenTableColumnById(id);
		mmap.put("genTableColumn", genTableColumn);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存代码生成列
	 */
	@RequiresPermissions("agile:genTableColumn:edit")
	@Log(title = "代码生成列", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(GenTableColumn genTableColumn)
	{		
		return toAjax(genTableColumnService.updateGenTableColumn(genTableColumn));
	}
	
	/**
	 * 删除代码生成列
	 */
	@RequiresPermissions("agile:genTableColumn:remove")
	@Log(title = "代码生成列", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(genTableColumnService.deleteGenTableColumnByIds(ids));
	}
	
}
