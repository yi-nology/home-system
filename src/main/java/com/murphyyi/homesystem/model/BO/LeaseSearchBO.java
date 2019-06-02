package com.murphyyi.homesystem.model.BO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: LeaseSearchBO
 * @description:
 * @author: zhangyi
 * @since: 2019-04-23 00:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaseSearchBO {
    Long uid=0L;
    String name="";
    Long phone=0L;
}
