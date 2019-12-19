package com.ruoyi.exam.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import cn.hutool.json.JSONArray;
import com.ruoyi.common.json.JSONObject;
import com.ruoyi.exam.domain.*;
import com.ruoyi.exam.service.IExamQuestionCategoryService;
import com.ruoyi.exam.service.IExamQuestionItemService;
import com.ruoyi.framework.web.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.exam.service.IExamQuestionService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;


/**
 * 问题 信息操作处理
 * 
 * @author zhujj
 * @date 2018-12-06
 */
@Controller
@RequestMapping("/exam/examQuestion")
public class ExamQuestionController extends BaseController
{
    private String prefix = "exam/examQuestion";
	
	@Autowired
	private IExamQuestionService examQuestionService;

	@Autowired
	private IExamQuestionCategoryService examQuestionCategoryService;

	@Autowired
    private IExamQuestionItemService examQuestionItemService;
	
	@RequiresPermissions("exam:examQuestion:view")
	@GetMapping()
	public String examQuestion()
	{
	    return prefix + "/examQuestion";
	}

	@GetMapping("/choiceadd/{id}")
	public String addChoiceUrl(@PathVariable("id") String id, ModelMap mmap)
	{
		mmap.put("categoryId",id);
		mmap.put("type",1);
		return prefix + "/choiceadd";
	}

	@GetMapping("/morechoiceadd/{id}")
	public String addMoreChoiceUrl(@PathVariable("id") String id, ModelMap mmap)
	{
		mmap.put("categoryId",id);
		mmap.put("type",2);
		return prefix + "/morechoiceadd";
	}

	@GetMapping("/judgeadd/{id}")
	public String JudgeUrl(@PathVariable("id") String id, ModelMap mmap)
	{
		mmap.put("categoryId",id);
		mmap.put("type",3);
		return prefix + "/judgeadd";
	}
	
	/**
	 * 查询问题列表
	 */
	@RequiresPermissions("exam:examQuestion:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ExamQuestion examQuestion)
	{
		ArrayList<ExamQuestion> objects = new ArrayList<>();
		List<ExamQuestion> listByIds = examQuestionService.selectListBycategory(examQuestion);
		return getDataTable(listByIds);
	}

	/**
	 * 递归找到所有下级的题目
	 * @param list
	 * @param examQuestion
	 * @return
	 */
	private List<ExamQuestion> getListByIds(List<ExamQuestion> list,ExamQuestion examQuestion){
		list.addAll(examQuestionService.selectQuestionList(examQuestion));
		String categoryId = examQuestion.getCategoryId();
		if (categoryId != null) {
			ExamQuestionCategory examQuestionCategory = new ExamQuestionCategory();
			examQuestionCategory.setParentId(Long.parseLong(categoryId));
			List<ExamQuestionCategory> examQuestionCategories = examQuestionCategoryService.selectList(examQuestionCategory);
			for (ExamQuestionCategory questionCategory : examQuestionCategories) {
				examQuestion.setCategoryId(questionCategory.getId().toString());
				getListByIds(list,examQuestion);
			}
		}
		return list;
	}
	
	
	/**
	 * 导出问题列表
	 */
	@RequiresPermissions("exam:examQuestion:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ExamQuestion examQuestion)
    {
    	List<ExcelQuestion> list = examQuestionService.selectExamQuestionListForExcel(examQuestion);
        ExcelUtil<ExcelQuestion> util = new ExcelUtil<ExcelQuestion>(ExcelQuestion.class);
        return util.exportExcel(list, "question");
    }
	
	/**
	 * 新增问题
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存问题
	 */
	@RequiresPermissions("exam:examQuestion:add")
	@Log(title = "问题", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ExamQuestion examQuestion,@RequestParam(value = "number", required = true) String[] number,
							  @RequestParam(value = "content", required = true) String[] content)
	{

		return toAjax(examQuestionService.insertQuestion(examQuestion,number,content));
	}


	@RequiresPermissions("exam:examQuestion:edit")
	@Log(title = "问题", businessType = BusinessType.INSERT)
	@PostMapping("/update")
	@ResponseBody
	public AjaxResult update(ExamQuestion examQuestion,@RequestParam(value = "number", required = true) String[] number,
							  @RequestParam(value = "content", required = true) String[] content)
	{

		return toAjax(examQuestionService.updateQuestion(examQuestion,number,content));
	}

	/**
	 * 修改问题
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		ExamQuestion examQuestion = examQuestionService.selectExamQuestionById(id);
		mmap.put("examQuestion", examQuestion);
		ExamQuestionItem examQuestionItem = new ExamQuestionItem();
		examQuestionItem.setExamQuestionId(id);
		List<ExamQuestionItem> examQuestionItems = examQuestionItemService.selectList(examQuestionItem);
		JSONArray arr = new JSONArray();
		arr.addAll(examQuestionItems);
		mmap.put("examQuestionItem", arr.toString());
		String s = "";
		if(examQuestion.getType().equals("1")){
			s= "/choiceUpdate";
		}else if(examQuestion.getType().equals("2")){
			s = "/morechoiceUpdate";
		}else{
			s = "/judgeUpdate";
		}
		return prefix + s;
	}
	
	/**
	 * 修改保存问题
	 */
	@RequiresPermissions("exam:examQuestion:edit")
	@Log(title = "问题", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ExamQuestion examQuestion)
	{		
		return toAjax(examQuestionService.updateExamQuestion(examQuestion));
	}
	
	/**
	 * 删除问题
	 */
	@RequiresPermissions("exam:examQuestion:remove")
	@Log(title = "问题", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
	    examQuestionItemService.deleteByQuestionIds(ids);
		return toAjax(examQuestionService.deleteExamQuestionByIds(ids));
	}


	@RequiresPermissions("exam:examQuestion:import")
	@Log(title = "问题", businessType = BusinessType.INSERT)
	@PostMapping( "/importfile")
	@ResponseBody
	@Transactional(rollbackFor = Exception.class)
	public AjaxResult importfile(@RequestParam("file") MultipartFile file, @RequestParam("categoryId")String id) throws Exception {

		InputStream inputStream = file.getInputStream();

		ExcelUtil<ExamQuestionFile> util = new ExcelUtil<ExamQuestionFile>(ExamQuestionFile.class);
		List<ExamQuestionFile> examQuestions = util.importExcel(inputStream);
		try {
			for (ExamQuestionFile item : examQuestions) {
				ExamQuestion insert = new ExamQuestion();
				insert.setCategoryId(id);
				insert.setCreateBy(ShiroUtils.getLoginName());
				insert.setAnswer(item.getAnswer());
				insert.setCreateDate(new Date());
				insert.setDelFlag("0");
				insert.setRemarks(item.getRemarks());
				insert.setTitle(item.getName());
				String type = "1";

				if (item.getItemA().equals("正确")||item.getItemA().equals("错误")) {
					type ="3";
				}else if(item.getAnswer().split(",").length>1){
					type="2";
				}
				insert.setType(type);
				examQuestionService.insertExamQuestion(insert);
				if(item.getItemA()!=null&&!item.getItemA().trim().equals("")){
					ExamQuestionItem examQuestionItem = new ExamQuestionItem();
					examQuestionItem.setExamQuestionId(insert.getId());
					examQuestionItem.setNumber("A");
					examQuestionItem.setContent(item.getItemA());
					examQuestionItem.setCreateBy(ShiroUtils.getLoginName());
					examQuestionItem.setCreateDate(new Date());
					examQuestionItem.setDelFlag("0");
					examQuestionItemService.insert(examQuestionItem);

				}
				if(item.getItemB()!=null&&!item.getItemB().trim().equals("")){
					ExamQuestionItem examQuestionItem = new ExamQuestionItem();
					examQuestionItem.setExamQuestionId(insert.getId());
					examQuestionItem.setNumber("B");
					examQuestionItem.setContent(item.getItemB());
					examQuestionItem.setCreateBy(ShiroUtils.getLoginName());
					examQuestionItem.setCreateDate(new Date());
					examQuestionItem.setDelFlag("0");
					examQuestionItemService.insert(examQuestionItem);


				}
				if(item.getItemC()!=null&&!item.getItemC().trim().equals("")){
					ExamQuestionItem examQuestionItem = new ExamQuestionItem();
					examQuestionItem.setExamQuestionId(insert.getId());
					examQuestionItem.setNumber("C");
					examQuestionItem.setContent(item.getItemC());
					examQuestionItem.setCreateBy(ShiroUtils.getLoginName());
					examQuestionItem.setCreateDate(new Date());
					examQuestionItem.setDelFlag("0");
					examQuestionItemService.insert(examQuestionItem);

				}
				if(item.getItemD()!=null&&!item.getItemD().trim().equals("")){
					ExamQuestionItem examQuestionItem = new ExamQuestionItem();
					examQuestionItem.setExamQuestionId(insert.getId());
					examQuestionItem.setNumber("D");
					examQuestionItem.setContent(item.getItemD());
					examQuestionItem.setCreateBy(ShiroUtils.getLoginName());
					examQuestionItem.setCreateDate(new Date());
					examQuestionItem.setDelFlag("0");
					examQuestionItemService.insert(examQuestionItem);

				}
				if(item.getItemE()!=null&&!item.getItemE().trim().equals("")){
					ExamQuestionItem examQuestionItem = new ExamQuestionItem();
					examQuestionItem.setExamQuestionId(insert.getId());
					examQuestionItem.setNumber("E");
					examQuestionItem.setContent(item.getItemE());
					examQuestionItem.setCreateBy(ShiroUtils.getLoginName());
					examQuestionItem.setCreateDate(new Date());
					examQuestionItem.setDelFlag("0");
					examQuestionItemService.insert(examQuestionItem);

				}
				if(item.getItemF()!=null&&!item.getItemF().trim().equals("")){
					ExamQuestionItem examQuestionItem = new ExamQuestionItem();
					examQuestionItem.setExamQuestionId(insert.getId());
					examQuestionItem.setNumber("F");
					examQuestionItem.setContent(item.getItemF());
					examQuestionItem.setCreateBy(ShiroUtils.getLoginName());
					examQuestionItem.setCreateDate(new Date());
					examQuestionItem.setDelFlag("0");
					examQuestionItemService.insert(examQuestionItem);

				}

			}
		}catch (Exception e){
			return error("导入失败，请检查文件后重试");
		}

		return success("导入成功");
	}
	
}
