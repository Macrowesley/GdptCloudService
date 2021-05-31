package cc.mrbird.febs.cloud.service;

import cc.mrbird.febs.cloud.entity.Job;
import cc.mrbird.febs.cloud.entity.JobUpdate;
import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* 作业对象表 Service接口
*
* @author lai
* @since 2020-09-15
*/
public interface IJobService extends IService<Job> {
    /**
    * 作业对象表分页列表
    * @param request QueryRequest
    * @param request job job
    * @return IPage<Job>
    */
    IPage<Job> page(QueryRequest request, Job job);

    /**
    * 作业对象表详情
    * @param id
    * @return
    */
    Job info(Integer id);

    /**
    * 作业对象表新增
    * @param param 根据需要进行传值
    * @return
    */
    Integer add(Job param);

    /**
    * 作业对象表修改
    * @param param 根据需要进行传值
    * @return
    */
    void modify(Job param);

    void modify(JobUpdate param);

    /**
    * 作业对象表删除(单个条目)
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

    /**
     * 判断ID是否存在
     * @param jobId
     */
    void checkIsExistById(int jobId);
}
