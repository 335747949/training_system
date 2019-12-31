package com.ruoyi.vip.service.impl;

import java.util.List;

import com.ruoyi.vip.domain.vo.VipUserOrdersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.vip.mapper.VipUserOrdersMapper;
import com.ruoyi.vip.domain.VipUserOrders;
import com.ruoyi.vip.service.IVipUserOrdersService;
import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
/**
 * 我的订单 服务层实现
 * 
 * @author zhujj
 * @date 2019-01-25
 */
@Service
public class VipUserOrdersServiceImpl extends AbstractBaseServiceImpl<VipUserOrdersMapper,VipUserOrders> implements IVipUserOrdersService
{
	@Autowired
	private VipUserOrdersMapper vipUserOrdersMapper;

	
	/**
     * 查询我的订单列表
     * 
     * @param vipUserOrders 我的订单信息
     * @return 我的订单集合
     */
	@Override
	public List<VipUserOrdersVO> selectVipUserOrdersList(VipUserOrdersVO vipUserOrders)
	{
        return vipUserOrdersMapper.selectVipUserOrdersList(vipUserOrders);
	}
    /**
     * 查询我的订单分页列表
     *
     * @param vipUserOrders 我的订单信息
     * @return 我的订单集合
     */
    @Override
    public List<VipUserOrdersVO> selectVipUserOrdersPage(VipUserOrdersVO vipUserOrders)
    {
        startPage();
        return vipUserOrdersMapper.selectVipUserOrdersList(vipUserOrders);
    }

}
