package com.murphyyi.homesystem.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: SearchHouseVO
 * @description:
 * @author: zhangyi
 * @since: 2019-05-21 05:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchHouseVO {
    String username;
    String address;
    String phone;
    Integer number;
    String name;
}
