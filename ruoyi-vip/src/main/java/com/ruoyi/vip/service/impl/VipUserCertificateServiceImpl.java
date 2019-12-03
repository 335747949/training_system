package com.ruoyi.vip.service.impl;

import java.util.List;

import com.ruoyi.vip.controller.ExcelVipUserCertification;
import com.ruoyi.vip.domain.vo.VipUserCertificateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.vip.mapper.VipUserCertificateMapper;
import com.ruoyi.vip.domain.VipUserCertificate;
import com.ruoyi.vip.service.IVipUserCertificateService;
import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
/**
 * 我的订单 服务层实现
 *
 * @author zhujj
 * @date 2019-01-15
 */
@Service
public class VipUserCertificateServiceImpl extends AbstractBaseServiceImpl<VipUserCertificateMapper,VipUserCertificate> implements IVipUserCertificateService
{
    @Autowired
    private VipUserCertificateMapper vipUserCertificateMapper;


    /**
     * 查询我的订单列表
     *
     * @param vipUserCertificate 我的订单信息
     * @return 我的订单集合
     */
    @Override
    public List<VipUserCertificateVO> selectVipUserCertificateList(VipUserCertificateVO vipUserCertificate)
    {
        return vipUserCertificateMapper.selectVipUserCertificateList(vipUserCertificate);
    }

    @Override
    public List<ExcelVipUserCertification> selectVipUserCertificateListFroExcel(VipUserCertificateVO vipUserCertificate) {

        return vipUserCertificateMapper.selectVipUserCertificateListFroExcel(vipUserCertificate);
    }

    /**
     * 查询我的订单分页列表
     *
     * @param vipUserCertificate 我的订单信息
     * @return 我的订单集合
     */
    @Override
    public List<VipUserCertificateVO> selectVipUserCertificatePage(VipUserCertificateVO vipUserCertificate)
    {
        startPage();
        return vipUserCertificateMapper.selectVipUserCertificateList(vipUserCertificate);
    }

}
