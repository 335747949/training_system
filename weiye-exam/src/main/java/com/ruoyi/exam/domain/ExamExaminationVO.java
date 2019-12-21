package com.ruoyi.exam.domain;

import com.ruoyi.common.annotation.Excel;

/**
 * Created by flower on 2019/1/12.
 */
public class ExamExaminationVO extends ExamExamination {

    @Excel(name = "课程名称",order = 1)
    private String trainCourseName;

    /**
     * 试卷分类名
     */
    private String examPaperCategoryName;

    /**
     * 练习分类名
     */
    private String trainCourseCategoryName;

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

    public String getExamPaperCategoryName() {
        return examPaperCategoryName;
    }

    public void setExamPaperCategoryName(String examPaperCategoryName) {
        this.examPaperCategoryName = examPaperCategoryName;
    }

    public String getTrainCourseCategoryName() {
        return trainCourseCategoryName;
    }

    public void setTrainCourseCategoryName(String trainCourseCategoryName) {
        this.trainCourseCategoryName = trainCourseCategoryName;
    }
}
