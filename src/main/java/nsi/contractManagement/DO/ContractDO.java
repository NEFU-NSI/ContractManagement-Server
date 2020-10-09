package nsi.contractManagement.DO;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 */
@ApiModel(value="nsi-contractmanagement-do-Contract")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "contract")
public class ContractDO {
    /**
     * 主键
     */
    @JsonIgnore
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="主键")
    private Integer id;

    /**
     * 合同名称
     */
    @NotNull(message = "请输入合同名称")
    @TableField(value = "`name`")
    @ApiModelProperty(value="合同名称")
    private String name;

    /**
     * 科室
     */
    @NotNull(message = "请输入科室")
    @TableField(value = "department")
    @ApiModelProperty(value="科室")
    private Integer department;

    /**
     * 合同总额
     */
    @NotNull(message = "请输入合同总额")
    @TableField(value = "amount")
    @ApiModelProperty(value="合同总额")
    private Long amount;

    /**
     * 合同签订日期
     */
    @NotNull(message = "请输入合同总额")

    @TableField(value = "sign")
    @ApiModelProperty(value="合同签订日期")
    private LocalDateTime sign;

    /**
     * 合同签订公司
     */
    @NotNull(message = "请输入合同签订公司")

    @TableField(value = "company")
    @ApiModelProperty(value="合同签订公司")
    private String company;

    /**
     * 质保金缴纳时间
     */
    @NotNull(message = "请输入质保金缴纳时间")
    @TableField(value = "quality_guarantee_datetime")
    @ApiModelProperty(value="质保金缴纳时间")
    private LocalDateTime qualityGuaranteeDatetime;

    /**
     * 合同编号
     */
    @NotNull(message = "请输入合同编号")
    @TableField(value = "contract_number")
    @ApiModelProperty(value="合同编号")
    private String contractNumber;

    /**
     * 维保时间
     */
    @NotNull(message = "请输入维保时间")

    @TableField(value = "maintenance_datetime")
    @ApiModelProperty(value="维保时间")
    private LocalDateTime maintenanceDatetime;

    /**
     * 附件
     */
    @TableField(value = "accessories")
    @ApiModelProperty(value="附件")
    private String accessories;

    /**
     * 创建时间
     */
    @JsonIgnore
    @TableField(value = "gmt_create",fill = FieldFill.INSERT)
    @ApiModelProperty(value="创建时间")
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    @JsonIgnore
    @TableField(value = "gmt_modified",fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value="更新时间")
    private LocalDateTime gmtModified;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_DEPARTMENT = "department";

    public static final String COL_AMOUNT = "amount";

    public static final String COL_SIGN = "sign";

    public static final String COL_COMPANY = "company";

    public static final String COL_QUALITY_GUARANTEE_DATETIME = "quality_guarantee_datetime";

    public static final String COL_CONTRACT_NUMBER = "contract_number";

    public static final String COL_MAINTENANCE_DATETIME = "maintenance_datetime";

    public static final String COL_ACCESSORIES = "accessories";

    public static final String COL_GMT_CREATE = "gmt_create";

    public static final String COL_GMT_MODIFIED = "gmt_modified";
}