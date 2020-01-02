package com.ruoyi.web.controller.system.cloud;

import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;
import com.ruoyi.framework.web.exception.user.OssException;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

/**
 * 七牛云存储
 */
public class QiniuCloudStorageService extends CloudStorageService
{
    private UploadManager uploadManager;

    private String        token;

    public QiniuCloudStorageService(CloudStorageConfig config)
    {
        this.config = config;
        // 初始化
        init();
    }

    private void init()
    {
        uploadManager = new UploadManager(new Configuration(Zone.autoZone()));
        token = Auth.create(config.getQiniuAccessKey(), config.getQiniuSecretKey())
                .uploadToken(config.getQiniuBucketName());
    }

    @Override
    public String upload(byte[] data, String path)
    {
        try
        {
            Response res = uploadManager.put(data, path, token);
            if (!res.isOK())
            {
                throw new RuntimeException("上传七牛出错：" + res.toString());
            }
        }
        catch (Exception e)
        {
            throw new OssException("上传文件失败，请核对七牛配置信息");
        }
        return config.getQiniuDomain() + "/" + path;
    }

    @Override
    public String upload(InputStream inputStream, String path)
    {
        try
        {
            byte[] data = IOUtils.toByteArray(inputStream);
            return this.upload(data, path);
        }
        catch (IOException e)
        {
            throw new OssException("上传文件失败");
        }
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix)
    {
        return upload(data, getPath(config.getQiniuPrefix(), suffix));
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix)
    {
        return upload(inputStream, getPath(config.getQiniuPrefix(), suffix));
    }

    @Override
    public String getToken(){
        return token;
    }


    /**
     *
     * @param data
     * @param suffix
     * @param localPath
     * @return
     */
    public String BreakpointRenewalUpload(byte[] data, String suffix, String localPath){
        String localTempDir = Paths.get(localPath, config.getQiniuBucketName()).toString();
        try {
            //设置断点续传文件进度保存目录
            FileRecorder fileRecorder = new FileRecorder(localTempDir);
            //构造一个带指定 Region 对象的配置类
            Configuration cfg = new Configuration(Region.region0());
            String path = getPath(config.getQiniuPrefix(), suffix);
            try
            {
                UploadManager breakpointUploadManager = new UploadManager(cfg, fileRecorder);

                Response response = breakpointUploadManager.put(data, path, token);
                if (!response.isOK())
                {
                    throw new RuntimeException("上传七牛出错：" + response.toString());
                }
            }catch (Exception e)
            {
                throw new OssException("上传文件失败，请核对七牛配置信息");
            }
            return config.getQiniuDomain() + "/" + path;
        }catch (IOException e){
            throw new OssException("上传文件失败");
        }

    }
}
