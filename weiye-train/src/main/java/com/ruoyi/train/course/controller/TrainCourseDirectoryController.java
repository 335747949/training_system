package com.ruoyi.train.course.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.constant.ExamConstants;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.util.ShiroUtils;
import com.ruoyi.train.course.domain.TrainCourseDirectory;
import com.ruoyi.train.course.service.ITrainCourseDirectoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 课程目录管理
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/train/course/directory")
public class TrainCourseDirectoryController extends BaseController {
    private String prefix = "train/course/directory";

    @Autowired
    private ITrainCourseDirectoryService trainCourseDirectoryService;

    @RequiresPermissions("train:course:Directory:view")
    @GetMapping()
    public String directory() {
        return prefix + "/directory";
    }

    @RequiresPermissions("train:course:Directory:list")
    @GetMapping("/list")
    @ResponseBody
    public List<TrainCourseDirectory> list(TrainCourseDirectory directory) {
        List<TrainCourseDirectory> directoryList = trainCourseDirectoryService.selectAllDirectoryList( directory );
        return directoryList;
    }

    /**
     * 新增课程目录
     */
    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId, ModelMap mmap, HttpServletResponse response) throws IOException {
        TrainCourseDirectory directory = trainCourseDirectoryService.selectDirectoryById( parentId );
        mmap.put( "directory",  directory);
        return prefix + "/add";
    }

    /**
     * 新增保存课程目录
     */
    @Log(title = "课程目录管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("train:course:directory:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TrainCourseDirectory directory) {
        // 目录名称
        Assert.hasText(directory.getName(), "课程目录不能为空！");
        // 暂时最大仅支持二级目录
        String ids = directory.getParentIds();
        String[] idsStr = ids.split(",");
        if(idsStr.length > 2){
            return AjaxResult.error("暂时最大仅支持二级目录");
        }
        // 重复性校验
        String checkNameUnique = trainCourseDirectoryService.checkNameUnique(directory.getName(), directory.getParentId());
        if (checkNameUnique.equals(ExamConstants.TRAIN_COURSE_DIRECTORY_NAME_NOT_UNIQUE)) {
            return error("同一课程目录下目录名称不能重复！");
        }
        directory.setCreateBy( ShiroUtils.getLoginName() );
        return toAjax( trainCourseDirectoryService.insertDirectory( directory ) );
    }

    /**
     * 修改
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        TrainCourseDirectory directory = trainCourseDirectoryService.selectDirectoryById( id );
        mmap.put( "directory", directory );
        return prefix + "/edit";
    }

    /**
     * 保存
     */
    @Log(title = "课程目录管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("train:course:directory:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TrainCourseDirectory directory) {
        Assert.notNull(directory.getId(), "课程目录ID为必填项！");
        Assert.hasText(directory.getName(), "课程目录不能为空！");
        TrainCourseDirectory directoryOld = trainCourseDirectoryService.selectDirectoryById(directory.getId());
        if(StringUtils.isNotNull(directoryOld) && !directory.getName().equals(directoryOld.getName())) {
            String checkNameUnique = trainCourseDirectoryService.checkNameUnique(directory.getName(), directory.getParentId());
            if (checkNameUnique.equals(ExamConstants.TRAIN_COURSE_CATEGORY_NAME_NOT_UNIQUE)) {
                return error("同一课程目录下目录名称不能重复！");
            }
        }
        if (directoryOld.getStatus() != directory.getStatus()){
            if (directory.getStatus() == 0){
                // 停用变为正常
                directory.setUpdateBy( ShiroUtils.getLoginName() );
                return toAjax( trainCourseDirectoryService.updateDirectory( directory ) );
            }else {
                // 正常变为停用
                if (trainCourseDirectoryService.checkDirectoryExistCourse( directory.getId() )) {
                    return error( 1, "课程目录存在用户,不允许停用" );
                }

                //停用当前节点及所有子节点
                trainCourseDirectoryService.updateDirectoryByParentId(directory.getId(), ShiroUtils.getLoginName());
                return AjaxResult.success();
            }
        }else {
            directory.setUpdateBy( ShiroUtils.getLoginName() );
            return toAjax( trainCourseDirectoryService.updateDirectory( directory ) );
        }
    }

    /**
     * 删除
     */
    @Log(title = "课程目录管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("train:course:directory:remove")
    @PostMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id) {
        if (trainCourseDirectoryService.selectDirectoryCount( id ) > 0) {
            return error( 1, "存在下级课程目录,不允许删除" );
        }
        if (trainCourseDirectoryService.checkDirectoryExistCourse( id )) {
            return error( 1, "课程目录在使用中,不允许删除" );
        }
        return toAjax( trainCourseDirectoryService.deleteDirectoryById( id ) );
    }

    /**
     * 校验课程目录名称
     */
    @PostMapping("/checkDirectoryNameUnique")
    @ResponseBody
    public String checkDirectoryNameUnique(TrainCourseDirectory directory) {
        return trainCourseDirectoryService.checkDirectoryNameUnique( directory );
    }

    /**
     * 选择课程目录树
     */
    @GetMapping("/selectDirectoryTree/{id}")
    public String selectCategoryTree(@PathVariable("id") Long id, ModelMap mmap) {
        mmap.put( "directory", trainCourseDirectoryService.selectDirectoryById( id ) );
        return prefix + "/tree";
    }

    /**
     * 加载课程目录列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Map<String, Object>> treeData() {
        List<Map<String, Object>> tree = trainCourseDirectoryService.selectDirectoryTree();
        return tree;
    }

}
