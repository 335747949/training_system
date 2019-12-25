package com.ruoyi.exam.controller;

import java.awt.*;
import java.util.*;
import java.util.List;

import cn.hutool.core.lang.*;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.ruoyi.exam.domain.*;
import com.ruoyi.exam.service.*;
import com.ruoyi.framework.web.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.ExcelUtil;

/**
 * 试卷 信息操作处理
 *
 * @author zhujj
 * @date 2018-12-16
 */
@Controller
@RequestMapping("/exam/examPaper")
public class ExamPaperController extends BaseController {
    private String prefix = "exam/examPaper";

    @Autowired
    private IExamPaperService examPaperService;

    @Autowired
    private IExamPaperCategoryService examPaperCategoryService;

    @Autowired
    private IExamPaperQuestionService examPaperQuestionService;

    @Autowired
    private IExamQuestionService examQuestionService;

    @Autowired
    private IExamPaperTypeNumberService examPaperTypeNumberService;


    @RequiresPermissions("exam:examPaper:view")
    @GetMapping()
    public String examPaper() {
        return prefix + "/examPaper";
    }


    /**
     * 查询试卷列表
     */
    @RequiresPermissions("exam:examPaper:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ExamPaper examPaper) {
        List<ExamPaper> list = new ArrayList<>();
        List<ExamPaper> listByIds = examPaperService.selectListByCategory(examPaper);
        return getDataTable(listByIds);
    }

    /**
     * 递归找到所有下级的试卷
     *
     * @param list
     * @param examPaper
     * @return
     */
    private List<ExamPaper> getListByIds(List<ExamPaper> list, ExamPaper examPaper) {
        list.addAll(examPaperService.selectExamPaperList(examPaper));
        String categoryId = examPaper.getExamPaperCategoryId().toString();
        ExamPaperCategory examPaperCategory = new ExamPaperCategory();
        examPaperCategory.setParentId(Integer.parseInt(categoryId));
        List<ExamPaperCategory> examPaperCategorys = examPaperCategoryService.selectList(examPaperCategory);
        for (ExamPaperCategory questionCategory : examPaperCategorys) {
            examPaper.setExamPaperCategoryId(questionCategory.getId());
            getListByIds(list, examPaper);
        }
        return list;
    }


    /**
     * 导出试卷列表
     */
    @RequiresPermissions("exam:examPaper:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ExamPaper examPaper) {
        List<ExamPaper> list = examPaperService.selectExamPaperList(examPaper);
        ExcelUtil<ExamPaper> util = new ExcelUtil<ExamPaper>(ExamPaper.class);
        return util.exportExcel(list, "examPaper");
    }

    /**
     * 新增试卷
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") String id, ModelMap mmap) {
        mmap.put("examPaperCategoryId", id);
        return prefix + "/add";
    }

    /**
     * 新增保存试卷
     */
    @RequiresPermissions("exam:examPaper:add")
    @Log(title = "试卷", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ExamPaperVO examPaper) {
        examPaper.setCreateBy(ShiroUtils.getLoginName());
        examPaper.setCreateDate(new Date());
        examPaper.setScore(0);
        examPaper.setQuestionNumber(0);
        examPaper.setDelFlag("0");
        int insert = examPaperService.insert((ExamPaper) examPaper);


        ExamPaperTypeNumber examPaperTypeNumber1 = new ExamPaperTypeNumber();
        examPaperTypeNumber1.setDelFlag("0");
        examPaperTypeNumber1.setExamPaperId(examPaper.getId());
        examPaperTypeNumber1.setExamQuestionType(1);
        examPaperTypeNumber1.setNumber(examPaper.getChoiceNumber());
        examPaperTypeNumberService.insert(examPaperTypeNumber1);

        ExamPaperTypeNumber examPaperTypeNumber2 = new ExamPaperTypeNumber();
        examPaperTypeNumber2.setDelFlag("0");
        examPaperTypeNumber2.setExamPaperId(examPaper.getId());
        examPaperTypeNumber2.setExamQuestionType(2);
        examPaperTypeNumber2.setNumber(examPaper.getMoreChoiceNumber());
        examPaperTypeNumberService.insert(examPaperTypeNumber2);

        ExamPaperTypeNumber examPaperTypeNumber3 = new ExamPaperTypeNumber();
        examPaperTypeNumber3.setDelFlag("0");
        examPaperTypeNumber3.setExamPaperId(examPaper.getId());
        examPaperTypeNumber3.setExamQuestionType(3);
        examPaperTypeNumber3.setNumber(examPaper.getJudgeNumber());
        examPaperTypeNumberService.insert(examPaperTypeNumber3);


        return success();
    }

    /**
     * 修改试卷
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        JSONObject jsonObject = new JSONObject(examPaperService.selectById(id));
        ExamPaperVO examPaper = jsonObject.toBean(ExamPaperVO.class);
        examPaper.setChoiceNumber(0);
        examPaper.setMoreChoiceNumber(0);
        examPaper.setJudgeNumber(0);
        if (examPaper.getType().equals("2")) {
            ExamPaperTypeNumber examPaperTypeNumber = new ExamPaperTypeNumber();
            examPaperTypeNumber.setExamPaperId(id);
            List<ExamPaperTypeNumber> examPaperTypeNumbers = examPaperTypeNumberService.selectList(examPaperTypeNumber);
            for (ExamPaperTypeNumber item : examPaperTypeNumbers) {
                if (item.getExamQuestionType() == 1) {
                    examPaper.setChoiceNumber(item.getNumber());
                }
                if (item.getExamQuestionType() == 2) {
                    examPaper.setMoreChoiceNumber(item.getNumber());
                }
                if (item.getExamQuestionType() == 3) {
                    examPaper.setJudgeNumber(item.getNumber());
                }
            }
        }

        mmap.put("examPaper", examPaper);
        return prefix + "/edit";
    }

    /**
     * 修改保存试卷
     */
    @RequiresPermissions("exam:examPaper:edit")
    @Log(title = "试卷", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ExamPaperVO examPaper) {

        ExamPaperTypeNumber delete = new ExamPaperTypeNumber();
        delete.setExamPaperId(examPaper.getId());
        examPaperTypeNumberService.delete(delete);

        ExamPaperTypeNumber examPaperTypeNumber1 = new ExamPaperTypeNumber();
        examPaperTypeNumber1.setDelFlag("0");
        examPaperTypeNumber1.setExamPaperId(examPaper.getId());
        examPaperTypeNumber1.setExamQuestionType(1);
        examPaperTypeNumber1.setNumber(examPaper.getChoiceNumber());
        examPaperTypeNumberService.insert(examPaperTypeNumber1);

        ExamPaperTypeNumber examPaperTypeNumber2 = new ExamPaperTypeNumber();
        examPaperTypeNumber2.setDelFlag("0");
        examPaperTypeNumber2.setExamPaperId(examPaper.getId());
        examPaperTypeNumber2.setExamQuestionType(2);
        examPaperTypeNumber2.setNumber(examPaper.getMoreChoiceNumber());
        examPaperTypeNumberService.insert(examPaperTypeNumber2);

        ExamPaperTypeNumber examPaperTypeNumber3 = new ExamPaperTypeNumber();
        examPaperTypeNumber3.setDelFlag("0");
        examPaperTypeNumber3.setExamPaperId(examPaper.getId());
        examPaperTypeNumber3.setExamQuestionType(3);
        examPaperTypeNumber3.setNumber(examPaper.getJudgeNumber());
        examPaperTypeNumberService.insert(examPaperTypeNumber3);

        if(examPaper.getType().equals("2")){
            int number = examPaper.getChoiceNumber()+examPaper.getMoreChoiceNumber()+examPaper.getJudgeNumber();
            examPaper.setQuestionNumber(number);
        }

        examPaper.setUpdateBy(ShiroUtils.getLoginName());
        examPaper.setUpdateDate(new Date());

        examPaperService.updateSelectiveById(examPaper);

        return success();
    }

    /**
     * 删除试卷
     */
    @RequiresPermissions("exam:examPaper:remove")
    @Log(title = "试卷", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        String[] split = ids.split(",");
        for (String s : split) {
            ExamPaperTypeNumber delete = new ExamPaperTypeNumber();
            delete.setExamPaperId(Integer.parseInt(s));
            examPaperTypeNumberService.delete(delete);
        }
        return toAjax(examPaperService.deleteByIds(ids));
    }


    @GetMapping("/addQuestion/{id}")
    public String addQuestion(@PathVariable("id") String ids, ModelMap mmap) {
        String[] split = ids.split(",");
        List<String> strings = Arrays.asList(split);
        mmap.put("examPaperId", strings.get(0));
        mmap.put("examPaperQuestionIds", strings.subList(1, strings.size()));
        return prefix + "/examQuestion";
    }

    //	@RequiresPermissions("exam:examPaper:add")
//	@Log(title = "试卷", businessType = BusinessType.DELETE)
    @RequestMapping("/saveQuestion")
    @ResponseBody
    public AjaxResult saveQuestion(@RequestBody List<ExamPaperQuestion> paperQuestionList) {
        ExamPaperQuestion examPaperQuestion = paperQuestionList.get(0);
        ExamPaperQuestion delete = new ExamPaperQuestion();
        delete.setExamPaperId(examPaperQuestion.getExamPaperId());
        ExamPaper examPaper = new ExamPaper();
        examPaper.setId(examPaperQuestion.getExamPaperId());
        ExamPaper paper = examPaperService.selectById(examPaperQuestion.getExamPaperId());
        String type = paper.getType();

        examPaperQuestionService.delete(delete);
        int num = 0;
        int score = 0;
        int choiceScore = 0;
        int choiceNum = 0;
        int moreChoiceScore = 0;
        int moreChoiceNum = 0;
        int judgeScore = 0;
        int judgeNum = 0;

        for (int i = 1; i < paperQuestionList.size(); i++) {
            ExamPaperQuestion item = paperQuestionList.get(i);
            item.setDelFlag("0");
            examPaperQuestionService.insert(item);
            ExamQuestion question = examQuestionService.selectById(item.getExamQuestionId());
            if ("1".equals(question.getType())){
                choiceScore =(item.getScore() == null ? 0 : item.getScore()) ;
                choiceNum++ ;

            }else if ("2".equals(question.getType())){
                moreChoiceScore = (item.getScore() == null ? 0 : item.getScore()) ;
                moreChoiceNum++ ;
            }else if ("3".equals(question.getType())){
                judgeScore = (item.getScore() == null ? 0 : item.getScore()) ;
                judgeNum ++;
            }
        }

        // 計算縂分數
        if (type.equals("1")){
            for (int i = 1; i < paperQuestionList.size(); i++) {
                ExamPaperQuestion item = paperQuestionList.get(i);
                item.setDelFlag("0");
                examPaperQuestionService.insert(item);
                num++;
                score += (item.getScore() == null ? 0 : item.getScore()) ;
            }
            //固定考试才更新题目数量
            examPaper.setQuestionNumber(num);
            examPaper.setScore(score);
        }else {
            ExamPaperTypeNumber examPaperTypeNumber = new ExamPaperTypeNumber();
            examPaperTypeNumber.setExamPaperId(paper.getId());
            List<ExamPaperTypeNumber> examPaperTypeNumbers = examPaperTypeNumberService.selectList(examPaperTypeNumber);
            for (ExamPaperTypeNumber typeNumber : examPaperTypeNumbers) {
                if (typeNumber.getExamQuestionType() == 1){
                    if (typeNumber.getNumber().compareTo(choiceNum) < 0){
                        score += ((typeNumber.getNumber() == null ? 0 : typeNumber.getNumber()) * choiceScore);
                        num += typeNumber.getNumber();
                    }else {
                        score += (choiceNum * choiceScore);
                        num += choiceNum;
                    }
                }else if (typeNumber.getExamQuestionType() == 2){
                    if (typeNumber.getNumber().compareTo(moreChoiceNum) < 0){
                        score += ((typeNumber.getNumber() == null ? 0 : typeNumber.getNumber()) * moreChoiceScore);
                        num += typeNumber.getNumber();
                    }else {
                        score += (moreChoiceNum * moreChoiceScore);
                        num += moreChoiceNum;
                    }
                }else if (typeNumber.getExamQuestionType() == 3){
                    if (typeNumber.getNumber().compareTo(judgeNum) < 0){
                        score += ((typeNumber.getNumber() == null ? 0 : typeNumber.getNumber()) * judgeScore);
                        num += typeNumber.getNumber();
                    }else {
                        score += (judgeNum * judgeScore);
                        num += judgeNum;
                    }
                }
            }
            examPaper.setQuestionNumber(num);
            examPaper.setScore(score);
        }

        examPaperService.updateSelectiveById(examPaper);
        return AjaxResult.success();
    }


    @GetMapping("/toManagerPaperQuestion/{id}")
    public String toManagerPaperQuestion(@PathVariable("id") Integer id, ModelMap mmap) {
        mmap.put("examPaper", examPaperService.selectById(id));
        ExamPaperTypeNumber examPaperTypeNumber = new ExamPaperTypeNumber();
        examPaperTypeNumber.setExamPaperId(id);
        List<ExamPaperTypeNumber> examPaperTypeNumbers = examPaperTypeNumberService.selectList(examPaperTypeNumber);
        for (ExamPaperTypeNumber item : examPaperTypeNumbers) {
            if (item.getExamQuestionType() == 1) {
                mmap.put("choiceNumber",item.getNumber());
            }
            if (item.getExamQuestionType() == 2) {
                mmap.put("moreChoiceNumber",item.getNumber());
            }
            if (item.getExamQuestionType() == 3) {
                mmap.put("judgeNumber",item.getNumber());
            }
        }
        JSONObject json = new JSONObject();
        List<ExamPaperQuestionVO> examPaperQuestions = examPaperQuestionService.selectQuestionForPaperId(id);
        for (ExamPaperQuestionVO examPaperQuestion : examPaperQuestions) {
            if (null == examPaperQuestion.getScore()){
                examPaperQuestion.setScore(0);
            }
            //排序用
            json.append(examPaperQuestion.getOrderNum().toString() + examPaperQuestion.getExamQuestionId().toString(), new JSONObject(examPaperQuestion).toString());
        }
        mmap.put("examPaperQuestion", json.toString());
        return prefix + "/managerPaperQuestion";
    }

    @RequiresPermissions("exam:examPaper:add")
    @Log(title = "试卷", businessType = BusinessType.DELETE)
    @PostMapping("/addQuestionForModel")
    @ResponseBody
    public AjaxResult addQuestionForModel(@RequestParam(value = "questionId[]", required = false) String[] questionId, @RequestParam("paperId") String paperId) {
        //题目数量和总分数
        int questionNum = 0;
        int score = 0;
        ExamPaperQuestion examPaperQuestion = new ExamPaperQuestion();
        examPaperQuestion.setExamPaperId(Integer.parseInt(paperId));
        ExamPaper examPaper = new ExamPaper();
        if (questionId == null) {
            examPaperQuestionService.delete(examPaperQuestion);
            examPaper.setId(Integer.parseInt(paperId));
            examPaper.setQuestionNumber(0);
            examPaper.setScore(0);
            examPaperService.updateSelectiveById(examPaper);
            return AjaxResult.success();
        }
        List<ExamPaperQuestionVO> dbDatas = examPaperQuestionService.selectExamPaperQuestionList(examPaperQuestion);
        questionNum += dbDatas.size();
        HashSet<Integer> dbSet = new HashSet<>();
        for (ExamPaperQuestionVO dbData : dbDatas) {
            dbSet.add(dbData.getExamQuestionId());
            score += (dbData.getScore() == null ? 0 : dbData.getScore());
        }

        HashSet<Integer> htmlSet = new HashSet<>();
        //新增的
        for (String s : questionId) {
            Integer i = Integer.parseInt(s);
            if (!dbSet.contains(i)) {
                ExamPaperQuestion insert = new ExamPaperQuestion();
                insert.setExamPaperId(Integer.parseInt(paperId));
                insert.setDelFlag("0");
                insert.setCreateDate(new Date());
                insert.setCreateBy(ShiroUtils.getLoginName());
                insert.setExamQuestionId(i);
                insert.setOrderNum(9999);
                insert.setScore(0);
                examPaperQuestionService.insert(insert);
                questionNum++;
            }
            htmlSet.add(i);
        }

        for (ExamPaperQuestionVO dbData : dbDatas) {
            if (!htmlSet.contains(dbData.getExamQuestionId())) {
                examPaperQuestionService.delete(dbData);
                questionNum--;
                score -= (dbData.getScore() == null ? 0 : dbData.getScore());
            }
        }

        examPaper.setId(Integer.parseInt(paperId));
        examPaper.setQuestionNumber(questionNum);
        examPaper.setScore(score);
        examPaperService.updateSelectiveById(examPaper);

        return AjaxResult.success();
    }
}
