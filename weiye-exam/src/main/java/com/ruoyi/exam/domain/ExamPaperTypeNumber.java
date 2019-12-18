package com.ruoyi.exam.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;
						    import javax.persistence.Id;
    import java.util.Date;
		
/**
 * 随机试卷题型数量表 exam_paper_type_number
 *
 * @author zhujj
 * @date 2019-01-16
 */
public class ExamPaperTypeNumber
{
private static final long serialVersionUID = 1L;

        /** 试卷题型数量ID */
		@Id
    private Integer id;
	        /** 试卷代码 */
	    private Integer examPaperId;
	        /** 问题类型（来自字典表） */
	    private Integer examQuestionType;
	        /** 题型数量 */
	    private Integer number;
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
	
	    /** 设置试卷题型数量ID */
    public void setId(Integer id)
            {
            this.id = id;
            }

    /** 获取试卷题型数量ID */
    public Integer getId()
            {
            return id;
            }
	    /** 设置试卷代码 */
    public void setExamPaperId(Integer examPaperId)
            {
            this.examPaperId = examPaperId;
            }

    /** 获取试卷代码 */
    public Integer getExamPaperId()
            {
            return examPaperId;
            }
	    /** 设置问题类型（来自字典表） */
    public void setExamQuestionType(Integer examQuestionType)
            {
            this.examQuestionType = examQuestionType;
            }

    /** 获取问题类型（来自字典表） */
    public Integer getExamQuestionType()
            {
            return examQuestionType;
            }
	    /** 设置题型数量 */
    public void setNumber(Integer number)
            {
            this.number = number;
            }

    /** 获取题型数量 */
    public Integer getNumber()
            {
            return number;
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
	            .append("examPaperId", getExamPaperId())
	            .append("examQuestionType", getExamQuestionType())
	            .append("number", getNumber())
	            .append("createBy", getCreateBy())
	            .append("createDate", getCreateDate())
	            .append("updateBy", getUpdateBy())
	            .append("updateDate", getUpdateDate())
	            .append("remarks", getRemarks())
	            .append("delFlag", getDelFlag())
	        .toString();
        }
        }
