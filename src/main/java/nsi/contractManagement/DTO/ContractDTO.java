package nsi.contractManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Tao
 * @Time: 2020/10/9 15:24
 * @ProjectName: contract-management
 * @FileName: ContractDTO.java
 * @IDE: IntelliJ IDEA
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractDTO<T> {
    Integer page;
    Integer number;
    Integer totalPage;
    List<T> pageContent;

}
