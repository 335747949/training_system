package com.ruoyi.vip.mapper;

import com.ruoyi.vip.controller.ExcelVipUserCertification;
import com.ruoyi.vip.domain.VipUserCertificate;
import java.util.List;
import com.ruoyi.framework.web.base.MyMapper;
import com.ruoyi.vip.domain.vo.VipUserCertificateVO;

/**
 * 我的订单 数据层
 * 
 * @author zhujj
 * @date 2019-01-15
 */
public interface VipUserCertificateMapper  extends MyMapper<VipUserCertificate>
{

	/**
     * 查询我的订单列表
     * 
     * @param vipUserCertificate 我的订单信息
     * @return 我的订单集合
     */
	public List<VipUserCertificateVO> selectVipUserCertificateList(VipUserCertificateVO vipUserCertificate);

    List<ExcelVipUserCertification> selectVipUserCertificateListFroExcel(VipUserCertificateVO vipUserCertificate);
}