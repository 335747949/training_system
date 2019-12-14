package com.ruoyi.exam.domain;

import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;

import javax.persistence.Id;
import java.util.Date;

/**
 * 考试表 exam_examination
 *
 * @author zhujj
 * @date 2018-12-24
 */
public class ExamExamination {
    private static final long serialVersionUID = 1L;

    /**
     * 考试ID
     */
    @Id
    private Integer id;
    /**
     * 部门ID
     */
    private Integer deptId;
    /**
     * 试卷代码
     */
    private Integer examPaperId;
    /**
     * 课程代码
     */
    private Integer trainCourseId;
    /**
     * 试卷名称
     */
    @Excel(name = "考试名称", order = 3)
    private String name;
    /**
     * 考试类型(1-模拟考试；2-正式考试)
     */
    @Excel(name = "考试类型", order = 4, readConverterExp = "1=模拟考试,2=正式考试")
    private String type;
    /**
     * 是否控制开始结束时间（0-不控制,1-控制）
     */
    private String enableControlTime;
    /**
     * 开始时间
     */
    @Excel(name = "开始时间", order = 5,dateFormat = "yyyy-MM-dd hh:mm:ss")
    private Date startTime;
    /**
     * 结束时间
     */
    @Excel(name = "结束时间", order = 6)
    private Date endTime;
    /**
     * 考试时长（分钟）
     */
    private Integer timeLength;
    /**
     * 考试次数
     */
    private Integer examNumber;
    /**
     * 及格分数
     */
    private Integer passMark;
    /**
     * 题目乱序（1-不打乱顺序，2-打乱顺序）
     */
    private String questionDisorder;
    /**
     * 交卷后（0-全部不可见，1-分数可见，2-对错可见，3-答案可见）
     */
    private String finishedPaper;
    /**
     * 考试结束后（0-全部不可见，1-分数可见，2-对错可见，3-答案可见）
     */
    private String examEnd;
    /**
     * 考试对象（1-所有人，2-限定对象）
     */
    private String examinationUserLimit;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateDate;
    /**
     * 考试说明
     */
    private String remarks;
    /**
     * 删除标记
     */
    private String delFlag;

    /**
     * 设置考试ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取考试ID
     */
    public Integer getId() {
        return id;
    }

    public Integer getTrainCourseId() {
        return trainCourseId;
    }

    public void setTrainCourseId(Integer trainCourseId) {
        this.trainCourseId = trainCourseId;
    }

    /**
     * 设置部门ID
     */
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取部门ID
     */
    public Integer getDeptId() {
        return deptId;
    }

    /**
     * 设置试卷代码
     */
    public void setExamPaperId(Integer examPaperId) {
        this.examPaperId = examPaperId;
    }

    /**
     * 获取试卷代码
     */
    public Integer getExamPaperId() {
        return examPaperId;
    }

    /**
     * 设置试卷名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取试卷名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置是否控制开始结束时间（0-不控制,1-控制）
     */
    public void setEnableControlTime(String enableControlTime) {
        this.enableControlTime = enableControlTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取是否控制开始结束时间（0-不控制,1-控制）
     */
    public String getEnableControlTime() {
        return enableControlTime;
    }

    /**
     * 设置开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置考试时长（分钟）
     */
    public void setTimeLength(Integer timeLength) {
        this.timeLength = timeLength;
    }

    /**
     * 获取考试时长（分钟）
     */
    public Integer getTimeLength() {
        return timeLength;
    }

    /**
     * 设置考试次数
     */
    public void setExamNumber(Integer examNumber) {
        this.examNumber = examNumber;
    }

    /**
     * 获取考试次数
     */
    public Integer getExamNumber() {
        return examNumber;
    }

    /**
     * 设置及格分数
     */
    public void setPassMark(Integer passMark) {
        this.passMark = passMark;
    }

    /**
     * 获取及格分数
     */
    public Integer getPassMark() {
        return passMark;
    }

    /**
     * 设置题目乱序（1-不打乱顺序，2-打乱顺序）
     */
    public void setQuestionDisorder(String questionDisorder) {
        this.questionDisorder = questionDisorder;
    }

    /**
     * 获取题目乱序（1-不打乱顺序，2-打乱顺序）
     */
    public String getQuestionDisorder() {
        return questionDisorder;
    }

    /**
     * 设置交卷后（0-全部不可见，1-分数可见，2-对错可见，3-答案可见）
     */
    public void setFinishedPaper(String finishedPaper) {
        this.finishedPaper = finishedPaper;
    }

    /**
     * 获取交卷后（0-全部不可见，1-分数可见，2-对错可见，3-答案可见）
     */
    public String getFinishedPaper() {
        return finishedPaper;
    }

    /**
     * 设置考试结束后（0-全部不可见，1-分数可见，2-对错可见，3-答案可见）
     */
    public void setExamEnd(String examEnd) {
        this.examEnd = examEnd;
    }

    /**
     * 获取考试结束后（0-全部不可见，1-分数可见，2-对错可见，3-答案可见）
     */
    public String getExamEnd() {
        return examEnd;
    }

    /**
     * 设置考试对象（1-所有人，2-限定对象）
     */
    public void setExaminationUserLimit(String examinationUserLimit) {
        this.examinationUserLimit = examinationUserLimit;
    }

    /**
     * 获取考试对象（1-所有人，2-限定对象）
     */
    public String getExaminationUserLimit() {
        return examinationUserLimit;
    }

    /**
     * 设置创建者
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取创建者
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置更新者
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 获取更新者
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置考试说明
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 获取考试说明
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置删除标记
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 获取删除标记
     */
    public String getDelFlag() {
        return delFlag;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("deptId", getDeptId())
                .append("examPaperId", getExamPaperId())
                .append("name", getName())
                .append("enableControlTime", getEnableControlTime())
                .append("startTime", getStartTime())
                .append("endTime", getEndTime())
                .append("timeLength", getTimeLength())
                .append("examNumber", getExamNumber())
                .append("passMark", getPassMark())
                .append("questionDisorder", getQuestionDisorder())
                .append("finishedPaper", getFinishedPaper())
                .append("examEnd", getExamEnd())
                .append("examinationUserLimit", getExaminationUserLimit())
                .append("createBy", getCreateBy())
                .append("createDate", getCreateDate())
                .append("updateBy", getUpdateBy())
                .append("updateDate", getUpdateDate())
                .append("remarks", getRemarks())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
