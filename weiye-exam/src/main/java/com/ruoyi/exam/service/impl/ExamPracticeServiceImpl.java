package com.ruoyi.exam.service.impl;

import com.ruoyi.common.constant.ExamConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.exam.domain.ExamPractice;
import com.ruoyi.exam.domain.ExamPracticeVO;
import com.ruoyi.exam.mapper.ExamPracticeMapper;
import com.ruoyi.exam.service.IExamPracticeService;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 练习 服务层实现
 * 
 * @author zhujj
 * @date 2018-12-28
 */
@Service
public class ExamPracticeServiceImpl extends AbstractBaseServiceImpl<ExamPracticeMapper,ExamPractice> implements IExamPracticeService
{
	@Autowired
	private ExamPracticeMapper examPracticeMapper;

	
	/**
     * 查询练习列表
     * 
     * @param examPractice 练习信息
     * @return 练习集合
     */
	@Override
	public List<ExamPractice> selectExamPracticeList(ExamPractice examPractice)
	{
        return examPracticeMapper.selectExamPracticeList(examPractice);
	}

    @Override
    public List<ExamPracticeVO> selectListFromWeb(ExamPractice examPractice) {
        startPage();
        return examPracticeMapper.selectListFromWeb(examPractice);
    }


    /**
     * 查询练习分页列表
     *
     * @param examPractice 练习信息
     * @return 练习集合
     */
    @Override
    public List<ExamPractice> selectExamPracticePage(ExamPractice examPractice)
    {
        startPage();
        return examPracticeMapper.selectExamPracticeList(examPractice);
    }

    @Override
    public int update(ExamPractice examPractice) {
        return examPracticeMapper.update(examPractice);
    }

    @Override
    public ExamPracticeVO selectExamPracticeById(Integer id) {
        return examPracticeMapper.selectExamPracticeById(id);
    }

    @Override
    public String checkNameUnique(String name) {
        List<ExamPracticeVO> examPracticeVOList = examPracticeMapper.selectByName(name);
        if (CollectionUtils.isEmpty(examPracticeVOList)) {
            return ExamConstants.EXAM_NAME_UNIQUE;
        }
        return ExamConstants.EXAM_NAME_NOT_UNIQUE;
    }
}
