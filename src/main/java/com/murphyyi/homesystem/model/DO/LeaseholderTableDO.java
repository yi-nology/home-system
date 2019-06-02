package com.murphyyi.homesystem.model.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: LeaseholderTableDO
 * @description:
 * @author: zhangyi
 * @since: 2019-05-27 16:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaseholderTableDO {
    Long uid;
    String name;
    String phone;
    String homeName;
    Long houseId;
    Long homeId;
    String work;
    String back;
    String front;
    Long id;
    Long contractId;
}
