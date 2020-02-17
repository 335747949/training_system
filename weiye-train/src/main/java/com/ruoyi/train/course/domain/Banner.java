package com.ruoyi.train.course.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;
							    import javax.persistence.Id;
    import java.util.Date;
		
/**
 * banner表 banner
 *
 * @author zhujj
 * @date 2020-02-04
 */
public class Banner
{
private static final long serialVersionUID = 1L;

        /** id */
		@Id
    private Integer id;
	        /** banner名称 */
	    private String name;
	        /** 跳转类型：1.小程序页面  2.小程序tab页  3.h5页面*/
	    private Integer openType;
	        /** 跳转路径 */
	    private String link;
	        /** banner图片地址 */
	    private String imageUrl;
	        /** 排序 */
	    private Integer sort;
	        /** banner展示开始时间 */
	    private Date startTime;
	        /** banner展示结束时间 */
	    private Date endTime;
	        /** 是否展示 1、展示  0、隐藏 */
	    private Integer enabled;
	        /** 创建者 */
	    private String createBy;
	        /** 创建时间 */
	    private Date createDate;
	        /** 更新者 */
	    private String updateBy;
	        /** 更新时间 */
	    private Date updateDate;
	        /** 备注 */
	    private String remarks;
	        /** 删除标记 */
	    private Integer delFlag;
	
	    /** 设置id */
    public void setId(Integer id)
            {
            this.id = id;
            }

    /** 获取id */
    public Integer getId()
            {
            return id;
            }
	    /** 设置banner名称 */
    public void setName(String name)
            {
            this.name = name;
            }

    /** 获取banner名称 */
    public String getName()
            {
            return name;
            }
	    /** 设置跳转类型：1.跳转页面  2.跳转table页  3.跳转其他小程序 */
    public void setOpenType(Integer openType)
            {
            this.openType = openType;
            }

    /** 获取跳转类型：1.跳转页面  2.跳转table页  3.跳转其他小程序 */
    public Integer getOpenType()
            {
            return openType;
            }
	    /** 设置跳转路径 */
    public void setLink(String link)
            {
            this.link = link;
            }

    /** 获取跳转路径 */
    public String getLink()
            {
            return link;
            }
	    /** 设置banner图片地址 */
    public void setImageUrl(String imageUrl)
            {
            this.imageUrl = imageUrl;
            }

    /** 获取banner图片地址 */
    public String getImageUrl()
            {
            return imageUrl;
            }
	    /** 设置排序 */
    public void setSort(Integer sort)
            {
            this.sort = sort;
            }

    /** 获取排序 */
    public Integer getSort()
            {
            return sort;
            }
	    /** 设置banner展示开始时间 */
    public void setStartTime(Date startTime)
            {
            this.startTime = startTime;
            }

    /** 获取banner展示开始时间 */
    public Date getStartTime()
            {
            return startTime;
            }
	    /** 设置banner展示结束时间 */
    public void setEndTime(Date endTime)
            {
            this.endTime = endTime;
            }

    /** 获取banner展示结束时间 */
    public Date getEndTime()
            {
            return endTime;
            }
	    /** 设置是否展示 1、展示  0、隐藏 */
    public void setEnabled(Integer enabled)
            {
            this.enabled = enabled;
            }

    /** 获取是否展示 1、展示  0、隐藏 */
    public Integer getEnabled()
            {
            return enabled;
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
	    /** 设置备注 */
    public void setRemarks(String remarks)
            {
            this.remarks = remarks;
            }

    /** 获取备注 */
    public String getRemarks()
            {
            return remarks;
            }
	    /** 设置删除标记 */
    public void setDelFlag(Integer delFlag)
            {
            this.delFlag = delFlag;
            }

    /** 获取删除标记 */
    public Integer getDelFlag()
            {
            return delFlag;
            }
	
public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
	            .append("id", getId())
	            .append("name", getName())
	            .append("openType", getOpenType())
	            .append("link", getLink())
	            .append("imageUrl", getImageUrl())
	            .append("sort", getSort())
	            .append("startTime", getStartTime())
	            .append("endTime", getEndTime())
	            .append("enabled", getEnabled())
	            .append("createBy", getCreateBy())
	            .append("createDate", getCreateDate())
	            .append("updateBy", getUpdateBy())
	            .append("updateDate", getUpdateDate())
	            .append("remarks", getRemarks())
	            .append("delFlag", getDelFlag())
	        .toString();
        }
        }
