package com.murphyyi.homesystem.manager;

import com.murphyyi.homesystem.model.DO.HomeInfoDO;
import com.murphyyi.homesystem.model.DO.HomePriceDO;

import java.util.List;

/**
 * @ClassName: HomePriceManager
 * @description: 房间价格
 * @author: zhangyi
 * @since: 2019-05-07 02:44
 */
public interface HomePriceManager {
    /**
    * @Description: 更新保存数据
    * @Param1: homePriceDO
    * @return: java.lang.Long
    * @Author: zhangyi
    * @Date: 2019-05-24
    */
    Long updateOrSave( HomePriceDO homePriceDO);

    /**
    * @Description: 按条件查询数据
    * @Param1: condition
* @Param2: type
    * @return: java.util.List<com.murphyyi.homesystem.model.DO.HomePriceDO>
    * @Author: zhangyi
    * @Date: 2019-05-24
    */
    HomePriceDO getByPid(Long priceId);

    /**
    * @Description: 删除
    * @Param1: null
    * @return:
    * @Author: zhangyi
    * @Date: 2019-05-24
    */
    Boolean delPrice(Long priceId);
}
