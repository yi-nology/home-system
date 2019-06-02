package com.murphyyi.homesystem.model.VO;

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
public class HomeTableVO {
    String price_id;
    String home_id;
    String house_id;
    String rc_id;
    String name;
    Float size;
    String remarks;
    Float price;
    Float water;
    Float electric;
    Float net;
    Float remark_price;
    Integer delectric;
    Integer dwater;
    Boolean home_empty;
}
