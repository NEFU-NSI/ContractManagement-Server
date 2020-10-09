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
@ApiModel(value="nsi-contractmanagement-do-User")
@Data
@Builder
@TableName(value = "`user`")
public class UserDO {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="id")
    private Integer id;

    /**
     * 用户邮箱
     */
    @TableField(value = "email")
    @ApiModelProperty(value="用户邮箱")
    private Integer email;

    /**
     * 用户密码
     */
    @TableField(value = "`password`")
    @ApiModelProperty(value="用户密码")
    private String password;

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
    @TableField(value = "gmt_modified",fill = FieldFill.UPDATE)
    @ApiModelProperty(value="更新时间")
    private LocalDateTime gmtModified;

    public static final String COL_ID = "id";

    public static final String COL_EMAIL = "email";

    public static final String COL_PASSWORD = "password";

    public static final String COL_GMT_CREATE = "gmt_create";

    public static final String COL_GMT_MODIFIED = "gmt_modified";
}