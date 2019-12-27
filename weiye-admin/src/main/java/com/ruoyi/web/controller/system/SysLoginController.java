package com.ruoyi.web.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.util.ServletUtils;
import com.ruoyi.framework.web.base.BaseController;

import java.io.IOException;

/**
 * 登录验证
 *
 * @author ruoyi
 */
@Controller
public class SysLoginController extends BaseController {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPasswordService passwordService;

    @GetMapping("/admin")
    public String admin(HttpServletRequest request, HttpServletResponse response) throws IOException {
//         如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest( request )) {
            return ServletUtils.renderString( response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}" );
        }

        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe, Boolean isAdmin) {
        SysUser sysUser;
        if (isAdmin){
            sysUser = userService.selectUserByLoginName(username, UserConstants.USER_SYS);
        }else {
            sysUser = userService.selectUserByLoginName(username, UserConstants.USER_VIP);
        }
        if (!ObjectUtils.isEmpty(sysUser)){
            String encryptPassWord = passwordService.encryptPassword(username, password, sysUser.getSalt());
            if (!sysUser.getPassword().equals(encryptPassWord)){
                return error( "用户或密码错误" );
            }
        }else {
            return error( "用户不存在" );
        }


        UsernamePasswordToken token = new UsernamePasswordToken( username, password, rememberMe );
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login( token );
            Session session = subject.getSession();
            session.setAttribute("user",sysUser);
            return success();
        } catch (AuthenticationException e) {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty( e.getMessage() )) {
                msg = e.getMessage();
            }
            return error( msg );
        }
    }

    @GetMapping("/unauth")
    public String unauth() {
        return "/error/unauth";
    }
}
