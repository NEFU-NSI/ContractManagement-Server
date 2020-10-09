package nsi.contractManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Tao
 * @Time: 2020/10/9 19:28
 * @ProjectName: contract-management
 * @FileName: statisticsDTO.java
 * @IDE: IntelliJ IDEA
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsDTO {
    String departmentName;
    Long amount;
}
