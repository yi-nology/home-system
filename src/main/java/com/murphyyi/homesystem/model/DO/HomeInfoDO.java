package com.murphyyi.homesystem.model.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: HomeInfoDO
 * @description: 房屋信息表
 * @author: zhangyi
 * @since: 2019-04-21 19:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeInfoDO {
    //房屋ID

    Long homeId;
    //房屋名称

    String homeName;
    //房屋价钱ID

    Long priceId;
    //房屋备注

    String remarks;
    //房屋面积

    Integer homeSize;

    Long houseId;

    Boolean homeEmpty;
}
