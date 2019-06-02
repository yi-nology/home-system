package com.murphyyi.homesystem.model.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UserInfoDO
 * @description:
 * @author: zhangyi
 * @since: 2019-04-21 22:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDO {
    Long uid;
    String nickName;
    String identityId;
    String phone;
    String address;
    String headerUrl;
}
