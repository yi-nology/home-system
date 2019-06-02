package com.murphyyi.homesystem.model.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

/**
 * @ClassName: RentConsumeDO
 * @description: 租客消费
 * @author: zhangyi
 * @since: 2019-04-21 22:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentConsumeDO {
    Integer id;
    Long priceId;
    LocalDate date;
    Integer water;
    Integer electric;
    LocalDate commitDate;
}
