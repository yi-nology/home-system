package com.murphyyi.homesystem.manager;

import com.murphyyi.homesystem.model.DO.HouseInfoDO;

import java.util.List;

/**
 * @ClassName: HouseManager
 * @description:
 * @author: zhangyi
 * @since: 2019-04-23 11:22
 */
public interface HouseManager {
    /**
     * @Description: 更新保存
     * @Param1:
     * @return: java.lang.Long
     * @Author: zhangyi
     * @Date: 2019-04-23
     */
    Long updateOrSave(HouseInfoDO houseInfoDO);

    /**
     * @Description: 按条件查询
     * @Param1: String condition, String type
     * @return: java.util.List<com.murphyyi.homesystem.model.DO.HouseInfoDO>
     * @Author: zhangyi
     * @Date: 2019-04-23
     */
    List<HouseInfoDO> getByCondition(String condition, String type);

    /** 
    * @Description: 按条件删除 
    * @Param1: houseInfoDO 
    * @return: java.lang.Boolean
    * @Author: zhangyi
    * @Date: 2019-05-10 
    */
    Boolean del(HouseInfoDO houseInfoDO);
}
