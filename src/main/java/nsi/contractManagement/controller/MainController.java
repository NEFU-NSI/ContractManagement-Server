package nsi.contractManagement.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nsi.contractManagement.DO.Contract;
import nsi.contractManagement.config.ResponseResultBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Tao @Time: 2020/10/8 17:46 @ProjectName: contract-management @FileName:
 * MainController.java @IDE: IntelliJ IDEA
 */
@Api
@RestController
@ResponseResultBody
public class MainController {
  @ApiOperation("首页")
  @GetMapping("index")
  public Object index() {
    Contract contract = new Contract();
    contract.setAmount(1000L);
    return contract;
  }
}
