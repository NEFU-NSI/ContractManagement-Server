package nsi.contractManagement.service.impl;

import lombok.extern.slf4j.Slf4j;
import nsi.contractManagement.DO.ContractDO;
import nsi.contractManagement.DO.RemindDO;
import nsi.contractManagement.mapper.ContractMapper;
import nsi.contractManagement.mapper.RemindMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Tao
 * @Time: 2020/10/13 13:56
 * @ProjectName: contract-management
 * @FileName: MyRemindService.java
 * @IDE: IntelliJ IDEA
 */
@Slf4j
@Service
public class MyRemindServiceImpl {

    @Autowired
    private ContractMapper contractMapper;

    @Autowired
    private RemindMapper remindMapper;

    @Autowired
    private MyRemindServiceImpl myRemindServiceImpl;


    public List<ContractDO> qualityGuaranteeExpire() {
        return contractMapper.qualityGuaranteeDatetime();
    }

    public List<RemindDO> qualityGuaranteeExpireDoList(List<ContractDO> qualityGuaranteeExpireDo) {
        List<RemindDO> qualityGuaranteeExpireDoList = new ArrayList<>(100);
        StringBuilder stringBuilder = new StringBuilder();
        for (ContractDO contractDO : qualityGuaranteeExpireDo) {
            stringBuilder.append(contractDO.getName());
            stringBuilder.append(" 质保金到期");
            RemindDO remindDO = new RemindDO();
            BeanUtils.copyProperties(contractDO, remindDO);
            remindDO.setMessage(stringBuilder.toString());
            stringBuilder.delete(0, stringBuilder.length());
            remindDO.setReadOrNot(false);
            qualityGuaranteeExpireDoList.add(remindDO);
        }
        return qualityGuaranteeExpireDoList;
    }

    public List<RemindDO> maintenanceExpiredRemindList(){
        List<RemindDO> maintenanceExpiredRemindList = new ArrayList<>();
        List<ContractDO> maintenanceExpired = contractMapper.maintenanceExpired();
        StringBuilder stringBuilder = new StringBuilder();
        for (ContractDO contractDO : maintenanceExpired) {
            stringBuilder.append(contractDO.getName());
            stringBuilder.append(" 维保到期");
            RemindDO remindDO = new RemindDO();
            BeanUtils.copyProperties(contractDO, remindDO);
            remindDO.setMessage(stringBuilder.toString());
            stringBuilder.delete(0, stringBuilder.length());
            remindDO.setReadOrNot(false);
            maintenanceExpiredRemindList.add(remindDO);
        }
        return maintenanceExpiredRemindList;
    }

    public void saveToDb(List<RemindDO> remindDoList) {
        for (RemindDO remindDO : remindDoList) {
            if (remindMapper.insertRemind(remindDO) != 1) {
                log.warn(remindDO.getMessage() + "插入失败");
            }
        }
    }

    public void scanAndSave() {
        List<ContractDO> expire = this.qualityGuaranteeExpire();
        List<RemindDO> remindDOList = this.qualityGuaranteeExpireDoList(expire);
        List<RemindDO> remindDoS = this.maintenanceExpiredRemindList();
        this.saveToDb(remindDoS);
        this.saveToDb(remindDOList);
    }

}
