package nsi.contractManagement.mapper;

import nsi.contractManagement.DO.DepartmentDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DepartmentDOMapperTest {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Test
    void testSave(){
        departmentMapper.insert(DepartmentDO.builder().departmentName("123").build());

    }

}