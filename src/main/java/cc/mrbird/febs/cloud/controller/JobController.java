package cc.mrbird.febs.cloud.controller;

import cc.mrbird.febs.cloud.entity.GdptJSONResult;
import cc.mrbird.febs.cloud.entity.Job;
import cc.mrbird.febs.cloud.entity.JobUpdate;
import cc.mrbird.febs.cloud.entity.MyId;
import cc.mrbird.febs.cloud.service.IJobService;
import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.annotation.Limit;
import cc.mrbird.febs.common.constant.LimitConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import com.wuwenze.poi.ExcelKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 作业对象控制器
 */
@Slf4j
@Api(value = "作业对象信息接口", tags = {"作业对象信息接口"})
@RestController
@RequestMapping("/job")
public class JobController extends BaseController {
    @Autowired
    IJobService jobService;

    @GetMapping("list")
    @RequiresPermissions("job:view")
    @ControllerEndpoint(operation = "作业对象列表", exceptionMessage = "{job.operation.listError}")
    @Limit(period = LimitConstant.Loose.period, count = LimitConstant.Loose.count, prefix = "limit_system_job")
    public FebsResponse jobList(Job job, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(this.jobService.findJobDetailList(job, request));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "导出Excel", exceptionMessage = "{job.operation.exportError}")
    @GetMapping("excel")
    @RequiresPermissions("job:export")
    @Limit(period = LimitConstant.Strict.period, count = LimitConstant.Strict.count, prefix = "limit_device_device")
    public void export(QueryRequest queryRequest, Job bean, HttpServletResponse response) {
        List<Job> beans = this.jobService.findJobDetailList(bean, queryRequest).getRecords();
        ExcelKit.$Export(Job.class, response).downXlsx(beans, false);
    }

    @ApiOperation(value = "注册作业对象")
    @PostMapping("/register")
    public GdptJSONResult register(@RequestBody @Validated Job job) {
        return GdptJSONResult.ok(MyId.create(jobService.add(job)));
    }


    @ApiOperation(value = "更新作业对象状态")
    @PostMapping("/update")
    @PutMapping
    public GdptJSONResult update(@RequestBody @Validated JobUpdate jobUpdate) {
        jobService.modify(jobUpdate);
        return GdptJSONResult.ok(MyId.create(jobUpdate.getJobId()));
    }

}

