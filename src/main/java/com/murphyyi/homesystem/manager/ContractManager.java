package com.murphyyi.homesystem.manager;

import com.murphyyi.homesystem.model.DO.ContractDO;


import java.util.List;

/**
 * @ClassName: ContractManager
 * @description: 合同
 * @author: zhangyi
 * @since: 2019-05-07 00:45
 */
public interface ContractManager {
    /**
    * @Description:
    * @Param1: contractDO
    * @return: java.lang.Long
    * @Author: zhangyi
    * @Date: 2019-05-07
    */
    Long updateOrSave(ContractDO contractDO);
    /**
    * @Description:
    * @Param1: condition1 开始时间
    * @Param2: condition2 结束时间
    * @Param3: type id/date
    * @return: java.util.List<com.murphyyi.homesystem.model.DO.ContractDO>
    * @Author: zhangyi
    * @Date: 2019-05-07
    */
    ContractDO getById(Long id);
}
