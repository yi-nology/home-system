package com.murphyyi.homesystem.service;

import com.murphyyi.homesystem.model.DO.ContractDO;
import com.murphyyi.homesystem.model.VO.HomeTableVO;

import java.util.List;

/**
 * @ClassName: ContractService
 * @description:
 * @author: zhangyi
 * @since: 2019-05-26 03:56
 */
public interface ContractService {
    List getInfo(Integer page, Integer pageSize, Long userId);
    ContractDO getInfoById(Long uid);
    Integer getTotal(Long uid);
    Long saveOrUpdate(ContractDO contractDO);
}
