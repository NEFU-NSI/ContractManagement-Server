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
@ToString
@Getter
@Setter
public class PageDTO<T> {
    Long current;
    Long size;
    Long total;
    List<T> pageContent;

    public PageDTO(List<T> pageContent, Page<T> page, Long total) {
        this.current = page.getCurrent();
        this.size = page.getSize();
        this.total = total;
        this.pageContent = pageContent;
    }
}
