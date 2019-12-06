package com.ruoyi.agile.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.agile.mapper.GenTableColumnMapper;
import com.ruoyi.agile.domain.GenTableColumn;
import com.ruoyi.agile.service.IGenTableColumnService;
import com.ruoyi.common.support.Convert;

/**
 * 代码生成列 服务层实现
 * 
 * @author zhujj
 * @date 2018-11-29
 */
@Service
public class GenTableColumnServiceImpl implements IGenTableColumnService 
{
	@Autowired
	private GenTableColumnMapper genTableColumnMapper;

	/**
     * 查询代码生成列信息
     * 
     * @param id 代码生成列ID
     * @return 代码生成列信息
     */
    @Override
	public GenTableColumn selectGenTableColumnById(String id)
	{
	    return genTableColumnMapper.selectGenTableColumnById(id);
	}
	
	/**
     * 查询代码生成列列表
     * 
     * @param genTableColumn 代码生成列信息
     * @return 代码生成列集合
     */
	@Override
	public List<GenTableColumn> selectGenTableColumnList(GenTableColumn genTableColumn)
	{
	    return genTableColumnMapper.selectGenTableColumnList(genTableColumn);
	}
	
    /**
     * 新增代码生成列
     * 
     * @param genTableColumn 代码生成列信息
     * @return 结果
     */
	@Override
	public int insertGenTableColumn(GenTableColumn genTableColumn)
	{
	    return genTableColumnMapper.insertGenTableColumn(genTableColumn);
	}
	
	/**
     * 修改代码生成列
     * 
     * @param genTableColumn 代码生成列信息
     * @return 结果
     */
	@Override
	public int updateGenTableColumn(GenTableColumn genTableColumn)
	{
	    return genTableColumnMapper.updateGenTableColumn(genTableColumn);
	}

	/**
     * 删除代码生成列对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteGenTableColumnByIds(String ids)
	{
		return genTableColumnMapper.deleteGenTableColumnByIds(Convert.toStrArray(ids));
	}
	
}
