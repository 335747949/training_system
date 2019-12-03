package com.ruoyi.vip.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;

import java.util.Date;

/**
 * Created by flower on 2019/2/24.
 */
public class ExcelVipUserCertification {

    @Excel(name = "会员名称",order = 0)
    private String userName;

    @Excel(name = "证书名称",order = 1)
    private String name;

    @Excel(name = "生效日期",order = 2,dateFormat = "yyyy-MM-dd")
    private Date startDate;

    @Excel(name = "截止日期",order = 3,dateFormat = "yyyy-MM-dd")
    private Date endDate;

}
