package com.murphyyi.homesystem.utils;

/**
 * @ClassName: SSOException
 * @description:sso登陆错误
 * @author: zhangyi
 * @since: 2019-03-15 17:06
 */
public class SSOException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SSOException(String message) {
        super(message);
    }

}