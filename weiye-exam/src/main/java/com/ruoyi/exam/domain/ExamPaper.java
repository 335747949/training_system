package com.ruoyi.exam.domain;

import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;
										    import javax.persistence.Id;
    import java.util.Date;
		
/**
 * 试卷表 exam_paper
 *
 * @author zhujj
 * @date 2018-12-16
 */
public class ExamPaper
{
private static final long serialVersionUID = 1L;

    /** 试卷ID */
    @Id
    private Integer id;
    /** 部门ID */
    private Integer deptId;
    /** 试卷分类 */
    private Integer examPaperCategoryId;
    /** 名称 */
    @Excel (name = "试卷名称",order = 2)
    private String name;
    /** 试卷类型（1-固定试卷；2-随机试卷） */
    @Excel(name = "试卷类型", readConverterExp = "1=固定试卷,2=随机试卷",order = 3)
    private String type;
    /** 总分数 */
    @Excel(name = "总分数",order = 4)
    private Integer score;
    /** 试题数量 */
    @Excel(name = "试题数量",order = 5)
    private Integer questionNumber;
    /** 显示顺序 */
    private Integer orderNum;
    /** 创建者 */
    @Excel(name = "创建者",order = 6)
    private String createBy;
    /** 创建时间 */
    @Excel(name = "创建时间",order = 7,dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    /** 更新者 */
    private String updateBy;
    /** 更新时间 */
    private Date updateDate;
    /** 备注信息 */
    @Excel(name = "备注信息",order = 8)
    private String remarks;
    /** 删除标记 0.正常 1.删除 */
    private String delFlag;

	
	    /** 设置试卷ID */
    public void setId(Integer id)
            {
            this.id = id;
            }

    /** 获取试卷ID */
    public Integer getId()
            {
            return id;
            }
	    /** 设置部门ID */
    public void setDeptId(Integer deptId)
            {
            this.deptId = deptId;
            }

    /** 获取部门ID */
    public Integer getDeptId()
            {
            return deptId;
            }
	    /** 设置试卷分类 */
    public void setExamPaperCategoryId(Integer examPaperCategoryId)
            {
            this.examPaperCategoryId = examPaperCategoryId;
            }

    /** 获取试卷分类 */
    public Integer getExamPaperCategoryId()
            {
            return examPaperCategoryId;
            }
	    /** 设置名称 */
    public void setName(String name)
            {
            this.name = name;
            }

    /** 获取名称 */
    public String getName()
            {
            return name;
            }
	    /** 设置试卷类型（1-固定试卷；2-随机试卷） */
    public void setType(String type)
            {
            this.type = type;
            }

    /** 获取试卷类型（1-固定试卷；2-随机试卷） */
    public String getType()
            {
            return type;
            }
	    /** 设置总分数 */
    public void setScore(Integer score)
            {
            this.score = score;
            }

    /** 获取总分数 */
    public Integer getScore()
            {
            return score;
            }
	    /** 设置试题数量 */
    public void setQuestionNumber(Integer questionNumber)
            {
            this.questionNumber = questionNumber;
            }

    /** 获取试题数量 */
    public Integer getQuestionNumber()
            {
            return questionNumber;
            }
	    /** 设置显示顺序 */
    public void setOrderNum(Integer orderNum)
            {
            this.orderNum = orderNum;
            }

    /** 获取显示顺序 */
    public Integer getOrderNum()
            {
            return orderNum;
            }
	    /** 设置创建者 */
    public void setCreateBy(String createBy)
            {
            this.createBy = createBy;
            }

    /** 获取创建者 */
    public String getCreateBy()
            {
            return createBy;
            }
	    /** 设置创建时间 */
    public void setCreateDate(Date createDate)
            {
            this.createDate = createDate;
            }

    /** 获取创建时间 */
    public Date getCreateDate()
            {
            return createDate;
            }
	    /** 设置更新者 */
    public void setUpdateBy(String updateBy)
            {
            this.updateBy = updateBy;
            }

    /** 获取更新者 */
    public String getUpdateBy()
            {
            return updateBy;
            }
	    /** 设置更新时间 */
    public void setUpdateDate(Date updateDate)
            {
            this.updateDate = updateDate;
            }

    /** 获取更新时间 */
    public Date getUpdateDate()
            {
            return updateDate;
            }
	    /** 设置备注信息 */
    public void setRemarks(String remarks)
            {
            this.remarks = remarks;
            }

    /** 获取备注信息 */
    public String getRemarks()
            {
            return remarks;
            }
	    /** 设置删除标记 */
    public void setDelFlag(String delFlag)
            {
            this.delFlag = delFlag;
            }

    /** 获取删除标记 */
    public String getDelFlag()
            {
            return delFlag;
            }

    @Override
public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
	            .append("id", getId())
	            .append("deptId", getDeptId())
	            .append("examPaperCategoryId", getExamPaperCategoryId())
	            .append("name", getName())
	            .append("type", getType())
	            .append("score", getScore())
	            .append("questionNumber", getQuestionNumber())
	            .append("orderNum", getOrderNum())
	            .append("createBy", getCreateBy())
	            .append("createDate", getCreateDate())
	            .append("updateBy", getUpdateBy())
	            .append("updateDate", getUpdateDate())
	            .append("remarks", getRemarks())
	            .append("delFlag", getDelFlag())
	        .toString();
        }
        }
