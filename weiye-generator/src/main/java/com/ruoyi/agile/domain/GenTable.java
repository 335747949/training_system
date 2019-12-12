package com.ruoyi.agile.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;

import javax.persistence.Id;
import java.util.Date;

/**
 * 代码生成表 gen_table
 * 
 * @author zhujj
 * @date 2018-11-30
 */
public class GenTable extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编码 */
	@Id
	private Integer id;
	/** 表名 */
	private String tableName;
	/** 实体类名称 */
	private String className;
	/** 表说明 */
	private String comments;
	/** 关联父表的表名 */
	private String parentTableName;
	/** 本表关联父表的外键名 */
	private String parentTableFkName;
	/** 数据源名称 */
	private String dataSourceName;
	/** 使用的模板 */
	private String tplCategory;
	/** 生成包路径 */
	private String packageName;
	/** 生成模块名 */
	private String moduleName;
	/** 生成子模块名 */
	private String subModuleName;
	/** 生成功能名 */
	private String functionName;
	/** 生成功能名（简写） */
	private String functionNameSimple;
	/** 生成功能作者 */
	private String functionAuthor="AgileDev";
	/** 生成基础路径 */
	private String genBaseDir;
	/** 其它生成选项 */
	private String options;
	/** 创建者 */
	private String createBy;
	/** 创建时间 */
	private Date createDate;
	/** 更新者 */
	private String updateBy;
	/** 更新时间 */
	private Date updateDate;
	/** 备注信息 */
	private String remarks;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
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
	public void setClassName(String className) 
	{
		this.className = className;
	}

	public String getClassName() 
	{
		return className;
	}
	public void setComments(String comments) 
	{
		this.comments = comments;
	}

	public String getComments() 
	{
		return comments;
	}
	public void setParentTableName(String parentTableName) 
	{
		this.parentTableName = parentTableName;
	}

	public String getParentTableName() 
	{
		return parentTableName;
	}
	public void setParentTableFkName(String parentTableFkName) 
	{
		this.parentTableFkName = parentTableFkName;
	}

	public String getParentTableFkName() 
	{
		return parentTableFkName;
	}
	public void setDataSourceName(String dataSourceName) 
	{
		this.dataSourceName = dataSourceName;
	}

	public String getDataSourceName() 
	{
		return dataSourceName;
	}
	public void setTplCategory(String tplCategory) 
	{
		this.tplCategory = tplCategory;
	}

	public String getTplCategory() 
	{
		return tplCategory;
	}
	public void setPackageName(String packageName) 
	{
		this.packageName = packageName;
	}

	public String getPackageName() 
	{
		return packageName;
	}
	public void setModuleName(String moduleName) 
	{
		this.moduleName = moduleName;
	}

	public String getModuleName() 
	{
		return moduleName;
	}
	public void setSubModuleName(String subModuleName) 
	{
		this.subModuleName = subModuleName;
	}

	public String getSubModuleName() 
	{
		return subModuleName;
	}
	public void setFunctionName(String functionName) 
	{
		this.functionName = functionName;
	}

	public String getFunctionName() 
	{
		return functionName;
	}
	public void setFunctionNameSimple(String functionNameSimple) 
	{
		this.functionNameSimple = functionNameSimple;
	}

	public String getFunctionNameSimple() 
	{
		return functionNameSimple;
	}
	public void setFunctionAuthor(String functionAuthor) 
	{
		this.functionAuthor = functionAuthor;
	}

	public String getFunctionAuthor() 
	{
		return functionAuthor;
	}
	public void setGenBaseDir(String genBaseDir) 
	{
		this.genBaseDir = genBaseDir;
	}

	public String getGenBaseDir() 
	{
		return genBaseDir;
	}
	public void setOptions(String options) 
	{
		this.options = options;
	}

	public String getOptions() 
	{
		return options;
	}
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}

	public String getCreateBy() 
	{
		return createBy;
	}
	public void setCreateDate(Date createDate) 
	{
		this.createDate = createDate;
	}

	public Date getCreateDate() 
	{
		return createDate;
	}
	public void setUpdateBy(String updateBy) 
	{
		this.updateBy = updateBy;
	}

	public String getUpdateBy() 
	{
		return updateBy;
	}
	public void setUpdateDate(Date updateDate) 
	{
		this.updateDate = updateDate;
	}

	public Date getUpdateDate() 
	{
		return updateDate;
	}
	public void setRemarks(String remarks) 
	{
		this.remarks = remarks;
	}

	public String getRemarks() 
	{
		return remarks;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("tableName", getTableName())
            .append("className", getClassName())
            .append("comments", getComments())
            .append("parentTableName", getParentTableName())
            .append("parentTableFkName", getParentTableFkName())
            .append("dataSourceName", getDataSourceName())
            .append("tplCategory", getTplCategory())
            .append("packageName", getPackageName())
            .append("moduleName", getModuleName())
            .append("subModuleName", getSubModuleName())
            .append("functionName", getFunctionName())
            .append("functionNameSimple", getFunctionNameSimple())
            .append("functionAuthor", getFunctionAuthor())
            .append("genBaseDir", getGenBaseDir())
            .append("options", getOptions())
            .append("createBy", getCreateBy())
            .append("createDate", getCreateDate())
            .append("updateBy", getUpdateBy())
            .append("updateDate", getUpdateDate())
            .append("remarks", getRemarks())
            .toString();
    }
}
