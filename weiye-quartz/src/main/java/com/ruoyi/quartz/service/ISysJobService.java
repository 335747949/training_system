package com.ruoyi.quartz.service;

import java.util.List;

import com.ruoyi.framework.web.base.AbstractBaseService;
import com.ruoyi.quartz.domain.SysJob;
import com.ruoyi.quartz.domain.SysJobLog;

/**
 * 定时任务调度信息信息 服务层
 * 
 * @author ruoyi
 */
public interface ISysJobService extends AbstractBaseService<SysJob>
{
    /**
     * 获取quartz调度器的计划任务
     * 
     * @param job 调度信息
     * @return 调度任务集合
     */
    public List<SysJob> selectJobList(SysJob job);

    /**
     * 通过调度任务ID查询调度信息
     * 
     * @param jobId 调度任务ID
     * @return 调度任务对象信息
     */
    public SysJob selectJobById(Long jobId);

    /**
     * 暂停任务
     * 
     * @param job 调度信息
     * @return 结果
     */
    public int pauseJob(SysJob job);

    /**
     * 恢复任务
     * 
     * @param job 调度信息
     * @return 结果
     */
    public int resumeJob(SysJob job);

    /**
     * 删除任务后，所对应的trigger也将被删除
     * 
     * @param job 调度信息
     * @return 结果
     */
    public int deleteJob(SysJob job);

    /**
     * 批量删除调度信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public void deleteJobByIds(String ids);

    /**
     * 任务调度状态修改
     * 
     * @param job 调度信息
     * @return 结果
     */
    public int changeStatus(SysJob job);

    /**
     * 立即运行任务
     * 
     * @param job 调度信息
     * @return 结果
     */
    public int run(SysJob job);

    /**
     * 新增任务表达式
     * 
     * @param job 调度信息
     * @return 结果
     */
    public int insertJobCron(SysJob job);

    /**
     * 更新任务的时间表达式
     * 
     * @param job 调度信息
     * @return 结果
     */
    public int updateJobCron(SysJob job);
    
    /**
     * 校验cron表达式是否有效
     * 
     * @param cronExpression 表达式
     * @return 结果
     */
    public boolean checkCronExpressionIsValid(String cronExpression);
}
