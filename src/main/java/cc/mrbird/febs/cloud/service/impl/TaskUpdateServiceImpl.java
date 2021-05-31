package cc.mrbird.febs.cloud.service.impl;

import cc.mrbird.febs.cloud.entity.TaskUpdate;
import cc.mrbird.febs.cloud.mapper.TaskUpdateMapper;
import cc.mrbird.febs.cloud.service.ITaskUpdateService;
import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 任务状态更新表 Service实现
*
* @author lai
* @since 2020-09-15
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TaskUpdateServiceImpl extends ServiceImpl<TaskUpdateMapper, TaskUpdate> implements ITaskUpdateService {

    /**
    * 任务状态更新表分页列表
    * @param request QueryRequest
    * @param request taskUpdate taskUpdate
    * @return IPage<TaskUpdate>
    */
    @Override
    public IPage<TaskUpdate> page(QueryRequest request, TaskUpdate param) {
        LambdaQueryWrapper<TaskUpdate> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件

        Page<TaskUpdate> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    /**
    * 任务状态更新表详情
    * @param id
    * @return
    */
    @Override
    public TaskUpdate info(Integer id) {
        return getById(id);
    }

    /**
    * 任务状态更新表新增
    * @param param 根据需要进行传值
    * @return
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer add(TaskUpdate param) {
        return baseMapper.insert(param);
    }

    /**
    * 任务状态更新表修改
    * @param param 根据需要进行传值
    * @return
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify(TaskUpdate param) {
        updateById(param);
    }

    /**
    * 任务状态更新表删除(单个条目)
    * @param id
    * @return
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Integer id) {
        removeById(id);
    }

    /**
    * 任务状态更新表删除(多个条目)
    * @param ids
    * @return
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removes(List<Integer> ids) {
        removeByIds(ids);
    }
}
