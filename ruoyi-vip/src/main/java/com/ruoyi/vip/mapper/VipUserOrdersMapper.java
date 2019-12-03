package com.ruoyi.vip.mapper;

import com.ruoyi.vip.domain.VipUserOrders;
import java.util.List;
import com.ruoyi.framework.web.base.MyMapper;
import com.ruoyi.vip.domain.vo.VipUserOrdersVO;

/**
 * 我的订单 数据层
 * 
 * @author zhujj
 * @date 2019-01-25
 */
public interface VipUserOrdersMapper  extends MyMapper<VipUserOrders>
{

	/**
     * 查询我的订单列表
     * 
     * @param vipUserOrders 我的订单信息
     * @return 我的订单集合
     */
	public List<VipUserOrdersVO> selectVipUserOrdersList(VipUserOrdersVO vipUserOrders);
	
}