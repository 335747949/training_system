package com.ruoyi.exam.domain;

import com.ruoyi.common.annotation.Excel;

import java.util.List;

/**
 * Created by flower on 2019/1/14.
 */
public class ExamUserExaminationVO extends ExamUserExamination{

    private ExamExaminationVO examExaminationVO;

    private String loginName;

    @Excel(name = "用户名称",order = 0)
    private String userName;

    @Excel(name = "试卷名称",order = 2)
    private String examPaperName;

    private Integer nullAnswer = 0;

    private Integer right = 0;

    private Integer error = 0;

    private List<ExamUserExaminationQuestionVO> examUserExaminationQuestions;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getExamPaperName() {
        return examPaperName;
    }

    public void setExamPaperName(String examPaperName) {
        this.examPaperName = examPaperName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public ExamExaminationVO getExamExaminationVO() {
        return examExaminationVO;
    }

    public void setExamExaminationVO(ExamExaminationVO examExaminationVO) {
        this.examExaminationVO = examExaminationVO;
    }

    public List<ExamUserExaminationQuestionVO> getExamUserExaminationQuestions() {
        return examUserExaminationQuestions;
    }

    public void setExamUserExaminationQuestions(List<ExamUserExaminationQuestionVO> examUserExaminationQuestions) {
        this.examUserExaminationQuestions = examUserExaminationQuestions;
    }

    public Integer getNullAnswer() {
        return nullAnswer;
    }

    public void setNullAnswer(Integer nullAnswer) {
        this.nullAnswer = nullAnswer;
    }

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }
}
