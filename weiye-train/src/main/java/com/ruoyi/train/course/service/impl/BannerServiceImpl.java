package com.ruoyi.train.course.service.impl;

import java.util.List;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.train.course.domain.Banner;
import com.ruoyi.train.course.mapper.BannerMapper;
import com.ruoyi.train.course.service.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
/**
 * banner 服务层实现
 * 
 * @author zhujj
 * @date 2020-02-04
 */
@Service
public class BannerServiceImpl extends AbstractBaseServiceImpl<BannerMapper, Banner> implements IBannerService
{
	@Autowired
	private BannerMapper bannerMapper;

	
	/**
     * 查询banner列表
     * 
     * @param banner banner信息
     * @return banner集合
     */
	@Override
	public List<Banner> selectBannerList(Banner banner)
	{
        return bannerMapper.selectBannerList(banner);
	}

    /**
     * 查询banner分页列表
     *
     * @param banner banner信息
     * @return banner集合
     */
    @Override
    public List<Banner> selectBannerPage(Banner banner)
    {
        startPage();
        return bannerMapper.selectBannerList(banner);
    }


    @Override
    public String checkBannerNameUnique(Banner banner) {
        Long id = StringUtils.isNull( banner.getId() ) ? -1L : banner.getId();
        Banner banner1 = bannerMapper.checkBannerNameUnique(banner.getName());
        if (StringUtils.isNotNull( banner1 ) && banner1.getId().longValue() != id.longValue()) {
            return "1";
        }
        return "0";
    }

    @Override
    public List<Banner> selectBanners() {
        return bannerMapper.selectBanners();
    }

}
