package com.murphyyi.homesystem.manager;

import com.murphyyi.homesystem.model.BO.LeaseSearchBO;
import com.murphyyi.homesystem.model.DO.LeaseholderDO;

import java.util.List;

/**
 * @ClassName: LeaseUserManager
 * @description: 租户信息
 * @author: zhangyi
 * @since: 2019-04-23 00:45
 */
public interface LeaseUserManager {
    /**
    * @Description: 更新保存接口
    * @Param1: leaseholderDO
    * @return: com.murphyyi.homesystem.model.DO.LeaseholderDO
    * @Author: zhangyi
    * @Date: 2019-04-23
    */
    Long updateOrSave(LeaseholderDO leaseholderDO);

    /**
    * @Description:  按条件查询接口
    * @Param1: leaseSearchBO
    * @return: java.util.List<com.murphyyi.homesystem.model.DO.LeaseholderDO>
    * @Author: zhangyi
    * @Date: 2019-04-23
    */
    List<LeaseholderDO> getByCondition(String condition, String type);
    LeaseholderDO getById(Long id);
    Boolean del(Long id);
}
