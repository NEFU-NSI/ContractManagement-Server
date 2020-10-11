package nsi.contractManagement.mapper;

import io.swagger.annotations.Api;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentMapperTest {
    @Autowired
    DepartmentMapper departmentMapper;
    @Test
    void testSelectDepartment() {
        departmentMapper.selectById(1);
    }
}