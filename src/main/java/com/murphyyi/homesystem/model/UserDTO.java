package com.murphyyi.homesystem.model;

import lombok.Data;

/**
 * @ClassName: UserDTO
 * @description:
 * @author: zhangyi
 * @since: 2019-04-18 10:48
 */
@Data
public class UserDTO {
    private Long uid;
    private String passWord;
    private String nickName;
    private String email;
    private Boolean wechat;
    private Integer jurisdiction;
}
