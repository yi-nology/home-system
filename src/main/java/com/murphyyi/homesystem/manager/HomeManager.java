package com.murphyyi.homesystem.manager;

import com.murphyyi.homesystem.model.DO.HomeInfoDO;
import com.murphyyi.homesystem.model.DO.HouseInfoDO;

import java.util.List;

/**
 * @ClassName: HomeManager
 * @description: 房间信息
 * @author: zhangyi
 * @since: 2019-05-06 23:58
 */
public interface HomeManager {
    Long updateOrSave(HomeInfoDO homeInfoDO);

    List<HomeInfoDO> getByCondition(String condition, String type, Integer page, Integer pageSize);

    List<Integer> getAllEmptyRoom(Long houseId);

    Boolean delhome(Long homeId);
}
