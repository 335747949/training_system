package com.ruoyi.exam.domain;

/**
 * 题库练习
 */
public class ExamPracticeVO extends ExamPractice{

    private String trainCourseName;

    private String trainCourseCategoryName;

    private String vipUserId;

    public String getVipUserId() {
        return vipUserId;
    }

    public void setVipUserId(String vipUserId) {
        this.vipUserId = vipUserId;
    }

    public String getTrainCourseName() {
        return trainCourseName;
    }

    public void setTrainCourseName(String trainCourseName) {
        this.trainCourseName = trainCourseName;
    }

    public String getTrainCourseCategoryName() {
        return trainCourseCategoryName;
    }

    public void setTrainCourseCategoryName(String trainCourseCategoryName) {
        this.trainCourseCategoryName = trainCourseCategoryName;
    }
}
