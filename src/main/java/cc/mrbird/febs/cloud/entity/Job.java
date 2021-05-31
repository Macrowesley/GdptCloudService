package cc.mrbird.febs.cloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 作业对象表
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
@TableName("c_job")
@ApiModel
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 作业对象编号id
     */
    @ApiModelProperty(hidden = true)
    @TableId(value = "job_id", type = IdType.AUTO)
    private Integer jobId;

    /**
     * 作业对象名称
     */
    @ApiModelProperty(name = "name", value = "作业对象名称", required = true, dataType = "String")
    @NotBlank(message = "作业对象名称不能为空")
    private String name;

    /**
     * 作业对象描述(120字内)
     */
    @ApiModelProperty(name = "description", value = "作业对象描述(120字内)", required = true, dataType = "String")
    @Length(min = 1,max = 120, message="作业对象描述(120字内)")
    private String description;

    /**
     * 作业对象状态（1 正常/ 2 检修/ 3 预警）
     */
    @ApiModelProperty(name = "status", value = "作业对象状态（1 正常/ 2 检修/ 3 预警）", required = true)
    @NotNull
    @Range(min = 1, max = 3)
    private Integer status;

    /**
     * 作业对象检修计划
     */
    @ApiModelProperty(hidden = true)
    @TableField("repair_schedule")
    private String repairSchedule;

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
