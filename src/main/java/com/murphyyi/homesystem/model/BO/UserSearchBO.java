package com.murphyyi.homesystem.model.BO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UserSearchBO
 * @description:
 * @author: zhangyi
 * @since: 2019-05-05 02:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchBO {
    Long uid=0L;
    String nickName="";
    String phone="";
}
