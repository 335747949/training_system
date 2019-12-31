package com.ruoyi.system.service;


import cn.hutool.json.JSONObject;

/**
 * 参数配置 服务层
 * 
 * @author ruoyi
 */
public interface AliyunOssClientService
{
    /**
     * 获取Policy签名等信息
     *
     * @param dir 存储在bucket的目录
     * @return
     */
    public JSONObject getPostObjectPolicy(String dir);
}
