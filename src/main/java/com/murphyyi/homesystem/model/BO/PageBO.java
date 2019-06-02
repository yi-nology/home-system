package com.murphyyi.homesystem.model.BO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @ClassName: PageBO
 * @description:
 * @author: zhangyi
 * @since: 2019-05-26 05:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBO {
    Integer currentPage;
    Integer pageSize;
    String name;
}
