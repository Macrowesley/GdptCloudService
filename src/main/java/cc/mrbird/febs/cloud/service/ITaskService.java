package cc.mrbird.febs.cloud.service;

import cc.mrbird.febs.cloud.entity.Task;
import cc.mrbird.febs.cloud.entity.TaskUpdate;
import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* 任务表 Service接口
*
* @author lai
* @since 2020-09-15
*/
public interface ITaskService extends IService<Task> {
    /**
    * 任务表分页列表
    * @param request QueryRequest
    * @param request task task
    * @return IPage<Task>
    */
    IPage<Task> page(QueryRequest request, Task task);

    /**
    * 任务表详情
    * @param id
    * @return
    */
    Task info(Integer id);

    /**
    * 任务表新增
    * @param param 根据需要进行传值
    * @return
    */
    Integer add(Task param);

    /**
    * 任务表修改
    * @param param 根据需要进行传值
    * @return
    */
    void modify(Task param);
    void modify(TaskUpdate taskUpdate);
    /**
    * 任务表删除(单个条目)
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

    void checkIsExistById(int taskId);

    void checkCanModify(TaskUpdate taskUpdate);
}
