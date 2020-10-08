package nsi.contractManagement.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
public class User {
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
    @TableField(value = "gmt_create")
    @ApiModelProperty(value="创建时间")
    private Date gmtCreate;

    /**
     * 更新时间
     */
    @TableField(value = "gmt_modified")
    @ApiModelProperty(value="更新时间")
    private Date gmtModified;

    public static final String COL_ID = "id";

    public static final String COL_EMAIL = "email";

    public static final String COL_PASSWORD = "password";

    public static final String COL_GMT_CREATE = "gmt_create";

    public static final String COL_GMT_MODIFIED = "gmt_modified";
}