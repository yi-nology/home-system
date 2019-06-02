package com.murphyyi.homesystem.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: HomeSearchVO
 * @description:
 * @author: zhangyi
 * @since: 2019-05-24 00:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeSearchVO {
    Integer currentPage;
    Integer pageSize;
}
