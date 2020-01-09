package com.ruoyi.exam.controller.api;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.PaginUtil;
import com.ruoyi.exam.domain.*;
import com.ruoyi.exam.service.*;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.PageDomain;
import com.ruoyi.framework.web.page.TableSupport;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by flower on 2019/1/9.
 */
@Api("练习接口")
@RestController
@RequestMapping("/api")
public class ApiPracticeController extends BaseController {

    @Autowired
    private IExamPracticeService examPracticeService;


    @Autowired
    private IExamQuestionService examQuestionService;
    @Autowired
    private IExamPracticeQuestionService examPracticeQuestionService;

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 查询练习列表
     *
     * @param examPractice
     * @return
     */
    @GetMapping("/v1/practice/list")
    public AjaxResult list(ExamPracticeVO examPractice) {
        SysUser sysUser = sysUserService.selectUserByLoginName(JwtUtil.getLoginName(), UserConstants.USER_VIP);
        examPractice.setVipUserId(sysUser.getUserId().toString());
        List<ExamPracticeVO> list = examPracticeService.selectListFromWeb(examPractice);
        // 服务端分页
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        Map<String,Object> reslutMap = PaginUtil.getPagingResultMap(list,pageNum,pageSize);

        AjaxResult success = AjaxResult.success( "查询成功" );
        success.put( "data", reslutMap.get("result") );
        success.put("pages",reslutMap.get("totalPageNum"));
        success.put("total",reslutMap.get("totalRowNum"));
        return success;
    }

    /**
     * 查询练习题库的问题列表
     *
     * @param map
     * @return
     */
    @GetMapping("/v1/practice/info")
    public AjaxResult queryOne(@RequestParam Map<String, Object> map) {
        ExamPracticeQuestion examPracticeQuestion = new ExamPracticeQuestion();
        if (map != null && map.get("practiceId") != null) {
            examPracticeQuestion.setExamPracticeId(Integer.parseInt(map.get("practiceId").toString()));
        }
        AjaxResult success = success("查询成功");
        //根据练习id对问题进行分页
        List<ExamPracticeQuestionVO> examPracticeQuestionVOS = examPracticeQuestionService.selectExamPracticeQuestionPage(examPracticeQuestion);
        PageInfo pageInfo = new PageInfo(examPracticeQuestionVOS);
        List<Integer> questionIds = new ArrayList<>();
        for (ExamPracticeQuestionVO question : examPracticeQuestionVOS) {
            questionIds.add(question.getExamQuestionId());
        }
        SysUser sysUser = sysUserService.selectUserByLoginName(JwtUtil.getLoginName(),UserConstants.USER_VIP);
        map.put("vipUserId", sysUser.getUserId());
        map.put("questionIds", questionIds);
        //根据问题id集合查询问题详情
        //删掉分页参数，不然会执行分页
        map.remove("pageNum");
        map.remove("pageSize");
        List<ExamQuestionVO> result = examQuestionService.selectQuestionListByPracticeId(map);
        List<ExamQuestionVO> list = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            ExamQuestionVO examQuestionVO = result.get(i);
            examQuestionVO.setIndex(i + pageInfo.getStartRow());
            list.add(examQuestionVO);
        }
        if (map.containsKey("disorder") && map.get("disorder").toString().equals("1")) {
            Collections.shuffle(list);
        }
        success.put("total", pageInfo.getTotal());
        success.put("data", list);
        return success;
    }

}
