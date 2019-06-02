package com.murphyyi.homesystem.model.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: HouseInfoDO
 * @description:
 * @author: zhangyi
 * @since: 2019-04-21 22:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseInfoDO {
    Long houseId;
    Long landId;
    String houseName;
    String houseAddress;
}
