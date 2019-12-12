package com.ruoyi.exam.domain;

/**
 * Created by flower on 2019/1/16.
 */
public class ExamPaperVO extends ExamPaper {

    private Integer choiceNumber;
    private Integer moreChoiceNumber;
    private Integer judgeNumber;

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
}
