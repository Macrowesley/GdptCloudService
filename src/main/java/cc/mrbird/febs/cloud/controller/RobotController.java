package cc.mrbird.febs.cloud.controller;


import cc.mrbird.febs.cloud.entity.GdptJSONResult;
import cc.mrbird.febs.cloud.entity.MyId;
import cc.mrbird.febs.cloud.entity.Robot;
import cc.mrbird.febs.cloud.entity.RobotUpdate;
import cc.mrbird.febs.cloud.service.IRobotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 机器人控制器
 */
@Api(value = "机器人信息接口", tags= {"机器人信息接口"})
@RestController
@RequestMapping("/robot")
public class RobotController extends BaseController {
    @Autowired
    IRobotService robotService;

    @ApiOperation(value = "注册机器人")
    @PostMapping("/register")
    public GdptJSONResult register(@RequestBody @Validated Robot robot) {
        return GdptJSONResult.ok(MyId.create(robotService.add(robot)));
    }


    @ApiOperation(value = "更新机器人状态")
    @PostMapping("/update")
    public GdptJSONResult update(@RequestBody @Validated RobotUpdate robotUpdate) {
        robotService.modify(robotUpdate);
        return GdptJSONResult.ok(MyId.create(robotUpdate.getRobotId()));
    }
}
