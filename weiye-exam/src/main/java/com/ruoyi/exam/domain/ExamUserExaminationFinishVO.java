package com.ruoyi.exam.domain;


import java.util.List;

/**
 * 我参与过的考试试题表 exam_user_examination_question
 *
 * @author zhujj
 * @date 2019-01-13
 */
public class ExamUserExaminationFinishVO {

    private static final long serialVersionUID = 1L;

    /**
     * 用户考试id
     */
    private Integer examUserExaminationId;

    /**
     * 考试id
     */
    private Integer examinationId;

    /**
     * 试卷id
     */
    private Integer paperId;

    /**
     *
     */
    private List<ExamUserExaminationQuestion> examUserExaminationQuestion;

    public Integer getExamUserExaminationId() {
        return examUserExaminationId;
    }

    public void setExamUserExaminationId(Integer examUserExaminationId) {
        this.examUserExaminationId = examUserExaminationId;
    }

    public List<ExamUserExaminationQuestion> getExamUserExaminationQuestion() {
        return examUserExaminationQuestion;
    }

    public void setExamUserExaminationQuestion(List<ExamUserExaminationQuestion> examUserExaminationQuestion) {
        this.examUserExaminationQuestion = examUserExaminationQuestion;
    }

    public Integer getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(Integer examinationId) {
        this.examinationId = examinationId;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    @Override
    public String toString() {
        return "ExamUserExaminationFinishVO{" +
                "examUserExaminationId=" + examUserExaminationId +
                ", examinationId=" + examinationId +
                ", paperId=" + paperId +
                ", examUserExaminationQuestion=" + examUserExaminationQuestion +
                '}';
    }
}
