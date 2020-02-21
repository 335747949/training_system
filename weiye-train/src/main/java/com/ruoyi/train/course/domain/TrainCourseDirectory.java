package com.ruoyi.train.course.domain;

import com.ruoyi.common.base.BaseEntity;

import javax.persistence.Id;

/**
 * 课程目录表
 *
 * @author ruoyi
 */
public class TrainCourseDirectory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**
     * 课程目录ID
    */
    @Id
    private Long id;

    /**
     * 父课程目录ID
    */
    private Long parentId;

    /**
     * 祖级列表
    */
    private String parentIds;

    /**
     * 课程目录名称
    */
    private String name;

    /**
     * 显示顺序
    */
    private String orderNum;

    /**
     * 父目录名称
     */
    private String parentName;

    /**
     * 删除标记 0.正常 1.删除
    */
    private String delFlag;

    /**
     * 状态 0.正常 1.停用
    */
    private int status;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TrainCourseDirectory{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", parentIds='" + parentIds + '\'' +
                ", name='" + name + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", parentName='" + parentName + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", status=" + status +
                '}';
    }
}
