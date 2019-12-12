//package com.ruoyi.cms.controller;
//
//import cn.binarywang.wx.miniapp.api.WxMaService;
//import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
//import cn.hutool.core.util.IdUtil;
//import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
//import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
//import com.github.binarywang.wxpay.bean.notify.WxScanPayNotifyResult;
//import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
//import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
//import com.github.binarywang.wxpay.exception.WxPayException;
//import com.github.binarywang.wxpay.service.WxPayService;
//import com.ruoyi.common.annotation.Log;
//import com.ruoyi.common.base.AjaxResult;
//import com.ruoyi.common.constant.UserConstants;
//import com.ruoyi.common.enums.BusinessType;
//import com.ruoyi.common.utils.IpUtils;
//import com.ruoyi.framework.jwt.JwtUtil;
//import com.ruoyi.framework.web.base.BaseController;
//import com.ruoyi.framework.web.util.ServletUtils;
//import com.ruoyi.framework.web.util.ShiroUtils;
//import com.ruoyi.system.domain.SysUser;
//import com.ruoyi.system.service.ISysUserService;
//import com.ruoyi.train.course.domain.*;
//import com.ruoyi.train.course.service.ITrainCourseCategoryService;
//import com.ruoyi.train.course.service.ITrainCourseSectionService;
//import com.ruoyi.train.course.service.ITrainCourseService;
//import com.ruoyi.train.course.service.ITrainCourseUserService;
//import com.ruoyi.vip.domain.VipUserOrders;
//import com.ruoyi.vip.service.IVipUserOrdersService;
//import com.ruoyi.wx.cp.utils.JsonUtils;
//import com.ruoyi.wx.miniapp.config.WxMaConfiguration;
//import io.swagger.annotations.ApiOperation;
//import me.chanjar.weixin.common.error.WxErrorException;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.List;
//
///**
// * 课程 信息操作处理
// *
// * @author zhujj
// * @date 2018-12-23
// */
//@RestController
//@RequestMapping("/api/v1/wx/pay")
//public class ApiWxPayController extends BaseController {
//
//    private static final Logger log = LoggerFactory.getLogger(ApiWxPayController.class);
//    @Autowired
//    private ITrainCourseUserService trainCourseUserService;
//
//    @Autowired
//    private IVipUserOrdersService vipUserOrdersService;
//    @Autowired
//    private ISysUserService sysUserService;
//    @Autowired
//    private WxPayService wxService;
//
//    /**
//     * 登陆接口
//     */
//    @GetMapping("/authinfo")
//    public String getAuthInfo(String appid, String code) {
//        if (StringUtils.isBlank(appid)) {
//            return "empty appid";
//        }
//        if (StringUtils.isBlank(code)) {
//            return "empty jscode";
//        }
//
//        final WxMaService wxService = WxMaConfiguration.getMaService(appid);
//
//        try {
//            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
//            //TODO 可以增加自己的逻辑，关联业务相关数据
//            return JsonUtils.toJson(session);
//        } catch (WxErrorException e) {
//            return e.toString();
//        }
//    }
//
//    @Log(title = "支付回调中post", businessType = BusinessType.INSERT)
//    @PostMapping("/notify/order")
//    public String parseOrderNotifyResult(@RequestBody String xmlData) throws WxPayException {
//
//        final WxPayOrderNotifyResult notifyResult = this.wxService.parseOrderNotifyResult(xmlData);
//        log.debug("-------------------------------支付回调中post----------------------------");
//        if (null != notifyResult && notifyResult.getReturnCode().equals("SUCCESS")) {
//            VipUserOrders userOrders = new VipUserOrders();
//            userOrders.setId(notifyResult.getOutTradeNo());
//            userOrders.setDelFlag("1");
//            vipUserOrdersService.updateSelectiveById(userOrders);
//            VipUserOrders vipUserOrders = vipUserOrdersService.selectById(notifyResult.getOutTradeNo());
//            TrainCourseUser courseUser = new TrainCourseUser();
//            courseUser.setVipUserId(vipUserOrders.getVipUserId());
//            courseUser.setTrainCourseId(vipUserOrders.getTrainCourseId());
//            courseUser.setDelFlag("0");
//            courseUser.setCreateDate(new Date());
//            courseUser.setUpdateDate(new Date());
//            courseUser.setRemarks("微信支付成功回调，订单ID:" + vipUserOrders.getId());
//            trainCourseUserService.insertSelective(courseUser);
//            log.debug("-------------------------------支付回调中post成功----------------------------");
//        }
//        return WxPayNotifyResponse.success("成功");
//    }
//    @ApiOperation(value = "扫码支付回调通知处理")
//    @PostMapping("/notify/scanpay")
//    public String parseScanPayNotifyResult(String xmlData) throws WxPayException {
//        final WxScanPayNotifyResult result = this.wxService.parseScanPayNotifyResult(xmlData);
//
//        // TODO 根据自己业务场景需要构造返回对象
//        return WxPayNotifyResponse.success("成功");
//    }
//
//    /**
//     * 调用统一下单接口，并组装生成支付所需参数对象.
//     *
//     * @param request 统一下单请求参数
//     * @param <T>     请使用{@link com.github.binarywang.wxpay.bean.order}包下的类
//     * @return 返回 {@link com.github.binarywang.wxpay.bean.order}包下的类对象
//     * <p>
//     * 示例参数
//     * {
//     * "body":"测试商品",
//     * "outTradeNo":"12344324242342342342554",
//     * "totalFee":1.01,
//     * "spbillCreateIp":"1.80.82.241",
//     * "notifyUrl":"http://www.baidu.com",
//     * "tradeType":"NATIVE",
//     * "productId":"13652b4a71df2f49e3647c55c8e31a88"
//     * }
//     * 返回
//     * {
//     * "codeUrl": "weixin://wxpay/bizpayurl?pr=pK0R74G"
//     * }
//     */
//    @ApiOperation(value = "统一下单，并组装所需支付参数")
//    @PostMapping("/createOrder")
//    public <T> T createOrder(@RequestBody WxPayUnifiedOrderRequest request) throws WxPayException {
//        request.setOutTradeNo(IdUtil.simpleUUID());
//        request.setSpbillCreateIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
//
//        VipUserOrders userOrders = new VipUserOrders();
//        userOrders.setId(request.getOutTradeNo());
//        userOrders.setVipUserId(Integer.parseInt(request.getOpenid()));
//        userOrders.setTrainCourseId(Integer.parseInt(request.getProductId()));
//        userOrders.setPrice(new BigDecimal(request.getTotalFee().intValue()).divide(new BigDecimal(100)));
//        //未支付订单
//        userOrders.setDelFlag("0");
//        vipUserOrdersService.insert(userOrders);
//        //零时存放我们自己的用户id,这儿清空
//        request.setOpenid(null);
//        return this.wxService.createOrder(request);
//    }
//
//    /**
//     * 统一下单(详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1)
//     * 在发起微信支付前，需要调用统一下单接口，获取"预支付交易会话标识"
//     * 接口地址：https://api.mch.weixin.qq.com/pay/unifiedorder
//     *
//     * @param request 请求对象，注意一些参数如appid、mchid等不用设置，方法内会自动从配置对象中获取到（前提是对应配置中已经设置）
//     *                示例参数
//     *                {
//     *                "body":"测试商品",
//     *                "outTradeNo":"12344324242342342342554",
//     *                "totalFee":1.01,
//     *                "spbillCreateIp":"1.80.82.241",
//     *                "notifyUrl":"http://www.baidu.com",
//     *                "tradeType":"JSAPI",
//     *                "productId":"13652b4a71df2f49e3647c55c8e31a88"
//     *                "openid":''
//     *                }
//     *                返回
//     *                {
//     *                "codeUrl": "weixin://wxpay/bizpayurl?pr=pK0R74G"
//     *                }
//     */
//    @ApiOperation(value = "原生的统一下单接口")
//    @PostMapping("/unifiedOrder")
//    public WxPayUnifiedOrderResult unifiedOrder(@RequestBody WxPayUnifiedOrderRequest request) throws WxPayException {
//        request.setOutTradeNo(IdUtil.simpleUUID());
//        request.setSpbillCreateIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
//        request.setSignType("MD5");
//        VipUserOrders userOrders = new VipUserOrders();
//        userOrders.setId(request.getOutTradeNo());
//        SysUser sysUser = sysUserService.selectUserByLoginName(JwtUtil.getLoginName(), UserConstants.USER_VIP);
//        userOrders.setVipUserId(sysUser.getUserId().intValue());
//        userOrders.setTrainCourseId(Integer.parseInt(request.getProductId()));
//        userOrders.setPrice(new BigDecimal(request.getTotalFee().intValue()).divide(new BigDecimal(100)));
//        //未支付订单
//        userOrders.setDelFlag("0");
//        vipUserOrdersService.insert(userOrders);
//        return this.wxService.unifiedOrder(request);
//    }
//}
