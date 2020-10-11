package nsi.contractManagement.DO;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Tao
 */
@ApiModel(value = "nsi-contractmanagement-do-User")
@Data
@Builder
@TableName(value = "`user`")
public class UserDO {
    @JsonIgnore
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 用户邮箱
     */
    @Email(message = "邮箱格式错误")
    @TableField(value = "email")
    @ApiModelProperty(value = "用户邮箱")
    private String email;

    /**
     * 用户姓名
     */
    @NotBlank(message = "用户名不能为空")
    @TableField(value = "name")
    @ApiModelProperty(value = "用户姓名")
    private String name;
    /**
     * 用户密码
     */
    @NotBlank(message = "密码不能为空")
    @TableField(value = "`password`")
    @ApiModelProperty(value = "用户密码")
    private String password;

    /**
     * 用户所属部门
     */
    @NotNull(message = "用户部门不能为空")
    @TableField(value = "department")
    @ApiModelProperty(value = "用户部门")
    private Integer department;
    /**
     * 创建时间
     */
    @JsonIgnore
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    @JsonIgnore
    @TableField(value = "gmt_modified", fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime gmtModified;

    public static final String COL_ID = "id";

    public static final String COL_EMAIL = "email";

    public static final String COL_PASSWORD = "password";

    public static final String COL_GMT_CREATE = "gmt_create";

    public static final String COL_GMT_MODIFIED = "gmt_modified";
}