package com.ruoyi.wx.cp.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.wx.cp.config.WxCpConfiguration;
import com.ruoyi.wx.cp.config.WxCpProperties;
import com.ruoyi.wx.cp.constant.ErrorCodeText;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpDepartmentService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.WxCpUserService;
import me.chanjar.weixin.cp.bean.WxCpDepart;
import me.chanjar.weixin.cp.bean.WxCpUser;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 成员同步
 *
 * @author zhujj
 */
@RestController
@RequestMapping("/wx/cp/user")
public class WxUserController {
    private final Logger logger = LoggerFactory.getLogger( this.getClass() );

    @Autowired
    private WxCpProperties properties;

    /**
     * 获取成员列表详情
     *
     * @param departId    部门id
     * @param fetch_child 是否递归获取子部门下面的成员
     * @param state       成员状态 默认0-正常
     * @return
     */
    @GetMapping("/userList")
    public AjaxResult userList(Long departId, boolean fetch_child, Integer state) {
        try {
            WxCpUserService userService = WxCpConfiguration.getCpService( 999999 ).getUserService();

            List<WxCpUser> wxCpUsers = userService.listByDepartment( departId, fetch_child, state );
            return AjaxResult.success( wxCpUsers, "获取成员成功" );
        } catch (WxErrorException e) {
            return AjaxResult.error( "获取成员出错，错误码【" + e.getError().getErrorCode() + "】，原因：" + ErrorCodeText.errorMsg( e.getError().getErrorCode() ) );
        }
    }

    /**
     * 获取成员列表（只返回成员简洁信息）
     *
     * @param departId    部门id
     * @param fetch_child 是否递归获取子部门下面的成员
     * @param state       成员状态 默认0-正常
     * @return
     */
    @GetMapping("/userSimpleList")
    public AjaxResult userSimpleList(Long departId, boolean fetch_child, Integer state) {
        try {
            WxCpUserService userService = WxCpConfiguration.getCpService( 999999 ).getUserService();
            List<WxCpUser> wxCpUsers = userService.listSimpleByDepartment( departId, fetch_child, state );
            return AjaxResult.success( wxCpUsers, "获取成员成功" );
        } catch (WxErrorException e) {
            return AjaxResult.error( "获取成员出错，错误码【" + e.getError().getErrorCode() + "】，原因：" + ErrorCodeText.errorMsg( e.getError().getErrorCode() ) );
        }
    }

    @Log(title = "新增成员", businessType = BusinessType.INSERT)
    @PostMapping("/insert")
    public AjaxResult insert(@RequestBody WxCpUser wxCpUuser) {
        try {

            WxCpUserService userService = WxCpConfiguration.getCpService( 999999 ).getUserService();
            userService.create( wxCpUuser );
            return AjaxResult.success( "新增成员成功" );
        } catch (WxErrorException e) {
            return AjaxResult.error( "新增成员出错，错误码【" + e.getError().getErrorCode() + "】，原因：" + ErrorCodeText.errorMsg( e.getError().getErrorCode() ) );
        }
    }

    @Log(title = "全量新增成员", businessType = BusinessType.INSERT)
    @PostMapping("/insertList")
    public AjaxResult insertList(@RequestBody List<WxCpUser> wxCpUsers) {
        int i = 0;
        int u = 0;
        WxCpUser temp=null;
        try {
            WxCpUserService userService = WxCpConfiguration.getCpService( 999999 ).getUserService();
            for (WxCpUser wxCpUser : wxCpUsers) {
                WxCpUser user = null;
                try {
                    user = userService.getById( wxCpUser.getUserId() );
                } catch (WxErrorException e) {
                    this.logger.info( "成员不存在" );
                }
                temp=wxCpUser;
                if (user == null) {
                    i++;
                    userService.create( wxCpUser );
                } else {
                    u++;
                    userService.update( wxCpUser );
                }
            }
            return AjaxResult.success( i, "全量新增成员成功【" + i + "】条,更新成员成功【"+u+"】条" );
        } catch (WxErrorException e) {
            return AjaxResult.error( "全量新增成员成功【" + i + "】条,更新成员成功【"+u+"】条,失败成员信息【"+(temp!=null?temp.toString():"成员信息不存在")+"】，错误码【" + e.getError().getErrorCode() + "】，原因：" + ErrorCodeText.errorMsg( e.getError().getErrorCode() ) );
        }
    }

    @Log(title = "更新成员", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult update(@RequestBody WxCpUser wxCpUser) {
        try {
            WxCpUserService userService = WxCpConfiguration.getCpService( 999999 ).getUserService();
            userService.update( wxCpUser );
            return AjaxResult.success( "更新成员成功" );
        } catch (WxErrorException e) {
            return AjaxResult.error( "更新成员失败，错误码【" + e.getError().getErrorCode() + "】，原因：" + ErrorCodeText.errorMsg( e.getError().getErrorCode() ) );
        }
    }

    @Log(title = "删除成员", businessType = BusinessType.DELETE)
    @GetMapping("/delete")
    public AjaxResult delete(String id) {
        try {
            WxCpUserService userService = WxCpConfiguration.getCpService( 999999 ).getUserService();
            userService.delete( id );
            return AjaxResult.success( "删除成员成功" );
        } catch (WxErrorException e) {
            return AjaxResult.error( "删除成员出错，错误码【" + e.getError().getErrorCode() + "】，原因：" + ErrorCodeText.errorMsg( e.getError().getErrorCode() ) );
        }
    }

    @Log(title = "批量删除成员", businessType = BusinessType.DELETE)
    @PostMapping("/deleteByIds")
    public AjaxResult delete(@RequestBody String[] ids) {
        try {
            WxCpUserService userService = WxCpConfiguration.getCpService( 999999 ).getUserService();
            userService.delete( ids );
            return AjaxResult.success( "批量删除成员成功" );
        } catch (WxErrorException e) {
            return AjaxResult.error( "删除成员出错，错误码【" + e.getError().getErrorCode() + "】，原因：" + ErrorCodeText.errorMsg( e.getError().getErrorCode() ) );
        }
    }

}
