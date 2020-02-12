package com.ruoyi.vip.controller.api;

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
import com.ruoyi.framework.web.exception.user.AuthExpireException;
import com.ruoyi.framework.web.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.mapper.SysConfigMapper;
import com.ruoyi.system.service.ISysUserOnlineService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.vip.domain.vo.ApiVipUserUpdatePwdVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 用户信息
 *
 * @author ruoyi
 */
@Api(value = "用户信息管理",tags = { "用户信息接口"},description ="App相关操作接口定义类")
@RestController
@RequestMapping("/api/v1")
public class ApiVipUserController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private SysPasswordService passwordService;
    @Autowired
    private SysLoginService loginService;

    @Resource
    private SysConfigMapper sysConfigMapper;

    @Autowired
    private ISysUserOnlineService sysUserOnlineService;

    @Log(title = "用户登陆", businessType = BusinessType.EXPORT)
    @RequestMapping(value = "/user/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "用户登陆", notes = "登录用户实体", httpMethod = "POST")
    @ApiImplicitParam(name = "user", value = "登录用户实体", required = true, dataType = "SysUser")
    public AjaxResult login(@RequestBody SysUser user) {
        boolean rememberMe = false;
        UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(), user.getPassword(), rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            SysUser sysUser = loginService.login(user.getLoginName(), user.getPassword(),UserConstants.USER_VIP);
            if (sysUser == null) {
                return error("用户或密码错误");
            }
            String tokenSign = JwtUtil.sign(sysUser.getLoginName(), user.getPassword());
            JSONObject json = new JSONObject();

            json.put("token", tokenSign);
            json.put("user", sysUser);
            AjaxResult success = success("登陆成功");
            success.put("data", json);
            return success;
        } catch (AuthenticationException e) {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage())) {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    @GetMapping("/member/user/info")
    public AjaxResult get() {
        AjaxResult success = success("获取用户信息成功");
        SysUser sysUser = sysUserService.selectUserByLoginName(JwtUtil.getLoginName(),UserConstants.USER_VIP);
        // 无效用户
        if (null == sysUser) {
            throw new AuthExpireException();
        }
        success.put("data", sysUser);
        return success;
    }


    /**
     * 新增保存用户
     */
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/user/add")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public AjaxResult addSave(@RequestBody SysUser user) {
        String s = sysUserService.checkLoginNameUnique(user.getLoginName(),user.getUserType());
        //用户名不唯一
        if (s.equals(UserConstants.USER_NAME_NOT_UNIQUE)) {
            return error("用户名已经注册");
        }
//        String phoneUnique = sysUserService.checkPhoneUnique(user);
//        if (s.equals(UserConstants.USER_PHONE_NOT_UNIQUE)){
//            return error("手机号已经注册");
//        }
        user.setStatus("0");
        user.setDelFlag("0");
        user.setCreateTime(new Date());
        user.setSalt(ShiroUtils.randomSalt());
        user.setUserType(UserConstants.USER_VIP);
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        return toAjax(sysUserService.insertUser(user));
    }


    /**
     * 修改保存用户
     */
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/member/user/edit")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult editSave(@RequestBody SysUser sysUser) {
        Assert.hasText(sysUser.getLoginName(), "登录账号不能为空！");
        Assert.hasText(sysUser.getUserName(), "用户姓名不能为空！");
        // 原用户信息
        SysUser userInfo = sysUserService.selectUserByLoginName(JwtUtil.getLoginName(), UserConstants.USER_VIP);
        // 无效用户
        if (null == userInfo) {
            throw new AuthExpireException();
        }
        String loginName = JwtUtil.getLoginName();
        if (!loginName.equals(sysUser.getLoginName())) {
            return error("您无权修改其他用户姓名！");
        }
        if (userInfo.getUserName().equals(sysUser.getUserName())) {
            return success();
        }
        // 更新对象
        SysUser updateUser = new SysUser();
        updateUser.setUserId(userInfo.getUserId());
        updateUser.setAvatar(userInfo.getAvatar());
        // 修改用户姓名
        updateUser.setUserName(sysUser.getUserName());
        updateUser.setUpdateBy(userInfo.getPhonenumber());
        return toAjax(sysUserService.updateUserInfo(updateUser));
    }

    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/member/user/resetPwd")
    @ResponseBody
    public AjaxResult resetPwdSave(@RequestBody SysUser user) {
        Assert.hasText(user.getLoginName(), "登录账号不能为空！");
        Assert.notNull(user.getUserId(), "用户ID不能为空！");
        String loginName = JwtUtil.getLoginName();
        if (!loginName.equals(user.getLoginName())) {
            return error("您无权修改其他用户密码！");
        }
        // 默认密码
        String defaultPwd = sysConfigMapper.checkConfigKeyUnique("sys.user.initPassword").getConfigValue();
        user.setPassword(defaultPwd);
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        return toAjax(sysUserService.resetUserPwd(user));
    }


    @Log(title = "修改密码", businessType = BusinessType.UPDATE)
    @PostMapping("/member/user/updatePwd")
    @ResponseBody
    public AjaxResult updatePwd(@RequestBody ApiVipUserUpdatePwdVO vo) {
        Assert.hasText(vo.getLoginName(), "登录账号不能为空！");
        Assert.hasText(vo.getPassword(), "原密码不能为空！");
        Assert.hasText(vo.getNewPassword(), "新密码不能为空！");
        SysUser sysUser = sysUserService.selectUserByLoginName(JwtUtil.getLoginName(), UserConstants.USER_VIP);
        // 无效用户
        if (null == sysUser) {
            throw new AuthExpireException();
        }
        // 校验填写的原密码是否与原密码一直
        String inputPassword = passwordService.encryptPassword(vo.getLoginName(), vo.getPassword(), sysUser.getSalt());
        if (!sysUser.getPassword().equals(inputPassword)) {
            return error("原密码错误！");
        }
        String loginName = JwtUtil.getLoginName();
        if (!loginName.equals(vo.getLoginName())) {
            return error("您无权修改其他用户密码！");
        }
        if (vo.getPassword().equals(vo.getNewPassword())) {
            return error("新密码不能和原密码相同！");
        }
        // 更新对象
        SysUser user = new SysUser();
        user.setUserId(sysUser.getUserId());
        user.setLoginName(vo.getLoginName());
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), vo.getNewPassword(), user.getSalt()));
        user.setAvatar(sysUser.getAvatar());
        user.setUpdateBy(sysUser.getPhonenumber());
//        // 退出登录,刪除session和cookie
//        Subject subject = SecurityUtils.getSubject();
//        subject.logout();
//        ShiroUtils.clearCachedAuthorizationInfo();
//        sysUserOnlineService.deleteOnlineById(ShiroUtils.getSessionId());
        return toAjax(sysUserService.updateUserInfo(user));
    }

    /**
     * 校验用户名
     */
    @PostMapping("/member/user/checkLoginNameUnique")
    @ResponseBody
    public String checkLoginNameUnique(SysUser user) {
        return sysUserService.checkLoginNameUnique(user.getLoginName(),user.getUserType());
    }

    /**
     * 校验手机号码
     */
    @PostMapping("/member/user/checkPhoneUnique")
    @ResponseBody
    public String checkPhoneUnique(SysUser user) {

        return sysUserService.checkPhoneUnique(user);

    }

    /**
     * 校验email邮箱
     */
    @PostMapping("/member/user/checkEmailUnique")
    @ResponseBody
    public String checkEmailUnique(SysUser user) {
        return sysUserService.checkEmailUnique(user);
    }
}