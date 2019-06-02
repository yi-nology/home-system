package com.murphyyi.homesystem.model.BO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: HouseSearchBO
 * @description:
 * @author: zhangyi
 * @since: 2019-04-23 15:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseSearchBO {
    Long homeId=0L;
    String homeName="";
    Long userId=0L;
}
