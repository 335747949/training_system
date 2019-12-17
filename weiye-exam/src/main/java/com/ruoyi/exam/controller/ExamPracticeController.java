package com.ruoyi.exam.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import cn.hutool.json.JSONObject;
import com.ruoyi.exam.domain.*;
import com.ruoyi.exam.service.IExamPracticeQuestionService;
import com.ruoyi.framework.web.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.exam.service.IExamPracticeService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.ExcelUtil;

/**
 * 练习 信息操作处理
 * 
 * @author zhujj
 * @date 2018-12-28
 */
@Controller
@RequestMapping("/exam/examPractice")
public class ExamPracticeController extends BaseController
{
    private String prefix = "exam/examPractice";
	
	@Autowired
	private IExamPracticeService examPracticeService;

	@Autowired
	private IExamPracticeQuestionService examPracticeQuestionService;
	
	@RequiresPermissions("exam:examPractice:view")
	@GetMapping()
	public String examPractice()
	{
	    return prefix + "/examPractice";
	}
	
	/**
	 * 查询练习列表
	 */
	@RequiresPermissions("exam:examPractice:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ExamPractice examPractice)
	{
        List<ExamPractice> list = examPracticeService.selectExamPracticePage(examPractice);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出练习列表
	 */
	@RequiresPermissions("exam:examPractice:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ExamPractice examPractice)
    {
    	List<ExamPractice> list = examPracticeService.selectExamPracticeList(examPractice);
        ExcelUtil<ExamPractice> util = new ExcelUtil<ExamPractice>(ExamPractice.class);
        return util.exportExcel(list, "examPractice");
    }
	
	/**
	 * 新增练习
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存练习
	 */
	@RequiresPermissions("exam:examPractice:add")
	@Log(title = "练习", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ExamPractice examPractice)
	{
		examPractice.setDelFlag("0");
		examPractice.setCreateBy(ShiroUtils.getLoginName());
		examPractice.setCreateDate(new Date());
		// 若不控制时间，考试开始结束时间为空
		if ("0".equals(examPractice.getEnableControlTime())){
			examPractice.setStartTime(null);
			examPractice.setEndTime(null);
		}
		return toAjax(examPracticeService.insert(examPractice));
	}

	/**
	 * 修改练习
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		ExamPractice examPractice = examPracticeService.selectById(id);
		mmap.put("examPractice", examPractice);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存练习
	 */
	@RequiresPermissions("exam:examPractice:edit")
	@Log(title = "练习", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ExamPractice examPractice)
	{
		examPractice.setDelFlag("0");
		examPractice.setUpdateBy(ShiroUtils.getLoginName());
		examPractice.setUpdateDate(new Date());
		// 若不控制时间，考试开始结束时间为空
		if ("0".equals(examPractice.getEnableControlTime())){
			examPractice.setStartTime(null);
			examPractice.setEndTime(null);
		}
		return toAjax(examPracticeService.update(examPractice));
	}
	
	/**
	 * 删除练习
	 */
	@RequiresPermissions("exam:examPractice:remove")
	@Log(title = "练习", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(examPracticeService.deleteByIds(ids));
	}


	@GetMapping("/toManagerPracticeQuestion/{id}")
	public String toManagerPaperQuestion(@PathVariable("id") Integer id, ModelMap mmap)
	{
		mmap.put("examPractice", examPracticeService.selectById(id));
		JSONObject json = new JSONObject();
		List<ExamPracticeQuestionVO> examPracticeQuestions = examPracticeQuestionService.selectQuestionForPracticeId(id);
		for (ExamPracticeQuestionVO examPracticeQuestion : examPracticeQuestions) {
			//排序用
			json.append(examPracticeQuestion.getOrderNum().toString()+examPracticeQuestion.getExamQuestionId().toString(),new JSONObject(examPracticeQuestion).toString());
		}
		mmap.put("examPracticeQuestion",json.toString());
		return prefix + "/managerPracticeQuestion";
	}

	@GetMapping("/addQuestion/{id}")
	public String addQuestion(@PathVariable("id") String ids, ModelMap mmap)
	{
		String[] split = ids.split(",");
		List<String> strings = Arrays.asList(split);
		mmap.put("examPracticeId", strings.get(0));
		mmap.put("examPracticeQuestionIds",strings.subList(1,strings.size()));
		return prefix + "/examQuestion";
	}

	//	@RequiresPermissions("exam:examPaper:add")
//	@Log(title = "试卷", businessType = BusinessType.DELETE)
	@PostMapping( "/saveQuestion")
	@ResponseBody
	public AjaxResult saveQuestion(@RequestBody List<ExamPracticeQuestion>  practiceQuestionList)
	{
		ExamPracticeQuestion examPracticeQuestion = practiceQuestionList.get(0);
		ExamPracticeQuestion delete = new ExamPracticeQuestion();
		delete.setExamPracticeId(examPracticeQuestion.getExamPracticeId());
		ExamPractice examPractice = new ExamPractice();
		examPractice.setId(examPracticeQuestion.getExamPracticeId());
		examPracticeQuestionService.delete(delete);
		int num =0;
		int score = 0;
		for (int i = 1; i < practiceQuestionList.size(); i++) {
			ExamPracticeQuestion item = practiceQuestionList.get(i);
			item.setDelFlag("0");
			examPracticeQuestionService.insert(item);
			num++;
			score+=item.getScore();
		}
		examPracticeService.updateSelectiveById(examPractice);
		return AjaxResult.success();
	}

	@RequiresPermissions("exam:examPractice:add")
	@Log(title = "试卷", businessType = BusinessType.DELETE)
	@PostMapping( "/addQuestionForModel")
	@ResponseBody
	public AjaxResult addQuestionForModel(@RequestParam(value = "questionId[]" ,required = false) String[] questionId,@RequestParam("practiceId")String practiceId)
	{
		//题目数量和总分数
		int questionNum = 0;
		int score = 0;
		ExamPracticeQuestion examPracticeQuestion = new ExamPracticeQuestion();
		examPracticeQuestion.setExamPracticeId(Integer.parseInt(practiceId));
		ExamPractice practice = new ExamPractice();
		if(questionId==null){
			examPracticeQuestionService.delete(examPracticeQuestion);
			practice.setId(Integer.parseInt(practiceId));
//			practice.setQuestionNumber(0);
//			practice.setScore(0);
			examPracticeService.updateSelectiveById(practice);
			return AjaxResult.success();
		}
		List<ExamPracticeQuestionVO> dbDatas = examPracticeQuestionService.selectExamPracticeQuestionList(examPracticeQuestion);
		questionNum +=dbDatas.size();
		HashSet<Integer> dbSet = new HashSet<>();
		for (ExamPracticeQuestionVO dbData : dbDatas) {
			dbSet.add(dbData.getExamQuestionId());
			score+=dbData.getScore();
		}

		HashSet<Integer> htmlSet = new HashSet<>();
		//新增的
		for (String s : questionId) {
			Integer i = Integer.parseInt(s);
			if(!dbSet.contains(i)){
				ExamPracticeQuestion insert = new ExamPracticeQuestion();
				insert.setExamPracticeId(Integer.parseInt(practiceId));
				insert.setDelFlag("0");
				insert.setCreateDate(new Date());
				insert.setCreateBy(ShiroUtils.getLoginName());
				insert.setExamQuestionId(i);
				insert.setOrderNum(9999);
				insert.setScore(0);
				examPracticeQuestionService.insert(insert);
				questionNum++;
			}
			htmlSet.add(i);
		}

		for (ExamPracticeQuestionVO dbData : dbDatas) {
			if(!htmlSet.contains(dbData.getExamQuestionId())){
				examPracticeQuestionService.delete(dbData);
				questionNum--;
				score-=dbData.getScore();
			}
		}

		practice.setId(Integer.parseInt(practiceId));
//		practice.setQuestionNumber(questionNum);
//		practice.setScore(score);
		examPracticeService.updateSelectiveById(practice);

		return AjaxResult.success();
	}



	
}
