package com.ruoyi.exam.domain;

import java.util.List;

/**
 * Created by flower on 2019/1/16.
 */
public class ExamUserExaminationQuestionVO extends ExamUserExaminationQuestion{


    private String answer;

    private String title;

    private String type;

    private List<ExamQuestionItem> questionItems;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ExamQuestionItem> getQuestionItems() {
        return questionItems;
    }

    public void setQuestionItems(List<ExamQuestionItem> questionItems) {
        this.questionItems = questionItems;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
