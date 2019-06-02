package com.murphyyi.homesystem.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: LeaseholderTableVO
 * @description:
 * @author: zhangyi
 * @since: 2019-05-27 05:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaseholderTableVO {
    String uid;
    String name;
    String phone;
    String home_name;
    String house_id;
    String home_id;
    String work;
    String id_card_back;
    String id_card_front;
    String id;
    String contract_id;
}
