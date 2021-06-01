package cc.mrbird.febs.cloud.controller;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.utils.FebsUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;


@Controller("cloud")
@RequestMapping(FebsConstant.VIEW_PREFIX)
@ApiIgnore
public class ViewController {

    @GetMapping(value = "/", produces = "text/plain;charset=UTF-8")
    public String index() {
        return "欢迎访问云平台";
    }

    @GetMapping("job/job")
    @RequiresPermissions("job:view")
    public String job() {
        return FebsUtil.view("cloud/job/job");
    }



    @GetMapping("robot/robot")
    @RequiresPermissions("robot:view")
    public String robot() {
        return FebsUtil.view("cloud/robot/robot");
    }

    @GetMapping("task/task")
    @RequiresPermissions("task:view")
    public String task() {
        return FebsUtil.view("cloud/task/task");
    }

}
