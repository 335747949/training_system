package com.ruoyi.vip.controller;

import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.vip.domain.VipUserOrders;
import com.ruoyi.vip.domain.vo.VipUserCertificateVO;
import com.ruoyi.vip.domain.vo.VipUserOrdersVO;
import com.ruoyi.vip.service.IVipUserOrdersService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 我的订单
 *
 * @author ruoyi
 */
@Api("我的订单")
@RestController
@RequestMapping("/api")
public class ApiVipUserOrdersController extends BaseController {


    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IVipUserOrdersService vipUserOrdersService;

    @GetMapping("/v1/user/orders/page")
    public AjaxResult get() {
        AjaxResult success = success( "获取我的订单" );
        SysUser sysUser = sysUserService.selectUserByLoginName( JwtUtil.getLoginName(), UserConstants.USER_VIP );
        VipUserOrdersVO vipUserOrders = new VipUserOrdersVO();
        vipUserOrders.setVipUserId( sysUser.getUserId().intValue() );
        List<VipUserOrdersVO> ordersVOS = vipUserOrdersService.selectVipUserOrdersPage( vipUserOrders );
        success.put( "data", ordersVOS );
        return success;
    }

}