package cc.mrbird.febs.cloud.service;

import cc.mrbird.febs.cloud.entity.TaskUpdate;
import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* 任务状态更新表 Service接口
*
* @author lai
* @since 2020-09-15
*/
public interface ITaskUpdateService extends IService<TaskUpdate> {
    /**
    * 任务状态更新表分页列表
    * @param request QueryRequest
    * @param request taskUpdate taskUpdate
    * @return IPage<TaskUpdate>
    */
    IPage<TaskUpdate> page(QueryRequest request, TaskUpdate taskUpdate);

    /**
    * 任务状态更新表详情
    * @param id
    * @return
    */
    TaskUpdate info(Integer id);

    /**
    * 任务状态更新表新增
    * @param param 根据需要进行传值
    * @return
    */
    Integer add(TaskUpdate param);

    /**
    * 任务状态更新表修改
    * @param param 根据需要进行传值
    * @return
    */
    void modify(TaskUpdate param);

    /**
    * 任务状态更新表删除(单个条目)
    * @param id
    * @return
    */
    void remove(Integer id);

    /**
    * 删除(多个条目)
    * @param ids
    * @return
    */
    void removes(List<Integer> ids);
}
