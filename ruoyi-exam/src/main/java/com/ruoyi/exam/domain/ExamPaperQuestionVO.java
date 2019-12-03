package com.ruoyi.exam.domain;

/**
 * Created by flower on 2018/12/23.
 */
public class ExamPaperQuestionVO extends ExamPaperQuestion {

    private Integer questionType;

    private String questionName;

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }
}
