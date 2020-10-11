package nsi.contractManagement.DTO;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.*;
import nsi.contractManagement.DO.ContractDO;
import org.springframework.stereotype.Service;

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
public class PageDTO<T> {
    Integer current;
    Integer size;
    Long total;
    List<T> pageContent;
}
