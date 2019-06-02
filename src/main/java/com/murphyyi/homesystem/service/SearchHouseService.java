package com.murphyyi.homesystem.service;

import java.util.Map;

/**
 * @ClassName: SearchHouseService
 * @description:
 * @author: zhangyi
 * @since: 2019-05-21 05:39
 */
public interface SearchHouseService {
    Map searchByAddress(String address, Integer page, Integer pageSize);
}
