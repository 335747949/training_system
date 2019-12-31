package com.ruoyi.exam.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;
					    import javax.persistence.Id;
    import java.util.Date;
		
/**
 * 我参与过的考试试题表 exam_user_examination_question
 *
 * @author zhujj
 * @date 2019-01-13
 */
public class ExamUserExaminationQuestion
{
private static final long serialVersionUID = 1L;

        /** 练习对象 */
		@Id
    private Integer id;
	        /** 考试记录编码 */
	    private Integer examUserExaminationId;
	        /** 试题编码 */
	    private Integer examQuestionId;
        /** 回答答案 */
        private String userAnswer;
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


    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

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
	    /** 设置考试记录编码 */
    public void setExamUserExaminationId(Integer examUserExaminationId)
            {
            this.examUserExaminationId = examUserExaminationId;
            }

    /** 获取考试记录编码 */
    public Integer getExamUserExaminationId()
            {
            return examUserExaminationId;
            }
	    /** 设置试题编码 */
    public void setExamQuestionId(Integer examQuestionId)
            {
            this.examQuestionId = examQuestionId;
            }

    /** 获取试题编码 */
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
	            .append("examUserExaminationId", getExamUserExaminationId())
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
