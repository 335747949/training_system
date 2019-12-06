package com.ruoyi.agile.mapper;

import com.ruoyi.agile.domain.GenTableColumn;
import com.ruoyi.framework.web.base.MyMapper;
import com.ruoyi.generator.domain.TableInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 代码生成列 数据层
 * 
 * @author zhujj
 * @date 2018-11-29
 */
public interface GenTableColumnMapper  extends MyMapper<TableInfo>
{
	/**
     * 查询代码生成列信息
     * 
     * @param id 代码生成列ID
     * @return 代码生成列信息
     */
	public GenTableColumn selectGenTableColumnById(String id);
	
	/**
     * 查询代码生成列列表
     * 
     * @param genTableColumn 代码生成列信息
     * @return 代码生成列集合
     */
	public List<GenTableColumn> selectGenTableColumnList(GenTableColumn genTableColumn);
	
	/**
     * 新增代码生成列
     * 
     * @param genTableColumn 代码生成列信息
     * @return 结果
     */
	public int insertGenTableColumn(GenTableColumn genTableColumn);
	
	/**
     * 修改代码生成列
     * 
     * @param genTableColumn 代码生成列信息
     * @return 结果
     */
	public int updateGenTableColumn(GenTableColumn genTableColumn);
	
	/**
     * 删除代码生成列
     * 
     * @param id 代码生成列ID
     * @return 结果
     */
	public int deleteGenTableColumnById(String id);
	
	/**
     * 批量删除代码生成列
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteGenTableColumnByIds(String[] ids);
	
}