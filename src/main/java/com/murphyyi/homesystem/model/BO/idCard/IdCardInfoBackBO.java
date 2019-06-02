package com.murphyyi.homesystem.model.BO.idCard;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: IdCardInfoBackBO
 * @description:
 * @author: zhangyi
 * @since: 2019-04-22 14:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdCardInfoBackBO {
    @JSONField(name = "签发机关")
    private ObjectWord issue;
    @JSONField(name = "失效日期")
    private ObjectWord permitEnd;
    @JSONField(name = "签发日期")
    private ObjectWord permitStart;
}
