package com.murphyyi.homesystem.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UserVO
 * @description:
 * @author: zhangyi
 * @since: 2019-04-26 14:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    private String name;
    private String passwd;
    private String email;
}
