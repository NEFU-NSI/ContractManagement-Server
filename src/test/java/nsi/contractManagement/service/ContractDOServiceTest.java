package nsi.contractManagement.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import nsi.contractManagement.DO.ContractDO;
import nsi.contractManagement.mapper.ContractMapper;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@SpringBootTest
class ContractDOServiceTest {
    @Autowired
    private ContractMapper contractMapper;

    @Autowired
    private ContractService contractService;

    @Test
    public void testSave() {
        contractMapper.insert(ContractDO.builder().contractNumber("2018-1-2").sign(LocalDateTime.of(2018, 1, 2, 0, 0, 0)).department(1).company("东软").build());
        contractMapper.insert(ContractDO.builder().contractNumber("2018-1-3").sign(LocalDateTime.of(2018, 1, 3, 0, 0, 0)).department(1).company("东软").build());
        contractMapper.insert(ContractDO.builder().contractNumber("2018-12-31").sign(LocalDateTime.of(2018, 12, 31, 23, 59, 59)).department(1).company("东软").build());
        contractMapper.insert(ContractDO.builder().contractNumber("2018-12-31").sign(LocalDateTime.of(2018, 12, 31, 0, 0, 0)).department(1).company("东软").build());
        contractMapper.insert(ContractDO.builder().contractNumber("2019-2-3").sign(LocalDateTime.of(2019, 2, 3, 0, 0, 0)).department(1).company("东软").build());
        contractMapper.insert(ContractDO.builder().contractNumber("2020-2-3").sign(LocalDateTime.of(2020, 2, 3, 0, 0, 0)).department(1).company("东软").build());

    }

    @Test
    public void testSearch() {
        ContractDO contractDO =
                ContractDO.builder().contractNumber("123123").amount(1000L).company("NEFU").build();
        QueryWrapper<ContractDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("sign", LocalDateTime.of(2018, 1, 1, 0, 0, 0), LocalDateTime.of(2018,
                12, 31, 23, 59, 59));
        queryWrapper.eq("company", "");
        queryWrapper.eq("name", "");
        queryWrapper.eq("department", "");
        log.warn(contractMapper.selectList(queryWrapper).toString());
    }

}