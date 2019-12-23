package com.ruoyi.exam.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;
					    import javax.persistence.Id;
    import java.util.Date;
		
/**
 * 试卷题目表 exam_paper_question
 *
 * @author zhujj
 * @date 2018-12-17
 */
public class ExamPaperQuestion
{
private static final long serialVersionUID = 1L;

        /** 试卷题目ID */
		@Id
    private Integer id;
	        /** 试卷代码 */
	    private Integer examPaperId;
	        /**  */
	    private Integer examQuestionId;
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

	    private Integer score;

	    private Integer orderNum;



    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /** 设置试卷题目ID */
    public void setId(Integer id)
            {
            this.id = id;
            }

    /** 获取试卷题目ID */
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
	    /** 设置 */
    public void setExamQuestionId(Integer examQuestionId)
            {
            this.examQuestionId = examQuestionId;
            }

    /** 获取 */
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
	
public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
	            .append("id", getId())
	            .append("examPaperId", getExamPaperId())
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
