package nsi.contractManagement.service.impl;

import nsi.contractManagement.DO.Contract;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nsi.contractManagement.mapper.ContractMapper;
import nsi.contractManagement.service.ContractService;
/** @author Tao */
@Service
public class ContractServiceImpl extends ServiceImpl<ContractMapper, Contract>
    implements ContractService {}
