package com.murphyyi.homesystem.model.BO.idCard;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: IdCardInfoBO
 * @description:
 * @author: zhangyi
 * @since: 2019-04-20 21:33
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class IdCardInfoBO implements Serializable{

    /**
     * 姓名 : {"words":"某某某","location":{"top":160,"left":131,"width":52,"height":17}}
     * 民族 : {"words":"汉","location":{"top":195,"left":214,"width":11,"height":14}}
     * 住址 : {"words":"此处打十个字然后回车","location":{"top":263,"left":129,"width":163,"height":17}}
     * 公民身份号码 : {"words":"","location":{"top":0,"left":0,"width":0,"height":0}}
     * 性别 : {"words":"女","location":{"top":195,"left":129,"width":13,"height":16}}
     */

    @JSONField(name = "姓名")
    private ObjectWord name;
    @JSONField(name = "民族")
    private ObjectWord nation;
    @JSONField(name = "住址")
    private ObjectWord address;
    @JSONField(name = "公民身份号码")
    private ObjectWord idCode;
    @JSONField(name= "性别")
    private ObjectWord sex;
}
