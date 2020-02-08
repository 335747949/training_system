package com.ruoyi.train.course.mapper;

import java.util.List;
import com.ruoyi.framework.web.base.MyMapper;
import com.ruoyi.train.course.domain.Banner;

/**
 * banner 数据层
 * 
 * @author zhujj
 * @date 2020-02-04
 */
public interface BannerMapper  extends MyMapper<Banner>
{

	/**
     * 查询banner列表
     * 
     * @param banner banner信息
     * @return banner集合
     */
	public List<Banner> selectBannerList(Banner banner);

	Banner checkBannerNameUnique(String name);

	List<Banner> selectBanners();
}