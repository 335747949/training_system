package com.ruoyi.train.course.service.impl;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.constant.ExamConstants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
import com.ruoyi.train.course.domain.TrainCourseCategory;
import com.ruoyi.train.course.domain.vo.ApiCourseCategoryVO;
import com.ruoyi.train.course.mapper.TrainCourseCategoryMapper;
import com.ruoyi.train.course.service.ITrainCourseCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课程分类管理 服务实现
 *
 * @author ruoyi
 */
@Service
public class TrainCourseCategoryServiceImpl extends AbstractBaseServiceImpl<TrainCourseCategoryMapper, TrainCourseCategory> implements ITrainCourseCategoryService {

    @Autowired
    private TrainCourseCategoryMapper trainCourseCategoryMapper;

    /**
     * 查询课程分类管理数据
     *
     * @return 课程分类信息集合
     */
    @Override
    @DataScope(tableAlias = "d")
    public List<TrainCourseCategory> selectCategoryList(TrainCourseCategory category) {
        return trainCourseCategoryMapper.selectCategoryList( category );
    }

    @Override
    @DataScope(tableAlias = "d")
    public List<TrainCourseCategory> selectAllCategoryList(TrainCourseCategory category) {
        return trainCourseCategoryMapper.selectAllCategoryList( category );
    }

    /**
     * 查询课程分类管理树
     *
     * @return 所有课程分类信息
     */
    @Override
    public List<Map<String, Object>> selectCategoryTree() {
        List<TrainCourseCategory> deptList = selectCategoryList( new TrainCourseCategory() );
        List<Map<String, Object>> trees = getTrees( deptList, false, null );
        return trees;
    }


    /**
     * 对象转课程分类树
     *
     * @param deptList     课程分类列表
     * @param isCheck      是否需要选中
     * @param roleCategoryList 角色已存在菜单列表
     * @return
     */
    public List<Map<String, Object>> getTrees(List<TrainCourseCategory> deptList, boolean isCheck, List<String> roleCategoryList) {

        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        for (TrainCourseCategory dept : deptList) {
            if (UserConstants.DEPT_NORMAL.equals( dept.getDelFlag() )) {
                Map<String, Object> deptMap = new HashMap<String, Object>();
                deptMap.put( "id", dept.getId() );
                deptMap.put( "pId", dept.getParentId() );
                deptMap.put( "name", dept.getName() );
                deptMap.put( "title", dept.getName() );
                if (isCheck) {
                    deptMap.put( "checked", roleCategoryList.contains( dept.getId() + dept.getName() ) );
                } else {
                    deptMap.put( "checked", false );
                }
                trees.add( deptMap );
            }
        }
        return trees;
    }

    /**
     * 查询课程分类人数
     *
     * @param parentId 课程分类ID
     * @return 结果
     */
    @Override
    public int selectCategoryCount(Long parentId) {
        TrainCourseCategory dept = new TrainCourseCategory();
        dept.setParentId( parentId );
        return trainCourseCategoryMapper.selectCategoryCount( dept );
    }

    /**
     * 查询课程分类是否存在用户
     *
     * @param deptId 课程分类ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkCategoryExistCourse(Long deptId) {
        int result = trainCourseCategoryMapper.checkCategoryExistCourse( deptId );
        return result > 0 ? true : false;
    }

    /**
     * 删除课程分类管理信息
     *
     * @param deptId 课程分类ID
     * @return 结果
     */
    @Override
    public int deleteCategoryById(Long deptId) {
        return trainCourseCategoryMapper.deleteCategoryById( deptId );
    }

    /**
     * 新增保存课程分类信息
     *
     * @param dept 课程分类信息
     * @return 结果
     */
    @Override
    public int insertCategory(TrainCourseCategory dept) {
        TrainCourseCategory info = trainCourseCategoryMapper.selectCategoryById( dept.getParentId() );
        dept.setParentIds( info.getParentIds() + "," + dept.getParentId() );
        return trainCourseCategoryMapper.insertCategory( dept );
    }

    /**
     * 修改保存课程分类信息
     *
     * @param dept 课程分类信息
     * @return 结果
     */
    @Override
    public int updateCategory(TrainCourseCategory dept) {
        TrainCourseCategory info = trainCourseCategoryMapper.selectCategoryById( dept.getParentId() );
        if (StringUtils.isNotNull( info )) {
            String ancestors = info.getParentIds() + "," + dept.getParentId();
            dept.setParentIds( ancestors );
            updateCategoryChildren( dept.getId(), ancestors );
        }
        return trainCourseCategoryMapper.updateCategory( dept );
    }

    /**
     * 修改子元素关系
     *
     * @param deptId    课程分类ID
     * @param ancestors 元素列表
     */
    public void updateCategoryChildren(Long deptId, String ancestors) {
        TrainCourseCategory dept = new TrainCourseCategory();
        dept.setParentId( deptId );
        List<TrainCourseCategory> childrens = trainCourseCategoryMapper.selectCategoryList( dept );
        for (TrainCourseCategory children : childrens) {
            children.setParentIds( ancestors + "," + dept.getParentId() );
        }
        if (childrens.size() > 0) {
            trainCourseCategoryMapper.updateCategoryChildren( childrens );
        }
    }

    /**
     * 根据课程分类ID查询信息
     *
     * @param deptId 课程分类ID
     * @return 课程分类信息
     */
    @Override
    public TrainCourseCategory selectCategoryById(Long deptId) {
        return trainCourseCategoryMapper.selectCategoryById( deptId );
    }

    /**
     * 校验课程分类名称是否唯一
     *
     * @param dept 课程分类信息
     * @return 结果
     */
    @Override
    public String checkCategoryNameUnique(TrainCourseCategory dept) {
        Long deptId = StringUtils.isNull( dept.getId() ) ? -1L : dept.getId();
        TrainCourseCategory info = trainCourseCategoryMapper.checkCategoryNameUnique( dept.getName(), dept.getParentId() );
        if (StringUtils.isNotNull( info ) && info.getId().longValue() != deptId.longValue()) {
            return UserConstants.DEPT_NAME_NOT_UNIQUE;
        }
        return UserConstants.DEPT_NAME_UNIQUE;
    }

    @Override
    public int updateCategoryByParentId(Long id, String updateBy) {
        return trainCourseCategoryMapper.updateCategoryByParentId(id,updateBy);
    }

    /**
     *
     * @param name
     * @param parentId
     * @return
     */
    @Override
    public String checkNameUnique(String name, Long parentId) {
        List<TrainCourseCategory> trainCourseCategoryList = trainCourseCategoryMapper.selectByNameAndParentId(name, parentId);
        if (CollectionUtils.isEmpty(trainCourseCategoryList)) {
            return ExamConstants.TRAIN_COURSE_CATEGORY_NAME_UNIQUE;
        }
        return ExamConstants.TRAIN_COURSE_CATEGORY_NAME_NOT_UNIQUE;
    }

    /**
     * api查询课程分类树
     * @return
     */

    @Override
    public List<ApiCourseCategoryVO> selectCategoryTreeList() {
        List<TrainCourseCategory> trainCourseCategoryList = trainCourseCategoryMapper.selectCategoryList(new TrainCourseCategory());
        return buildCategoryTreeData(trainCourseCategoryList);
    }

    /**
     * 构建课程分类树结构
     * 备注：
     *  1、只展示三级树结构
     *  2、默认的一级分类parent_id为100
     *  3、默认所有分类均增加项【全部】，其id为-1，name为全部
     * @param trainCourseCategoryList
     * @return
     */
    private List<ApiCourseCategoryVO> buildCategoryTreeData(List<TrainCourseCategory> trainCourseCategoryList) {
        List<ApiCourseCategoryVO> categoryVOTree = new ArrayList<>();
        // 默认每级的全部
        ApiCourseCategoryVO baseApiCategoryVO = new ApiCourseCategoryVO();
        baseApiCategoryVO.setId(-1L);
        baseApiCategoryVO.setPid(100L);
        baseApiCategoryVO.setName("全部");
        baseApiCategoryVO.setOrderNum("0");
        // 添加默认全部
        categoryVOTree.add(baseApiCategoryVO);
        // 一级分类
        for (TrainCourseCategory trainCourseCategory : trainCourseCategoryList) {
            // 其他一级分类，父亲id为100
            if (trainCourseCategory.getParentId().equals(100L)) {
                ApiCourseCategoryVO apiCourseCategoryVO1 = new ApiCourseCategoryVO();
                BeanUtils.copyProperties(trainCourseCategory, apiCourseCategoryVO1);
                apiCourseCategoryVO1.setPid(trainCourseCategory.getParentId());
                categoryVOTree.add(apiCourseCategoryVO1);
            }
        }
//        // 不存在其他分类项时不再需要显示【全部】
//        if (categoryVOTree.size() == 1) {
//            categoryVOTree.clear();
//        }
        // 二级分类
        for (ApiCourseCategoryVO apiCourseCategoryVO1 : categoryVOTree) {
            if (null != apiCourseCategoryVO1.getId()) {
                List<ApiCourseCategoryVO> secondCourseCategoryVOList = getListByParentId(trainCourseCategoryList, apiCourseCategoryVO1.getId());
                // 三级分类
                for (ApiCourseCategoryVO apiCourseCategoryVO2 : secondCourseCategoryVOList) {
                    if (null != apiCourseCategoryVO2.getId()) {
                        List<ApiCourseCategoryVO> thirdCourseCategoryVOList = getListByParentId(trainCourseCategoryList, apiCourseCategoryVO2.getId());
                        apiCourseCategoryVO2.setChildren(thirdCourseCategoryVOList);
                    }
                }
                apiCourseCategoryVO1.setChildren(secondCourseCategoryVOList);
            }
        }
        return categoryVOTree;
    }

    /**
     *
     * @param trainCourseCategoryList
     * @param parentId
     * @return
     */
    private List<ApiCourseCategoryVO> getListByParentId(List<TrainCourseCategory> trainCourseCategoryList , Long parentId) {
        List<ApiCourseCategoryVO> list = new ArrayList<>();
        // 默认每级的全部
        ApiCourseCategoryVO baseApiCategoryVO = new ApiCourseCategoryVO();
        baseApiCategoryVO.setId(-1L);
        baseApiCategoryVO.setName("全部");
        baseApiCategoryVO.setOrderNum("0");
        baseApiCategoryVO.setPid(parentId);
        // 默认全部
        list.add(baseApiCategoryVO);
        for (TrainCourseCategory trainCourseCategory : trainCourseCategoryList) {
            // 其他一级分类，父亲id为100
            if (trainCourseCategory.getParentId().equals(parentId)) {
                ApiCourseCategoryVO apiCourseCategoryVO = new ApiCourseCategoryVO();
                BeanUtils.copyProperties(trainCourseCategory, apiCourseCategoryVO);
                apiCourseCategoryVO.setPid(trainCourseCategory.getParentId());
                list.add(apiCourseCategoryVO);
            }
        }
//        // 不存在其他分类项时不再需要显示【全部】
//        if (list.size() == 1) {
//            list.clear();
//        }
        return list;
    }
}
