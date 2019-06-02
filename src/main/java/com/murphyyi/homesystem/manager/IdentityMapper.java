package com.murphyyi.homesystem.manager;

import com.murphyyi.homesystem.model.DO.HouseInfoDO;
import com.murphyyi.homesystem.model.DO.IdentityDO;

/**
 * @ClassName: IdentityMapper
 * @description:
 * @author: zhangyi
 * @since: 2019-05-27 03:21
 */
public interface IdentityMapper {
    Long updateOrSave(IdentityDO identityDO);
    IdentityDO getById(Long id);
}
