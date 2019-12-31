package com.ruoyi.wx.cp.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.wx.cp.config.WxCpConfiguration;
import com.ruoyi.wx.cp.config.WxCpProperties;
import com.ruoyi.wx.cp.constant.ErrorCodeText;
import com.ruoyi.wx.cp.utils.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpDepartmentService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpDepart;
import me.chanjar.weixin.cp.bean.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.WxCpXmlOutMessage;
import me.chanjar.weixin.cp.config.WxCpInMemoryConfigStorage;
import me.chanjar.weixin.cp.util.crypto.WxCpCryptUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 同步
 * @author zhujj
 */
@RestController
@RequestMapping("/wx/cp/department")
public class WxDepartmentController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired

    private WxCpProperties properties;

    /**
     * 获取指定部门及其下的子部门。 如果不填，默认获取全量组织架构
     * @param id
     * @return
     */
    @GetMapping("/departAllList")
    public AjaxResult departAllList(Long id) {
        try {
            final WxCpService wxCpService = WxCpConfiguration.getCpService(999999);
            WxCpDepartmentService departmentService = wxCpService.getDepartmentService();
            List<WxCpDepart> list = departmentService.list(id);
            return AjaxResult.success(list,"获取组织机构成功");
        } catch (WxErrorException e) {
            return AjaxResult.error("获取组织机构出错，错误码【"+ e.getError().getErrorCode()+"】，原因："+ ErrorCodeText.errorMsg(e.getError().getErrorCode()));
        }
    }

    @Log(title = "新增组织机构", businessType = BusinessType.INSERT)
    @PostMapping("/insert")
    public AjaxResult insert(@RequestBody WxCpDepart wxCpDepart) {
        try {
            WxCpDepartmentService departmentService = WxCpConfiguration.getCpService(999999).getDepartmentService();
            Integer id = departmentService.create(wxCpDepart);
            return AjaxResult.success(id,"新增组织机构成功");
        } catch (WxErrorException e) {
            return AjaxResult.error("新增组织机构出错，错误码【"+ e.getError().getErrorCode()+"】，原因："+ ErrorCodeText.errorMsg(e.getError().getErrorCode()));
        }
    }
    @Log(title = "全量新增组织机构", businessType = BusinessType.INSERT)
    @PostMapping("/insertList")
    public AjaxResult insertList(@RequestBody List<WxCpDepart> wxCpDeparts) {
        this.logger.info("全量新增组织机构");
        int i=0;
        int u=0;
        WxCpDepart temp=null;
        try {
            WxCpDepartmentService departmentService = WxCpConfiguration.getCpService(999999).getDepartmentService();
            for (WxCpDepart wxCpDepart : wxCpDeparts) {
                List<WxCpDepart> list =null;
                temp=wxCpDepart;
                try {
                    list = departmentService.list(wxCpDepart.getId());
                } catch (WxErrorException e) {
                    this.logger.info( "组织机构不存在" );
                }
                if(list!=null&& list.size()>0){
                    departmentService.update(wxCpDepart);
                    u++;
                }else{
                    Integer id = departmentService.create(wxCpDepart);
                    i++;
                }
            }
            return AjaxResult.success(wxCpDeparts.size(),"全量同步组织机构成功，新增【"+i+"】条，更新【"+u+"】条");
        } catch (WxErrorException e) {
            return AjaxResult.error("全量新增组织机构失败，新增成功【"+i+"】条，更新成功【"+u+"】条。出错部门名称信息【"+(temp!=null?temp.toString():"部门信息为空")+"】，错误码【"+ e.getError().getErrorCode()+"】，原因："+ ErrorCodeText.errorMsg(e.getError().getErrorCode()));
        }
    }
    @Log(title = "获取组织机构", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult update(@RequestBody WxCpDepart wxCpDepart) {
        try {
            WxCpDepartmentService departmentService = WxCpConfiguration.getCpService(999999).getDepartmentService();

            departmentService.update(wxCpDepart);
            return AjaxResult.success("更新组织机构成功");
        } catch (WxErrorException e) {
            return AjaxResult.error("更新组织机构失败，错误码【"+ e.getError().getErrorCode()+"】，原因："+ ErrorCodeText.errorMsg(e.getError().getErrorCode()));
        }
    }

    @Log(title = "删除组织机构", businessType = BusinessType.DELETE)
    @GetMapping("/delete")
    public AjaxResult delete(Long id) {
        try {
            WxCpDepartmentService departmentService = WxCpConfiguration.getCpService(999999).getDepartmentService();
            departmentService.delete(id);
            return AjaxResult.success("删除组织机构成功");
        } catch (WxErrorException e) {
            return AjaxResult.error("删除组织机构出错，错误码【"+ e.getError().getErrorCode()+"】，原因："+ ErrorCodeText.errorMsg(e.getError().getErrorCode()));
        }
    }

}
