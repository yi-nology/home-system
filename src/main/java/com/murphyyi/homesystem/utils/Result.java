package com.murphyyi.homesystem.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Result
 * @description:
 * @author: zhangyi
 * @since: 2019-02-28 19:46
 */
@NoArgsConstructor
@Data
public class Result<T> {
    private T data = null;
    private String massage;
    private Integer status = 500;

    private Result(T data, Integer status, String massage) {
        this.data = data;
        this.status = status;
        this.massage = massage;
    }

    public static <T> Result<T> success(T data, String msg) {
        return new Result<>(data, 200, msg);
    }
    public static <T> Result success(String msg) {
        Map<String, Object> map = new HashMap();
        return new Result(map, 200, msg);
    }

    public static <T> Result<T> fail(String msg) {
        return new Result<T>(null, 500, msg);
    }
    public static <T> Result<T> failErr() {
        return new Result<T>(null, 401, "登陆失败，账号或密码错误");
    }
    public static <T> Result<T> failSSO() {
        return new Result<T>(null, 400, "登陆信息不存在");
    }
}
