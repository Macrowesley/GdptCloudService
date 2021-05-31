package cc.mrbird.febs.cloud.service;

import cc.mrbird.febs.cloud.entity.RobotUpdate;
import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* 机器人状态更新表 Service接口
*
* @author lai
* @since 2020-09-15
*/
public interface IRobotUpdateService extends IService<RobotUpdate> {
    /**
    * 机器人状态更新表分页列表
    * @param request QueryRequest
    * @param request robotUpdate robotUpdate
    * @return IPage<RobotUpdate>
    */
    IPage<RobotUpdate> page(QueryRequest request, RobotUpdate robotUpdate);

    /**
    * 机器人状态更新表详情
    * @param id
    * @return
    */
    RobotUpdate info(Integer id);

    /**
    * 机器人状态更新表新增
    * @param param 根据需要进行传值
    * @return
    */
    Integer add(RobotUpdate param);

    /**
    * 机器人状态更新表修改
    * @param param 根据需要进行传值
    * @return
    */
    void modify(RobotUpdate param);

    /**
    * 机器人状态更新表删除(单个条目)
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
