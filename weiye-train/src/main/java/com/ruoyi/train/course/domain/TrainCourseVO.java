package com.ruoyi.train.course.domain;

/**
 * 自定义课程
 */
public class TrainCourseVO extends TrainCourse{

    private Integer userId;

    private String trainCourseCategoryName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTrainCourseCategoryName() {
        return trainCourseCategoryName;
    }

    public void setTrainCourseCategoryName(String trainCourseCategoryName) {
        this.trainCourseCategoryName = trainCourseCategoryName;
    }
}
