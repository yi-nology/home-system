package com.murphyyi.homesystem.manager.Impl;

import com.murphyyi.homesystem.dao.HomePriceDao;
import com.murphyyi.homesystem.manager.HomePriceManager;
import com.murphyyi.homesystem.model.DO.HomePriceDO;
import com.murphyyi.homesystem.utils.Snowflake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * @ClassName: HomePriceManagerImpl
 * @description:
 * @author: zhangyi
 * @since: 2019-05-07 02:50
 */
@Slf4j
@Service
public class HomePriceManagerImpl implements HomePriceManager {
    @Resource
    HomePriceDao homePriceDao;

    @Override
    public Long updateOrSave(HomePriceDO homePriceDO) {
        Boolean status = false;
        if (homePriceDO.getPriceId() == null) {
            homePriceDO.setPriceId(Snowflake.userId());
            status = homePriceDao.insert(homePriceDO);
        } else {
            status = homePriceDao.update(homePriceDO);
        }
        if (status) {
            log.info("存储成功,{}", toJSONString(homePriceDO));
        } else {
            log.error("存储失败,{}", toJSONString(homePriceDO));
        }
        return homePriceDO.getPriceId();
    }

    @Override
    public HomePriceDO getByPid(Long Pid) {
        HomePriceDO result = homePriceDao.getOneByPriceId(Pid);
        log.info("查询数据为{}", toJSONString(result));
        return result;
    }

    @Override
    public Boolean delPrice(Long priceId) {
        return homePriceDao.del(priceId);
    }
}
