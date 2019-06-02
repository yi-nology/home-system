package com.murphyyi.homesystem.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @ClassName: HomePriceTableVO
 * @description:
 * @author: zhangyi
 * @since: 2019-05-28 20:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomePriceTableVO {
    String price_id;
    String rc_id;
    String user_name;
    String house_name;
    String home_name;
    LocalDate date;
    String sum;
    Integer state;
    Integer water;
    Integer electric;
    Integer rate;
}
