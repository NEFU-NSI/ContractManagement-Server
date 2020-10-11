package nsi.contractManagement.VO;

import lombok.*;

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
public class PageVO<T> {
    Integer current;
    Integer size;
    Long total;
    List<T> pageContent;
}
