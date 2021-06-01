package cc.mrbird.febs.cloud.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

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
@ApiModel
public class RobotDTO implements Serializable {

    private static final long serialVersionUID = 1L;


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
    private String productionDate;

    /**
     * 状态（1 正常/ 2 故障/ 3 报废）
     */
    @ApiModelProperty(name = "status", value = "机器人状态 （1 正常/ 2 故障/ 3 报废）", required = true, dataType = "int")
    @NotNull
    @Range(min = 1, max = 3)
    private Integer status;




}
