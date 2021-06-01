package cc.mrbird.febs.cloud.controller;


import cc.mrbird.febs.cloud.dto.RobotDTO;
import cc.mrbird.febs.cloud.dto.RobotUpdateDTO;
import cc.mrbird.febs.cloud.entity.GdptJSONResult;
import cc.mrbird.febs.cloud.entity.MyId;
import cc.mrbird.febs.cloud.entity.Robot;
import cc.mrbird.febs.cloud.entity.RobotUpdate;
import cc.mrbird.febs.cloud.service.IRobotService;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.utils.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
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
