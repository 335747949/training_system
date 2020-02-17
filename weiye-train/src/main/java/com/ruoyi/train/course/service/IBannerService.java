package com.ruoyi.train.course.service;

import java.util.List;
import com.ruoyi.framework.web.base.AbstractBaseService;
import com.ruoyi.train.course.domain.Banner;

/**
 * banner 服务层
 * 
 * @author zhujj
 * @date 2020-02-04
 */
public interface IBannerService extends AbstractBaseService<Banner>
{
	/**
     * 查询banner分页列表
     *
     * @param banner banner信息
     * @return banner集合
     */
	public List<Banner> selectBannerPage(Banner banner);
    /**
     * 查询banner列表
     *
     * @param banner banner信息
     * @return banner集合
     */
    public List<Banner> selectBannerList(Banner banner);


    String checkBannerNameUnique(Banner banner);

    List<Banner> selectBanners();
}
