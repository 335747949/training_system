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
}
