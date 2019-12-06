package com.ruoyi.vip.domain.vo;

import com.ruoyi.system.domain.SysUser;
import com.ruoyi.vip.domain.VipUserCertificate;

/**
 * @ProjectName: RuoYi
 * @Package: com.ruoyi.vip.domain.vo
 * @ClassName: VipUserCertificateVO
 * @Description: java类作用描述
 * @Author: Zhujj
 * @CreateDate: 2019/1/15 0015 17:42
 * @Version: 1.0
 */
public class VipUserCertificateVO extends VipUserCertificate {
    /**
     * 会员
     */
    private SysUser user;

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }
}
