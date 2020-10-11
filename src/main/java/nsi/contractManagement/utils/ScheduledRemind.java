package nsi.contractManagement.utils;

import nsi.contractManagement.mapper.ContractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: Tao
 * @Time: 2020/10/11 9:27
 * @ProjectName: contract-management
 * @FileName: ScheduledRemind.java
 * @IDE: IntelliJ IDEA
 */
@Component
public class ScheduledRemind {
    @Autowired
    private ContractMapper contractMapper;
    @Scheduled(fixedRate = 3000)
    public void scheduledRemind() {

    }
}
