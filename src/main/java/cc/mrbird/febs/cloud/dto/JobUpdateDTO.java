package cc.mrbird.febs.cloud.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 作业对象状态更新表
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
@ApiModel
public class JobUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 作业对象编号id
     */
    @ApiModelProperty(name = "jobId", value = "作业对象Id", required = true, dataType = "Integer")
    @NotNull
    @Min(1)
    private Integer jobId;

    /**
     * 作业对象状态（1 正常/ 2 检修/ 3 预警）
     */
    @ApiModelProperty(name = "status", value = "作业对象状态（1 正常/ 2 检修/ 3 预警）", required = true, dataType = "int")
    @NotNull
    @Range(min = 1, max = 3)
    private Integer status;

    /**
     * 作业对象检修计划(120字内)
     */
    @ApiModelProperty(name = "repairSchedule", value = "作业对象检修计划(120字内)", required = true, dataType = "String")
    @Length(min = 1,max = 120, message="作业对象检修计划(120字内)")
    private String repairSchedule;

    /**
     * 备注(120字内)
     */
    @ApiModelProperty(name = "remark", value = "备注(120字内)", required = false, dataType = "String")
    @Length(min = 0,max = 120, message="备注(120字内)")
    private String remark;


}
