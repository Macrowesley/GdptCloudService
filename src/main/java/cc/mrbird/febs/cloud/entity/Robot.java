package cc.mrbird.febs.cloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 机器人数据表
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
@TableName("c_robot")
@ApiModel
public class Robot implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 机器人编号id
     */
    @ApiModelProperty(hidden = true)
    @TableId(value = "robot_id", type = IdType.AUTO)
    private Integer robotId;

    /**
     * 机器人名称
     */
    @ApiModelProperty(name = "name", value = "机器人名称", required = true, dataType = "String")
    @NotBlank()
    private String name;

    /**
     * 品牌
     */
    @ApiModelProperty(name = "brand", value = "机器人品牌", required = true, dataType = "String")
    @NotBlank(message = "不能为空")
    private String brand;

    /**
     * 型号
     */
    @ApiModelProperty(name = "model", value = "机器人型号", required = true, dataType = "String")
    @NotBlank(message = "不能为空")
    private String model;

    /**
     * 生产日期
     */
    @ApiModelProperty(name = "productionDate", value = "yyyy-MM-dd", required = true, dataType = "String")
    @NotNull(message = "生产日期不能为空")
    @TableField("production_date")
    private Date productionDate;

    /**
     * 状态（1 正常/ 2 故障/ 3 报废）
     */
    @ApiModelProperty(name = "status", value = "机器人状态 （1 正常/ 2 故障/ 3 报废）", required = true, dataType = "int")
    @NotNull
    @Range(min = 1, max = 3)
    private Integer status;

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
