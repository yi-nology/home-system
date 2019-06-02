package com.murphyyi.homesystem.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: LeaseholderVO
 * @description:
 * @author: zhangyi
 * @since: 2019-04-23 00:49
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LeaseholderVO {
    Long uid;
    String rentName;
    Long rentPhone;
    String work;
    Long contractId;
    Long homeId;
    Long identityId;
}
