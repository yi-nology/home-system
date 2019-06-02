package com.murphyyi.homesystem.manager.Impl;

import com.murphyyi.homesystem.dao.RentConsumeDao;
import com.murphyyi.homesystem.manager.RentConsumeManager;
import com.murphyyi.homesystem.model.DO.RentConsumeDO;
import com.murphyyi.homesystem.utils.Snowflake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * @ClassName: RentConsumeManagerImpl
 * @description:
 * @author: zhangyi
 * @since: 2019-05-07 02:48
 */
@Slf4j
@Service
public class RentConsumeManagerImpl implements RentConsumeManager {
    @Resource
    RentConsumeDao rentConsumeDao;

    private static final DateTimeFormatter
            formatter = DateTimeFormatter.ofPattern("yyyy-MM");
    Calendar calendar = Calendar.getInstance();

    @Override
    public RentConsumeDO updateOrSave(RentConsumeDO rentConsumeDO) {
        Boolean status = false;
        if (rentConsumeDO.getId() == null) {
            status = rentConsumeDao.insert(rentConsumeDO);
        } else {
            status = rentConsumeDao.update(rentConsumeDO);
        }
        if (status) {
            log.info("存储成功,{}", toJSONString(rentConsumeDO));
        } else {
            log.error("存储失败,{}", toJSONString(rentConsumeDO));
        }
        return rentConsumeDO;
    }

    @Override
    public RentConsumeDO getById(Integer id) {
        RentConsumeDO rentConsumeDO = rentConsumeDao.getOneById(id);
        log.info("查询信息为{}", toJSONString(rentConsumeDO));
        return rentConsumeDO;
    }

    @Override
    public Boolean del(Integer id) {
        return rentConsumeDao.del(id);
    }

    @Override
    public RentConsumeDO getByPriceIdNULL(Long prcieId) {
        return rentConsumeDao.getByByPrice(prcieId);

    }

    @Override
    public RentConsumeDO getByPriceAndDate(Long priceId, LocalDate date) {
        return rentConsumeDao.getOneByPriceIdAndDate(priceId,date);
    }
}
