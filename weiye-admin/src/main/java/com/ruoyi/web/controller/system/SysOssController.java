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
import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jodconverter.DocumentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 文件上传
 */
@Controller
@RequestMapping("system/oss")
public class SysOssController extends BaseController
{
    private String prefix = "system/oss";

    @Value("${office.suffix}")
    private String officeSuffix;

    @Value("${spring.servlet.multipart.location}")
    private String filePath;

    @Autowired
    private DocumentConverter documentConverter;

    private final static String KEY = CloudConstant.CLOUD_STORAGE_CONFIG_KEY;

    @Autowired
    private ISysOssService sysOssService;

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
    @RequiresPermissions("sys:oss:add")
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
        String suffix = fileName.substring( fileName.lastIndexOf( "." ) );
        // 如果文件为office文件，统一转换为pdf
        List<String> suffixNames = Arrays.asList(officeSuffix.split(","));
        CloudStorageService storage = OSSFactory.build();
        String url;
        String originUrl;
        File pdflOutputFile = null;
        // 保存文件信息实体
        SysOss ossEntity = new SysOss();
        // 获取后缀,若为office文件，直接转换成pdf
        if (suffixNames.contains(suffix)){
            File f = new File(fileName);
            FileUtils.copyInputStreamToFile(file.getInputStream(),f);
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String timesuffix = sdf.format(date);
            String pdfFileName;
            String name = fileName.substring(0,fileName.lastIndexOf( "." ));
            if(".doc".equals(suffix)){
                pdfFileName = name + "_" + timesuffix + ".pdf";
            }else if(".docx".equals(suffix)){
                pdfFileName = name + "_" + timesuffix + ".pdf";
            }else if(".xls".equals(suffix)){
                pdfFileName = name + "_" + timesuffix + ".pdf";
            }else if(".xlsx".equals(suffix)){
                pdfFileName = name + "_" + timesuffix + ".pdf";
            }else if(".ppt".equals(suffix)){
                pdfFileName = name + "_" + timesuffix + ".pdf";
            }else if(".pptx".equals(suffix)){
                pdfFileName = name + "_" + timesuffix + ".pdf";
            }else if(".txt".equals(suffix)){
                pdfFileName = name + "_" + timesuffix + ".pdf";
            }else{
                return null;
            }

            File file1 = new File(filePath);
            if (!file1.exists()){
                file1.createNewFile();
            }

            pdflOutputFile =  new File(filePath + File.separatorChar +pdfFileName);
            if (!pdflOutputFile.exists()) {
                // 文件转换为pdf
                documentConverter.convert(f).to(pdflOutputFile).execute();
            }
            FileInputStream fis = new FileInputStream(pdflOutputFile);
            url = storage.uploadSuffix(fis, ".pdf" );
            originUrl = storage.uploadSuffix( file.getBytes(), suffix );
            // 关闭文件流，删除pdf文件
            fis.close();
            FileUtils.deleteQuietly(pdflOutputFile);
            ossEntity.setFileName( fileName.substring(0,fileName.lastIndexOf( "." )) + ".pdf");
            ossEntity.setFileSuffix(".pdf");
        }else {
            url = storage.uploadSuffix( file.getBytes(), suffix );
            originUrl = url;
        }
        ossEntity.setUrl(url);
        ossEntity.setOriginUrl(originUrl);
        ossEntity.setFileSuffix(suffix);
        ossEntity.setCreateBy(ShiroUtils.getLoginName());
        ossEntity.setFileName( fileName);
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
    @RequiresPermissions("sys:oss:edit")
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
