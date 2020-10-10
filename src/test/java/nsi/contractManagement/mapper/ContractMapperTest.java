package nsi.contractManagement.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import nsi.contractManagement.DO.ContractDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContractMapperTest {

    @Autowired
    ContractMapper contractMapper;

    @Test
    void statisticsMapper() {
        Page<ContractDO> contractDOPage = new Page<>(1, 3);
        QueryWrapper<ContractDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("company", "东软");
        System.out.println(contractDOPage.getTotal()+"                   " +
                "**************************     ");
        System.out.println(contractMapper.selectPage(contractDOPage, queryWrapper).getRecords().toString());

    }
}