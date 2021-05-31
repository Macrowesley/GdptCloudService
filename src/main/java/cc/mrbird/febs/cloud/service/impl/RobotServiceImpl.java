package cc.mrbird.febs.cloud.service.impl;

import cc.mrbird.febs.cloud.entity.Robot;
import cc.mrbird.febs.cloud.entity.RobotUpdate;
import cc.mrbird.febs.cloud.mapper.RobotMapper;
import cc.mrbird.febs.cloud.service.IRobotService;
import cc.mrbird.febs.cloud.service.IRobotUpdateService;
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
* 机器人数据表 Service实现
*
* @author lai
* @since 2020-09-15
*/
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class RobotServiceImpl extends ServiceImpl<RobotMapper, Robot> implements IRobotService {
    @Autowired
    IRobotUpdateService robotUpdateService;

    /**
    * 机器人数据表分页列表
    * @param request QueryRequest
    * @param request robot robot
    * @return IPage<Robot>
    */
    @Override
    public IPage<Robot> page(QueryRequest request, Robot param) {
        LambdaQueryWrapper<Robot> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件

        Page<Robot> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    /**
    * 机器人数据表详情
    * @param id
    * @return
    */
    @Override
    public Robot info(Integer id) {
        return getById(id);
    }

    /**
    * 机器人数据表新增
    * @param param 根据需要进行传值
    * @return
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer add(Robot param) {
        Date date = new Date();
        param.setRegisterDate(date);
        log.info(param.toString());
        this.baseMapper.insert(param);
        if (this.baseMapper.insert(param) != 1){
            throw new FebsException("机器人" + param.getName() + "注册失败1");
        }
        int robotId = param.getRobotId();

        RobotUpdate robotUpdate = new RobotUpdate();
        robotUpdate.setRobotId(robotId);
        robotUpdate.setStatus(param.getStatus());
        robotUpdate.setUpdateDate(date);
        if(!robotUpdateService.save(robotUpdate)){
            throw new FebsException("作业对象" + param.getName() + "注册失败2");
        }

        return robotId;
    }

    /**
    * 机器人数据表修改
    * @param param 根据需要进行传值
    * @return
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify(Robot param) {
        updateById(param);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify(RobotUpdate param) {
        this.checkIsExistById(param.getRobotId());

        Date updateDate = new Date();
        Robot robot = new Robot();
        robot.setRobotId(param.getRobotId());
        robot.setStatus(param.getStatus());
        robot.setUpdateDate(updateDate);
        this.updateById(robot);

        param.setUpdateDate(updateDate);
        if (!robotUpdateService.save(param)){
            throw new FebsException("编号为：" + param.getRobotId() + "的机器人更新失败");
        }

    }

    /**
    * 机器人数据表删除(单个条目)
    * @param id
    * @return
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Integer id) {
        removeById(id);
    }

    /**
    * 机器人数据表删除(多个条目)
    * @param ids
    * @return
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removes(List<Integer> ids) {
        removeByIds(ids);
    }

    @Override
    public void checkIsExistByIds(int[] arr) {
        LambdaQueryWrapper<Robot> lambdaQueryWrapper;
        for (int i = 0; i <arr.length; i++) {
            lambdaQueryWrapper = new LambdaQueryWrapper<Robot>();
            lambdaQueryWrapper.eq(Robot::getRobotId, arr[i]);
            if(this.count(lambdaQueryWrapper) != 1){
                throw new FebsException("编号为：" + arr[i] + "的机器人不存在");
            }
        }
    }

    @Override
    public void checkIsExistById(int robotId) {
        LambdaQueryWrapper<Robot> lambdaQueryWrapper = new LambdaQueryWrapper<Robot>();
        lambdaQueryWrapper.eq(Robot::getRobotId, robotId);
        if (this.count(lambdaQueryWrapper) != 1) {
            throw new FebsException("编号为：" + robotId + "的机器人不存在");
        }
    }

}
