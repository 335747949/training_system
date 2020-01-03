package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysPostService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * 用户信息
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/client")
public class SysClientController extends BaseController {
    private String prefix = "system/client";

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysPostService postService;

    @Autowired
    private SysPasswordService passwordService;

    @RequiresPermissions("system:client:view")
    @GetMapping()
    public String user() {
        return prefix + "/client";
    }

    @RequiresPermissions("system:client:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysUser user) {
        // 会员用户
        user.setUserType(UserConstants.USER_VIP);
        List<SysUser> list = userService.selectUserList( user );
        return getDataTable( list );
    }

    @Log(title = "客户管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:client:import")
    @PostMapping("/import")
    @ResponseBody
    public AjaxResult importFile(@RequestParam("file") MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelUtil<SysUser> util = new ExcelUtil<>(SysUser.class);
        List<SysUser> sysUserList = util.importExcel(inputStream);
        return userService.addClientUserList(sysUserList);
    }

    @Log(title = "客户管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:client:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysUser user) {
        // 会员用户
        user.setUserType(UserConstants.USER_VIP);
        List<SysUser> list = userService.selectUserList( user );
        ExcelUtil<SysUser> util = new ExcelUtil<>( SysUser.class );
        return util.exportExcel( list, "user" );
    }

    /**
     * 新增用户
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        mmap.put( "roles", roleService.selectRoleAll() );
        mmap.put( "posts", postService.selectPostAll() );
        return prefix + "/add";
    }

    /**
     * 新增保存用户
     */
    @RequiresPermissions("system:client:add")
    @Log(title = "客户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public AjaxResult addSave(SysUser user) {
        // 客户
        user.setUserType(UserConstants.USER_VIP);
        if (StringUtils.isNotNull( user.getUserId() ) && SysUser.isAdmin( user.getUserId() )) {
            return error( "不允许修改超级管理员用户" );
        }
        String s = userService.checkLoginNameUnique(user.getLoginName(),user.getUserType());
        //用户名不唯一
        if (s.equals(UserConstants.USER_NAME_NOT_UNIQUE)) {
            return error("登录名称不能重复！");
        }
        user.setSalt( ShiroUtils.randomSalt() );
        user.setPassword( passwordService.encryptPassword( user.getLoginName(), user.getPassword(), user.getSalt() ) );
        user.setCreateBy( ShiroUtils.getLoginName() );
        return toAjax( userService.insertUser( user ) );
    }

    /**
     * 修改用户
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap) {
        mmap.put( "user", userService.selectUserById( userId ) );
        mmap.put( "roles", roleService.selectRolesByUserId( userId ) );
        mmap.put( "posts", postService.selectPostsByUserId( userId ) );
        return prefix + "/edit";
    }

    /**
     * 修改保存用户
     */
    @RequiresPermissions("system:client:edit")
    @Log(title = "客户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public AjaxResult editSave(SysUser user) {
        // 客户
        user.setUserType(UserConstants.USER_VIP);
        // 会员用户
        user.setUserType(UserConstants.USER_VIP);
        if (StringUtils.isNotNull( user.getUserId() ) && SysUser.isAdmin( user.getUserId() )) {
            return error( "不允许修改超级管理员用户" );
        }
        String s = userService.checkLoginNameUnique(user.getLoginName(),user.getUserType());
        //用户名不唯一
        if (s.equals(UserConstants.USER_NAME_NOT_UNIQUE)) {
            return error("登录名称不能重复！");
        }
        user.setUpdateBy( ShiroUtils.getLoginName() );
        return toAjax( userService.updateUser( user ) );
    }

    @RequiresPermissions("system:client:resetPwd")
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @GetMapping("/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") Long userId, ModelMap mmap) {
        mmap.put( "user", userService.selectUserById( userId ) );
        return prefix + "/resetPwd";
    }

    @RequiresPermissions("system:client:resetPwd")
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwdSave(SysUser user) {
        user.setSalt( ShiroUtils.randomSalt() );
        user.setPassword( passwordService.encryptPassword( user.getLoginName(), user.getPassword(), user.getSalt() ) );
        return toAjax( userService.resetUserPwd( user ) );
    }

    @RequiresPermissions("system:client:remove")
    @Log(title = "客户管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        try {
            return toAjax( userService.deleteUserByIds( ids ) );
        } catch (Exception e) {
            return error( e.getMessage() );
        }
    }

    /**
     * 校验用户名
     */
    @PostMapping("/checkLoginNameUnique")
    @ResponseBody
    public String checkLoginNameUnique(SysUser user) {
        return userService.checkLoginNameUnique( user.getLoginName(),user.getUserType() );
    }

    /**
     * 校验手机号码
     */
    @PostMapping("/checkPhoneUnique")
    @ResponseBody
    public String checkPhoneUnique(SysUser user) {
        return userService.checkPhoneUnique( user );
    }

    /**
     * 校验email邮箱
     */
    @PostMapping("/checkEmailUnique")
    @ResponseBody
    public String checkEmailUnique(SysUser user) {
        return userService.checkEmailUnique( user );
    }
}