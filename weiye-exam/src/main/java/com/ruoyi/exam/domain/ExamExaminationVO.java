package com.ruoyi.exam.domain;

import com.ruoyi.common.annotation.Excel;

/**
 * Created by flower on 2019/1/12.
 */
public class ExamExaminationVO extends ExamExamination {

    @Excel(name = "课程名称",order = 1)
    private String trainCourseName;

    private String examExaminationUserVipUserId;

    public String getTrainCourseName() {
        return trainCourseName;
    }

    public void setTrainCourseName(String trainCourseName) {
        this.trainCourseName = trainCourseName;
    }

    public String getExamExaminationUserVipUserId() {
        return examExaminationUserVipUserId;
    }

    public void setExamExaminationUserVipUserId(String examExaminationUserVipUserId) {
        this.examExaminationUserVipUserId = examExaminationUserVipUserId;
    }
}
