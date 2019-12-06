package com.ruoyi.exam.domain;

import com.ruoyi.common.annotation.Excel;

import java.util.Date;

/**
 * Created by flower on 2019/2/23.
 */
public class ExcelUserExamination {

    @Excel(name = "用户名称",order = 0)
    private String userName;

    @Excel(name = "课程名称",order = 1)
    private String trainCourseName;

    @Excel(name = "试卷名称",order = 2)
    private String examPaperName;

    @Excel(name = "考试名称", order = 3)
    private String name;

    @Excel(name = "考试类型", order = 4, readConverterExp = "1=模拟考试,2=正式考试")
    private String type;

    @Excel(name = "开始时间", order = 5,dateFormat = "yyyy-MM-dd hh:mm:ss")
    private Date startTime;
    /**
     * 结束时间
     */
    @Excel(name = "结束时间", order = 6,dateFormat = "yyyy-MM-dd hh:mm:ss")
    private Date endTime;

    @Excel(name = "考试得分", order = 7)
    private Integer score;

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

    public String getExamPaperName() {
        return examPaperName;
    }

    public void setExamPaperName(String examPaperName) {
        this.examPaperName = examPaperName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }




}
