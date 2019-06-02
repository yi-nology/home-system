package com.murphyyi.homesystem.manager.Impl;

import com.murphyyi.homesystem.dao.HouseInfoDao;
import com.murphyyi.homesystem.manager.HouseManager;
import com.murphyyi.homesystem.model.DO.HouseInfoDO;
import com.murphyyi.homesystem.utils.Snowflake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * @ClassName: HouseManagerImpl
 * @description:
 * @author: zhangyi
 * @since: 2019-04-23 11:23
 */
@Slf4j
@Service
public class HouseManagerImpl implements HouseManager {

    @Resource
    private HouseInfoDao houseInfoDao;

    @Override
    public Long updateOrSave(HouseInfoDO houseInfoDO) {
        Boolean status = false;
        if (houseInfoDO.getHouseId() == null) {
            houseInfoDO.setHouseId(Snowflake.userId());
            status = houseInfoDao.insert(houseInfoDO);
        } else {
            status = houseInfoDao.update(houseInfoDO);
        }
        if (status) {
            log.info("存储成功,{}", toJSONString(houseInfoDO));
        } else {
            log.error("存储失败,{}", toJSONString(houseInfoDO));
        }
        return houseInfoDO.getHouseId();
    }

    @Override
    public List<HouseInfoDO> getByCondition(String condition, String type) {
        List<HouseInfoDO> result = new ArrayList<HouseInfoDO>();
        if ("house".equals(type)) {
            result = houseInfoDao.getOnebyHouseId(Long.parseLong(condition));
        }
        if ("user".equals(type)) {
            result = houseInfoDao.getOneByUserId(Long.parseLong(condition));
        }
        if ("name".equals(type)) {
            result = houseInfoDao.getOneByHouseName(condition);
        }
        log.info("查询数据为{}", toJSONString(result));
        return result;
    }

    @Override
    public Boolean del(HouseInfoDO houseInfoDO) {
        return houseInfoDao.del(houseInfoDO);
    }
}
