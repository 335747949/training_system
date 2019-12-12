package com.ruoyi.vip.service;

import com.ruoyi.vip.domain.VipUserCourseSection;
import java.util.List;
import com.ruoyi.framework.web.base.AbstractBaseService;
import com.ruoyi.vip.domain.vo.VipUserCourseSectionVO;

/**
 * 我的课程学习 服务层
 * 
 * @author zhujj
 * @date 2019-01-15
 */
public interface IVipUserCourseSectionService extends AbstractBaseService<VipUserCourseSection>
{
	/**
     * 查询我的课程学习分页列表
     *
     * @param vipUserCourseSection 我的课程学习信息
     * @return 我的课程学习集合
     */
	public List<VipUserCourseSectionVO> selectVipUserCourseSectionPage(VipUserCourseSectionVO vipUserCourseSection);
    /**
     * 查询我的课程学习列表
     *
     * @param vipUserCourseSection 我的课程学习信息
     * @return 我的课程学习集合
     */
    public List<VipUserCourseSectionVO> selectVipUserCourseSectionList(VipUserCourseSectionVO vipUserCourseSection);


    void updateStudy(VipUserCourseSection trainCourseSection);
}
