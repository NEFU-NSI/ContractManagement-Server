package nsi.contractManagement.VO;

import lombok.Data;

/**
 * @Author: Tao
 * @Time: 2020/10/10 20:14
 * @ProjectName: contract-management
 * @FileName: ContractDTO.java
 * @IDE: IntelliJ IDEA
 */
@Data
public class ContractVO {
    String year;
    String company;
    String contractName;
    String contractDepartment;
    Integer current;
    Integer size;
}
