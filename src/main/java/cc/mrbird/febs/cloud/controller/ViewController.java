package cc.mrbird.febs.cloud.controller;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.utils.FebsUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@Controller("cloudView")
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

    @GetMapping("job/detail/{jobId}")
    public String jobDetail(@PathVariable String jobId) {
        log.info("得到的jobId={}",jobId);
        return FebsUtil.view("cloud/job/detail");
    }



    @GetMapping("robot/robot")
    @RequiresPermissions("robot:view")
    public String robot() {
        return FebsUtil.view("cloud/robot/robot");
    }

    @GetMapping("robot/detail/{robotId}")
    public String robotDetail(@PathVariable String robotId) {
        log.info("得到的robotId={}",robotId);
        return FebsUtil.view("cloud/robot/detail");
    }

    @GetMapping("task/task")
    @RequiresPermissions("task:view")
    public String task() {
        return FebsUtil.view("cloud/task/task");
    }

    @GetMapping("task/detail/{taskId}")
    public String taskDetail(@PathVariable String taskId) {
        log.info("得到的 taskId={}",taskId);
        return FebsUtil.view("cloud/task/detail");
    }
}
