package com.ruoyi.framework.web.exception.user;

import com.ruoyi.framework.web.exception.base.BaseException;

/**
 * 用户不存在异常类
 * 
 * @author ruoyi
 */
public class AuthExpireException extends UserException
{
    private static final long serialVersionUID = 1L;

    public AuthExpireException()
    {
        super("no.auth", null);
    }

}
