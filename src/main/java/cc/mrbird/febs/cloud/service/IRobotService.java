package cc.mrbird.febs.cloud.service;

import cc.mrbird.febs.cloud.entity.Robot;
import cc.mrbird.febs.cloud.entity.RobotUpdate;
import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* 机器人数据表 Service接口
*
* @author lai
* @since 2020-09-15
*/
public interface IRobotService extends IService<Robot> {
    /**
    * 机器人数据表分页列表
    * @param request QueryRequest
    * @param request robot robot
    * @return IPage<Robot>
    */
    IPage<Robot> page(QueryRequest request, Robot robot);

    /**
    * 机器人数据表详情
    * @param id
    * @return
    */
    Robot info(Integer id);

    /**
    * 机器人数据表新增
    * @param param 根据需要进行传值
    * @return
    */
    Integer add(Robot param);

    /**
    * 机器人数据表修改
    * @param param 根据需要进行传值
    * @return
    */
    void modify(Robot param);
    public void modify(RobotUpdate param);

    /**
    * 机器人数据表删除(单个条目)
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
     * 判断多个ID是否存在
     * @param arr
     */
    void checkIsExistByIds(int[] arr);

    public void checkIsExistById(int robotId);

    IPage<Robot> findRobotDetailList(Robot robot, QueryRequest request);
}
