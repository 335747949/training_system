package com.ruoyi.vip.service;

import com.ruoyi.vip.controller.ExcelVipUserCertification;
import com.ruoyi.vip.domain.VipUserCertificate;
import java.util.List;
import com.ruoyi.framework.web.base.AbstractBaseService;
import com.ruoyi.vip.domain.vo.VipUserCertificateVO;

/**
 * 我的订单 服务层
 *
 * @author zhujj
 * @date 2019-01-15
 */
public interface IVipUserCertificateService extends AbstractBaseService<VipUserCertificate>
{
    /**
     * 查询我的订单分页列表
     *
     * @param vipUserCertificate 我的订单信息
     * @return 我的订单集合
     */
    public List<VipUserCertificateVO> selectVipUserCertificatePage(VipUserCertificateVO vipUserCertificate);
    /**
     * 查询我的订单列表
     *
     * @param vipUserCertificate 我的订单信息
     * @return 我的订单集合
     */
    public List<VipUserCertificateVO> selectVipUserCertificateList(VipUserCertificateVO vipUserCertificate);


    List<ExcelVipUserCertification> selectVipUserCertificateListFroExcel(VipUserCertificateVO vipUserCertificate);
}
