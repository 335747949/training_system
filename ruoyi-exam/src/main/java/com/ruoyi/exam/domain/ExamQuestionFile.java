package com.ruoyi.exam.domain;

import com.ruoyi.common.annotation.Excel;

/**
 * Created by flower on 2019/1/1.
 */
public class ExamQuestionFile {

    @Excel(order = 1)
    private String name;
    @Excel(order = 10)
    private String answer;
    @Excel(order = 4)
    private String itemA;
    @Excel(order = 5)
    private String itemB;
    @Excel(order = 6)
    private String itemC;
    @Excel(order = 7)
    private String itemD;
    @Excel(order = 8)
    private String itemE;
    @Excel(order = 9)
    private String itemF;
    @Excel(order = 11)
    private String remarks;

    public String getItemA() {
        return itemA;
    }

    public void setItemA(String itemA) {
        this.itemA = itemA;
    }

    public String getItemB() {
        return itemB;
    }

    public void setItemB(String itemB) {
        this.itemB = itemB;
    }

    public String getItemC() {
        return itemC;
    }

    public void setItemC(String itemC) {
        this.itemC = itemC;
    }

    public String getItemD() {
        return itemD;
    }

    public void setItemD(String itemD) {
        this.itemD = itemD;
    }

    public String getItemE() {
        return itemE;
    }

    public void setItemE(String itemE) {
        this.itemE = itemE;
    }

    public String getItemF() {
        return itemF;
    }

    public void setItemF(String itemF) {
        this.itemF = itemF;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
