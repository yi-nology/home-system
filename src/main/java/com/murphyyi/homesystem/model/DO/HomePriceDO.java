package com.murphyyi.homesystem.model.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * @ClassName: HomePriceDO
 * @description:
 * @author: zhangyi
 * @since: 2019-04-21 18:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomePriceDO {


    Long priceId;


    //房屋价格

    Integer price;

    //水费单价

    Integer water;

    //电费单价
    Integer electric;

    //网络单价

    Integer net;

    //备注价格

    Integer remarkPrice;

    LocalDate date;
}
