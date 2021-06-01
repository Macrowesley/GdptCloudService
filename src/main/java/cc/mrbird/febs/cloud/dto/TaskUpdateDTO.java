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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 任务状态更新表
 * </p>
 *
 * @author lai
 * @since 2020-09-16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel
public class TaskUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务编号id
     */
    @ApiModelProperty(name = "taskId", value = "任务Id", required = true, dataType = "int")
    @NotNull
    @Min(1)
    private Integer taskId;

    /**
     * 任务状态（1 执行中/ 2 暂停/ 3 异常中止/ 4 完成）
     */
    @ApiModelProperty(name = "status", value = "任务新状态（1 执行中/ 2 暂停/ 3 异常中止/ 4 完成）", required = true, dataType = "int")
    @NotNull
    @Range(min = 1,max = 4)
    private Integer status;

    /**
     * 实际任务地图地址（长度不超过200）
     */
    @ApiModelProperty(name = "realityImage", value = "实际任务地图地址(200字内)", required = true, dataType = "String")
    @NotBlank
    @Length(min = 1,max = 200)
    private String realityImage;

    /**
     * 状态描述(120字内)
     */
    @ApiModelProperty(name = "stateDescription", value = "状态描述(120字内)", required = true, dataType = "String")
    @NotBlank
    @Length(min = 1,max = 120)
    private String stateDescription;



}
