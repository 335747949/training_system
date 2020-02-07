package com.ruoyi.train.course.domain.vo;

/**
 * 根据课程分类分页查询课程列表
 */
public class ApiCourseListByCategoryVO {
    /**
     *
     */
    private Integer pageNum = 1;

    /**
     *
     */
    private Integer pageSize = 10;

    /**
     * 一级分类id
     */
    private String categoryId1;

    /**
     * 二级分类id
     */
    private String categoryId2;

    /**
     * 三级分类id
     */
    private String categoryId3;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getCategoryId1() {
        return categoryId1;
    }

    public void setCategoryId1(String categoryId1) {
        this.categoryId1 = categoryId1;
    }

    public String getCategoryId2() {
        return categoryId2;
    }

    public void setCategoryId2(String categoryId2) {
        this.categoryId2 = categoryId2;
    }

    public String getCategoryId3() {
        return categoryId3;
    }

    public void setCategoryId3(String categoryId3) {
        this.categoryId3 = categoryId3;
    }

    @Override
    public String toString() {
        return "ApiCourseListByCategoryVO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", categoryId1='" + categoryId1 + '\'' +
                ", categoryId2='" + categoryId2 + '\'' +
                ", categoryId3='" + categoryId3 + '\'' +
                '}';
    }
}
