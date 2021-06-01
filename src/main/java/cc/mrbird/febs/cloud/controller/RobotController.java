package cc.mrbird.febs.cloud.controller;


import cc.mrbird.febs.cloud.dto.RobotDTO;
import cc.mrbird.febs.cloud.dto.RobotUpdateDTO;
import cc.mrbird.febs.cloud.entity.*;
import cc.mrbird.febs.cloud.service.IRobotService;
import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.annotation.Limit;
import cc.mrbird.febs.common.constant.LimitConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.DateUtil;
import com.wuwenze.poi.ExcelKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 机器人控制器
 */
@Api(value = "机器人信息接口", tags= {"机器人信息接口"})
@RestController
@RequestMapping("/robot")
public class RobotController extends BaseController {
    @Autowired
    IRobotService robotService;

    @GetMapping("list")
    @RequiresPermissions("robot:view")
    @ControllerEndpoint(operation = "作业对象列表", exceptionMessage = "{robot.operation.listError}")
    @Limit(period = LimitConstant.Loose.period, count = LimitConstant.Loose.count, prefix = "limit_system_robot")
    public FebsResponse robotList(Robot robot, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(this.robotService.findRobotDetailList(robot, request));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "导出Excel", exceptionMessage = "{robot.operation.exportError}")
    @GetMapping("excel")
    @RequiresPermissions("robot:export")
    @Limit(period = LimitConstant.Strict.period, count = LimitConstant.Strict.count, prefix = "limit_device_device")
    public void export(QueryRequest queryRequest, Robot bean, HttpServletResponse response) {
        List<Robot> beans = this.robotService.findRobotDetailList(bean, queryRequest).getRecords();
        ExcelKit.$Export(Robot.class, response).downXlsx(beans, false);
    }

    @ApiOperation(value = "注册机器人")
    @PostMapping("/register")
    public GdptJSONResult register(@RequestBody @Validated RobotDTO robotDTO) {
        Robot bean = new Robot();
        BeanUtils.copyProperties(robotDTO, bean);
        bean.setProductionDate(DateUtil.getSimpleDateFormat(robotDTO.getProductionDate()));
        return GdptJSONResult.ok(MyId.create(robotService.add(bean)));
    }


    @ApiOperation(value = "更新机器人状态")
    @PostMapping("/update")
    public GdptJSONResult update(@RequestBody @Validated RobotUpdateDTO robotUpdateDTO) {
        RobotUpdate bean = new RobotUpdate();
        BeanUtils.copyProperties(robotUpdateDTO, bean);
        robotService.modify(bean);
        return GdptJSONResult.ok(MyId.create(bean.getRobotId()));
    }
}
