package nsi.contractManagement.VO;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: Tao
 * @Time: 2020/10/10 19:07
 * @ProjectName: contract-management
 * @FileName: DepartmentDTO.java
 * @IDE: IntelliJ IDEA
 */
@Data
public class DepartmentVO {
    @NotBlank(message = "部门名称不能为空")
    String departmentName;
}
