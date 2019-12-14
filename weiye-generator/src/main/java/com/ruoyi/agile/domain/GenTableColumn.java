package com.ruoyi.agile.domain;

import com.ruoyi.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 代码生成列表 gen_table_column
 * 
 * @author zhujj
 * @date 2018-11-29
 */
public class GenTableColumn extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Id
	private String id;
	/** 表名 */
	private String tableName;
	/** 列名 */
	private String columnName;
	/** 列排序（升序） */
	private BigDecimal columnSort;
	/** 类型 */
	private String columnType;
	/** 列标签名 */
	private String columnLabel;
	/** 列备注说明 */
	private String comments;
	/** 类的属性名 */
	private String attrName;
	/** 类的属性类型 */
	private String attrType;
	/** 是否主键 */
	private String isPk;
	/** 是否可为空 */
	private String isNull;
	/** 是否插入字段 */
	private String isInsert;
	/** 是否更新字段 */
	private String isUpdate;
	/** 是否列表字段 */
	private String isList;
	/** 是否查询字段 */
	private String isQuery;
	/** 查询方式 */
	private String queryType;
	/** 是否编辑字段 */
	private String isEdit;
	/** 表单类型 */
	private String showType;
	/** 其它生成选项 */
	private String options;

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getId() 
	{
		return id;
	}
	public void setTableName(String tableName) 
	{
		this.tableName = tableName;
	}

	public String getTableName() 
	{
		return tableName;
	}
	public void setColumnName(String columnName) 
	{
		this.columnName = columnName;
	}

	public String getColumnName() 
	{
		return columnName;
	}
	public void setColumnSort(BigDecimal columnSort) 
	{
		this.columnSort = columnSort;
	}

	public BigDecimal getColumnSort() 
	{
		return columnSort;
	}
	public void setColumnType(String columnType) 
	{
		this.columnType = columnType;
	}

	public String getColumnType() 
	{
		return columnType;
	}
	public void setColumnLabel(String columnLabel) 
	{
		this.columnLabel = columnLabel;
	}

	public String getColumnLabel() 
	{
		return columnLabel;
	}
	public void setComments(String comments) 
	{
		this.comments = comments;
	}

	public String getComments() 
	{
		return comments;
	}
	public void setAttrName(String attrName) 
	{
		this.attrName = attrName;
	}

	public String getAttrName() 
	{
		return attrName;
	}
	public void setAttrType(String attrType) 
	{
		this.attrType = attrType;
	}

	public String getAttrType() 
	{
		return attrType;
	}
	public void setIsPk(String isPk) 
	{
		this.isPk = isPk;
	}

	public String getIsPk() 
	{
		return isPk;
	}
	public void setIsNull(String isNull) 
	{
		this.isNull = isNull;
	}

	public String getIsNull() 
	{
		return isNull;
	}
	public void setIsInsert(String isInsert) 
	{
		this.isInsert = isInsert;
	}

	public String getIsInsert() 
	{
		return isInsert;
	}
	public void setIsUpdate(String isUpdate) 
	{
		this.isUpdate = isUpdate;
	}

	public String getIsUpdate() 
	{
		return isUpdate;
	}
	public void setIsList(String isList) 
	{
		this.isList = isList;
	}

	public String getIsList() 
	{
		return isList;
	}
	public void setIsQuery(String isQuery) 
	{
		this.isQuery = isQuery;
	}

	public String getIsQuery() 
	{
		return isQuery;
	}
	public void setQueryType(String queryType) 
	{
		this.queryType = queryType;
	}

	public String getQueryType() 
	{
		return queryType;
	}
	public void setIsEdit(String isEdit) 
	{
		this.isEdit = isEdit;
	}

	public String getIsEdit() 
	{
		return isEdit;
	}
	public void setShowType(String showType) 
	{
		this.showType = showType;
	}

	public String getShowType() 
	{
		return showType;
	}
	public void setOptions(String options) 
	{
		this.options = options;
	}

	public String getOptions() 
	{
		return options;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("tableName", getTableName())
            .append("columnName", getColumnName())
            .append("columnSort", getColumnSort())
            .append("columnType", getColumnType())
            .append("columnLabel", getColumnLabel())
            .append("comments", getComments())
            .append("attrName", getAttrName())
            .append("attrType", getAttrType())
            .append("isPk", getIsPk())
            .append("isNull", getIsNull())
            .append("isInsert", getIsInsert())
            .append("isUpdate", getIsUpdate())
            .append("isList", getIsList())
            .append("isQuery", getIsQuery())
            .append("queryType", getQueryType())
            .append("isEdit", getIsEdit())
            .append("showType", getShowType())
            .append("options", getOptions())
            .toString();
    }
}
