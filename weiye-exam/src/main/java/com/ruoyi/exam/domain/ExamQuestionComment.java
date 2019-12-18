package com.ruoyi.exam.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 问题点评表 exam_question_comment
 * 
 * @author zhujj
 * @date 2018-12-07
 */
public class ExamQuestionComment
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	@Id
	private String id;
	/** 点评内容 */
	
	private Integer content;
	/** 问题 */
	
	private String examQuestionId;
	/** 点赞数量 */
	
	private Integer praiseCount;
	/** 类型（0 学生点评 1 老师点评） */
	
	private String commentType;
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
	/** 删除标记 0.正常 1.删除 */
	
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
	/** 设置点评内容 */
	public void setContent(Integer content) 
	{
		this.content = content;
	}

	/** 获取点评内容 */
	public Integer getContent() 
	{
		return content;
	}
	/** 设置问题 */
	public void setExamQuestionId(String examQuestionId) 
	{
		this.examQuestionId = examQuestionId;
	}

	/** 获取问题 */
	public String getExamQuestionId() 
	{
		return examQuestionId;
	}
	/** 设置点赞数量 */
	public void setPraiseCount(Integer praiseCount) 
	{
		this.praiseCount = praiseCount;
	}

	/** 获取点赞数量 */
	public Integer getPraiseCount() 
	{
		return praiseCount;
	}
	/** 设置类型（0 学生点评 1 老师点评） */
	public void setCommentType(String commentType) 
	{
		this.commentType = commentType;
	}

	/** 获取类型（0 学生点评 1 老师点评） */
	public String getCommentType() 
	{
		return commentType;
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
            .append("content", getContent())
            .append("examQuestionId", getExamQuestionId())
            .append("praiseCount", getPraiseCount())
            .append("commentType", getCommentType())
            .append("createBy", getCreateBy())
            .append("createDate", getCreateDate())
            .append("updateBy", getUpdateBy())
            .append("updateDate", getUpdateDate())
            .append("remarks", getRemarks())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
