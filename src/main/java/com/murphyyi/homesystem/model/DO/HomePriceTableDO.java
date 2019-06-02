package com.murphyyi.homesystem.model.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @ClassName: HomePriceTableDO
 * @description:
 * @author: zhangyi
 * @since: 2019-05-28 22:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomePriceTableDO {
    Long priceId;
    Integer rcId;
    String homeName;
    String houseName;
    String name;
    Integer rate;
    Integer water;
    Integer electric;
    LocalDate commitDate;
}
