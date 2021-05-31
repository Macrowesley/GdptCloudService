package cc.mrbird.febs.cloud.service.impl;

import cc.mrbird.febs.cloud.entity.JobUpdate;
import cc.mrbird.febs.cloud.mapper.JobUpdateMapper;
import cc.mrbird.febs.cloud.service.IJobUpdateService;
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
* 作业对象状态更新表 Service实现
*
* @author lai
* @since 2020-09-15
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class JobUpdateServiceImpl extends ServiceImpl<JobUpdateMapper, JobUpdate> implements IJobUpdateService {

    /**
    * 作业对象状态更新表分页列表
    * @param request QueryRequest
    * @param request jobUpdate jobUpdate
    * @return IPage<JobUpdate>
    */
    @Override
    public IPage<JobUpdate> page(QueryRequest request, JobUpdate param) {
        LambdaQueryWrapper<JobUpdate> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件

        Page<JobUpdate> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    /**
    * 作业对象状态更新表详情
    * @param id
    * @return
    */
    @Override
    public JobUpdate info(Integer id) {
        return getById(id);
    }

    /**
    * 作业对象状态更新表新增
    * @param param 根据需要进行传值
    * @return
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer add(JobUpdate param) {
        return baseMapper.insert(param);
    }

    /**
    * 作业对象状态更新表修改
    * @param param 根据需要进行传值
    * @return
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify(JobUpdate param) {
        updateById(param);
    }

    /**
    * 作业对象状态更新表删除(单个条目)
    * @param id
    * @return
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Integer id) {
        removeById(id);
    }

    /**
    * 作业对象状态更新表删除(多个条目)
    * @param ids
    * @return
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removes(List<Integer> ids) {
        removeByIds(ids);
    }
}
