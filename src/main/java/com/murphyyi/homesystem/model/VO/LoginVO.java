package com.murphyyi.homesystem.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: LoginVO
 * @description:
 * @author: zhangyi
 * @since: 2019-05-08 14:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginVO {
    String name;
    String token;
    Long uuid;
}
