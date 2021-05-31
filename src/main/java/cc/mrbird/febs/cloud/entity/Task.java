package cc.mrbird.febs.cloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 任务表
 * </p>
 *
 * @author lai
 * @since 2020-09-16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@TableName("c_task")
@ApiModel
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务编号id
     */
    @ApiModelProperty(hidden = true)
    @TableId(value = "task_id", type = IdType.AUTO)
    private Integer taskId;

    /**
     * 任务名称
     */
    @ApiModelProperty(name = "name", value = "任务名称", required = true, dataType = "String")
    @NotBlank
    private String name;

    /**
     * 任务状态（1 执行中/ 2 暂停/ 3 异常中止/ 4 完成）
     */
    @ApiModelProperty(name = "status", value = "任务状态（1 执行中/ 2 暂停/ 3 异常中止/ 4 完成）", required = true, dataType = "int")
    @Range(min = 1, max = 4)
    private Integer status;

    /**
     * 任务描述(120字内)
     */
    @ApiModelProperty(name = "description", value = "任务描述(120字内)", required = true, dataType = "String")
    @NotBlank
    @Length(min = 1,max = 120)
    private String description;

    /**
     * 预计任务地图地址（长度不超过200）
     */
    @ApiModelProperty(name = "predictImage", value = "预计任务地图地址（长度不超过200）", required = true, dataType = "String")
    @NotBlank
    @Length(min = 1,max = 200)
    private String predictImage;

    /**
     * 实际任务地图地址（长度不超过200）
     */
    private String realityImage;

    /**
     * 作业对象编号id
     */
    @ApiModelProperty(name = "jobId", value = "作业对象编号id", required = true, dataType = "int")
    @NotNull
    @Min(1)
    private Integer jobId;

    /**
     * 关联的多个机器人编号id（多个id用英文逗号,隔开）
     */
    @ApiModelProperty(name = "robotIds", value = "关联的多个机器人编号id（多个id用英文逗号,隔开）", required = true, dataType = "String")
    @Length(min = 1,max = 200)
    @TableField("robot_ids")
    private String robotIds;

    /**
     * 任务开始时间
     */
    @ApiModelProperty(name = "beginDate", value = "任务开始时间 xxxx-xx-xx", required = true, dataType = "String")
    @NotNull
    @TableField("begin_date")
    private String beginDate;

    /**
     * 任务预计完成时间
     */
    @ApiModelProperty(name = "expectedFinishDate", value = "任务预计完成时间 xxxx-xx-xx", required = true, dataType = "String")
    @NotNull
    @TableField("expected_finish_date")
    private Date expectedFinishDate;

    /**
     * 注册时间
     */
    @ApiModelProperty(hidden = true)
    @TableField("register_date")
    private Date registerDate;

    /**
     * 更新时间
     */
    @ApiModelProperty(hidden = true)
    @TableField("update_date")
    private Date updateDate;


}
