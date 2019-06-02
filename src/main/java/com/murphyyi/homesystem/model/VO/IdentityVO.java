package com.murphyyi.homesystem.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: IdentityVO
 * @description: 身份信息对外展示
 * @author: zhangyi
 * @since: 2019-04-22 15:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdentityVO {
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
