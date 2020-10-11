package nsi.contractManagement.VO;

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
public class StatisticsVO {
    String departmentName;
    Long amount;
}
