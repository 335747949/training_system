package com.ruoyi.framework.web.exception;

import com.ruoyi.framework.web.exception.user.AuthExpireException;
import com.ruoyi.framework.web.exception.user.UserNotExistsException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.exception.DemoModeException;
import com.ruoyi.framework.web.util.PermissionUtils;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

/**
 * 自定义异常处理器
 * 
 * @author ruoyi
 */
@ControllerAdvice
public class DefaultExceptionHandler
{
    private static final Logger log = LoggerFactory.getLogger(DefaultExceptionHandler.class);
    
    /**
     * 权限校验失败
     */
    @ExceptionHandler(AuthorizationException.class)
    public String  handleAuthorizationException(AuthorizationException e)
    {
        log.error(e.getMessage(), e);
//        return AjaxResult.error(PermissionUtils.getMsg(e.getMessage()));
        return "/error/unauth.html";
    }

    /**
     * 请求方式不支持
     */
    @ResponseBody
    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    public AjaxResult handleException(HttpRequestMethodNotSupportedException e)
    {
        log.error(e.getMessage(), e);
        return AjaxResult.error("不支持' " + e.getMethod() + "'请求");
    }
    /**
     * 拦截未知的运行时异常
     */
    @ResponseBody
    @ExceptionHandler(UserNotExistsException.class)
    public AjaxResult userNotFound(UserNotExistsException e)
    {
        log.error("运行时异常:", e);
        return AjaxResult.error(302,"运行时异常:" + e.getMessage());
    }

    /**
     * 拦截未知的运行时异常
     */
    @ResponseBody
    @ExceptionHandler(AuthExpireException.class)
    public AjaxResult AuthExpire(AuthExpireException e)
    {
        log.error("运行时异常:", e);
        return AjaxResult.error(401, e.getMessage());
    }
    /**
     * 拦截未知的运行时异常
     */
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public AjaxResult notFount(RuntimeException e)
    {
        log.error("运行时异常:", e);
        return AjaxResult.error("运行时异常:" + e.getMessage());
    }

    /**
     * 系统异常
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public AjaxResult handleException(Exception e)
    {
        log.error(e.getMessage(), e);
        return AjaxResult.error("服务器错误，请联系管理员");
    }

    /**
     * 数据库错误
     */
    @ResponseBody
    @ExceptionHandler({ SQLException.class })
    public AjaxResult handleException(SQLException e)
    {
        log.error(e.getMessage(), e);
        return AjaxResult.error("数据库错误，请联系管理员");
    }

    /**
     * 数据库错误
     */
    @ResponseBody
    @ExceptionHandler({ SQLSyntaxErrorException.class })
    public AjaxResult handleException(SQLSyntaxErrorException e)
    {
        log.error(e.getMessage(), e);
        return AjaxResult.error("sql错误，请联系管理员");
    }


    /**
     * 演示模式异常
     */
    @ResponseBody
    @ExceptionHandler(DemoModeException.class)
    public AjaxResult demoModeException(DemoModeException e)
    {
        return AjaxResult.error("演示模式，不允许操作");
    }
}
