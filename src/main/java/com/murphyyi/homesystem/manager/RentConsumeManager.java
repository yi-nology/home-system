package com.murphyyi.homesystem.manager;

import com.murphyyi.homesystem.model.DO.RentConsumeDO;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * @ClassName: RentConsumeManager
 * @description:
 * @author: zhangyi
 * @since: 2019-05-07 02:46
 */
public interface RentConsumeManager {
    RentConsumeDO updateOrSave(RentConsumeDO rentConsumeDO);
    RentConsumeDO getById(Integer id);
    Boolean del(Integer id);
    RentConsumeDO getByPriceIdNULL(Long prcieId);
    RentConsumeDO getByPriceAndDate(Long priceId, LocalDate date);
}
