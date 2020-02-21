package com.ruoyi.train.course.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 小程序课程详情VO对象
 */
public class ApiTrainCourseVO {
    private static final long serialVersionUID = 1L;

    /**
     * 课程ID
     */
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
    private String description;
    /**
     * 是否公开（默认 1-公开，2-不公开）
     */
    private String state;

    /**
     *  最新推荐  0.否  1.是
     */
    private Integer isNew;
    /**
     * 精品推荐   0. 否  1.是
     */
    private Integer isGood;

    /**
     * 删除标记 0.正常 1.删除
     */
    private String delFlag;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
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
     * 课程内容目录
     */
    private List<ApiTrainCourseSectionVO> directory;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getTrainCourseCategoryId() {
        return trainCourseCategoryId;
    }

    public void setTrainCourseCategoryId(Integer trainCourseCategoryId) {
        this.trainCourseCategoryId = trainCourseCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public Integer getIsGood() {
        return isGood;
    }

    public void setIsGood(Integer isGood) {
        this.isGood = isGood;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<ApiTrainCourseSectionVO> getDirectory() {
        return directory;
    }

    public void setDirectory(List<ApiTrainCourseSectionVO> directory) {
        this.directory = directory;
    }

    @Override
    public String toString() {
        return "ApiTrainCourseVO{" +
                "id=" + id +
                ", deptId=" + deptId +
                ", trainCourseCategoryId=" + trainCourseCategoryId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", cover='" + cover + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                ", isNew=" + isNew +
                ", isGood=" + isGood +
                ", delFlag='" + delFlag + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                ", directory=" + directory +
                '}';
    }
}
