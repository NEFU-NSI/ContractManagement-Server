package nsi.contractManagement.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nsi.contractManagement.DO.RemindDO;
import nsi.contractManagement.config.response.ResponseResultBody;
import nsi.contractManagement.mapper.RemindMapper;
import nsi.contractManagement.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: Tao
 * @Time: 2020/10/9 20:48
 * @ProjectName: contract-management
 * @FileName: RemindController.java
 * @IDE: IntelliJ IDEA
 */
@RestController
@Api(tags = "提醒功能")
@RequestMapping("api/remind")
@ResponseResultBody
public class RemindController {

    @Autowired
    UserUtil userUtil;
    @Autowired
    RemindMapper remindMapper;

    @ApiOperation(value = "获取用户的通知信息")
    @GetMapping("list")
    public List<RemindDO> listReminds(HttpServletRequest request) {
        int userDepartment = userUtil.getUserDepartment(request);
        return remindMapper.selectList(new QueryWrapper<RemindDO>().eq("department",
                userDepartment).eq("read_or_not", false));
    }

    @ApiOperation(value = "标记通知为已读")
    @PostMapping("read")
    public boolean read(HttpServletRequest request, @RequestParam("id") Integer id) {
        return remindMapper.updateById(RemindDO.builder().id(id).readOrNot(true
        ).build()) == 1;
    }
}
