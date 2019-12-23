package com.ruoyi.web.controller.system;

import com.ruoyi.common.config.Global;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.system.domain.SysMenu;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.ruoyi.exam.service.IExamExaminationService;
import com.ruoyi.exam.service.IExamPaperService;
import com.ruoyi.exam.service.IExamPaperQuestionService;
import com.ruoyi.exam.service.IExamPracticeService;
import com.ruoyi.train.course.service.ITrainCourseService;
import java.util.List;

/**
 * 首页 业务处理
 *
 * @author ruoyi
 */
@Controller
public class SysIndexController extends BaseController
{
    @Autowired
    private ISysMenuService menuService;
    // 系统CMS首页
    @GetMapping({"","/"})
    public String cmsindex(ModelMap mmap)
    {
        // 取身份信息
        SysUser user = getSysUser();
        mmap.put("user", user);
        return "web";
    }
    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        SysUser user = getSysUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("copyrightYear", Global.getCopyrightYear());
        return "index";
    }

    @Autowired
    private IExamExaminationService examExaminationService;

    @Autowired
    private IExamPaperService examPaperService;


    @Autowired
    private IExamPaperQuestionService examPaperQuestionService;

    @Autowired
    private IExamPracticeService examPracticeService;


    @Autowired
    private ITrainCourseService trainCourseService;

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        Long trainCourseNum = trainCourseService.selectCount( null );
        mmap.put("trainCourseNum", trainCourseNum);
        Long aLong = examExaminationService.selectCount( null );
        mmap.put("examExaminationNum",aLong);
        Long examPaperNum = examPaperService.selectCount( null );
        mmap.put("examPaperNum", examPaperNum);
        Long examPractice = examPracticeService.selectCount( null );
        mmap.put("examPractice",examPractice);
        mmap.put("version", Global.getVersion());
        SysUser user = getSysUser();
        mmap.put("user", user);
        return "main";
    }
}
