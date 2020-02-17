package com.ruoyi.train.course.domain.vo;

import java.util.List;

/**
 * 课程分类api接口vo对象
 *
 */
public class ApiCourseCategoryVO {

    /**
     * 课程分类id
     */
    private Long id;

    /**
     * 父id
     */
    private Long pid;

    /**
     * 课程分类名称
     */
    private String name;

    /**
     * 顺序
     */
    private String orderNum;

    /**
     * 子分类
     */
    private List<ApiCourseCategoryVO> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
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
