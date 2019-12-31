package com.ruoyi.exam.domain;

/**
 * Created by flower on 2019/1/16.
 */
public class ExamPaperVO extends ExamPaper {

    private Integer choiceNumber;
    private Integer moreChoiceNumber;
    private Integer judgeNumber;

    private Integer choiceCount;
    private Integer moreChoiceCount;
    private Integer judgeCount;

    public Integer getChoiceNumber() {
        return choiceNumber;
    }

    public void setChoiceNumber(Integer choiceNumber) {
        this.choiceNumber = choiceNumber;
    }

    public Integer getMoreChoiceNumber() {
        return moreChoiceNumber;
    }

    public void setMoreChoiceNumber(Integer moreChoiceNumber) {
        this.moreChoiceNumber = moreChoiceNumber;
    }

    public Integer getJudgeNumber() {
        return judgeNumber;
    }

    public void setJudgeNumber(Integer judgeNumber) {
        this.judgeNumber = judgeNumber;
    }

    public Integer getChoiceCount() {
        return choiceCount;
    }

    public void setChoiceCount(Integer choiceCount) {
        this.choiceCount = choiceCount;
    }

    public Integer getMoreChoiceCount() {
        return moreChoiceCount;
    }

    public void setMoreChoiceCount(Integer moreChoiceCount) {
        this.moreChoiceCount = moreChoiceCount;
    }

    public Integer getJudgeCount() {
        return judgeCount;
    }

    public void setJudgeCount(Integer judgeCount) {
        this.judgeCount = judgeCount;
    }
}
