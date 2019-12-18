package com.ruoyi.train.course.domain;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 课程表 train_course
 *
 * @author zhujj
 * @date 2018-12-23
 */
public class TrainCourse {
    private static final long serialVersionUID = 1L;

    /**
     * 课程ID
     */
    @Id
    private Integer id;
    /**
     * 部门ID
     */
    private Integer deptId;
    /**
     * 课程分类
     */
    private Integer trainCourseCategoryId;
    /**
     * 课程名称
     */
    @Excel(name="课程名称",order = 0)
    private String name;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 课程封面
     */
    private String cover;
    /**  */
    @Excel(name="课程介绍",order = 1)
    private String description;
    /**
     * 是否公开（默认 1-公开，2-不公开）
     */
    @Excel(name="课程介绍",order = 2,readConverterExp = "1=公开,2=不公开")
    private String state;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;
    /**
     * 创建者
     */
    @Excel(name="课程介绍",order = 3)
    private String createBy;
    /**
     * 创建时间
     */
    @Excel(name="课程介绍",order = 3,dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
    /**
     * 备注
     */
    private String remark;

    /**
     * 设置课程ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取课程ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置部门ID
     */
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取部门ID
     */
    public Integer getDeptId() {
        return deptId;
    }

    /**
     * 设置课程分类
     */
    public void setTrainCourseCategoryId(Integer trainCourseCategoryId) {
        this.trainCourseCategoryId = trainCourseCategoryId;
    }

    /**
     * 获取课程分类
     */
    public Integer getTrainCourseCategoryId() {
        return trainCourseCategoryId;
    }

    /**
     * 设置课程名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取课程名称
     */
    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 设置课程封面
     */
    public void setCover(String cover) {
        this.cover = cover;
    }

    /**
     * 获取课程封面
     */
    public String getCover() {
        return cover;
    }

    /**
     * 设置
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置是否公开（默认 1-公开，2-不公开）
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取是否公开（默认 1-公开，2-不公开）
     */
    public String getState() {
        return state;
    }

    /**
     * 设置删除标志（0代表存在 2代表删除）
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 获取删除标志（0代表存在 2代表删除）
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 设置创建者
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取创建者
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置更新者
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 获取更新者
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取备注
     */
    public String getRemark() {
        return remark;
    }

    public String toString() {
        return new ToStringBuilder( this, ToStringStyle.MULTI_LINE_STYLE )
                .append( "id", getId() )
                .append( "deptId", getDeptId() )
                .append( "trainCourseCategoryId", getTrainCourseCategoryId() )
                .append( "name", getName() )
                .append( "cover", getCover() )
                .append( "description", getDescription() )
                .append( "state", getState() )
                .append( "delFlag", getDelFlag() )
                .append( "createBy", getCreateBy() )
                .append( "createTime", getCreateTime() )
                .append( "updateBy", getUpdateBy() )
                .append( "updateTime", getUpdateTime() )
                .append( "remark", getRemark() )
                .toString();
    }
}
