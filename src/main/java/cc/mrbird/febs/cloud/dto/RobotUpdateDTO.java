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
 * 机器人状态更新表
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
public class RobotUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 机器人编号id
     */
    @ApiModelProperty(name = "robotId", value = "机器人Id", required = true, dataType = "int")
    @NotNull
    @Min(1)
    private Integer robotId;

    /**
     * 新状态（1 正常/ 2 故障/ 3 报废）
     */
    @ApiModelProperty(name = "status", value = "机器人新状态（1 正常/ 2 故障/ 3 报废）", required = true, dataType = "int")
    @NotNull
    @Range(min = 1, max = 3)
    private Integer status;

    /**
     * 备注(120字内)
     */
    @ApiModelProperty(name = "remark", value = "备注(120字内)", required = false, dataType = "String")
    @Length(min = 0,max = 120, message="备注(120字内)")
    private String remark;


}
