package com.ruoyi.web.controller.tool;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.exception.user.OssException;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.util.FileUploadUtils;
import com.ruoyi.framework.web.util.ShiroUtils;
import com.ruoyi.system.domain.SysOss;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysOssService;
import com.ruoyi.system.service.ISysPostService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.web.controller.system.SysProfileController;
import com.ruoyi.web.controller.system.cloud.CloudStorageService;
import com.ruoyi.web.controller.system.cloud.OSSFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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

    @Autowired
    private ISysOssService sysOssService;
    /**
     * 上传文件
     */
    @Log(title = "上传文件", businessType = BusinessType.INSERT)
    @PostMapping("/files")
    public AjaxResult updateAvatar(@RequestParam("file") MultipartFile file, String module) {
        try {
            if (!file.isEmpty()) {
                String originalFileName = file.getOriginalFilename();
                originalFileName.substring( originalFileName.lastIndexOf( "." ) );
                String filePath = "";
                //上传文件路径由模块参数（module）和上传的当天日期组成
                if (null != module) {
                    filePath = module + "/" + DateUtil.today() + "/";
                }
                String fileName = FileUploadUtils.upload( Global.getAvatarPath() + filePath, file, false, originalFileName );
                AjaxResult ajaxResult = new AjaxResult();
                ajaxResult.put( "fileName", filePath + fileName );
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
        if (file.isEmpty()) {
            throw new OssException( "上传文件不能为空" );
        }
        // 上传文件
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring( fileName.lastIndexOf( "." ) );
        CloudStorageService storage = OSSFactory.build();
        String url = storage.uploadSuffix( file.getBytes(), suffix );
        // 保存文件信息
        SysOss ossEntity = new SysOss();
        ossEntity.setUrl( url );
        ossEntity.setFileSuffix( suffix );
        ossEntity.setCreateBy( ShiroUtils.getLoginName() );
        ossEntity.setFileName( fileName );
        ossEntity.setCreateTime( new Date() );
        ossEntity.setService( storage.getService() );
        log.info("AliOSS上传结束");
        return toAjax( sysOssService.save( ossEntity ) ).put( "data", ossEntity.getUrl() );
    }
}