package com.murphyyi.homesystem.service;

import com.murphyyi.homesystem.model.VO.HomeTableVO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: HomeService
 * @description:
 * @author: zhangyi
 * @since: 2019-05-24 00:28
 */
public interface HomeService {
    List getInfo(Integer page, Integer pageSize, Long userId);
    Integer getTotal(Long userId);
    Boolean delInfo(Long homeId, Long priceId);
    Boolean saveOrUpdate(HomeTableVO homeTableVO);
    List getHomeInfo(Long houseId);
}
