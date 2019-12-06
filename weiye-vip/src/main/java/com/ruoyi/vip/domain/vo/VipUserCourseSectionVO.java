package com.ruoyi.vip.domain.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.vip.domain.VipUserCourseSection;

/**
 * 自定义学习进度
 *
 * @author zhujj
 * @date 2019-01-15
 */
public class VipUserCourseSectionVO extends VipUserCourseSection {

    @Excel(name = "用户名称",order = 1)
    private String userName;
    @Excel(name = "课程名称",order = 3)
    private String trainCourseName;
    @Excel(name = "章节名称",order = 5)
    private String trainCourseSectionName;
    /** 学习时间长度（分钟） */
    @Excel(name = "学习时长（分钟）",order = 5)
    private Integer duration;

    @Override
    public Integer getDuration() {
        return duration;
    }

    @Override
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTrainCourseName() {
        return trainCourseName;
    }

    public void setTrainCourseName(String trainCourseName) {
        this.trainCourseName = trainCourseName;
    }

    public String getTrainCourseSectionName() {
        return trainCourseSectionName;
    }

    public void setTrainCourseSectionName(String trainCourseSectionName) {
        this.trainCourseSectionName = trainCourseSectionName;
    }
}
