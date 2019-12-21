package com.ruoyi.exam.domain;

import com.ruoyi.common.annotation.Excel;

import java.util.Date;

/**
 * 考试结果
 * @author Administrator
 */
public class ExamExaminationResultVo{
    private static final long serialVersionUID = 1L;

    /**
     * 用户考试id
     */
    private Integer userExamId;

    /**
     * 参考人id
     */
    private Integer vipUserId;

    /**
     * 参考人姓名
     */
    @Excel(name = "姓名", order = 5)
    private String vipUserName;

    /**
     * 参考人电话
     */
    @Excel(name = "电话", order = 6)
    private String vipUserMobile;

    /**
     * 部门
     */
    @Excel(name = "部门", order = 7)
    private String deptName;

    /**
     * 参考人成绩
     */
    @Excel(name = "成绩", order = 8)
    private Integer score;

    /**
     * 考试结束时间
     */
    @Excel(name = "交卷时间", order = 9,dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date finshTime;

    /**
     * 试卷id
     */
    private Integer examPaperId;

    /**
     * 试卷名称
     */
    @Excel(name = "考试名称", order = 2)
    private String examName;

    /**
     * 试卷名称
     */
    @Excel(name = "试卷名称", order = 3)
    private String examPaperName;

    /**
     * 试卷类型
     */
    @Excel(name = "考试类型", order = 4, readConverterExp = "1=模拟考试,2=正式考试")
    private String examPaperType;

    /**
     * 及格分数线
     */
    private Integer passScore;


    public Integer getUserExamId() {
        return userExamId;
    }

    public void setUserExamId(Integer userExamId) {
        this.userExamId = userExamId;
    }

    public Integer getVipUserId() {
        return vipUserId;
    }

    public void setVipUserId(Integer vipUserId) {
        this.vipUserId = vipUserId;
    }

    public String getVipUserName() {
        return vipUserName;
    }

    public void setVipUserName(String vipUserName) {
        this.vipUserName = vipUserName;
    }

    public String getVipUserMobile() {
        return vipUserMobile;
    }

    public void setVipUserMobile(String vipUserMobile) {
        this.vipUserMobile = vipUserMobile;
    }

    public Integer getScore() {
        if (null == score){
            return 0;
        }
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getFinshTime() {
        return finshTime;
    }

    public void setFinshTime(Date finshTime) {
        this.finshTime = finshTime;
    }

    public Integer getExamPaperId() {
        return examPaperId;
    }

    public void setExamPaperId(Integer examPaperId) {
        this.examPaperId = examPaperId;
    }

    public String getExamPaperName() {
        return examPaperName;
    }

    public void setExamPaperName(String examPaperName) {
        this.examPaperName = examPaperName;
    }

    public String getExamPaperType() {
        return examPaperType;
    }

    public void setExamPaperType(String examPaperType) {
        this.examPaperType = examPaperType;
    }

    public Integer getPassScore() {
        return passScore;
    }

    public void setPassScore(Integer passScore) {
        this.passScore = passScore;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
