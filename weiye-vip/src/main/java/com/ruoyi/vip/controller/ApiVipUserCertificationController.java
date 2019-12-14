package com.ruoyi.vip.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.json.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.shiro.service.SysLoginService;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.vip.domain.vo.VipUserCertificateVO;
import com.ruoyi.vip.service.IVipUserCertificateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 用户信息
 *
 * @author ruoyi
 */
@Api("我的证书管理")
@RestController
@RequestMapping("/api/v1")
public class ApiVipUserCertificationController extends BaseController {


    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IVipUserCertificateService vipUserCertificateService;
    @GetMapping("/user/cerificate/page")
    @ApiOperation(value = "获取用户证书", notes = "", httpMethod = "GET")
    public AjaxResult get() {
        AjaxResult success = success( "获取我的证书成功" );
        SysUser sysUser = sysUserService.selectUserByLoginName( JwtUtil.getLoginName(),UserConstants.USER_VIP );
        VipUserCertificateVO certificateVO = new VipUserCertificateVO();
        certificateVO.setVipUserId( sysUser.getUserId().intValue() );
        List<VipUserCertificateVO> list = vipUserCertificateService.selectVipUserCertificatePage( certificateVO );
        success.put( "data", list );
        return success;
    }

}