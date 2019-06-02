package com.murphyyi.homesystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: User
 * @description:
 * @author: zhangyi
 * @since: 2019-03-15 15:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long uid;
    private String userName;
    private String passWord;
    private String nickName;
    private String email;
    private Boolean wechat;
    private Integer jurisdiction;
}
