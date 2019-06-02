package com.murphyyi.homesystem.constants;

/**
 * @ClassName: IdCardEnum
 * @description: 身份证枚举类型
 * @author: zhangyi
 * @since: 2019-04-20 15:45
 */
public enum IdCardEnum {
    //身份证正面，反面
    front("front"),back("back");
    private String value;

    IdCardEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
