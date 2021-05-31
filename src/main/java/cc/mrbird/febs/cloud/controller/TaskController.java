package cc.mrbird.febs.cloud.controller;

import cc.mrbird.febs.cloud.entity.GdptJSONResult;
import cc.mrbird.febs.cloud.entity.MyId;
import cc.mrbird.febs.cloud.entity.Task;
import cc.mrbird.febs.cloud.entity.TaskUpdate;
import cc.mrbird.febs.cloud.service.ITaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 任务控制器
 * 任务把任务和作业对象关联起来
 */
@Api(value = "任务信息接口", tags = {"任务信息接口"})
@RestController
@RequestMapping("/task")
public class TaskController extends BaseController {
    @Autowired
    ITaskService taskService;

    @ApiOperation(value = "注册任务")
    @PostMapping("/register")
    public GdptJSONResult register(@RequestBody @Validated Task task) {
        return GdptJSONResult.ok(MyId.create(taskService.add(task)));
    }


    @ApiOperation(value = "更新任务状态")
    @PostMapping("/update")
    public GdptJSONResult update(@RequestBody @Validated TaskUpdate taskUpdate) {
        taskService.modify(taskUpdate);
        return GdptJSONResult.ok(MyId.create(taskUpdate.getTaskId()));
    }
}