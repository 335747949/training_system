package com.ruoyi.system.service.impl;

import cn.hutool.json.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.ruoyi.framework.web.service.ConfigService;
import com.ruoyi.system.service.AliyunOssClientService;
import com.ruoyi.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class AliyunOSSClient implements AliyunOssClientService {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucket;

    private OSSClient ossClient;
    @Autowired
    private ISysConfigService configService;

    public AliyunOSSClient() {
    }

    @PostConstruct
    void init() {
//        System.out.println( "==============" );
//        this.endpoint = configService.selectConfigByKey( "ali.oss.endpoint");
//        this.accessKeyId = configService.selectConfigByKey("ali.oss.accessKeyId");
//        this.accessKeySecret = configService.selectConfigByKey("ali.oss.accessKeySecret");
//        this.bucket = configService.selectConfigByKey("ali.oss.bucket");
//        if (this.ossClient == null) {
//            this.ossClient = new OSSClient(this.endpoint, this.accessKeyId, this.accessKeySecret);
//        }
    }

    /**
     * 获取Policy签名等信息
     *
     * @param dir 存储在bucket的目录
     * @return
     */
    public JSONObject getPostObjectPolicy(String dir) {
        long expiredSeconds = 60;
        long expireEndTime = System.currentTimeMillis() + expiredSeconds * 1000;
        Date expiration = new Date(expireEndTime);
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

        String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
        byte[] binaryData = new byte[0];
        try {
            binaryData = postPolicy.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = ossClient.calculatePostSignature(postPolicy);

        JSONObject policy = new JSONObject();
        policy.put("accessId", accessKeyId);
        policy.put("host", "https://" + bucket + "." + endpoint);
        policy.put("dir", dir);
        policy.put("accessId", accessKeyId);
        policy.put("expire", String.valueOf(expireEndTime / 1000));
        policy.put("policy", encodedPolicy);
        policy.put("signature", postSignature);

//        JSONObject jasonCallback = new JSONObject();
//        String filename = UUID.randomUUID();
//        jasonCallback.put("callbackUrl", OSSConfig.CALL_BACK_URL);
//        jasonCallback.put("callbackBody", "filename=" + filename + "_${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
//        jasonCallback.put("callbackBodyType", "application/x-www-form-urlencoded");
//        String base64CallbackBody = BinaryUtil.toBase64String(jasonCallback.toString().getBytes());
//        policy.put("callback", base64CallbackBody);

        return policy;
    }
}