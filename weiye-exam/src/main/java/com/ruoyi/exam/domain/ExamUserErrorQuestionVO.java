package com.ruoyi.exam.domain;

/**
 * Created by flower on 2019/1/10.
 */
public class ExamUserErrorQuestionVO extends ExamUserErrorQuestion {
    private ExamQuestionVO question;

    public ExamQuestionVO getQuestion() {
        return question;
    }

    public void setQuestion(ExamQuestionVO question) {
        this.question = question;
    }
}
