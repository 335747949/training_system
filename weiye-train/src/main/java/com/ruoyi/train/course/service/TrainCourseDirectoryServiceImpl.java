package com.ruoyi.train.course.service;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.constant.ExamConstants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
import com.ruoyi.train.course.domain.TrainCourseDirectory;
import com.ruoyi.train.course.mapper.TrainCourseDirectoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课程目录管理 服务实现
 *
 * @author ruoyi
 */
@Service
public class TrainCourseDirectoryServiceImpl extends AbstractBaseServiceImpl<TrainCourseDirectoryMapper, TrainCourseDirectory> implements ITrainCourseDirectoryService {

    @Autowired
    private TrainCourseDirectoryMapper trainCourseDirectoryMapper;

    /**
     * 查询课程目录管理数据
     *
     * @return 课程目录信息集合
     */
    @Override
    @DataScope(tableAlias = "d")
    public List<TrainCourseDirectory> selectDirectoryList(TrainCourseDirectory category) {
        return trainCourseDirectoryMapper.selectDirectoryList( category );
    }

    @Override
    @DataScope(tableAlias = "d")
    public List<TrainCourseDirectory> selectAllDirectoryList(TrainCourseDirectory category) {
        return trainCourseDirectoryMapper.selectAllDirectoryList( category );
    }

    /**
     * 查询课程目录管理树
     *
     * @return 所有课程目录信息
     */
    @Override
    public List<Map<String, Object>> selectDirectoryTree() {
        List<TrainCourseDirectory> deptList = selectDirectoryList( new TrainCourseDirectory() );
        List<Map<String, Object>> trees = getTrees( deptList, false, null );
        return trees;
    }


    /**
     * 对象转课程目录树
     *
     * @param deptList     课程目录列表
     * @param isCheck      是否需要选中
     * @param roleDirectoryList 角色已存在菜单列表
     * @return
     */
    public List<Map<String, Object>> getTrees(List<TrainCourseDirectory> deptList, boolean isCheck, List<String> roleDirectoryList) {

        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        for (TrainCourseDirectory dept : deptList) {
            if (UserConstants.DEPT_NORMAL.equals( dept.getDelFlag() )) {
                Map<String, Object> deptMap = new HashMap<String, Object>();
                deptMap.put( "id", dept.getId() );
                deptMap.put( "pId", dept.getParentId() );
                deptMap.put( "name", dept.getName() );
                deptMap.put( "title", dept.getName() );
                if (isCheck) {
                    deptMap.put( "checked", roleDirectoryList.contains( dept.getId() + dept.getName() ) );
                } else {
                    deptMap.put( "checked", false );
                }
                trees.add( deptMap );
            }
        }
        return trees;
    }

    /**
     * 查询课程目录人数
     *
     * @param parentId 课程目录ID
     * @return 结果
     */
    @Override
    public int selectDirectoryCount(Long parentId) {
        TrainCourseDirectory dept = new TrainCourseDirectory();
        dept.setParentId( parentId );
        return trainCourseDirectoryMapper.selectDirectoryCount( dept );
    }

    /**
     * 查询课程内容表是否存在使用
     *
     * @param deptId 课程目录ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkDirectoryExistCourse(Long deptId) {
        int result = trainCourseDirectoryMapper.checkDirectoryExistCourse( deptId );
        return result > 0 ? true : false;
    }

    /**
     * 删除课程目录管理信息
     *
     * @param deptId 课程目录ID
     * @return 结果
     */
    @Override
    public int deleteDirectoryById(Long deptId) {
        return trainCourseDirectoryMapper.deleteDirectoryById( deptId );
    }

    /**
     * 新增保存课程目录信息
     *
     * @param dept 课程目录信息
     * @return 结果
     */
    @Override
    public int insertDirectory(TrainCourseDirectory dept) {
        TrainCourseDirectory info = trainCourseDirectoryMapper.selectDirectoryById( dept.getParentId() );
        dept.setParentIds( info.getParentIds() + "," + dept.getParentId() );
        return trainCourseDirectoryMapper.insertDirectory( dept );
    }

    /**
     * 修改保存课程目录信息
     *
     * @param dept 课程目录信息
     * @return 结果
     */
    @Override
    public int updateDirectory(TrainCourseDirectory dept) {
        TrainCourseDirectory info = trainCourseDirectoryMapper.selectDirectoryById( dept.getParentId() );
        if (StringUtils.isNotNull( info )) {
            String ancestors = info.getParentIds() + "," + dept.getParentId();
            dept.setParentIds( ancestors );
            updateDirectoryChildren( dept.getId(), ancestors );
        }
        return trainCourseDirectoryMapper.updateDirectory( dept );
    }

    /**
     * 修改子元素关系
     *
     * @param deptId    课程目录ID
     * @param ancestors 元素列表
     */
    public void updateDirectoryChildren(Long deptId, String ancestors) {
        TrainCourseDirectory dept = new TrainCourseDirectory();
        dept.setParentId( deptId );
        List<TrainCourseDirectory> childrens = trainCourseDirectoryMapper.selectDirectoryList( dept );
        for (TrainCourseDirectory children : childrens) {
            children.setParentIds( ancestors + "," + dept.getParentId() );
        }
        if (childrens.size() > 0) {
            trainCourseDirectoryMapper.updateDirectoryChildren( childrens );
        }
    }

    /**
     * 根据课程目录ID查询信息
     *
     * @param deptId 课程目录ID
     * @return 课程目录信息
     */
    @Override
    public TrainCourseDirectory selectDirectoryById(Long deptId) {
        return trainCourseDirectoryMapper.selectDirectoryById( deptId );
    }

    /**
     * 校验课程目录名称是否唯一
     *
     * @param dept 课程目录信息
     * @return 结果
     */
    @Override
    public String checkDirectoryNameUnique(TrainCourseDirectory dept) {
        Long deptId = StringUtils.isNull( dept.getId() ) ? -1L : dept.getId();
        TrainCourseDirectory info = trainCourseDirectoryMapper.checkDirectoryNameUnique( dept.getName(), dept.getParentId() );
        if (StringUtils.isNotNull( info ) && info.getId().longValue() != deptId.longValue()) {
            return UserConstants.DEPT_NAME_NOT_UNIQUE;
        }
        return UserConstants.DEPT_NAME_UNIQUE;
    }

    @Override
    public int updateDirectoryByParentId(Long id, String updateBy) {
        return trainCourseDirectoryMapper.updateDirectoryByParentId(id,updateBy);
    }

    /**
     *
     * @param name
     * @param parentId
     * @return
     */
    @Override
    public String checkNameUnique(String name, Long parentId) {
        List<TrainCourseDirectory> trainCourseDirectoryList = trainCourseDirectoryMapper.selectByNameAndParentId(name, parentId);
        if (CollectionUtils.isEmpty(trainCourseDirectoryList)) {
            return ExamConstants.TRAIN_COURSE_DIRECTORY_NAME_UNIQUE;
        }
        return ExamConstants.TRAIN_COURSE_DIRECTORY_NAME_NOT_UNIQUE;
    }

//    /**
//     * api查询课程目录树
//     * @return
//     */
//
//    @Override
//    public List<ApiCourseDirectoryVO> selectCategoryTreeList() {
//        List<TrainCourseCategory> trainCourseCategoryList = trainCourseCategoryMapper.selectCategoryList(new TrainCourseCategory());
//        return buildCategoryTreeData(trainCourseCategoryList);
//    }
//
//    /**
//     * 构建课程目录树结构
//     * 备注：
//     *  1、只展示三级树结构
//     *  2、默认的一级目录parent_id为100
//     *  3、默认所有目录均增加项【全部】，其id为-1，name为全部
//     * @param trainCourseCategoryList
//     * @return
//     */
//    private List<ApiCourseCategoryVO> buildCategoryTreeData(List<TrainCourseCategory> trainCourseCategoryList) {
//        List<ApiCourseCategoryVO> categoryVOTree = new ArrayList<>();
//        // 默认每级的全部
//        ApiCourseCategoryVO baseApiCategoryVO = new ApiCourseCategoryVO();
//        baseApiCategoryVO.setId(-1L);
//        baseApiCategoryVO.setPid(100L);
//        baseApiCategoryVO.setName("全部");
//        baseApiCategoryVO.setOrderNum("0");
//        // 添加默认全部
//        categoryVOTree.add(baseApiCategoryVO);
//        // 一级目录
//        for (TrainCourseCategory trainCourseCategory : trainCourseCategoryList) {
//            // 其他一级目录，父亲id为100
//            if (trainCourseCategory.getParentId().equals(100L)) {
//                ApiCourseCategoryVO apiCourseCategoryVO1 = new ApiCourseCategoryVO();
//                BeanUtils.copyProperties(trainCourseCategory, apiCourseCategoryVO1);
//                apiCourseCategoryVO1.setPid(trainCourseCategory.getParentId());
//                categoryVOTree.add(apiCourseCategoryVO1);
//            }
//        }
////        // 不存在其他目录项时不再需要显示【全部】
////        if (categoryVOTree.size() == 1) {
////            categoryVOTree.clear();
////        }
//        // 二级目录
//        for (ApiCourseCategoryVO apiCourseCategoryVO1 : categoryVOTree) {
//            if (null != apiCourseCategoryVO1.getId()) {
//                List<ApiCourseCategoryVO> secondCourseCategoryVOList = getListByParentId(trainCourseCategoryList, apiCourseCategoryVO1.getId());
//                // 三级目录
//                for (ApiCourseCategoryVO apiCourseCategoryVO2 : secondCourseCategoryVOList) {
//                    if (null != apiCourseCategoryVO2.getId()) {
//                        List<ApiCourseCategoryVO> thirdCourseCategoryVOList = getListByParentId(trainCourseCategoryList, apiCourseCategoryVO2.getId());
//                        apiCourseCategoryVO2.setChildren(thirdCourseCategoryVOList);
//                    }
//                }
//                apiCourseCategoryVO1.setChildren(secondCourseCategoryVOList);
//            }
//        }
//        return categoryVOTree;
//    }
//
//    /**
//     *
//     * @param trainCourseCategoryList
//     * @param parentId
//     * @return
//     */
//    private List<ApiCourseCategoryVO> getListByParentId(List<TrainCourseCategory> trainCourseCategoryList , Long parentId) {
//        List<ApiCourseCategoryVO> list = new ArrayList<>();
//        // 默认每级的全部
//        ApiCourseCategoryVO baseApiCategoryVO = new ApiCourseCategoryVO();
//        baseApiCategoryVO.setId(-1L);
//        baseApiCategoryVO.setName("全部");
//        baseApiCategoryVO.setOrderNum("0");
//        baseApiCategoryVO.setPid(parentId);
//        // 默认全部
//        list.add(baseApiCategoryVO);
//        for (TrainCourseCategory trainCourseCategory : trainCourseCategoryList) {
//            // 其他一级目录，父亲id为100
//            if (trainCourseCategory.getParentId().equals(parentId)) {
//                ApiCourseCategoryVO apiCourseCategoryVO = new ApiCourseCategoryVO();
//                BeanUtils.copyProperties(trainCourseCategory, apiCourseCategoryVO);
//                apiCourseCategoryVO.setPid(trainCourseCategory.getParentId());
//                list.add(apiCourseCategoryVO);
//            }
//        }
////        // 不存在其他目录项时不再需要显示【全部】
////        if (list.size() == 1) {
////            list.clear();
////        }
//        return list;
//    }
}
