package com.ruoyi.exam.controller.api;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.exam.domain.*;
import com.ruoyi.exam.service.*;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.util.ShiroUtils;
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
        List<ExamExamination> list = examExaminationService.selectListFromWeb( map );
        List<ExamExamination> resultList = new ArrayList<>();
        for (ExamExamination exam : list) {
            int maxExamNumber = exam.getExamNumber();
            // 根据用户id统计用户已参加考试次数
            ExamUserExamination examUserExamination = new ExamUserExamination();
            examUserExamination.setVipUserId(sysUser.getUserId().intValue());
            examUserExamination.setExamPaperId(exam.getExamPaperId());
            examUserExamination.setExamExaminationId(exam.getId());
            //考试记录集合
            List<ExamUserExamination> userExamination = examUserExaminationService.selectLastOne(examUserExamination);

            //超过考试次数
            if (userExamination.size() < maxExamNumber) {
                resultList.add(exam);
            }
        }

        AjaxResult success = success( "查询成功" );
        PageInfo pageInfo = new PageInfo(resultList);
        success.put( "data", resultList );
        success.put("pages",pageInfo.getPages());
        success.put("total",pageInfo.getTotal());
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

                last = userExamination.get( 0 );
                //最后一次考试已交卷，直接返回
                if (last.getUpdateDate() != null && !last.getUpdateDate().equals( "" )) {
                    return error( 1, "不能交卷，已超过" + examNumber + "次考试。" );
                } else {
                    // 最后一次考试未交卷，但超过考试时长,直接返回
                    if (last.getCreateDate().getTime() + timeLength * 60 * 1000 < System.currentTimeMillis()) {
                        return error( 1, "不能交卷，已超过" + examNumber + "次考试。" );
                    }
                }

            }


            if (userExamination.size() <= 0 //考试次数小于0
                    || userExamination.get( 0 ).getUpdateDate() != null //最后一次考试已交卷
                    || userExamination.get( 0 ).getCreateDate().getTime() + timeLength * 60 * 1000 < System.currentTimeMillis()//最后一次考试，已超过考过时长
                    ) {
                insert.setExamExaminationId( Integer.parseInt( id ) );
                insert.setVipUserId( userId );
                insert.setCreateDate( new Date() );
                insert.setExamPaperId( examPaperId );
                insert.setDelFlag( "0" );
                insert.setScore( 0 );
                examUserExaminationService.insertOne( insert );
                examUserExaminationId = insert.getId();
            } else {
                examUserExaminationId = userExamination.get( 0 ).getId();
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
        AjaxResult success = success( "查询成功" );
        PageInfo pageInfo = new PageInfo(list);
        success.put("data", list);
        success.put("pages",pageInfo.getPages());
        success.put("total",pageInfo.getTotal());
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
     * 交卷
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
                last = userExamination.get(0);
                //最后一次考试已交卷，直接返回
                if (last.getUpdateDate() != null && !last.getUpdateDate().equals("")) {
                    return AjaxResult.error(1, "已超过" + examNumber + "次考试，");
                } else {
                    // 最后一次考试未交卷，但超过考试时长,直接返回
                    if (last.getCreateDate().getTime() + timeLength * 60 * 1000 < System.currentTimeMillis()) {
                        return AjaxResult.error(1, "已超过" + examNumber + "次考试，");
                    }
                }
            }
        }


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
     * 考试记录列表
     * @return
     */
    @GetMapping("/user/examination/page")
    public AjaxResult userexamination() {
        SysUser sysUser = sysUserService.selectUserByLoginName( JwtUtil.getLoginName(),UserConstants.USER_VIP );
        ExamUserExaminationVO bean = new ExamUserExaminationVO();
        bean.setVipUserId( sysUser.getUserId().intValue() );
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
