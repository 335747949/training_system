package com.ruoyi.exam.domain;

import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 问题表 exam_question
 * 
 * @author zhujj
 * @date 2018-12-07
 */
public class ExamQuestion
{
	private static final long serialVersionUID = 1L;
	
	/**  */
    @Id
	private String id;
	/** 问题标题 */

	private String title;
	/** 问题答案 */

	private String answer;
	/** 问题类型 （1.单选题 2.多选题 3.判断题）*/
	
	private String type;
	/** 标签 */
	
	private String label;
	/** 类别 */
	
	private String categoryId;
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
	/** 删除标记 */
	
	private String delFlag;

	/** 设置 */
	public void setId(String id) 
	{
		this.id = id;
	}

	/** 获取 */
	public String getId() 
	{
		return id;
	}
	/** 设置问题标题 */
	public void setTitle(String title) 
	{
		this.title = title;
	}

	/** 获取问题标题 */
	public String getTitle() 
	{
		return title;
	}
	/** 设置问题答案 */
	public void setAnswer(String answer) 
	{
		this.answer = answer;
	}

	/** 获取问题答案 */
	public String getAnswer() 
	{
		return answer;
	}
	/** 设置问题类型 */
	public void setType(String type) 
	{
		this.type = type;
	}

	/** 获取问题类型 */
	public String getType() 
	{
		return type;
	}
	/** 设置标签 */
	public void setLabel(String label) 
	{
		this.label = label;
	}

	/** 获取标签 */
	public String getLabel() 
	{
		return label;
	}
	/** 设置类别 */
	public void setCategoryId(String categoryId) 
	{
		this.categoryId = categoryId;
	}

	/** 获取类别 */
	public String getCategoryId() 
	{
		return categoryId;
	}
	/** 设置创建者 */
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}

	/** 获取创建者 */
	public String getCreateBy() 
	{
		return createBy;
	}
	/** 设置创建时间 */
	public void setCreateDate(Date createDate) 
	{
		this.createDate = createDate;
	}

	/** 获取创建时间 */
	public Date getCreateDate() 
	{
		return createDate;
	}
	/** 设置更新者 */
	public void setUpdateBy(String updateBy) 
	{
		this.updateBy = updateBy;
	}

	/** 获取更新者 */
	public String getUpdateBy() 
	{
		return updateBy;
	}
	/** 设置更新时间 */
	public void setUpdateDate(Date updateDate) 
	{
		this.updateDate = updateDate;
	}

	/** 获取更新时间 */
	public Date getUpdateDate() 
	{
		return updateDate;
	}
	/** 设置备注信息 */
	public void setRemarks(String remarks) 
	{
		this.remarks = remarks;
	}

	/** 获取备注信息 */
	public String getRemarks() 
	{
		return remarks;
	}
	/** 设置删除标记 */
	public void setDelFlag(String delFlag) 
	{
		this.delFlag = delFlag;
	}

	/** 获取删除标记 */
	public String getDelFlag() 
	{
		return delFlag;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("answer", getAnswer())
            .append("type", getType())
            .append("label", getLabel())
            .append("categoryId", getCategoryId())
            .append("createBy", getCreateBy())
            .append("createDate", getCreateDate())
            .append("updateBy", getUpdateBy())
            .append("updateDate", getUpdateDate())
            .append("remarks", getRemarks())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
