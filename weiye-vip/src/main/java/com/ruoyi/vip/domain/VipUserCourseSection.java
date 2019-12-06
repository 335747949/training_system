package com.ruoyi.vip.domain;

import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;
							    import javax.persistence.Id;
    import java.util.Date;
		
/**
 * 我的课程学习表 vip_user_course_section
 *
 * @author zhujj
 * @date 2019-01-15
 */
public class VipUserCourseSection
{
private static final long serialVersionUID = 1L;

        /** 练习对象 */
		@Id
    private Integer id;
	        /** 会员代码 */
	    private Integer vipUserId;
	        /** 课程ID */
	    private Integer trainCourseId;
	        /** 章节id */
        private Integer trainCourseSectionId;
	        /** 学习时间长度（分钟） */

        @Excel(name = "学习时长（分钟）",order = 5)
	    private Integer duration;
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
	    /** 设置课程ID */
    public void setTrainCourseId(Integer trainCourseId)
            {
            this.trainCourseId = trainCourseId;
            }

    /** 获取课程ID */
    public Integer getTrainCourseId()
            {
            return trainCourseId;
            }
	    /** 设置章节id */
    public Integer getTrainCourseSectionId() {
        return trainCourseSectionId;
    }

    public void setTrainCourseSectionId(Integer trainCourseSectionId) {
        this.trainCourseSectionId = trainCourseSectionId;
    }

    /** 设置学习时间长度（分钟） */
    public void setDuration(Integer duration)
            {
            this.duration = duration;
            }

    /** 获取学习时间长度（分钟） */
    public Integer getDuration()
            {
            return duration;
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
	            .append("vipUserId", getVipUserId())
	            .append("trainCourseId", getTrainCourseId())
	            .append("trainCourseSection", getTrainCourseSectionId())
	            .append("duration", getDuration())
	            .append("createBy", getCreateBy())
	            .append("createDate", getCreateDate())
	            .append("updateBy", getUpdateBy())
	            .append("updateDate", getUpdateDate())
	            .append("remarks", getRemarks())
	            .append("delFlag", getDelFlag())
	        .toString();
        }
        }
