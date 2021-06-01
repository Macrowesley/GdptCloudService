package cc.mrbird.febs.cloud.service.impl;

import cc.mrbird.febs.cloud.entity.Robot;
import cc.mrbird.febs.cloud.entity.Task;
import cc.mrbird.febs.cloud.entity.TaskUpdate;
import cc.mrbird.febs.cloud.mapper.TaskMapper;
import cc.mrbird.febs.cloud.service.IJobService;
import cc.mrbird.febs.cloud.service.IRobotService;
import cc.mrbird.febs.cloud.service.ITaskService;
import cc.mrbird.febs.cloud.service.ITaskUpdateService;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.common.utils.SortUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 任务表 Service实现
 *
 * @author lai
 * @since 2020-09-15
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {

    @Autowired
    ITaskUpdateService taskUpdateService;

    @Autowired
    IJobService jobService;

    @Autowired
    IRobotService robotService;

    /**
     * 任务表分页列表
     *
     * @param request QueryRequest
     * @param request task task
     * @return IPage<Task>
     */
    @Override
    public IPage<Task> page(QueryRequest request, Task param) {
        LambdaQueryWrapper<Task> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件

        Page<Task> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    /**
     * 任务表详情
     *
     * @param id
     * @return
     */
    @Override
    public Task info(Integer id) {
        return getById(id);
    }

    /**
     * 任务表新增
     *
     * @param task 根据需要进行传值
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer add(Task task) {
        String robotIds = task.getRobotIds();

        try {
            //验证多个机器人编号是否合法
            String[] robotIdArr = robotIds.split(",");
            int[] arr = Arrays.asList(robotIdArr).stream().mapToInt(Integer::parseInt).toArray();
            //判断多个ID是否存在
            robotService.checkIsExistByIds(arr);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new FebsException(e.getMessage());
        }

        //判断ID是否存在
        int jobId = task.getJobId();
        jobService.checkIsExistById(jobId);

        Date date = new Date();
        task.setRegisterDate(date);
        if (this.baseMapper.insert(task) != 1) {
            throw new FebsException("任务" + task.getName() + "添加失败");
        }
        return task.getTaskId();
    }

    /**
     * 任务表修改
     *
     * @param param 根据需要进行传值
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify(Task param) {
        updateById(param);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify(TaskUpdate param) {
        this.checkCanModify(param);

        Date updateDate = new Date();
        Task task = new Task();
        task.setTaskId(param.getTaskId());
        task.setRealityImage(param.getRealityImage());
        task.setStatus(param.getStatus());
        task.setUpdateDate(updateDate);
        this.updateById(task);

        param.setUpdateDate(updateDate);
        if (!taskUpdateService.save(param)) {
            throw new FebsException("编号为：" + param.getTaskId() + "的任务更新失败");
        }
    }

    /**
     * 任务表删除(单个条目)
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
     * 任务表删除(多个条目)
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removes(List<Integer> ids) {
        removeByIds(ids);
    }

    @Override
    public void checkIsExistById(int taskId) {
        LambdaQueryWrapper<Task> lambdaQueryWrapper = new LambdaQueryWrapper<Task>();
        lambdaQueryWrapper.eq(Task::getTaskId, taskId);
        if (this.count(lambdaQueryWrapper) != 1) {
            throw new FebsException("编号为：" + taskId + "的任务不存在");
        }
    }

    @Override
    public void checkCanModify(TaskUpdate taskUpdate) {
        int taskId = taskUpdate.getTaskId();
        Task task = null;
        try {
            task = this.getById(taskId);
            if (task.getStatus() == 4) {
                throw new FebsException("编号为：" + taskId + "的任务当前状态不允许更新");
            }
        } catch (Exception e) {
            throw new FebsException("编号为：" + taskId + "的任务不存在");
        }
    }

    @Override
    public IPage<Task> findTaskDetailList(Task bean, QueryRequest request) {
        if (bean == null) {
            bean = new Task();
        }

        log.info(bean.toString());
        LambdaQueryWrapper<Task> queryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotBlank(bean.getName())) {
            queryWrapper.eq(Task::getName, bean.getName());
        }

        Page<Task> page = new Page<>();
        SortUtil.handlePageSort(request, page, "task_id", FebsConstant.ORDER_DESC, false);

        return this.page(page,queryWrapper);
    }
}
