package nsi.contractManagement.service.impl;

import nsi.contractManagement.DO.ContractDO;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nsi.contractManagement.mapper.ContractMapper;
import nsi.contractManagement.service.ContractService;
/** @author Tao */
@Service
public class ContractServiceImpl extends ServiceImpl<ContractMapper, ContractDO>
    implements ContractService {}
