package com.murphyyi.homesystem.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UserInfoVO
 * @description:
 * @author: zhangyi
 * @since: 2019-04-21 22:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVO {
    String name;
    String identity_id;
    String phone;
    String address;
    String header_url;
}
