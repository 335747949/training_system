package com.ruoyi.exam.domain;

/**
 * 我的收藏 exam_user_error_question
 *
 * @author zhujj
 * @date 2019-01-10
 */
public class ExamUserCollectionQuestionVO extends ExamUserCollectionQuestion {
    private ExamQuestion question;

    public ExamQuestion getQuestion() {
        return question;
    }

    public void setQuestion(ExamQuestion question) {
        this.question = question;
    }
}
