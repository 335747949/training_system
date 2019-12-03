package com.ruoyi.vip.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;
					    import javax.persistence.Id;
    import java.util.Date;
		
/**
 * 我的订单表 vip_user_certificate
 *
 * @author zhujj
 * @date 2019-01-15
 */
public class VipUserCertificate
{
private static final long serialVersionUID = 1L;

        /** 练习对象 */
		@Id
    private Integer id;
	        /** 会员代码 */
	    private Integer vipUserId;
	        /** 证书名称 */
	    private String name;
	        /** 证书照片 */
	    private String image;
	        /** 生效日期 */
        @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	    private Date startDate;
	        /** 截止日期 */
        @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	    private Date endDate;
	        /** 创建者 */
	    private String createBy;
	        /** 创建时间 */
	    private Date createDate;
	        /** 更新者 */
	    private String updateBy;
	        /** 更新时间 */
	    private Date updateDate;
	        /** 备注信息 */
	    private String remarks;
	        /** 删除标记 */
	    private String delFlag;
	
	    /** 设置练习对象 */
    public void setId(Integer id)
            {
            this.id = id;
            }

    /** 获取练习对象 */
    public Integer getId()
            {
            return id;
            }
	    /** 设置会员代码 */
    public void setVipUserId(Integer vipUserId)
            {
            this.vipUserId = vipUserId;
            }

    /** 获取会员代码 */
    public Integer getVipUserId()
            {
            return vipUserId;
            }
	    /** 设置证书名称 */
    public void setName(String name)
            {
            this.name = name;
            }

    /** 获取证书名称 */
    public String getName()
            {
            return name;
            }
	    /** 设置证书照片 */
    public void setImage(String image)
            {
            this.image = image;
            }

    /** 获取证书照片 */
    public String getImage()
            {
            return image;
            }
	    /** 设置生效日期 */
    public void setStartDate(Date startDate)
            {
            this.startDate = startDate;
            }

    /** 获取生效日期 */
    public Date getStartDate()
            {
            return startDate;
            }
	    /** 设置截止日期 */
    public void setEndDate(Date endDate)
            {
            this.endDate = endDate;
            }

    /** 获取截止日期 */
    public Date getEndDate()
            {
            return endDate;
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
	
public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
	            .append("id", getId())
	            .append("vipUserId", getVipUserId())
	            .append("name", getName())
	            .append("image", getImage())
	            .append("startDate", getStartDate())
	            .append("endDate", getEndDate())
	            .append("createBy", getCreateBy())
	            .append("createDate", getCreateDate())
	            .append("updateBy", getUpdateBy())
	            .append("updateDate", getUpdateDate())
	            .append("remarks", getRemarks())
	            .append("delFlag", getDelFlag())
	        .toString();
        }
        }
