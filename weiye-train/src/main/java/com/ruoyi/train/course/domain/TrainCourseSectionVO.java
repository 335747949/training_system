package com.ruoyi.train.course.domain;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Id;
import java.util.Date;

/**
 * 课程内容vo
 */
public class TrainCourseSectionVO
{
    private static final long serialVersionUID = 1L;

    /** 章节id */
    @Id
    private Integer id;
    /** 课程ID */
    private Integer trainCourseId;
    /** 章节名称 */
    private String name;

    /**
     * 课程目录ID
     */
    private Integer directoryId;

    /**
     * 课程目录名称
     */
    private String directoryName;


    /**
     * 课程目录父级ID
     */
    private Integer directoryParentId;

    /** 显示顺序 */
    private Integer orderNum;
    /** 课件类型 (1、音/視頻 2、課件)*/
    private Integer type;
    /** 课件 */
    private String courseware;
    /** 删除标记 0.正常 1.删除 */
    private String delFlag;
    /** 创建者 */
    private String createBy;
    /** 创建时间 */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /** 更新者 */
    private String updateBy;
    /** 更新时间 */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /** 备注 */
    private String remark;

    /** 设置章节id */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /** 获取章节id */
    public Integer getId()
    {
        return id;
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
    /** 设置章节名称 */
    public void setName(String name)
    {
        this.name = name;
    }

    public String getCourseware() {
        return courseware;
    }

    public void setCourseware(String courseware) {
        this.courseware = courseware;
    }

    /** 获取章节名称 */
    public String getName()
    {
        return name;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(Integer directoryId) {
        this.directoryId = directoryId;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

    public Integer getDirectoryParentId() {
        return directoryParentId;
    }

    public void setDirectoryParentId(Integer directoryParentId) {
        this.directoryParentId = directoryParentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("trainCourseId", getTrainCourseId())
                .append("name", getName())
                .append("courseware", getCourseware())
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
