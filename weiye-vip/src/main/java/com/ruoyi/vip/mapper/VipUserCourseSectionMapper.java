package com.ruoyi.vip.mapper;

import com.ruoyi.vip.domain.VipUserCourseSection;
import java.util.List;
import com.ruoyi.framework.web.base.MyMapper;
import com.ruoyi.vip.domain.vo.VipUserCourseSectionVO;

/**
 * 我的课程学习 数据层
 * 
 * @author zhujj
 * @date 2019-01-15
 */
public interface VipUserCourseSectionMapper  extends MyMapper<VipUserCourseSection>
{

	/**
     * 查询我的课程学习列表
     * 
     * @param vipUserCourseSection 我的课程学习信息
     * @return 我的课程学习集合
     */
	public List<VipUserCourseSectionVO> selectVipUserCourseSectionList(VipUserCourseSectionVO vipUserCourseSection);
	
}