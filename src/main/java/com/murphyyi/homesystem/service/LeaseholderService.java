package com.murphyyi.homesystem.service;

import com.murphyyi.homesystem.model.VO.LeaseholderNewUserVO;
import com.murphyyi.homesystem.model.VO.LeaseholderTableVO;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

/**
 * @ClassName: LeaseholderService
 * @description:
 * @author: zhangyi
 * @since: 2019-05-27 03:07
 */
public interface LeaseholderService {
    Boolean setinfo(LeaseholderNewUserVO leaseholderNewUserVO);

    Boolean setinfo(LeaseholderTableVO leaseholderTableVO);

    List<LeaseholderTableVO> getList(Long userId, String name, Integer cur, Integer pageSize);

    Integer getTotal(Long userId, String name);

    Boolean del(Long id);
}
