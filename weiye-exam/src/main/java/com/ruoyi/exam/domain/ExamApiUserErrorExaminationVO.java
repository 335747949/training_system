package com.ruoyi.exam.domain;

import java.io.Serializable;

/**
 * api接口用户模拟考试错题VO
 */
public class ExamApiUserErrorExaminationVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 模拟考试id
     */
    private Integer examinationId;

    /**
     * 模拟考试名称
     */
    private String examinationName;

    /**
     * 错题数量
     */
    private Integer errorQuestionNum;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(Integer examinationId) {
        this.examinationId = examinationId;
    }

    public String getExaminationName() {
        return examinationName;
    }

    public void setExaminationName(String examinationName) {
        this.examinationName = examinationName;
    }

    public Integer getErrorQuestionNum() {
        return errorQuestionNum;
    }

    public void setErrorQuestionNum(Integer errorQuestionNum) {
        this.errorQuestionNum = errorQuestionNum;
    }

    @Override
    public String toString() {
        return "ExamApiUserErrorExaminationVO{" +
                "examinationId=" + examinationId +
                ", examinationName='" + examinationName + '\'' +
                ", errorQuestionNum=" + errorQuestionNum +
                '}';
    }
}
