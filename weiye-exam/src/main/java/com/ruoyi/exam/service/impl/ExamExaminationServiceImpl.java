package com.ruoyi.exam.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.constant.ExamConstants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.exam.domain.*;
import com.ruoyi.exam.mapper.ExamExaminationMapper;
import com.ruoyi.exam.service.*;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
import com.ruoyi.framework.web.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 考试 服务层实现
 *
 * @author zhujj
 * @date 2018-12-24
 */
@Service
public class ExamExaminationServiceImpl extends AbstractBaseServiceImpl<ExamExaminationMapper, ExamExamination> implements IExamExaminationService {
    @Autowired
    private ExamExaminationMapper examExaminationMapper;

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
     * 查询考试列表
     *
     * @param examExamination 考试信息
     * @return 考试集合
     */
    @Override
    public List<ExamExamination> selectExamExaminationList(ExamExamination examExamination) {
        return examExaminationMapper.selectExamExaminationList(examExamination);
    }

    @Override
    public List<ExamExamination> selectListFromWeb(Map<String, Object> map, Long userId) {
        List<ExamExamination> list = examExaminationMapper.selectListFromWeb(map);

        List<ExamExamination> resultList = new ArrayList<>();
        for (ExamExamination exam : list) {
            int count = examExaminationService.countExamQuestion(exam.getId());
            if (count > 0){
                int maxExamNumber = exam.getExamNumber();
                // 根据用户id统计用户已参加考试次数
                ExamUserExamination examUserExamination = new ExamUserExamination();
                examUserExamination.setVipUserId(userId.intValue());
                examUserExamination.setExamPaperId(exam.getExamPaperId());
                examUserExamination.setExamExaminationId(exam.getId());
                //考试记录集合
                List<ExamUserExamination> userExamination = examUserExaminationService.selectLastOne(examUserExamination);

                //超过考试次数
                if (userExamination.size() < maxExamNumber) {
                    resultList.add(exam);
                }
            }
        }
        return resultList;
    }

    @Override
    public List<ExamExamination> selectSignUpListFromWeb(Map<String, Object> map) {
        List<ExamExamination> list = examExaminationMapper.selectSignUpListFromWeb(map);
        List<ExamExamination> resultList = new ArrayList<>();
        for (ExamExamination exam : list) {
            int count = examExaminationService.countExamQuestion(exam.getId());
            if (count > 0){
                resultList.add(exam);
            }
        }
        return resultList;
    }

    @Override
    public Map<String,Object> queryExaminationQuestion(ExamExamination examExamination, ExamUserExamination eue) {
        Map<String,Object> result = new HashMap<>();
        String id = examExamination.getId().toString();
        SysUser sysUser = sysUserService.selectUserByLoginName(ShiroUtils.getLoginName(), UserConstants.USER_VIP);
        Integer userId = Integer.parseInt(sysUser.getUserId().toString());
        //考试类型
        String type = examExamination.getType();
        //试卷ID
        Integer examPaperId = examExamination.getExamPaperId();
        //考试次数
        Integer examNumber = examExamination.getExamNumber();
        //考试时长
        Integer timeLength = examExamination.getTimeLength();

        //正式考试
        if (type.equals("2")) {
            ExamUserExamination examUserExamination = new ExamUserExamination();
            examUserExamination.setVipUserId(userId);
            examUserExamination.setExamPaperId(examPaperId);
            examUserExamination.setExamExaminationId(Integer.parseInt(id));
            //考试记录集合
            List<ExamUserExamination> userExamination = examUserExaminationService.selectLastOne(examUserExamination);
            // 最后一次考试
            ExamUserExamination last;

            //超过考试次数
            if (userExamination.size() >= examNumber) {
//
//                last = userExamination.get(0);
//                //最后一次考试已交卷，直接返回
//                if (last.getUpdateDate() != null && !last.getUpdateDate().equals("")) {
                      result.put("fail","已超过" + examNumber + "次考试");
                      return result;
//                }
                // TODO  目前默认未交卷及未参加考试
//                else {
//                    // 最后一次考试未交卷，但超过考试时长,直接返回
//                    if (last.getCreateDate().getTime() + timeLength * 60 * 1000 < System.currentTimeMillis()) {
//                        result.put("fail","已超过" + examNumber + "次考试");
//                        return result;
//                    }
//                }

            }

            if (userExamination.size() <= 0) {
                eue.setExamExaminationId(Integer.parseInt(id));
                eue.setVipUserId(userId);
                eue.setCreateDate(new Date());
                eue.setExamPaperId(examPaperId);
                eue.setDelFlag("0");
                eue.setScore(0);
                examUserExaminationService.insertOne(eue);
            }
        }
        ExamPaper examPaper = examPaperService.selectById(examPaperId);
        List<ExamQuestionVO> data = new ArrayList<>();
        List<ExamQuestionVO> list = examPaperService.selectQuestionAndItemByPaperId(examPaperId);
        //随机试卷
        if (examPaper.getType().equals("2")) {
            Collections.shuffle(list);
            ExamPaperTypeNumber examPaperTypeNumber = new ExamPaperTypeNumber();
            examPaperTypeNumber.setExamPaperId(examPaperId);
            List<ExamPaperTypeNumber> examPaperTypeNumbers = examPaperTypeNumberService.selectList(examPaperTypeNumber);
            //三种题型的数量
            int one = 0, two = 0, three = 0;
            for (ExamPaperTypeNumber item : examPaperTypeNumbers) {
                if (item.getExamQuestionType() == 1) {
                    one = item.getNumber();
                }
                if (item.getExamQuestionType() == 2) {
                    two = item.getNumber();
                }
                if (item.getExamQuestionType() == 3) {
                    three = item.getNumber();
                }
            }
            for (ExamQuestionVO item : list) {
                if (item.getType().equals("1") && one > 0) {
                    data.add(item);
                    one--;
                }
                if (item.getType().equals("2") && two > 0) {
                    data.add(item);
                    two--;
                }
                if (item.getType().equals("3") && three > 0) {
                    data.add(item);
                    three--;
                }
            }
        } else {
            data = list;
        }
        //是否乱序
        if (examExamination.getQuestionDisorder().equals("2")) {
            Collections.shuffle(data);
        }

        result.put("data",data);
        return result;
    }

    @Override
    public Integer finshExamination(List<ExamUserExaminationQuestion> examUserExaminationQuestion,SysUser user ,Integer examUserExaminationId, Integer examinationId, Integer paperId) {

        ArrayList<Map<String, String>> data = new ArrayList<>();
        Integer userId = user.getUserId().intValue();
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
        for (ExamUserExaminationQuestion item : examUserExaminationQuestion) {
            HashMap<String, String> returnItem = new HashMap<>();
            String userAnswer = item.getUserAnswer();
            //存入用户回答
            if (StrUtil.isNotBlank(userAnswer)) {
                returnItem.put("userAnswer", userAnswer);
            }
            Integer examQuestionId = item.getExamQuestionId();
            ExamQuestion examQuestion = examQuestionService.selectById(examQuestionId);
            if(examQuestion.getType().equals("4")||examQuestion.getType().equals("5")){
                continue;
            }
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

        return examUserExaminationId;
    }

    /**
     * 查询考试分页列表
     *
     * @param examExamination 考试信息
     * @return 考试集合
     */
    @Override
    public List<ExamExamination> selectExamExaminationPage(ExamExamination examExamination) {
        startPage();
        return examExaminationMapper.selectExamExaminationList(examExamination);
    }

    @Override
    public Integer countScore( List<ExamUserExaminationQuestionVO> examUserExaminationQuestionVOS, Integer examinationId) {
        Integer score = 0;
        ExamExamination examExamination = examExaminationService.selectById(examinationId);
        Integer paperId = examExamination.getExamPaperId();

        for (ExamUserExaminationQuestionVO item : examUserExaminationQuestionVOS) {
            String userAnswer = item.getUserAnswer();
            Integer examQuestionId = item.getExamQuestionId();
            ExamQuestion examQuestion = examQuestionService.selectById(examQuestionId);
            if(!(examQuestion.getType().equals("1")||examQuestion.getType().equals("2")||examQuestion.getType().equals("3"))){
                continue;
            }
            if (examQuestion.getAnswer().equals(userAnswer)) {
                ExamPaperQuestion examPaperQuestion = new ExamPaperQuestion();
                examPaperQuestion.setExamPaperId(paperId);
                examPaperQuestion.setExamQuestionId(examQuestionId);
                List<ExamPaperQuestionVO> paperQuestionVOS = examPaperQuestionService.selectExamPaperQuestionList(examPaperQuestion);
                if (paperQuestionVOS.size() > 0) {
                    ExamPaperQuestionVO paperQuestionVO = paperQuestionVOS.get(0);
                    score += (paperQuestionVO.getScore() == null ? 0 : paperQuestionVO.getScore());  }
                }

        }
        return score;
    }

    @Override
    public List<ExamExaminationResultVo> selectExamExaminationResultById(Integer examId) {
        List<ExamExaminationResultVo> examExaminationResultVoList = examExaminationUserService.selectExamExaminationResultByExamId(examId);
        for (ExamExaminationResultVo resultVo : examExaminationResultVoList) {
           ExamUserExamination userExamination = examUserExaminationService.selectById(resultVo.getUserExamId());
            resultVo.setFinshTime(userExamination.getUpdateDate());
        }
        return examExaminationResultVoList;
    }

    @Override
    public int update(ExamExamination examExamination) {
        return examExaminationMapper.update(examExamination);
    }

    @Override
    public ExamExaminationVO selectExamExaminationById(Integer id) {
        return examExaminationMapper.selectExamExaminationById(id);
    }

    @Override
    public int countExamQuestion(Integer id) {
        return examExaminationMapper.countExamQuestion(id);
    }
    /**
     * 校验考试名称是否唯一
     * @param name
     * @param type
     * @return
     */
    @Override
    public String checkNameUnique(String name, String type) {
        List<ExamExaminationVO> examExaminationVOList = examExaminationMapper.selectByNameAndType(name, type);
        if (CollectionUtils.isEmpty(examExaminationVOList)) {
            return ExamConstants.EXAM_NAME_UNIQUE;
        }
        return ExamConstants.EXAM_NAME_NOT_UNIQUE;
    }
}
