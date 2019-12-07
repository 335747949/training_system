package com.ruoyi.web.controller.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.exception.user.OssException;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.util.ShiroUtils;
import com.ruoyi.framework.web.util.ValidatorUtils;
import com.ruoyi.system.domain.SysOss;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysOssService;
import com.ruoyi.web.controller.system.cloud.CloudConstant;
import com.ruoyi.web.controller.system.cloud.CloudConstant.CloudService;
import com.ruoyi.web.controller.system.cloud.CloudStorageConfig;
import com.ruoyi.web.controller.system.cloud.CloudStorageService;
import com.ruoyi.web.controller.system.cloud.OSSFactory;
import com.ruoyi.web.controller.system.cloud.valdator.AliyunGroup;
import com.ruoyi.web.controller.system.cloud.valdator.QcloudGroup;
import com.ruoyi.web.controller.system.cloud.valdator.QiniuGroup;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * 文件上传
 */
@Controller
@RequestMapping("system/oss")
public class SysOssController extends BaseController
{
    private String              prefix = "system/oss";

    private final static String KEY    = CloudConstant.CLOUD_STORAGE_CONFIG_KEY;

    @Autowired
    private ISysOssService      sysOssService;

    @Autowired
    private ISysConfigService sysConfigService;

    @RequiresPermissions("system:dept:view")
    @GetMapping()
    public String dept()
    {
        return prefix + "/oss";
    }

    /**
     * 列表
     */
    @RequestMapping("list")
    @RequiresPermissions("sys:oss:list")
    @ResponseBody
    public TableDataInfo list(SysOss sysOss)
    {
        List<SysOss> list = sysOssService.getList(sysOss);
        return getDataTable(list);
    }

    /**
     * 云存储配置信息
     */
    @RequestMapping("config")
    @RequiresPermissions("sys:oss:config")
    public String config(Model model)
    {
        String jsonconfig = sysConfigService.selectConfigByKey(CloudConstant.CLOUD_STORAGE_CONFIG_KEY);
        // 获取云存储配置信息
        CloudStorageConfig config = JSON.parseObject(jsonconfig, CloudStorageConfig.class);
        model.addAttribute("config", config);
        return prefix + "/config";
    }

    /**
     * 保存云存储配置信息
     */
    @RequestMapping("saveConfig")
    @RequiresPermissions("sys:oss:config")
    @ResponseBody
    public AjaxResult saveConfig(CloudStorageConfig config)
    {
        // 校验类型
        ValidatorUtils.validateEntity(config);
        if (config.getType() == CloudService.QINIU.getValue())
        {
            // 校验七牛数据
            ValidatorUtils.validateEntity(config, QiniuGroup.class);
        }
        else if (config.getType() == CloudService.ALIYUN.getValue())
        {
            // 校验阿里云数据
            ValidatorUtils.validateEntity(config, AliyunGroup.class);
        }
        else if (config.getType() == CloudService.QCLOUD.getValue())
        {
            // 校验腾讯云数据
            ValidatorUtils.validateEntity(config, QcloudGroup.class);
        }
        return toAjax(sysConfigService.updateValueByKey(KEY, JSONObject.toJSONString(config)));
    }

    /**
     * 上传文件
     */
    @RequestMapping("/upload")
    @ResponseBody
    public AjaxResult upload(@RequestParam("file") MultipartFile file) throws Exception
    {
        if (file.isEmpty())
        {
            throw new OssException("上传文件不能为空");
        }
        // 上传文件
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        CloudStorageService storage = OSSFactory.build();
        String url = storage.uploadSuffix(file.getBytes(), suffix);
        // 保存文件信息
        SysOss ossEntity = new SysOss();
        ossEntity.setUrl(url);
        ossEntity.setFileSuffix(suffix);
        ossEntity.setCreateBy(ShiroUtils.getLoginName());
        ossEntity.setFileName(fileName);
        ossEntity.setCreateTime(new Date());
        ossEntity.setService(storage.getService());
        return toAjax(sysOssService.save(ossEntity)).put("data", ossEntity.getUrl());
    }

    /**
     * 修改
     */
    @GetMapping("edit/{ossId}")
    @RequiresPermissions("sys:oss:edit")
    public String edit(@PathVariable("ossId") Long ossId, Model model)
    {
        SysOss sysOss = sysOssService.findById(ossId);
        model.addAttribute("sysOss", sysOss);
        return prefix + "/edit";
    }

    @GetMapping("editor")
    @RequiresPermissions("sys:oss:add")
    public String editor()
    {
        return prefix + "/editor";
    }

    /**
     * 修改
     */
    @PostMapping("edit")
    @RequiresPermissions("sys:oss:edit")
    @ResponseBody
    public AjaxResult editSave(SysOss sysOss)
    {
        return toAjax(sysOssService.update(sysOss));
    }

    /**
     * 删除
     */
    @RequestMapping("remove")
    @RequiresPermissions("sys:oss:remove")
    @ResponseBody
    public AjaxResult delete(String ids)
    {
        return toAjax(sysOssService.deleteByIds(ids));
    }
}
