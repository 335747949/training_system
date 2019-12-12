package com.ruoyi.web.controller.tool;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.exception.user.OssException;
import com.ruoyi.framework.web.util.ShiroUtils;
import com.ruoyi.system.domain.SysOss;
import com.ruoyi.system.service.ISysOssService;
import com.ruoyi.web.controller.system.cloud.CloudStorageService;
import com.ruoyi.web.controller.system.cloud.OSSFactory;
import org.apache.commons.io.FileUtils;
import org.jodconverter.DocumentConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
 * 用户信息
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/upload")
public class UploadFileController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger( UploadFileController.class );


    @Value("${office.suffix}")
    private String officeSuffix;

    @Value("${spring.servlet.multipart.location}")
    private String filePath;

    @Autowired
    private ISysOssService sysOssService;

    @Autowired
    private DocumentConverter documentConverter;

    /**
     * 上传文件
     */
    @Log(title = "上传文件", businessType = BusinessType.INSERT)
    @PostMapping("/files")
    public AjaxResult updateAvatar(@RequestParam("file") MultipartFile file, String module) {
        try {
            if (!file.isEmpty()) {
                // TODO 修改为七牛云上传
                String contentType = file.getContentType();
                String suffix = contentType.substring(contentType.lastIndexOf("/"));
                CloudStorageService storage = OSSFactory.build();
                String avatar = storage.uploadSuffix(file.getBytes(), suffix);

                AjaxResult ajaxResult = new AjaxResult();
                ajaxResult.put( "fileName", avatar );
                ajaxResult.put( "code", "200" );
                ajaxResult.put( "msg", "上传成功" );
                return ajaxResult;
            }
            return error();
        } catch (Exception e) {
            log.error( "上传失败！", e );
            return error( e.getMessage() );
        }
    }

    /**
     * 上传文件
     */
    /**
     * 上传文件
     */
    @Log(title = "OSS上传文件", businessType = BusinessType.INSERT)
    @PostMapping("/oss")
    public AjaxResult upload(@RequestParam("file") MultipartFile file, String module) throws Exception {
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
        // 保存文件信息
        SysOss ossEntity = new SysOss();

        // 获取后缀,若为office文件，直接转换成pdf
        if (suffixNames.contains(suffix)){
            File f = new File(fileName);
            FileUtils.copyInputStreamToFile(file.getInputStream(),f);
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String timesuffix = sdf.format(date);
            String pdfFileName = null;
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

            // 若文件目录不存在，创建文件目录
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

        if (!ObjectUtils.isEmpty(pdflOutputFile) && pdflOutputFile.exists()){
            FileUtils.deleteQuietly(pdflOutputFile);
        }

        return toAjax(sysOssService.save(ossEntity)).put("data", ossEntity.getUrl());
    }

}