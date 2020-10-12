package nsi.contractManagement.VO;

import lombok.Data;
import nsi.contractManagement.DO.UserDO;

/**
 * @Author: Tao
 * @Time: 2020/10/11 21:27
 * @ProjectName: contract-management
 * @FileName: UserVO.java
 * @IDE: IntelliJ IDEA
 */
@Data
public class UserVO {
    String email;
    String name;
    String department;
}
