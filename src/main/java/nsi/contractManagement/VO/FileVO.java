package nsi.contractManagement.VO;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: Tao
 * @Time: 2020/10/13 9:19
 * @ProjectName: contract-management
 * @FileName: FileVO.java
 * @IDE: IntelliJ IDEA
 */
@Data
@Builder
public class FileVO {
    String fileUrl;
    String fileName;
}
