package nsi.contractManagement.service.impl;

import nsi.contractManagement.DO.Department;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nsi.contractManagement.mapper.DepartmentMapper;
import nsi.contractManagement.service.DepartmentService;
/**
 * @author Tao
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService{

}
