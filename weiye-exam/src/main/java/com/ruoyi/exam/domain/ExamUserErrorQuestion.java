package com.ruoyi.exam.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Id;
import java.util.Date;
		
/**
 * 我的错题表 exam_user_error_question
 *
 * @author zhujj
 * @date 2019-01-10
 */
public class ExamUserErrorQuestion
{
private static final long serialVersionUID = 1L;

        /** 练习对象 */
		@Id
    private Integer id;
	        /** 会员代码 */
	    private Integer vipUserId;
	        /** 练习题代码 */
	    private Integer examQuestionId;
    /**
     * 考试ID
     */
    private Integer examinationId;
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
	
	    /** 设置练习对象 */
    public void setId(Integer id)
            {
            this.id = id;
            }

    /** 获取练习对象 */
    public Integer getId()
            {
            return id;
            }
	    /** 设置会员代码 */
    public void setVipUserId(Integer vipUserId)
    {
        this.vipUserId = vipUserId;
    }

    /** 获取会员代码 */
    public Integer getVipUserId()
            {
            return vipUserId;
            }
	    /** 设置练习题代码 */
    public void setExamQuestionId(Integer examQuestionId)
            {
            this.examQuestionId = examQuestionId;
            }

    /** 获取练习题代码 */
    public Integer getExamQuestionId()
            {
            return examQuestionId;
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

    public Integer getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(Integer examinationId) {
        this.examinationId = examinationId;
    }

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
	            .append("id", getId())
	            .append("vipUserId", getVipUserId())
	            .append("examQuestionId", getExamQuestionId())
	            .append("createBy", getCreateBy())
	            .append("createDate", getCreateDate())
	            .append("updateBy", getUpdateBy())
	            .append("updateDate", getUpdateDate())
	            .append("remarks", getRemarks())
	            .append("delFlag", getDelFlag())
	        .toString();
        }
        }
