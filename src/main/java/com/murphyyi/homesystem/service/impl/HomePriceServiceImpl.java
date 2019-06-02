package com.murphyyi.homesystem.service.impl;

import com.murphyyi.homesystem.dao.UnionDao;

import com.murphyyi.homesystem.manager.HomePriceManager;
import com.murphyyi.homesystem.manager.RentConsumeManager;
import com.murphyyi.homesystem.model.DO.HomePriceDO;
import com.murphyyi.homesystem.model.DO.HomePriceTableDO;
import com.murphyyi.homesystem.model.DO.RentConsumeDO;
import com.murphyyi.homesystem.model.VO.HomePriceTableVO;
import com.murphyyi.homesystem.service.HomePriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.murphyyi.homesystem.utils.DateUtil.DataPlusMonth;
import static com.murphyyi.homesystem.utils.ObjectUtils.objectToMap;

/**
 * @ClassName: HomePriceServiceImpl
 * @description:
 * @author: zhangyi
 * @since: 2019-05-28 20:36
 */
@Service
@Slf4j
public class HomePriceServiceImpl implements HomePriceService {

    @Resource
    UnionDao unionDao;

    private final
    HomePriceManager homePriceManager;

    private final
    RentConsumeManager rentConsumeManager;

    public HomePriceServiceImpl(HomePriceManager homePriceManager, RentConsumeManager rentConsumeManager) {
        this.homePriceManager = homePriceManager;
        this.rentConsumeManager = rentConsumeManager;
    }

    @Override
    public List getInfo(Long uid, LocalDate date, Integer page, Integer pageSize) {
        LocalDate date1 = date.plusDays(20);
        List<HomePriceTableDO> homePriceTable = unionDao.getHomePriceTable(date, date1, uid, page * pageSize, pageSize);
        return homePriceTable.stream().map(this::sumResult).collect(Collectors.toList());
    }

    @Override
    public Map getDetail(HomePriceTableVO homePriceTableVO) {
        RentConsumeDO r1 = rentConsumeManager.getByPriceIdNULL(Long.parseLong(homePriceTableVO.getPrice_id()));
        RentConsumeDO r2 = rentConsumeManager.getById(Integer.parseInt(homePriceTableVO.getRc_id()));
        HomePriceDO homePriceDO = homePriceManager.getByPid(Long.parseLong(homePriceTableVO.getPrice_id()));
        homePriceDO.setPriceId(null);
        Integer w = r2.getWater() - r1.getWater();
        Integer q = r2.getElectric() - r1.getElectric();
        Map<String, Object> map = new HashMap<>();
        map.put("oldW", r1.getWater());
        map.put("newW", r2.getWater());
        map.put("oldE", r1.getElectric());
        map.put("newE", r2.getElectric());
        map.put("w", w);
        map.put("e", q);
        map.put("sum", calculateData(homePriceDO, w, q, homePriceTableVO.getRate()));
        map.put("rate", homePriceTableVO.getRate());
        try {
            map.putAll(objectToMap(homePriceDO));
        } catch (IllegalAccessException e) {
            log.error("e={}", e);
        }
        return map;
    }

    private HomePriceTableVO sumResult(HomePriceTableDO hDO) {

        Long pid = hDO.getPriceId();
        HomePriceDO homePriceDO = homePriceManager.getByPid(pid);
        RentConsumeDO rentConsumeDO = rentConsumeManager.getByPriceIdNULL(pid);
        Integer e = hDO.getElectric() - rentConsumeDO.getElectric();
        Integer w = hDO.getWater() - rentConsumeDO.getWater();
        Integer sum = this.calculateData(homePriceDO, w, e, hDO.getRate());
        Integer state = 0;
        if (hDO.getCommitDate() != null) {
            state = 1;
        }
        HomePriceTableVO homePriceTableVO = new HomePriceTableVO(hDO.getPriceId().toString(), hDO.getRcId().toString(), hDO.getName(),
                hDO.getHouseName(), hDO.getHomeName(), hDO.getCommitDate(), Float.valueOf(sum / 100).toString(), state, hDO.getWater(), hDO.getElectric(), hDO.getRate());
        return homePriceTableVO;
    }

    private Integer calculateData(HomePriceDO homePriceDO, Integer w, Integer e, Integer rate) {
        return homePriceDO.getPrice() * rate + homePriceDO.getElectric() * e + homePriceDO.getWater() * w + homePriceDO.getNet() + homePriceDO.getRemarkPrice();
    }

    @Override
    public Integer getTotal(Long uid, LocalDate date) {
        LocalDate date1 = date.plusDays(20);
        return unionDao.getHomePriceTotal(date, date1, uid);
    }

    @Override
    public Boolean del(Integer id) {
        return rentConsumeManager.del(id);
    }

    @Override
    public Boolean update(HomePriceTableVO homePriceTableVO) {
        try {
            homePriceTableVO.getRc_id();
            LocalDate now = LocalDate.now();
            LocalDate firstDayOfThisMonth = now.with(TemporalAdjusters.firstDayOfMonth());
            //更新表底
            RentConsumeDO rentConsumeDO = rentConsumeManager.getById(Integer.parseInt(homePriceTableVO.getRc_id()));
            rentConsumeDO.setWater(homePriceTableVO.getWater());
            rentConsumeDO.setElectric(homePriceTableVO.getElectric());
            rentConsumeDO.setCommitDate(LocalDate.now());
            rentConsumeManager.updateOrSave(rentConsumeDO);
            //创建下次触发数据
            LocalDate l = DataPlusMonth(firstDayOfThisMonth, homePriceTableVO.getRate());
            RentConsumeDO rDO = new RentConsumeDO(null, Long.parseLong(homePriceTableVO.getPrice_id()), l, null, null, null);
            rentConsumeManager.updateOrSave(rDO);
            return true;
        } catch (Exception e) {
            log.error("e={}", e);
            return false;
        }
    }

}
