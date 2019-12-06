package com.ruoyi.vip.service;

import com.ruoyi.vip.domain.VipUserOrders;
import java.util.List;
import com.ruoyi.framework.web.base.AbstractBaseService;
import com.ruoyi.vip.domain.vo.VipUserOrdersVO;

/**
 * 我的订单 服务层
 * 
 * @author zhujj
 * @date 2019-01-25
 */
public interface IVipUserOrdersService extends AbstractBaseService<VipUserOrders>
{
	/**
     * 查询我的订单分页列表
     *
     * @param vipUserOrders 我的订单信息
     * @return 我的订单集合
     */
	public List<VipUserOrdersVO> selectVipUserOrdersPage(VipUserOrdersVO vipUserOrders);
    /**
     * 查询我的订单列表
     *
     * @param vipUserOrders 我的订单信息
     * @return 我的订单集合
     */
    public List<VipUserOrdersVO> selectVipUserOrdersList(VipUserOrdersVO vipUserOrders);

	
}
