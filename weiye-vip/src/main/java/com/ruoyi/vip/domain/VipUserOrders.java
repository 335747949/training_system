package com.ruoyi.vip.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;
						    import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
		
/**
 * 我的订单表 vip_user_orders
 *
 * @author zhujj
 * @date 2019-01-25
 */
public class VipUserOrders
{
private static final long serialVersionUID = 1L;

        /** 订单号 */
		@Id
    private String id;
	        /** 会员代码 */
	    private Integer vipUserId;
	        /** 练习题代码 */
	    private Integer trainCourseId;
	        /** 支付金额 */
	    private BigDecimal price;
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
	        /** 订单状态（0-默认，未支付 1-已支付） */
	    private String delFlag;
	
	    /** 设置订单号 */
    public void setId(String id)
            {
            this.id = id;
            }

    /** 获取订单号 */
    public String getId()
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
	    /** 设置练习题代码 */
    public void setTrainCourseId(Integer trainCourseId)
            {
            this.trainCourseId = trainCourseId;
            }

    /** 获取练习题代码 */
    public Integer getTrainCourseId()
            {
            return trainCourseId;
            }
	    /** 设置支付金额 */
    public void setPrice(BigDecimal price)
            {
            this.price = price;
            }

    /** 获取支付金额 */
    public BigDecimal getPrice()
            {
            return price;
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
	    /** 设置订单状态（0-默认，未支付 1-已支付） */
    public void setDelFlag(String delFlag)
            {
            this.delFlag = delFlag;
            }

    /** 获取订单状态（0-默认，未支付 1-已支付） */
    public String getDelFlag()
            {
            return delFlag;
            }
	
public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
	            .append("id", getId())
	            .append("vipUserId", getVipUserId())
	            .append("trainCourseId", getTrainCourseId())
	            .append("price", getPrice())
	            .append("createBy", getCreateBy())
	            .append("createDate", getCreateDate())
	            .append("updateBy", getUpdateBy())
	            .append("updateDate", getUpdateDate())
	            .append("remarks", getRemarks())
	            .append("delFlag", getDelFlag())
	        .toString();
        }
        }
