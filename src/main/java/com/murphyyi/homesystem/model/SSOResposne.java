package com.murphyyi.homesystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName: SSOResposne
 * @description:
 * @author: zhangyi
 * @since: 2019-03-15 15:39
 */
@Data
@AllArgsConstructor
public class SSOResposne {
    private String token;
    private Integer status;

    public SSOResposne() {
        this.token = "";
        this.status = 400;
    }
}
