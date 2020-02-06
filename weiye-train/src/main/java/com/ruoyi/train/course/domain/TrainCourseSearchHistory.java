package com.ruoyi.train.course.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;
						    import javax.persistence.Id;
    import java.util.Date;
		
/**
 * 用户课程搜索记录表 train_course_search_history
 *
 * @author zhujj
 * @date 2020-02-06
 */
public class TrainCourseSearchHistory
{
private static final long serialVersionUID = 1L;

        /** 主键 */
		@Id
    private Integer id;
	        /** 关键字 */
	    private String keyword;
	        /** 用户Id */
	    private String userId;
	        /** 0正常 1删除 */
	    private Integer delFlag;
	        /** 创建时间 */
	    private Date createTime;
	        /** 更新时间 */
	    private Date updateTime;
	
	    /** 设置主键 */
    public void setId(Integer id)
            {
            this.id = id;
            }

    /** 获取主键 */
    public Integer getId()
            {
            return id;
            }
	    /** 设置关键字 */
    public void setKeyword(String keyword)
            {
            this.keyword = keyword;
            }

    /** 获取关键字 */
    public String getKeyword()
            {
            return keyword;
            }
	    /** 设置用户Id */
    public void setUserId(String userId)
            {
            this.userId = userId;
            }

    /** 获取用户Id */
    public String getUserId()
            {
            return userId;
            }
	    /** 设置0正常 1删除 */
    public void setDelFlag(Integer delFlag)
            {
            this.delFlag = delFlag;
            }

    /** 获取0正常 1删除 */
    public Integer getDelFlag()
            {
            return delFlag;
            }
	    /** 设置创建时间 */
    public void setCreateTime(Date createTime)
            {
            this.createTime = createTime;
            }

    /** 获取创建时间 */
    public Date getCreateTime()
            {
            return createTime;
            }
	    /** 设置更新时间 */
    public void setUpdateTime(Date updateTime)
            {
            this.updateTime = updateTime;
            }

    /** 获取更新时间 */
    public Date getUpdateTime()
            {
            return updateTime;
            }
	
public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
	            .append("id", getId())
	            .append("keyword", getKeyword())
	            .append("userId", getUserId())
	            .append("delFlag", getDelFlag())
	            .append("createTime", getCreateTime())
	            .append("updateTime", getUpdateTime())
	        .toString();
        }
        }
