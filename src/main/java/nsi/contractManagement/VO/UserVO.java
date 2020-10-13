package nsi.contractManagement.VO;

import lombok.Data;
import nsi.contractManagement.DO.UserDO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @Author: Tao
 * @Time: 2020/10/11 21:27
 * @ProjectName: contract-management
 * @FileName: UserVO.java
 * @IDE: IntelliJ IDEA
 */
@Data
public class UserVO {
    @Email
    @NotBlank(message = "邮箱不能为空")
    String email;
    @NotBlank(message = "用户名不能为空")
    String name;
    @NotBlank(message = "部门不能为空")
    String department;
}
