package cc.mrbird.febs.cloud.service.impl;

import cc.mrbird.febs.cloud.entity.Job;
import cc.mrbird.febs.cloud.entity.JobUpdate;
import cc.mrbird.febs.cloud.mapper.JobMapper;
import cc.mrbird.febs.cloud.service.IJobService;
import cc.mrbird.febs.cloud.service.IJobUpdateService;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 作业对象表 Service实现
 *
 * @author lai
 * @since 2020-09-15
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements IJobService {
    @Autowired
    IJobUpdateService jobUpdateService;

    /**
     * 作业对象表分页列表
     *
     * @param request QueryRequest
     * @param request job job
     * @return IPage<Job>
     */
    @Override
    public IPage<Job> page(QueryRequest request, Job param) {
        LambdaQueryWrapper<Job> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件

        Page<Job> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    /**
     * 作业对象表详情
     *
     * @param id
     * @return
     */
    @Override
    public Job info(Integer id) {
        return getById(id);
    }

    /**
     * 作业对象表新增
     *
     * @param param 根据需要进行传值
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer add(Job param) {
        Date date = new Date();
        param.setRegisterDate(date);
        if (this.baseMapper.insert(param) != 1) {
            throw new FebsException("作业对象" + param.getName() + "注册失败1");
        }
        int jobId = param.getJobId();

        JobUpdate jobUpdate = new JobUpdate();
        jobUpdate.setJobId(jobId);
        jobUpdate.setRemark("");
        jobUpdate.setRepairSchedule("");
        jobUpdate.setStatus(param.getStatus());
        jobUpdate.setUpdateDate(date);
        if (!jobUpdateService.save(jobUpdate)) {
            throw new FebsException("作业对象" + param.getName() + "注册失败2");
        }
        return jobId;
    }

    /**
     * 作业对象表修改
     *
     * @param param 根据需要进行传值
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify(Job param) {
        //修改job表的信息，添加信息到jobupdate
        updateById(param);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify(JobUpdate param) {
        this.checkIsExistById(param.getJobId());

        Date updateDate = new Date();
        //修改job表的信息，添加信息到jobupdate
        Job job = new Job();
        job.setJobId(param.getJobId());
        job.setStatus(param.getStatus());
        job.setRepairSchedule(param.getRepairSchedule());
        job.setUpdateDate(updateDate);
        this.updateById(job);

        param.setUpdateDate(updateDate);
        if (!jobUpdateService.save(param)) {
            throw new FebsException("编号为：" + param.getJobId() + "的作业对象更新失败");
        }

    }

    /**
     * 作业对象表删除(单个条目)
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Integer id) {
        removeById(id);
    }

    /**
     * 作业对象表删除(多个条目)
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removes(List<Integer> ids) {
        removeByIds(ids);
    }

    /**
     * 判断ID是否存在
     *
     * @param jobId
     * @return
     */
    @Override
    public void checkIsExistById(int jobId) {
        LambdaQueryWrapper<Job> lambdaQueryWrapper = new LambdaQueryWrapper<Job>();
        lambdaQueryWrapper.eq(Job::getJobId, jobId);
        if (this.count(lambdaQueryWrapper) != 1) {
            throw new FebsException("编号为：" + jobId + "的作业对象不存在");
        }
    }
}
