package com.ruoyi.train.course.domain;

import java.util.List;

/**
 * 课程分类api接口vo对象
 *
 */
public class ApiCourseCategoryVO {

    /**
     * 课程分类id
     */
    private Integer id;

    /**
     * 父id
     */
    private Integer pid;

    /**
     * 课程分类名称
     */
    private String name;

    /**
     * 顺序
     */
    private Integer orderNum;

    /**
     * 子分类
     */
    private List<ApiCourseCategoryVO> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public List<ApiCourseCategoryVO> getChildren() {
        return children;
    }

    public void setChildren(List<ApiCourseCategoryVO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "ApiCourseCategoryVO{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", orderNum=" + orderNum +
                ", children=" + children +
                '}';
    }
}
