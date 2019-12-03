package com.ruoyi.exam.domain;

import com.ruoyi.common.annotation.Excel;

/**
 * Created by flower on 2019/3/9.
 */
public class ExcelQuestion {

    @Excel(name = "标题",order = 0)
    private String title;

    @Excel(name = "问题类型",order = 1,readConverterExp = "1=单选,2=多选,3=判断")
    private String type;

    @Excel(name = "选项A",order = 2)
    private String answerA;

    @Excel(name = "选项B",order = 3)
    private String answerB;

    @Excel(name = "选项C",order = 4)
    private String answerC;

    @Excel(name = "选项D",order = 5)
    private String answerD;

    @Excel(name = "答案",order = 6)
    private String answer;

    @Excel(name = "解析",order = 7)
    private String remarks;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
