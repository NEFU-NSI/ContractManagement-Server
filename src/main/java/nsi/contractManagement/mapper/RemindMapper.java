package nsi.contractManagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

import nsi.contractManagement.DO.RemindDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Tao
 */
@Mapper
public interface RemindMapper extends BaseMapper<RemindDO> {

    /**
     * 插入提醒
     *
     * @param remindDo 提醒POJO
     * @return 插入状态
     */
    int insertRemind(@Param("remindDo") RemindDO remindDo);
}