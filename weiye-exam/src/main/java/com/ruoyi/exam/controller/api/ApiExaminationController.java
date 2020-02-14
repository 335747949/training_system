package com.ruoyi.exam.controller.api;

import cn.hutool.core.util.StrUtil;
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
 * 考试相关API接口
 * @author liugang
 * @date 2019/12/23
 */
@Api("考试相关API")
@RestController
@RequestMapping("/api/v1")
public class ApiExaminationController extends BaseController {

    @Autowired
    private IExamExaminationService examExaminationService;

    @Autowired
    private IExamPaperService examPaperService;

    @Autowired
    private IExamUserExaminationService examUserExaminationService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IExamExaminationUserService examExaminationUserService;

    @Autowired
    private IExamUserExaminationQuestionService examUserExaminationQuestionService;

    @Autowired
    private IExamQuestionService examQuestionService;

    @Autowired
    private IExamPaperQuestionService examPaperQuestionService;

    @Autowired
    private IExamPaperTypeNumberService examPaperTypeNumberService;

    @Autowired
    private IExamUserErrorQuestionService examUserErrorQuestionService;

    /**
     * 获取考试列表
     * @return
     */
    @GetMapping("/examination/list")
    public AjaxResult list() {
        SysUser sysUser = sysUserService.selectUserByLoginName( JwtUtil.getLoginName(), UserConstants.USER_VIP );
        Map<String, Object> map = new HashMap<>();
        ExamExamination examExamination = new ExamExamination();
        map.put( "examExamination", examExamination );
        map.put( "userId", sysUser.getUserId() );
        List<ExamExamination> list = examExaminationService.selectListFromWeb( map ,sysUser.getUserId());
        // 服务端分页
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        Map<String,Object> reslutMap = PaginUtil.getPagingResultMap(list,pageNum,pageSize);

        AjaxResult success = success( "查询成功" );
        success.put( "data", reslutMap.get("result") );
        success.put("pages",reslutMap.get("totalPageNum"));
        success.put("total",reslutMap.get("totalRowNum"));
        return success;
    }


    /**
     * 获取考试列表
     * @param type 1.模拟考试  2.正式考试
     * @return
     */
    @GetMapping("/examination/examList")
    public AjaxResult examList(@RequestParam("type") String type) {
        SysUser sysUser = sysUserService.selectUserByLoginName( JwtUtil.getLoginName(), UserConstants.USER_VIP );
        Map<String, Object> map = new HashMap<>();
        List<ExamExamination> list = examExaminationService.selectExamList(type, sysUser.getUserId());

        // 服务端分页
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        Map<String,Object> reslutMap = PaginUtil.getPagingResultMap(list,pageNum,pageSize);

        AjaxResult success = success( "查询成功" );
        success.put( "data", reslutMap.get("result") );
        success.put("pages",reslutMap.get("totalPageNum"));
        success.put("total",reslutMap.get("totalRowNum"));
        return success;
    }

    /**
     * 开始考试
     *
     * @param id 考试id
     * @return
     */
    @GetMapping("/examination/start/{id}")
    public AjaxResult start(@PathVariable("id") String id) {
        ExamExamination examExamination = examExaminationService.selectById( id );
        SysUser sysUser = sysUserService.selectUserByLoginName( JwtUtil.getLoginName() ,UserConstants.USER_VIP);
        Integer userId = Integer.parseInt( sysUser.getUserId().toString() );
        //考试类型
        String type = examExamination.getType();
        //试卷ID
        Integer examPaperId = examExamination.getExamPaperId();
        //考试次数
        Integer examNumber = examExamination.getExamNumber();
        //考试时长
        Integer timeLength = examExamination.getTimeLength();
        //考试记录ID
        Integer examUserExaminationId = -1;

        ExamUserExamination insert = new ExamUserExamination();
        //正式考试
        if (type.equals( "2" )) {

            // 若为正式考试，如果未报名先将考试相关信息入到用户考试表中
            ExamExaminationUser examExaminationUser = new ExamExaminationUser();
            examExaminationUser.setVipUserId( Integer.parseInt( userId.toString() ) );
            examExaminationUser.setExamExaminationId( Integer.parseInt( id ) );
            List<ExamExaminationUser> list = examExaminationUserService.selectList(examExaminationUser);
            if (list.isEmpty()){
                examExaminationUser.setDelFlag( "0" );
                examExaminationUser.setCreateDate( new Date() );
                examExaminationUser.setCreateBy( sysUser.getLoginName() );
                if (examExaminationUserService.insertOne( examExaminationUser ) == 0){
                    return AjaxResult.error("请重新考试");
                }
            }

            ExamUserExamination examUserExamination = new ExamUserExamination();
            examUserExamination.setVipUserId( userId );
            examUserExamination.setExamPaperId( examPaperId );
            examUserExamination.setExamExaminationId( Integer.parseInt( id ) );
            //考试记录集合
            List<ExamUserExamination> userExamination = examUserExaminationService.selectLastOne( examUserExamination );
            // 最后一次考试
            ExamUserExamination last;

            //超过考试次数
            if (userExamination.size() >= examNumber) {

//                last = userExamination.get( 0 );
//                //最后一次考试已交卷，直接返回
//                if (last.getUpdateDate() != null && !last.getUpdateDate().equals( "" )) {
                    return error( 1, "不能交卷，已超过" + examNumber + "次考试。" );
//                }
                // TODO  目前默认未交卷及未参加考试
//                else {
//                    // 最后一次考试未交卷，但超过考试时长,直接返回
//                    if (last.getCreateDate().getTime() + timeLength * 60 * 1000 < System.currentTimeMillis()) {
//                        return error( 1, "不能交卷，已超过" + examNumber + "次考试。" );
//                    }
//                }

            }


            if (userExamination.size() <= 0 ) {
                insert.setExamExaminationId( Integer.parseInt( id ) );
                insert.setVipUserId( userId );
                insert.setCreateDate( new Date() );
                insert.setExamPaperId( examPaperId );
                insert.setDelFlag( "0" );
                insert.setScore( 0 );
                examUserExaminationService.insertOne( insert );
                examUserExaminationId = insert.getId();
            }
        }
        ExamPaper examPaper = examPaperService.selectById(examPaperId);
        List<ExamQuestionVO> data = new ArrayList<>();
        List<ExamQuestionVO> list = examPaperService.selectQuestionAndItemByPaperId( examPaperId );
        //随机试卷
        if(examPaper.getType().equals("2")){
            Collections.shuffle( list );
            ExamPaperTypeNumber examPaperTypeNumber = new ExamPaperTypeNumber();
            examPaperTypeNumber.setExamPaperId(examPaperId);
            List<ExamPaperTypeNumber> examPaperTypeNumbers = examPaperTypeNumberService.selectList(examPaperTypeNumber);
            //三种题型的数量
            int one=0,two=0,three=0;
            for (ExamPaperTypeNumber item : examPaperTypeNumbers) {
                if(item.getExamQuestionType()==1){
                    one = item.getNumber();
                }
                if(item.getExamQuestionType()==2){
                    two = item.getNumber();
                }
                if(item.getExamQuestionType()==3){
                    three = item.getNumber();
                }
            }
            for (ExamQuestionVO item : list) {
                item.setAnswer("");
                if(item.getType().equals("1")&&one>0){
                    data.add(item);
                    one--;
                }
                if(item.getType().equals("2")&&two>0){
                    data.add(item);
                    two--;
                }
                if(item.getType().equals("3")&&three>0){
                    data.add(item);
                    three--;
                }
            }
        }else{
            data = list;
        }
        //是否乱序
        if (examExamination.getQuestionDisorder().equals( "2" )) {
            Collections.shuffle( list );
        }
        AjaxResult success = success( "查询成功" );
        success.put( "data", data );
        success.put( "examUserExaminationId", examUserExaminationId );
        success.put( "examExamination", examExamination );
        return success;
    }


    /**
     * 报名列表
     *
     * @return
     */
    @GetMapping("/examination/signup/list")
    public AjaxResult signUpList() {
        SysUser sysUser = sysUserService.selectUserByLoginName(JwtUtil.getLoginName(),UserConstants.USER_VIP );

        Map<String, Object> map = new HashMap<>();
        ExamExamination examExamination = new ExamExamination();
        examExamination.setType("2");
        map.put( "examExamination", examExamination );
        map.put( "userId", sysUser.getUserId() );
        List<ExamExamination> list = examExaminationService.selectSignUpListFromWeb( map );

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
     * 报名
     *
     * @param id
     * @return
     */
    @PostMapping("/examination/signup/{id}")
    public AjaxResult signUp(@PathVariable String id) {
        SysUser user = sysUserService.selectUserByLoginName( JwtUtil.getLoginName(),UserConstants.USER_VIP );
        Long userId = user.getUserId();

        ExamExaminationUser examExaminationUser = new ExamExaminationUser();
        examExaminationUser.setVipUserId( Integer.parseInt( userId.toString() ) );
        examExaminationUser.setDelFlag( "0" );
        examExaminationUser.setCreateDate( new Date() );
        examExaminationUser.setCreateBy( user.getLoginName() );
        examExaminationUser.setExamExaminationId( Integer.parseInt( id ) );
        examExaminationUserService.insertOne( examExaminationUser );

        AjaxResult success = success( "报名成功" );
        return success;
    }


    /**
     * 提交试卷
     * v1.1.0
     * 1、增加：模拟开始错题加入错题模拟考试记录，错题本；模拟考试及格分数
     * 2、模拟考试，展示分数，及格分数，正确、错误，漏答个数
     * @return
     */
    @PostMapping("/examination/finish")
    public AjaxResult finish(@RequestBody ExamUserExaminationFinishVO examUserExaminationFinishVO) {

        SysUser user = sysUserService.selectUserByLoginName( JwtUtil.getLoginName(),UserConstants.USER_VIP );
        Long userId = user.getUserId();
        Integer examUserExaminationId = examUserExaminationFinishVO.getExamUserExaminationId();
        Integer examinationId = examUserExaminationFinishVO.getExaminationId();
        Integer paperId = examUserExaminationFinishVO.getPaperId();
        List<ExamUserExaminationQuestion> examUserExaminationQuestion = examUserExaminationFinishVO.getExamUserExaminationQuestion();

        //交卷后返回的数据
        ArrayList<Map<String, String>> data = new ArrayList<>();

        ExamExamination examExaminationOld = examExaminationService.selectById( examinationId );
        //最大考试次数
        Integer examNumber = examExaminationOld.getExamNumber();
        //考试时长
        Integer timeLength = examExaminationOld.getTimeLength();

        // 校驗用戶已完成次數，若超過最大提交次數不允許提交
        if (examExaminationOld.getType().equals( "2" )) {
            ExamUserExamination examUserExamination = new ExamUserExamination();
            examUserExamination.setVipUserId(user.getUserId().intValue());
            examUserExamination.setExamPaperId(paperId);
            examUserExamination.setExamExaminationId(examinationId);
            //考试记录集合
            List<ExamUserExamination> userExamination = examUserExaminationService.selectLastOne(examUserExamination);
            // 最后一次考试
            ExamUserExamination last;

            //超过考试次数
            if (userExamination.size() >= examNumber) {
//                last = userExamination.get(0);
//                //最后一次考试已交卷，直接返回
//                if (last.getUpdateDate() != null && !last.getUpdateDate().equals("")) {
                    return AjaxResult.error(1, "已超过" + examNumber + "次考试，");
//                }
                // TODO  目前默认未交卷及未参加考试
//                else {
//                    // 最后一次考试未交卷，但超过考试时长,直接返回
//                    if (last.getCreateDate().getTime() + timeLength * 60 * 1000 < System.currentTimeMillis()) {
//                        return AjaxResult.error(1, "已超过" + examNumber + "次考试，");
//                    }
//                }
            }
        }


        // v1.1.0修改：模拟考试正常增加考试记录，避免下方逻辑错误，在考试记录查询时过滤掉处理
        //如果是模拟考试，考试记录新增数据
        if (examUserExaminationId == -1) {
            ExamUserExamination insert = new ExamUserExamination();
            insert.setExamExaminationId(examinationId);
            insert.setVipUserId(Integer.parseInt(userId.toString()));
            insert.setCreateDate(new Date());
            insert.setExamPaperId(paperId);
            insert.setDelFlag("0");
            insert.setScore(0);
            examUserExaminationService.insertOne(insert);
            examUserExaminationId = insert.getId();
        }

        Integer score = 0;
        Integer right = 0;
        Integer error = 0;
        Integer nullAnswer = 0;

        for (ExamUserExaminationQuestion item : examUserExaminationQuestion) {
            HashMap<String, String> returnItem = new HashMap<>();
            String userAnswer = item.getUserAnswer();
            //存入用户回答
            if (StrUtil.isNotBlank(userAnswer)) {
                returnItem.put("userAnswer", userAnswer);
            }
            Integer examQuestionId = item.getExamQuestionId();
            ExamQuestion examQuestion = examQuestionService.selectById(examQuestionId);
            //存入正确答案
            if (StrUtil.isNotBlank(examQuestion.getAnswer())) {
                returnItem.put("answer", examQuestion.getAnswer());
            }
            returnItem.put("title", examQuestion.getTitle());
            returnItem.put("rightWrong", "错误");
            if (examQuestion.getAnswer().equals(userAnswer)) {
                ExamPaperQuestion examPaperQuestion = new ExamPaperQuestion();
                examPaperQuestion.setExamPaperId(paperId);
                examPaperQuestion.setExamQuestionId(examQuestionId);
                ExamPaperQuestionVO paperQuestionVO = examPaperQuestionService.selectExamPaperQuestionList(examPaperQuestion).get(0);
                score += (paperQuestionVO.getScore() == null ? 0 : paperQuestionVO.getScore());
                returnItem.put("rightWrong", "正确");
            }
            item.setExamUserExaminationId(examUserExaminationId);
            item.setCreateDate(new Date());
            item.setCreateBy(user.getLoginName());
            item.setDelFlag("0");
            item.setId(null);
            examUserExaminationQuestionService.insertOne(item);
            data.add(returnItem);
        }

        // v1.1.0修改：模拟考试正常增加考试记录，避免下方逻辑错误，在考试记录查询时过滤掉处理
        ExamUserExamination examUserExamination = examUserExaminationService.selectById(examUserExaminationId);
        examUserExamination.setScore(score);
        examUserExamination.setUpdateDate(new Date());
        examUserExamination.setCreateBy(user.getLoginName());
        examUserExaminationService.updateOneSelectiveById(examUserExamination);

        ExamExamination examExamination = examExaminationService.selectById(examinationId);

        // 及格分数
        int passMark = examExamination.getPassMark();

        // 统计正确，错误，漏达数
        ExamUserExaminationVO userExaminationVO = examUserExaminationService.selectDetailById(examUserExaminationId);
        List<ExamUserExaminationQuestionVO> questions = userExaminationVO.getExamUserExaminationQuestions();

        for (ExamUserExaminationQuestionVO question : questions) {
            if (StrUtil.isBlank(question.getUserAnswer())) {
                nullAnswer++;
            } else if (question.getUserAnswer().equals(question.getAnswer())) {
                right++;
            } else {
                error++;
                // v1.1.0修改：模拟考试错题，加入错题记录
                if ("1".equals(examExaminationOld.getType())){
                    examUserErrorQuestionService.insertError(question.getExamQuestionId().toString(), examUserExaminationFinishVO.getExaminationId().toString(), user);
                }
            }
        }

        AjaxResult success = success( "考试完成" );
        success.put("right", right);
        success.put("error", error);
        success.put("nullAnswer", nullAnswer);

        //考试完成后参数
        success.put( "score", score );
        success.put( "passMark", passMark );
        return success;
    }


    /**
     * 小程序考试记录列表
     * v1.1.0修改：只查询正式考试记录列表
     * @return
     */
    @GetMapping("/user/examination/page")
    public AjaxResult userexamination() {
        SysUser sysUser = sysUserService.selectUserByLoginName( JwtUtil.getLoginName(),UserConstants.USER_VIP );
        ExamUserExaminationVO bean = new ExamUserExaminationVO();
        bean.setVipUserId( sysUser.getUserId().intValue() );
        // 只查询【正式考试】记录列表
        bean.setExaminationType(2);
        List<ExamUserExaminationVO> data = examUserExaminationService.selectMyExamUserExamination( bean );
        for (ExamUserExaminationVO userExaminationVO : data) {
            if (userExaminationVO.getUpdateDate() == null){
                ExamExamination examExamination = examExaminationService.selectById(userExaminationVO.getExamExaminationId());
                userExaminationVO.setScore(0);
                userExaminationVO.setRight(0);
                userExaminationVO.setError(0);
                userExaminationVO.setNullAnswer(examExamination.getExamNumber());
            }else {
                ExamUserExaminationVO userExamination = examUserExaminationService.selectDetailById(userExaminationVO.getId());
                List<ExamUserExaminationQuestionVO> questions = userExamination.getExamUserExaminationQuestions();
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
                userExaminationVO.setScore(userExaminationVO.getScore());
                userExaminationVO.setRight(right);
                userExaminationVO.setError(error);
                userExaminationVO.setNullAnswer(nullAnswer);
            }
        }
        AjaxResult success = success( "查询列表成功" );
        PageInfo pageInfo = new PageInfo(data);
        success.put("data", data);
        success.put("pages",pageInfo.getPages());
        success.put("total",pageInfo.getTotal());
        return success;
    }

    /**
     * 考试试卷信息
     * @param id
     * @return
     */
    @GetMapping("/examination/userexamination/detail/{id}")
    public AjaxResult detail(@PathVariable Integer id) {
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
        AjaxResult success = success( "考试信息" );
        success.put( "data", data );
        success.put("right", right);
        success.put("error", error);
        success.put("nullAnswer", nullAnswer);
        return success;
    }

}
