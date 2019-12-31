package com.ruoyi.exam.domain;

import java.util.List;

/**
 * Created by flower on 2019/1/10.
 */
public class ExamQuestionVO extends ExamQuestion {
    private List<ExamQuestionItem> questionItem;

    private String examUserCollectionQuestionId;

    private Integer index;

    private boolean resolve = false;

    private String myAnswer = "";

    public String getExamUserCollectionQuestionId() {
        return examUserCollectionQuestionId;
    }

    public void setExamUserCollectionQuestionId(String examUserCollectionQuestionId) {
        this.examUserCollectionQuestionId = examUserCollectionQuestionId;
    }

    public List<ExamQuestionItem> getQuestionItem() {
        return questionItem;
    }

    public void setQuestionItem(List<ExamQuestionItem> questionItem) {
        this.questionItem = questionItem;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public boolean isResolve() {
        return resolve;
    }

    public void setResolve(boolean resolve) {
        this.resolve = resolve;
    }

    public String getMyAnswer() {
        return myAnswer;
    }

    public void setMyAnswer(String myAnswer) {
        this.myAnswer = myAnswer;
    }
}
