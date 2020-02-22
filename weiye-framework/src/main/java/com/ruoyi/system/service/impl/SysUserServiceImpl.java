package com.ruoyi.system.service.impl;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.support.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
import com.ruoyi.framework.web.util.ShiroUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户 业务层处理
 *
 * @author ruoyi
 */
@Service
public class SysUserServiceImpl extends AbstractBaseServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysPostMapper postMapper;

    @Autowired
    private SysUserPostMapper userPostMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private SysConfigMapper sysConfigMapper;

    /**
     * 根据条件分页查询用户对象
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @Override
    @DataScope(tableAlias = "u")
    public List<SysUser> selectUserList(SysUser user) {
        startPage();
        return userMapper.selectUserList( user );
    }

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserByLoginName(String userName, String type) {

        return userMapper.selectUserByLoginName( userName, type );
    }


    /**
     * 通过手机号码查询用户
     *
     * @param phoneNumber 手机号码
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserByPhoneNumber(String phoneNumber, String type) {
        return userMapper.selectUserByPhoneNumber( phoneNumber, type );
    }

    /**
     * 通过邮箱查询用户
     *
     * @param email 邮箱
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserByEmail(String email, String type) {
        return userMapper.selectUserByEmail( email, type );
    }

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserById(Long userId) {
        return userMapper.selectUserById( userId );
    }

    /**
     * 通过用户ID删除用户
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public int deleteUserById(Long userId) {
        // 删除用户与角色关联
        userRoleMapper.deleteUserRoleByUserId( userId );
        // 删除用户与岗位表
        userPostMapper.deleteUserPostByUserId( userId );
        return userMapper.deleteUserById( userId );
    }

    /**
     * 批量删除用户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserByIds(String ids) throws Exception {
        Long[] userIds = Convert.toLongArray( ids );
        for (Long userId : userIds) {
            if (SysUser.isAdmin( userId )) {
                throw new Exception( "不允许删除超级管理员用户" );
            }
        }
        return userMapper.deleteUserByIds( userIds );
    }

    /**
     * 新增保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int insertUser(SysUser user) {
        // 新增用户信息
        int rows = userMapper.insertUser( user );
        // 新增用户岗位关联
        insertUserPost( user );
        // 新增用户与角色管理
        insertUserRole( user );
        return rows;
    }

    /**
     * 修改保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUser(SysUser user) {
        Long userId = user.getUserId();
        // 删除用户与角色关联
        userRoleMapper.deleteUserRoleByUserId( userId );
        // 新增用户与角色管理
        insertUserRole( user );
        // 删除用户与岗位关联
        userPostMapper.deleteUserPostByUserId( userId );
        // 新增用户与岗位管理
        insertUserPost( user );
        return userMapper.updateUser( user );
    }

    /**
     * 修改用户个人详细信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserInfo(SysUser user) {
        return userMapper.updateUser( user );
    }

    /**
     * 修改用户密码
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int resetUserPwd(SysUser user) {
        SysUser userInfo = selectUserById(user.getUserId());
        // 个人头像不变
        if (null != userInfo && null != userInfo.getAvatar()) {
            user.setAvatar(userInfo.getAvatar());
        }
        return updateUserInfo( user );
    }

    /**
     * 新增用户角色信息
     *
     * @param user 用户对象
     */
    public void insertUserRole(SysUser user) {
        // 新增用户与角色管理
        List<SysUserRole> list = new ArrayList<SysUserRole>();
        if (null != user.getRoleIds()) {
            for (Long roleId : user.getRoleIds()) {
                SysUserRole ur = new SysUserRole();
                ur.setUserId( user.getUserId() );
                ur.setRoleId( roleId );
                list.add( ur );
            }
        }

        if (list.size() > 0) {
            userRoleMapper.batchUserRole( list );
        }
    }

    /**
     * 新增用户岗位信息
     *
     * @param user 用户对象
     */
    public void insertUserPost(SysUser user) {
        // 新增用户与岗位管理
        List<SysUserPost> list = new ArrayList<SysUserPost>();
        if (null != user.getPostIds()) {
            for (Long postId : user.getPostIds()) {
                SysUserPost up = new SysUserPost();
                up.setUserId( user.getUserId() );
                up.setPostId( postId );
                list.add( up );
            }
        }

        if (list.size() > 0) {
            userPostMapper.batchUserPost( list );
        }
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param loginName 用户名
     * @return
     */
    @Override
    public String checkLoginNameUnique(String loginName, String type) {
        int count = userMapper.checkLoginNameUnique( loginName , type);
        if (count > 0) {
            return UserConstants.USER_NAME_NOT_UNIQUE;
        }
        return UserConstants.USER_NAME_UNIQUE;
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public String checkPhoneUnique(SysUser user) {
        Long userId = StringUtils.isNull( user.getUserId() ) ? -1L : user.getUserId();
        SysUser info = userMapper.checkPhoneUnique( user.getPhonenumber(), user.getUserType() );
        if (StringUtils.isNotNull( info ) && info.getUserId().longValue() != userId.longValue()) {
            return UserConstants.USER_PHONE_NOT_UNIQUE;
        }
        return UserConstants.USER_PHONE_UNIQUE;
    }

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public String checkEmailUnique(SysUser user) {
        Long userId = StringUtils.isNull( user.getUserId() ) ? -1L : user.getUserId();
        SysUser info = userMapper.checkEmailUnique( user.getEmail(), user.getUserType());
        if (StringUtils.isNotNull( info ) && info.getUserId().longValue() != userId.longValue()) {
            return UserConstants.USER_EMAIL_NOT_UNIQUE;
        }
        return UserConstants.USER_EMAIL_UNIQUE;
    }

    /**
     * 查询用户所属角色组
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public String selectUserRoleGroup(Long userId) {
        List<SysRole> list = roleMapper.selectRolesByUserId( userId );
        StringBuffer idsStr = new StringBuffer();
        for (SysRole role : list) {
            idsStr.append( role.getRoleName() ).append( "," );
        }
        if (StringUtils.isNotEmpty( idsStr.toString() )) {
            return idsStr.substring( 0, idsStr.length() - 1 );
        }
        return idsStr.toString();
    }

    /**
     * 查询用户所属岗位组
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public String selectUserPostGroup(Long userId) {
        List<SysPost> list = postMapper.selectPostsByUserId( userId );
        StringBuffer idsStr = new StringBuffer();
        for (SysPost post : list) {
            idsStr.append( post.getPostName() ).append( "," );
        }
        if (StringUtils.isNotEmpty( idsStr.toString() )) {
            return idsStr.substring( 0, idsStr.length() - 1 );
        }
        return idsStr.toString();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult addClientUserList(List<SysUser> list) {
        List<SysUser> successList = new ArrayList<>();
        List<String> errorPhoneNumList = new ArrayList<>();
        // 默认密码
        String defaultPwd = sysConfigMapper.checkConfigKeyUnique("sys.user.initPassword").getConfigValue();
        for (SysUser sysUser: list) {
            sysUser.setUserType(UserConstants.USER_VIP);
            // 校验手机号码格式
            if (!sysUser.getPhonenumber().matches(UserConstants.MOBILE_PHONE_NUMBER_PATTERN)) {
                errorPhoneNumList.add(sysUser.getPhonenumber());
                continue;
            } else if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(checkPhoneUnique(sysUser))) {
                // 校验手机号码是否唯一
                errorPhoneNumList.add(sysUser.getPhonenumber());
                continue;
            } else {
                sysUser.setUserName(StringUtils.isNotNull(sysUser.getUserName()) ? sysUser.getUserName() : "");
                sysUser.setLoginName(sysUser.getPhonenumber());
                // 默认部门id-269
                sysUser.setDeptId(UserConstants.DEFAULT_DEPT_ID);
                sysUser.setStatus("0");
                // 盐加密
                String salt = ShiroUtils.randomSalt();
                sysUser.setSalt(salt);
                // 加密根据登录用户名 + 密码 + 盐
                sysUser.setPassword(new Md5Hash(sysUser.getLoginName() + defaultPwd + salt).toHex());
                sysUser.setCreateBy(ShiroUtils.getLoginName());
                sysUser.setSex("2");
                successList.add(sysUser);
            }
        }
        if (CollectionUtils.isEmpty(list)) {
            return AjaxResult.error("导入失败，内容为空！");
        }
        // 保存符合条件的客户信息
        StringBuffer message = new StringBuffer();
        if (!CollectionUtils.isEmpty(successList)) {
            int i = userMapper.saveBatch(successList);
            if (i > 0) {
                message.append("客户列表导入成功！");
            }
        }
        // 校验不通过的账号提示信息
        if (!CollectionUtils.isEmpty(errorPhoneNumList)) {
            message.append(errorPhoneNumList.toString()).append("手机号码已存在或号码格式异常，请检查后再次导入！");
            return AjaxResult.error(message.toString());
        }
        return AjaxResult.success(message.toString());
    }
}
