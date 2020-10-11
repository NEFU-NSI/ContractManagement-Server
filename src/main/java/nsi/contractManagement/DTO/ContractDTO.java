package nsi.contractManagement.DTO;

import lombok.Data;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Tao
 * @Time: 2020/10/10 20:14
 * @ProjectName: contract-management
 * @FileName: ContractDTO.java
 * @IDE: IntelliJ IDEA
 */
@Data
public class ContractDTO {
    String year;
    String company;
    String contractName;
    String contractDepartment;
    Integer current;
    Integer size;
}
