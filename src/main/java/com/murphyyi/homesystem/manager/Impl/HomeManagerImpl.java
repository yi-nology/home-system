package com.murphyyi.homesystem.manager.Impl;

import com.murphyyi.homesystem.dao.HomeInfoDao;
import com.murphyyi.homesystem.manager.HomeManager;
import com.murphyyi.homesystem.model.DO.HomeInfoDO;
import com.murphyyi.homesystem.utils.Snowflake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * @ClassName: HomeManagerImpl
 * @description:
 * @author: zhangyi
 * @since: 2019-05-07 00:46
 */
@Service
@Slf4j
public class HomeManagerImpl implements HomeManager {

    @Resource
    HomeInfoDao homeInfoDao;

    @Override
    public Long updateOrSave(HomeInfoDO homeInfoDO) {
        Boolean status = false;
        if (homeInfoDO.getHomeId() == null) {
            homeInfoDO.setHomeId(Snowflake.userId());
            status = homeInfoDao.insert(homeInfoDO);
        } else {
            status = homeInfoDao.update(homeInfoDO);
        }
        if (status) {
            log.info("存储成功,{}", toJSONString(homeInfoDO));
        } else {
            log.error("存储失败,{}", toJSONString(homeInfoDO));
        }
        return homeInfoDO.getHomeId();
    }

    @Override
    public List<HomeInfoDO> getByCondition(String condition, String type, Integer page, Integer pageSize) {
        List<HomeInfoDO> result = new ArrayList<HomeInfoDO>();
        if ("id".equals(type)) {
            result = homeInfoDao.getOneByHomeId(Long.parseLong(condition),page*pageSize,pageSize);
        }
        if ("price".equals(type)) {
            result = homeInfoDao.getByPriceId(Long.parseLong(condition), page*pageSize, pageSize);
        }
        if ("name".equals(type)) {
            result = homeInfoDao.getByHomeName(condition,page*pageSize, pageSize);
        }
        if ("house".equals(type)) {
            result = homeInfoDao.getByHouseId(Long.parseLong(condition), page*pageSize ,pageSize);
        }

        log.info("查询数据为{}", toJSONString(result));
        return result;
    }

    @Override
    public List<Integer> getAllEmptyRoom(Long houseId) {
        Integer all = homeInfoDao.getAllNumberByHouseId(houseId);
        Integer empty = homeInfoDao.getEmptyNumberByHouseId(houseId);
        List<Integer> list = new ArrayList<>(2);
        list.add(all);
        list.add(empty);
        return list;
    }

    @Override
    public Boolean delhome(Long homeId) {
        return homeInfoDao.del(homeId);
    }
}
