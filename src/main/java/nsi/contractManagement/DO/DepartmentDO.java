package nsi.contractManagement.DO;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

/**
 * @author Tao
 */
@ApiModel(value="nsi-contractmanagement-do-Department")
@Data
@Builder
@TableName(value = "department")
public class DepartmentDO {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="id")
    private Integer id;

    /**
     * 部门名称
     */
    @TableField(value = "department_name")
    @ApiModelProperty(value="部门名称")
    private String departmentName;

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

    public static final String COL_DEPARTMENT_NAME = "department_name";

    public static final String COL_GMT_CREATE = "gmt_create";

    public static final String COL_GMT_MODIFIED = "gmt_modified";
}