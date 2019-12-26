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
        // 考试类型为2，正式考试
        examExamination.setType("2");
        map.put( "examExamination", examExamination );
        map.put( "userId", sysUser.getUserId() );
        List<ExamExamination> list = examExaminationService.selectListFromWeb( map );
        AjaxResult success = success( "查询成功" );
        success.put( "data", list );
        success.put("total",new PageInfo(list).getTotal());
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
                    return error( 500, "已超过" + examNumber + "次考试，" );
                } else {
                    // 最后一次考试未交卷，但超过考试时长,直接返回
                    if (last.getCreateDate().getTime() + timeLength * 60 * 1000 < System.currentTimeMillis()) {
                        return error( 500, "已超过" + examNumber + "次考试，" );
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
        success.put("data", list);
        success.put("total",new PageInfo(list).getTotal());
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
     *
     * @param examUserExaminationQuestion
     * @param examUserExaminationId
     * @param examinationId
     * @param paperId
     * @return
     */
    @PostMapping("/examination/finish/{examUserExaminationId}/{examinationId}/{paperId}/{time}")
    public AjaxResult finish(@RequestBody List<ExamUserExaminationQuestion> examUserExaminationQuestion,
                             @PathVariable Integer examUserExaminationId, @PathVariable Integer examinationId, @PathVariable Integer paperId,@PathVariable Long time) {


        SysUser user = sysUserService.selectUserByLoginName( JwtUtil.getLoginName(),UserConstants.USER_VIP );
        Long userId = user.getUserId();
        //交卷后返回的数据
        ArrayList<Map<String, String>> data = new ArrayList<>();


        //如果是模拟考试，考试记录新增数据
        if (examUserExaminationId == -1) {
            ExamUserExamination insert = new ExamUserExamination();
            insert.setExamExaminationId(examinationId);
            insert.setVipUserId(Integer.parseInt(userId.toString()));
            insert.setCreateDate(new Date(time));
            insert.setExamPaperId(paperId);
            insert.setDelFlag("0");
            insert.setScore(0);
            examUserExaminationService.insertOne(insert);
            examUserExaminationId = insert.getId();
        }

        Integer score = 0;
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
        String finishedPaper = examExamination.getFinishedPaper();


        AjaxResult success = success( "考试完成" );
        //考试完成后参数
        success.put( "finishedPaper", finishedPaper );
        success.put( "score", score );
        success.put( "examExamination", examExamination );
        if (!finishedPaper.equals( "0" )) {
            success.put( "data", data );
        }
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
        AjaxResult success = success( "查询列表成功" );
        success.put( "data",  data);
        success.put( "total", new PageInfo(data).getTotal() );
        return success;
    }

    /**
     * 考试试卷信息
     * @param id
     * @return
     */
    @GetMapping("/examination/userexamination/detail/{id}")
    public AjaxResult detail(@PathVariable Integer id) {
        ExamUserExaminationVO data = examUserExaminationService.selectDetailById( id );
        ExamExamination examExamination = examExaminationService.selectById( id );
        AjaxResult success = success( "考试信息" );
        success.put( "data", data );
        success.put( "examExamination", examExamination );
        return success;
    }


}
