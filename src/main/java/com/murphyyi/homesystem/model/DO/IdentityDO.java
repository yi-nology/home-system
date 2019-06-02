package com.murphyyi.homesystem.model.DO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: IdentityDO
 * @description:
 * @author: zhangyi
 * @since: 2019-04-20 22:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdentityDO {
    private Long id;
    private String idName;
    private String idNation;
    private String idAddress;
    private String idCode;
    private String idSex;
    private String idIssue;
    private String idPermitStart;
    private String idPermitEnd;
    private String frontUrl;
    private String backUrl;
}
