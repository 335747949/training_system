package com.ruoyi.web.controller.tool;

import cn.hutool.json.JSONObject;
import com.ruoyi.system.service.impl.AliyunOSSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("aliyun/oss")
public class AliyunOSSRest {

    @Autowired
    private AliyunOSSClient aliyunOSSClient ;
    @GetMapping("policy.json")
    public JSONObject getPolicy(String id) {
         String dir = String.valueOf(System.currentTimeMillis());
         return aliyunOSSClient .getPostObjectPolicy(dir);
    }
}