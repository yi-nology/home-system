package com.murphyyi.homesystem.model.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName: LeaseholderDO
 * @description:
 * @author: zhangyi
 * @since: 2019-04-21 20:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaseholderDO {
    Long uid;
    String rentName;
    String rentPhone;
    String work;
    Long contractId;
    Long homeId;
    Long identityId;
}
