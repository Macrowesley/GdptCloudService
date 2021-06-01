package cc.mrbird.febs.cloud.controller;

import cc.mrbird.febs.cloud.dto.TaskDTO;
import cc.mrbird.febs.cloud.dto.TaskUpdateDTO;
import cc.mrbird.febs.cloud.entity.*;
import cc.mrbird.febs.cloud.service.ITaskService;
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
 * 任务控制器
 * 任务把任务和作业对象关联起来
 */
@Api(value = "任务信息接口", tags = {"任务信息接口"})
@RestController
@RequestMapping("/task")
public class TaskController extends BaseController {
    @Autowired
    ITaskService taskService;

    @GetMapping("list")
    @RequiresPermissions("task:view")
    @ControllerEndpoint(operation = "作业对象列表", exceptionMessage = "{task.operation.listError}")
    @Limit(period = LimitConstant.Loose.period, count = LimitConstant.Loose.count, prefix = "limit_system_task")
    public FebsResponse taskList(Task task, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(this.taskService.findTaskDetailList(task, request));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "导出Excel", exceptionMessage = "{task.operation.exportError}")
    @GetMapping("excel")
    @RequiresPermissions("task:export")
    @Limit(period = LimitConstant.Strict.period, count = LimitConstant.Strict.count, prefix = "limit_device_device")
    public void export(QueryRequest queryRequest, Task bean, HttpServletResponse response) {
        List<Task> beans = this.taskService.findTaskDetailList(bean, queryRequest).getRecords();
        ExcelKit.$Export(Task.class, response).downXlsx(beans, false);
    }

    @ApiOperation(value = "注册任务")
    @PostMapping("/register")
    public GdptJSONResult register(@RequestBody @Validated TaskDTO taskDTO) {
        Task bean = new Task();
        BeanUtils.copyProperties(taskDTO, bean);
        bean.setBeginDate(DateUtil.getSimpleDateFormat(taskDTO.getBeginDate()));
        bean.setExpectedFinishDate(DateUtil.getSimpleDateFormat(taskDTO.getExpectedFinishDate()));
        return GdptJSONResult.ok(MyId.create(taskService.add(bean)));
    }


    @ApiOperation(value = "更新任务状态")
    @PostMapping("/update")
    public GdptJSONResult update(@RequestBody @Validated TaskUpdateDTO taskUpdateDTO) {
        TaskUpdate bean = new TaskUpdate();
        BeanUtils.copyProperties(taskUpdateDTO, bean);
        taskService.modify(bean);
        return GdptJSONResult.ok(MyId.create(bean.getTaskId()));
    }
}