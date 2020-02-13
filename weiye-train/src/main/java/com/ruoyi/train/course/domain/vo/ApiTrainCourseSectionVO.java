package com.ruoyi.train.course.domain.vo;

import java.util.List;

/**
 * 小程序课程内容VO对象
 */
public class ApiTrainCourseSectionVO implements Comparable<ApiTrainCourseSectionVO>
{
    private static final long serialVersionUID = 1L;

    /**
     *  章节id
    */
    private Integer id;

    /**
     *  章节名称
     */
    private String name;


    /**
     *  课程id
     */
    private Integer trainCourseId;

    /**
     *  课程目录Id
    */
    private Integer directoryId;

    /**
     * 课程目录父级Id
     */
    private Integer directoryParentId;

    /**
     * 课程目录祖级列表
     */
    private String directoryParentIds;

    /**
     *  课程目录名称
    */
    private String directoryName;

    /**
     *  课程目录显示顺序
    */
    private Integer directoryOrderNum;

    /**
     *  课件类型 (1、音/視頻 2、課件)
    */
    private Integer type;

    /**
     * 课件
    */
    private String courseWare;

    /**
     *  备注
    */
    private String remark;

    /**
     * 子课程
     */
    private List<ApiTrainCourseSectionVO> children;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTrainCourseId() {
        return trainCourseId;
    }

    public void setTrainCourseId(Integer trainCourseId) {
        this.trainCourseId = trainCourseId;
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

    public String getDirectoryParentIds() {
        return directoryParentIds;
    }

    public void setDirectoryParentIds(String directoryParentIds) {
        this.directoryParentIds = directoryParentIds;
    }

    public Integer getDirectoryOrderNum() {
        return directoryOrderNum;
    }

    public void setDirectoryOrderNum(Integer directoryOrderNum) {
        this.directoryOrderNum = directoryOrderNum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCourseWare() {
        return courseWare;
    }

    public void setCourseWare(String courseWare) {
        this.courseWare = courseWare;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<ApiTrainCourseSectionVO> getChildren() {
        return children;
    }

    public void setChildren(List<ApiTrainCourseSectionVO> children) {
        this.children = children;
    }

    @Override
    public int compareTo(ApiTrainCourseSectionVO o) {
        if (o.getDirectoryOrderNum().compareTo(this.directoryOrderNum) == 0) {
            return 0;
        } else if (o.getDirectoryOrderNum().compareTo(this.directoryOrderNum) > 0) {
            return -1;
        } else {
            return 1;
        }
    }
}
