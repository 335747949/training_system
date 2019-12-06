package com.ruoyi.train.course.domain;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;
					    import javax.persistence.Id;
    import java.util.Date;
		
/**
 * 课程使用对象表 train_course_user
 *
 * @author zhujj
 * @date 2018-12-23
 */
public class TrainCourseUser
{
private static final long serialVersionUID = 1L;

        /** 考试对象 */
		@Id
    private Integer id;
	        /** 考试代码 */
	    private Integer trainCourseId;
	        /** 会员代码 */
	    private Integer vipUserId;
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
	
	    /** 设置考试对象 */
    public void setId(Integer id)
            {
            this.id = id;
            }

    /** 获取考试对象 */
    public Integer getId()
            {
            return id;
            }
	    /** 设置考试代码 */
    public void setTrainCourseId(Integer trainCourseId)
            {
            this.trainCourseId = trainCourseId;
            }

    /** 获取考试代码 */
    public Integer getTrainCourseId()
            {
            return trainCourseId;
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
	            .append("trainCourseId", getTrainCourseId())
	            .append("vipUserId", getVipUserId())
	            .append("createBy", getCreateBy())
	            .append("createDate", getCreateDate())
	            .append("updateBy", getUpdateBy())
	            .append("updateDate", getUpdateDate())
	            .append("remarks", getRemarks())
	            .append("delFlag", getDelFlag())
	        .toString();
        }
        }
