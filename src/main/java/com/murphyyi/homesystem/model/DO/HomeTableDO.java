package com.murphyyi.homesystem.model.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: HomeTableVO
 * @description:
 * @author: zhangyi
 * @since: 2019-05-24 00:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeTableDO {
    Long priceId;
    Long homeId;
    Long houseId;
    String rcId;
    Integer delectric;
    Integer dwater;
    String homeName;
    Integer homeSize;
    String remarks;
    Integer price;
    Integer water;
    Integer electric;
    Integer net;
    Integer remarkPrice;
    Boolean homeEmpty;
}
