package com.ruoyi.exam.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;
					    import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
		
/**
 * 练习表 exam_practice
 *
 * @author zhujj
 * @date 2018-12-28
 */
public class ExamPractice
{
private static final long serialVersionUID = 1L;

        /** 练习ID */
		@Id
    private Integer id;
	        /** 部门ID */
	    private Integer deptId;
	        /** 练习名称 */
	    private String name;
        /** 课程代码 */
        private Integer trainCourseId;
        /** 是否控制开始结束时间（0-不控制,1-控制） */
	    private String enableControlTime;
	        /** 开始时间 */
	    private Date startTime;
	        /** 结束时间 */
	    private Date endTime;
	        /** 练习对象（1-所有人，2-限定对象） */
	    private String practiceUserLimit;
	        /** 创建者 */
	    private String createBy;
	        /** 创建时间 */
	    private Date createDate;
	        /** 更新者 */
	    private String updateBy;
	        /** 更新时间 */
	    private Date updateDate;
	        /** 考试说明 */
	    private String remarks;
	        /** 删除标记 */
	    private String delFlag;


    public Integer getTrainCourseId() {
        return trainCourseId;
    }

    public void setTrainCourseId(Integer trainCourseId) {
        this.trainCourseId = trainCourseId;
    }

    /** 设置练习ID */
    public void setId(Integer id)
            {
            this.id = id;
            }

    /** 获取练习ID */
    public Integer getId()
            {
            return id;
            }
	    /** 设置部门ID */
    public void setDeptId(Integer deptId)
            {
            this.deptId = deptId;
            }

    /** 获取部门ID */
    public Integer getDeptId()
            {
            return deptId;
            }
	    /** 设置练习名称 */
    public void setName(String name)
            {
            this.name = name;
            }

    /** 获取练习名称 */
    public String getName()
            {
            return name;
            }
	    /** 设置是否控制开始结束时间（0-不控制,1-控制） */
    public void setEnableControlTime(String enableControlTime)
            {
            this.enableControlTime = enableControlTime;
            }

    /** 获取是否控制开始结束时间（0-不控制,1-控制） */
    public String getEnableControlTime()
            {
            return enableControlTime;
            }
	    /** 设置开始时间 */
    public void setStartTime(Date startTime)
            {
            this.startTime = startTime;
            }

    /** 获取开始时间 */
    public Date getStartTime()
            {
            return startTime;
            }
	    /** 设置结束时间 */
    public void setEndTime(Date endTime)
            {
            this.endTime = endTime;
            }

    /** 获取结束时间 */
    public Date getEndTime()
            {
            return endTime;
            }
	    /** 设置练习对象（1-所有人，2-限定对象） */
    public void setPracticeUserLimit(String practiceUserLimit)
            {
            this.practiceUserLimit = practiceUserLimit;
            }

    /** 获取练习对象（1-所有人，2-限定对象） */
    public String getPracticeUserLimit()
            {
            return practiceUserLimit;
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
	    /** 设置考试说明 */
    public void setRemarks(String remarks)
            {
            this.remarks = remarks;
            }

    /** 获取考试说明 */
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
	            .append("deptId", getDeptId())
	            .append("name", getName())
	            .append("enableControlTime", getEnableControlTime())
	            .append("startTime", getStartTime())
	            .append("endTime", getEndTime())
	            .append("practiceUserLimit", getPracticeUserLimit())
	            .append("createBy", getCreateBy())
	            .append("createDate", getCreateDate())
	            .append("updateBy", getUpdateBy())
	            .append("updateDate", getUpdateDate())
	            .append("remarks", getRemarks())
	            .append("delFlag", getDelFlag())
	        .toString();
        }
        }
