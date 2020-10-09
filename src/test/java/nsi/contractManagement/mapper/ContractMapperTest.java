package nsi.contractManagement.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContractMapperTest {

    @Autowired
    private ContractMapper ContractMapper;
    @Test
    void statisticsMapper() {
//        System.out.println(ContractMapper.statisticsMapper("2018").toString());
    }
}