package com.murphyyi.homesystem.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: SearchVO
 * @description:
 * @author: zhangyi
 * @since: 2019-05-22 05:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchVO {
    String input;
    Integer page;
    Integer size;
}
