package com.ruoyi.cms.controller;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.exam.domain.*;

import com.ruoyi.exam.domain.ExamUserErrorQuestion;
import com.ruoyi.exam.domain.ExamUserErrorQuestionVO;
import com.ruoyi.exam.service.*;

import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.web.util.MessageUtils;
import com.ruoyi.framework.web.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserOnlineService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.vip.domain.vo.VipUserCertificateVO;
import com.ruoyi.vip.domain.vo.VipUserOrdersVO;
import com.ruoyi.vip.service.IVipUserCertificateService;
import com.ruoyi.vip.service.IVipUserOrdersService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 通用请求处理
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/web")
@SessionAttributes("user")
public class CmsUserController {
    private static final Logger log = LoggerFactory.getLogger(CmsUserController.class);

    private String prefix = "web";


    @Autowired
    private IExamUserErrorQuestionService examUserErrorQuestionService;

    @Autowired
    private IExamUserCollectionQuestionService examUserCollectionQuestionService;

    @Autowired
    private IExamUserExaminationService examUserExaminationService;

    @Autowired
    private IVipUserOrdersService vipUserOrdersService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private IVipUserCertificateService vipUserCertificateService;

    @Autowired
    private IExamExaminationService examExaminationService;

    @Autowired
    private ISysUserOnlineService sysUserOnlineService;


    @RequestMapping("/user/login.html")
    public String login(ModelMap map) {
        map.put("user", ShiroUtils.getSysUser());
        return prefix + "/user/login";
    }

    @RequestMapping("/user/reg.html")
    public String reg(ModelMap map) {
        map.put("user", ShiroUtils.getSysUser());
        return prefix + "/user/reg";
    }

    @RequestMapping("/user/regaccount")
    @ResponseBody
    public AjaxResult reg(SysUser user) {
        String s = sysUserService.checkLoginNameUnique(user.getLoginName(),UserConstants.USER_VIP);

        //用户名不唯一
        if (s.equals(UserConstants.USER_NAME_NOT_UNIQUE)) {
            return AjaxResult.error("用户名已经注册");
        }
        AjaxResult success = AjaxResult.success("注册成功");

        user.setStatus("0");
        user.setDelFlag("0");
        user.setCreateTime(new Date());
        user.setSalt(ShiroUtils.randomSalt());
        user.setUserType(UserConstants.USER_VIP);
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        sysUserService.insertUser(user);
        return success;
    }


    @RequestMapping("/user/index.html")
    public String webUserIndex(ModelMap map) {
        SysUser user = ShiroUtils.getSysUser();
        if (ObjectUtils.isEmpty(user)){
            return prefix + "/user/login";
        }
        map.put("user", user);
        map.addAttribute("user", ShiroUtils.getSysUser());
        return prefix + "/user/set";
    }

    @RequestMapping("/user/home.html")
    public String webUserHome(ModelMap map) {
        SysUser user = ShiroUtils.getSysUser();
        if (ObjectUtils.isEmpty(user)){
            return prefix + "/user/login";
        }
        map.put("user", user);
        return prefix + "/user/home";
    }

    @RequestMapping("/user/set.html")
    public String webUserSet(ModelMap map) {
        SysUser user = ShiroUtils.getSysUser();
        if (ObjectUtils.isEmpty(user)){
            return prefix + "/user/login";
        }
        map.put("user", user);
        return prefix + "/user/set";
    }

    @RequestMapping("/user/message.html")
    public String webUserMessage(ModelMap map) {
        SysUser user = ShiroUtils.getSysUser();
        if (ObjectUtils.isEmpty(user)){
            return prefix + "/user/login";
        }
        map.put("user", user);
        return prefix + "/user/message";
    }

    @RequestMapping("/user/errorquestion.html")
    public String errorquestion(ModelMap map) {
        SysUser user = ShiroUtils.getSysUser();
        if (ObjectUtils.isEmpty(user)){
            return prefix + "/user/login";
        }
        ExamUserErrorQuestion examUserErrorQuestion = new ExamUserErrorQuestion();
        examUserErrorQuestion.setVipUserId(ShiroUtils.getSysUser().getUserId().intValue());
        List<ExamUserErrorQuestionVO> list = examUserErrorQuestionService.selectExamUserErrorQuestionDetailList(examUserErrorQuestion);
        map.put("data", list);
        map.put("user", user);
        return prefix + "/user/errorquestion";
    }

    @RequestMapping("/user/collectquestion.html")
    public String collectQuestion(ModelMap map) {
        SysUser user = ShiroUtils.getSysUser();
        if (ObjectUtils.isEmpty(user) || !UserConstants.USER_VIP.equals(user.getUserType())){
            return prefix + "/user/login";
        }
        ExamUserCollectionQuestionVO examUserCollectionQuestion = new ExamUserCollectionQuestionVO();
        SysUser sysUser = ShiroUtils.getSysUser();
        examUserCollectionQuestion.setVipUserId(sysUser.getUserId().intValue());
        List<ExamUserCollectionQuestionVO> list = examUserCollectionQuestionService.selectExamUserCollectionQuestionList(examUserCollectionQuestion);
        map.put("data", list);
        map.put("user", user);
        return prefix + "/user/collectquestion";
    }

    /**
     * 增加错题
     *
     * @param questionId
     * @return
     */
    @RequestMapping("/user/adderrorquestion")
    @ResponseBody
    public AjaxResult addErrorquestion(String questionId) {
        SysUser sysUser = ShiroUtils.getSysUser();
        examUserErrorQuestionService.insertError(questionId, sysUser);
        AjaxResult success = AjaxResult.success("插入成功");
        return success;
    }

    @RequestMapping("/user/addcollectquestion")
    @ResponseBody
    public AjaxResult addCollectionquestion(String questionId) {
        SysUser sysUser = ShiroUtils.getSysUser();
        int i = examUserCollectionQuestionService.insertSelectiveBySelf(Integer.parseInt(questionId), sysUser);
        AjaxResult success = AjaxResult.success("收藏成功");
        if (i == 0) {
            success = AjaxResult.success("已收藏,无法重复收藏");
        }
        return success;
    }

    @RequestMapping("/user/delerrorquestion")
    @ResponseBody
    public AjaxResult delErrorquestion(Integer questionId) {
        ExamUserErrorQuestion question = new ExamUserErrorQuestion();
        question.setVipUserId(ShiroUtils.getUserId().intValue());
        question.setExamQuestionId(questionId);
        examUserErrorQuestionService.delete(question);
        AjaxResult success = AjaxResult.success("删除成功");
        return success;
    }

    @RequestMapping("/user/delcollectquestion")
    @ResponseBody
    public AjaxResult delCollectionquestion(Integer questionId) {
        ExamUserCollectionQuestion examUserCollectionQuestion = new ExamUserCollectionQuestion();
        examUserCollectionQuestion.setVipUserId(ShiroUtils.getUserId().intValue());
        examUserCollectionQuestion.setExamQuestionId(questionId);
        examUserCollectionQuestionService.delete(examUserCollectionQuestion);
        AjaxResult success = AjaxResult.success("删除成功");
        return success;
    }

    @RequestMapping("/user/userexamination.html")
    public String userExamquestion(ModelMap map) {
        SysUser user = ShiroUtils.getSysUser();
        if (ObjectUtils.isEmpty(user) || !UserConstants.USER_VIP.equals(user.getUserType())){
            return prefix + "/user/login";
        }
        map.put("user", user);
        return prefix + "/user/userexamination";
    }


    /**
     * 我的考试记录
     *
     * @return
     */
    @RequestMapping("/user/myuserexamination/list")
    @ResponseBody
    public AjaxResult userExamquestionList() {
        ExamUserExaminationVO examUserExamination = new ExamUserExaminationVO();
        examUserExamination.setVipUserId(ShiroUtils.getUserId().intValue());
        List<ExamUserExaminationVO> list = examUserExaminationService.selectMyExamUserExamination(examUserExamination);
        AjaxResult success = AjaxResult.success("查询成功");
        success.put("total", new PageInfo(list).getTotal());
        success.put("data", list);
        return success;
    }


    /**
     * 考试记录详情
     *
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("/user/myuserexamination/detail/{id}")
    public String userExamquestion(@PathVariable Integer id, ModelMap map) {
        SysUser user = ShiroUtils.getSysUser();
        if (ObjectUtils.isEmpty(user) || !UserConstants.USER_VIP.equals(user.getUserType())){
            return prefix + "/user/login";
        }
        map.put("user", user);
        ExamUserExaminationVO data = examUserExaminationService.selectDetailById(id);
        List<ExamUserExaminationQuestionVO> questions = data.getExamUserExaminationQuestions();
        int score = 0;
        int right = 0;
        int error = 0;
        int nullAnswer = 0;
        for (ExamUserExaminationQuestionVO question : questions) {
            if (StrUtil.isBlank(question.getUserAnswer())) {
                nullAnswer++;
            } else if (question.getUserAnswer().equals(question.getAnswer())) {
                right++;
            } else {
                error++;
            }
        }

        // 根据答案实时计算分数
        score = examExaminationService.countScore(questions, data.getExamExaminationId());
        // TODO,为避免出现考题答案错误的情况（后台修改答案），若记录分数与计算分数不一致重新设置考试分数
        if (data.getScore().compareTo(score) != 0) {
            data.setScore(score);
            examUserExaminationService.updateById(data);
        }
        map.put("data", data);
        map.put("right", right);
        map.put("error", error);
        map.put("nullAnswer", nullAnswer);
        return prefix + "/user/userexamination_detail";
    }

    @RequestMapping("/user/orders.html")
    public String orders(ModelMap map) {
        SysUser user = ShiroUtils.getSysUser();
        if (ObjectUtils.isEmpty(user) || !UserConstants.USER_VIP.equals(user.getUserType())){
            return prefix + "/user/login";
        }
        map.put("user", user);
        return prefix + "/user/orders";
    }

    @RequestMapping("/user/orders/list")
    @ResponseBody
    public AjaxResult orders(VipUserOrdersVO vipUserOrders) {
        AjaxResult success = AjaxResult.success("插入成功");
        vipUserOrders.setVipUserId(ShiroUtils.getUserId().intValue());
        List<VipUserOrdersVO> list = vipUserOrdersService.selectVipUserOrdersPage(vipUserOrders);
        success.put("total", new PageInfo(list).getTotal());
        success.put("data", list);
        return success;
    }

    /**
     * 保存头像
     */
    @Log(title = "个人信息修改", businessType = BusinessType.UPDATE)
    @RequestMapping("/user/update")
    @ResponseBody
    public AjaxResult update(SysUser user) {
        if (sysUserService.updateUserInfo(user) > 0) {
            ShiroUtils.setSysUser(sysUserService.selectUserById(user.getUserId()));
        }
        return AjaxResult.success();
    }

    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @RequestMapping("/user/resetPwd")
    @ResponseBody
    @Transactional (rollbackFor = Exception.class)
    public AjaxResult resetPwdSave(SysUser user, HttpServletRequest request, HttpServletResponse response) {
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        sysUserService.resetUserPwd(user);
        clearLoginCookie(request, response);
        return AjaxResult.success();
    }

    private void clearLoginCookie(HttpServletRequest request, HttpServletResponse response) {
        ShiroUtils.clearCachedAuthorizationInfo();
        sysUserOnlineService.deleteOnlineById(ShiroUtils.getSessionId());
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("JSESSIONID")) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
    }

    @RequestMapping("/user/usercertificate.html")
    public String userCertificate(ModelMap map) {
        SysUser user = ShiroUtils.getSysUser();
        if (ObjectUtils.isEmpty(user) || !UserConstants.USER_VIP.equals(user.getUserType())){
            return prefix + "/user/login";
        }
        map.put("user", user);
        return prefix + "/user/usercertificate";
    }

    @RequestMapping("/user/usercertificate/list")
    @ResponseBody
    public AjaxResult usercertificate(VipUserCertificateVO vipUserCertificate) {
        AjaxResult success = AjaxResult.success("查询成功");
        vipUserCertificate.setVipUserId(ShiroUtils.getUserId().intValue());
        List<VipUserCertificateVO> list = vipUserCertificateService.selectVipUserCertificateList(vipUserCertificate);
        success.put("total", new PageInfo(list).getTotal());
        success.put("data", list);
        return success;
    }

    @RequestMapping("/user/userlogout.html")
    public String userLogout(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws IOException {
        SysUser user = ShiroUtils.getSysUser();
        if (StringUtils.isNotNull(user)) {
            String loginName = user.getLoginName();
            // 记录用户退出日志
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.LOGOUT, MessageUtils.message("user.logout.success")));
        }
        // 退出登录,刪除session和cookie
        clearLoginCookie(request, response);
        return prefix + "/user/login";
    }

}
