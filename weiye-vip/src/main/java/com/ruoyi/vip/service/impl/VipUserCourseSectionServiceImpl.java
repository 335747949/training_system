package com.ruoyi.vip.service.impl;

import java.util.List;

import com.ruoyi.framework.web.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.vip.domain.vo.VipUserCourseSectionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.vip.mapper.VipUserCourseSectionMapper;
import com.ruoyi.vip.domain.VipUserCourseSection;
import com.ruoyi.vip.service.IVipUserCourseSectionService;
import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;

/**
 * 我的课程学习 服务层实现
 *
 * @author zhujj
 * @date 2019-01-15
 */
@Service
public class VipUserCourseSectionServiceImpl extends AbstractBaseServiceImpl<VipUserCourseSectionMapper, VipUserCourseSection> implements IVipUserCourseSectionService {
    @Autowired
    private VipUserCourseSectionMapper vipUserCourseSectionMapper;


    /**
     * 查询我的课程学习列表
     *
     * @param vipUserCourseSection 我的课程学习信息
     * @return 我的课程学习集合
     */
    @Override
    public List<VipUserCourseSectionVO> selectVipUserCourseSectionList(VipUserCourseSectionVO vipUserCourseSection) {
        return vipUserCourseSectionMapper.selectVipUserCourseSectionList( vipUserCourseSection );
    }

    @Override
    public void updateStudy(VipUserCourseSection trainCourseSection) {
        List<VipUserCourseSection> list = this.selectList( trainCourseSection );
        int duration = 1;
        if (list.size() > 0) {
            VipUserCourseSection vipUserCourseSection = list.get( 0 );
            vipUserCourseSection.setDuration( vipUserCourseSection.getDuration() + duration );
            this.updateById( vipUserCourseSection );
        } else {
            trainCourseSection.setDelFlag( "0" );
            trainCourseSection.setDuration( duration );
            this.insert( trainCourseSection );
        }
    }

    /**
     * 查询我的课程学习分页列表
     *
     * @param vipUserCourseSection 我的课程学习信息
     * @return 我的课程学习集合
     */
    @Override
    public List<VipUserCourseSectionVO> selectVipUserCourseSectionPage(VipUserCourseSectionVO vipUserCourseSection) {
        startPage();
        return vipUserCourseSectionMapper.selectVipUserCourseSectionList( vipUserCourseSection );
    }

}
