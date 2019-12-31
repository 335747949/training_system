package com.ruoyi.train.course.domain;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;
							    import javax.persistence.Id;
    import java.util.Date;
		
/**
 * 章节课件表 train_course_section_courseware
 *
 * @author zhujj
 * @date 2018-12-23
 */
public class TrainCourseSectionCourseware
{
private static final long serialVersionUID = 1L;

        /** 章节课件id */
		@Id
    private Integer id;
	        /** 课程章节ID */
	    private Integer trainCourseSectionId;
	        /** 课件ID */
	    private Integer trainCoursewareId;
	        /** 显示顺序 */
	    private Integer orderNum;
	        /** 删除标记 0.正常 1.删除 */
	    private String delFlag;
	        /** 创建者 */
	    private String createBy;
	        /** 创建时间 */
	    private Date createTime;
	        /** 更新者 */
	    private String updateBy;
	        /** 更新时间 */
	    private Date updateTime;
	        /** 备注 */
	    private String remark;
	
	    /** 设置章节课件id */
    public void setId(Integer id)
            {
            this.id = id;
            }

    /** 获取章节课件id */
    public Integer getId()
            {
            return id;
            }
	    /** 设置课程章节ID */
    public void setTrainCourseSectionId(Integer trainCourseSectionId)
            {
            this.trainCourseSectionId = trainCourseSectionId;
            }

    /** 获取课程章节ID */
    public Integer getTrainCourseSectionId()
            {
            return trainCourseSectionId;
            }
	    /** 设置课件ID */
    public void setTrainCoursewareId(Integer trainCoursewareId)
            {
            this.trainCoursewareId = trainCoursewareId;
            }

    /** 获取课件ID */
    public Integer getTrainCoursewareId()
            {
            return trainCoursewareId;
            }
	    /** 设置显示顺序 */
    public void setOrderNum(Integer orderNum)
            {
            this.orderNum = orderNum;
            }

    /** 获取显示顺序 */
    public Integer getOrderNum()
            {
            return orderNum;
            }
	    /** 设置删除标记 0.正常 1.删除 */
    public void setDelFlag(String delFlag)
            {
            this.delFlag = delFlag;
            }

    /** 获取删除标记 0.正常 1.删除 */
    public String getDelFlag()
            {
            return delFlag;
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
    public void setCreateTime(Date createTime)
            {
            this.createTime = createTime;
            }

    /** 获取创建时间 */
    public Date getCreateTime()
            {
            return createTime;
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
    public void setUpdateTime(Date updateTime)
            {
            this.updateTime = updateTime;
            }

    /** 获取更新时间 */
    public Date getUpdateTime()
            {
            return updateTime;
            }
	    /** 设置备注 */
    public void setRemark(String remark)
            {
            this.remark = remark;
            }

    /** 获取备注 */
    public String getRemark()
            {
            return remark;
            }
	
public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
	            .append("id", getId())
	            .append("trainCourseSectionId", getTrainCourseSectionId())
	            .append("trainCoursewareId", getTrainCoursewareId())
	            .append("orderNum", getOrderNum())
	            .append("delFlag", getDelFlag())
	            .append("createBy", getCreateBy())
	            .append("createTime", getCreateTime())
	            .append("updateBy", getUpdateBy())
	            .append("updateTime", getUpdateTime())
	            .append("remark", getRemark())
	        .toString();
        }
        }
