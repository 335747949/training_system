package com.ruoyi.exam.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Id;
    import java.util.Date;
		
/**
 * 试卷题目表 exam_practice_question
 *
 * @author zhujj
 * @date 2019-01-01
 */
public class ExamPracticeQuestion
{
private static final long serialVersionUID = 1L;

        /** 试卷题目ID */
		@Id
    private Integer id;
	        /** 试卷代码 */
	    private Integer examPracticeId;
	        /**  */
	    private Integer examQuestionId;
	        /** 分数 */
	    private Integer score;
	        /** 排序号 */
	    private Integer orderNum;
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
    public void setExamPracticeId(Integer examPracticeId)
            {
            this.examPracticeId = examPracticeId;
            }

    /** 获取试卷代码 */
    public Integer getExamPracticeId()
            {
            return examPracticeId;
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
	    /** 设置分数 */
    public void setScore(Integer score)
            {
            this.score = score;
            }

    /** 获取分数 */
    public Integer getScore()
            {
            return score;
            }
	    /** 设置排序号 */
    public void setOrderNum(Integer orderNum)
            {
            this.orderNum = orderNum;
            }

    /** 获取排序号 */
    public Integer getOrderNum()
            {
            return orderNum;
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
	            .append("examPracticeId", getExamPracticeId())
	            .append("examQuestionId", getExamQuestionId())
	            .append("score", getScore())
	            .append("orderNum", getOrderNum())
	            .append("createBy", getCreateBy())
	            .append("createDate", getCreateDate())
	            .append("updateBy", getUpdateBy())
	            .append("updateDate", getUpdateDate())
	            .append("remarks", getRemarks())
	            .append("delFlag", getDelFlag())
	        .toString();
        }
        }
