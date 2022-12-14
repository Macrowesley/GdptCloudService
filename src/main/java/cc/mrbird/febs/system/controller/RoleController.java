package cc.mrbird.febs.system.controller;


import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.annotation.Limit;
import cc.mrbird.febs.common.constant.LimitConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.system.entity.Role;
import cc.mrbird.febs.system.service.IRoleService;
import com.wuwenze.poi.ExcelKit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("role")
@ApiIgnore
public class RoleController extends BaseController {

    private final IRoleService roleService;

    @GetMapping
    @Limit(period = LimitConstant.Loose.period, count = LimitConstant.Loose.count, prefix = "limit_system_Role")
    public FebsResponse getAllRoles(Role role) {
        return new FebsResponse().success().data(roleService.findRoles(role));
    }

    @GetMapping("selectRole")
    @Limit(period = LimitConstant.Loose.period, count = LimitConstant.Loose.count, prefix = "limit_system_Role")
    public FebsResponse findSelectsRoleByUser() {
        return new FebsResponse().success().data(roleService.findSelectsRoleByUser());
    }

    @GetMapping("list")
    @RequiresPermissions("role:view")
    @Limit(period = LimitConstant.Loose.period, count = LimitConstant.Loose.count, prefix = "limit_system_Role")
    public FebsResponse roleList(Role role, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(this.roleService.findRoles(role, request));
        return new FebsResponse().success().data(dataTable);
    }

    @PostMapping
    @RequiresPermissions("role:add")
    @ControllerEndpoint(operation = "????????????", exceptionMessage = "{role.addFail}")
    @Limit(period = LimitConstant.Loose.period, count = LimitConstant.Loose.count, prefix = "limit_system_Role")
    public FebsResponse addRole(@Valid Role role) {
        this.roleService.createRole(role);
        return new FebsResponse().success();
    }

    @GetMapping("delete/{roleIds}")
    @RequiresPermissions("role:delete")
    @ControllerEndpoint(operation = "????????????", exceptionMessage = "{role.delFail}")
    @Limit(period = LimitConstant.Loose.period, count = LimitConstant.Loose.count, prefix = "limit_system_Role")
    public FebsResponse deleteRoles(@NotBlank(message = "{required}") @PathVariable String roleIds) {
        this.roleService.deleteRoles(roleIds);
        return new FebsResponse().success();
    }

    @PostMapping("update")
    @RequiresPermissions("role:update")
    @ControllerEndpoint(operation = "????????????", exceptionMessage = "{role.editFail}")
    @Limit(period = LimitConstant.Loose.period, count = LimitConstant.Loose.count, prefix = "limit_system_Role")
    public FebsResponse updateRole(Role role) {
        this.roleService.updateRole(role);
        return new FebsResponse().success();
    }

    @GetMapping("excel")
    @RequiresPermissions("role:export")
    @ControllerEndpoint(exceptionMessage = "{excelFail}")
    @Limit(period = LimitConstant.Loose.period, count = LimitConstant.Loose.count, prefix = "limit_system_Role")
    public void export(QueryRequest queryRequest, Role role, HttpServletResponse response) throws FebsException {
        List<Role> roles = this.roleService.findRoles(role, queryRequest).getRecords();
        ExcelKit.$Export(Role.class, response).downXlsx(roles, false);
    }

}
