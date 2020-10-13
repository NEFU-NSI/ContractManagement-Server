package nsi.contractManagement.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 */
@ApiModel(value = "nsi-contractManagement-DO-Remind")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "remind")
public class RemindDO {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 消息内容
     */
    @TableField(value = "message")
    @ApiModelProperty(value = "消息内容")
    private String message;

    /**
     * 部门
     */
    @TableField(value = "department")
    @ApiModelProperty(value = "部门")
    private Integer department;

    /**
     * 是否已读
     */
    @TableField(value = "read_or_not")
    @ApiModelProperty(value = "是否已读")
    private Boolean readOrNot;

    /**
     * 消息时间
     */
    @TableField(value = "message_time")
    @ApiModelProperty(value = "消息时间")
    private Date messageTime;

    @JsonIgnore
    @TableField(value = "gmt_create")
    private Date gmtCreate;

    @JsonIgnore
    @TableField(value = "gmt_modified")
    private Date gmtModified;
}