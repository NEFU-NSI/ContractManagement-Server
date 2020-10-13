package nsi.contractManagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import nsi.contractManagement.DO.ContractDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nsi.contractManagement.mapper.ContractMapper;
import nsi.contractManagement.service.ContractService;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

/**
 * @author Tao
 */
@Service
public class ContractServiceImpl extends ServiceImpl<ContractMapper, ContractDO> implements ContractService {

}


