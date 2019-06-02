package com.murphyyi.homesystem.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: HouseInfoVO
 * @description:
 * @author: zhangyi
 * @since: 2019-04-23 16:11
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HouseInfoVO {
    Long house_id;
    Long land_id;
    String house_name;
    String house_address;
}
