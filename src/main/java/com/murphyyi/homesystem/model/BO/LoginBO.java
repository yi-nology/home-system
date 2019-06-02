package com.murphyyi.homesystem.model.BO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: LoginBO
 * @description:
 * @author: zhangyi
 * @since: 2019-05-08 12:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginBO {
    String username;
    String password;
}
