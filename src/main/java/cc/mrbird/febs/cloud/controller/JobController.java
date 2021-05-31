package cc.mrbird.febs.cloud.controller;

import cc.mrbird.febs.cloud.entity.GdptJSONResult;
import cc.mrbird.febs.cloud.entity.Job;
import cc.mrbird.febs.cloud.entity.JobUpdate;
import cc.mrbird.febs.cloud.entity.MyId;
import cc.mrbird.febs.cloud.service.IJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

