package com.ruoyi.agile.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.agile.domain.GenTable;
import com.ruoyi.agile.service.IGenTableService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.ExcelUtil;

/**
 * 代码生成 信息操作处理
 *
 * @author zhujj
 * @date 2018-11-29
 */
@RestController
@RequestMapping("/genTable")
public class GenTableController extends BaseController {
    private String prefix = "agile/genTable";

    @Autowired
    private IGenTableService genTableService;

    @GetMapping()
    public String genTable() {
        return prefix + "/genTable";
    }

    /**
     * 查询代码生成列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GenTable genTable) {
        List<GenTable> list = genTableService.selectListByPage(genTable);
        return getDataTable(list);
    }


    /**
     * 导出代码生成列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GenTable genTable) {
        List<GenTable> list = genTableService.selectList(genTable);
        ExcelUtil<GenTable> util = new ExcelUtil<GenTable>(GenTable.class);
        return util.exportExcel(list, "genTable");
    }

    /**
     * 新增代码生成
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存代码生成
     */
    @Log(title = "代码生成", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GenTable genTable) {

        return toAjax(genTableService.insert(genTable));
    }

    /**
     * 修改代码生成
     */
    @GetMapping("/edit/{tableName}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        GenTable genTable = genTableService.selectById(id);
        mmap.put("genTable", genTable);
        return prefix + "/edit";
    }

    /**
     * 修改保存代码生成
     */
    @Log(title = "代码生成", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GenTable genTable) {
        return toAjax(genTableService.updateById(genTable));
    }

    /**
     * 删除代码生成
     */
    @Log(title = "代码生成", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        genTableService.deleteByIds(ids);
//        return toAjax(genTableService.deleteGenTableByIds(ids));
        return null;
    }


}
