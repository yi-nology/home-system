package com.murphyyi.homesystem.service.impl;

import com.murphyyi.homesystem.dao.UnionDao;
import com.murphyyi.homesystem.manager.HomeManager;
import com.murphyyi.homesystem.manager.HomePriceManager;
import com.murphyyi.homesystem.manager.HouseManager;

import com.murphyyi.homesystem.manager.RentConsumeManager;
import com.murphyyi.homesystem.model.DO.HomeInfoDO;
import com.murphyyi.homesystem.model.DO.HomePriceDO;
import com.murphyyi.homesystem.model.DO.RentConsumeDO;
import com.murphyyi.homesystem.model.DataMapper;
import com.murphyyi.homesystem.model.DO.HomeTableDO;
import com.murphyyi.homesystem.model.VO.HomeTableVO;
import com.murphyyi.homesystem.service.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: HomeServiceImpl
 * @description:
 * @author: zhangyi
 * @since: 2019-05-24 00:28
 */
@Service
@Slf4j
public class HomeServiceImpl implements HomeService {

    private final HomeManager homeManager;
    private final HomePriceManager homePriceManager;
    private final RentConsumeManager rentConsumeManager;
    public HomeServiceImpl(HomeManager homeManager, HomePriceManager homePriceManager, RentConsumeManager rentConsumeManager) {
        this.homeManager = homeManager;
        this.homePriceManager = homePriceManager;
        this.rentConsumeManager = rentConsumeManager;
    }

    @Resource
    UnionDao unionDao;

    @Override
    public List getInfo(Integer page, Integer pageSize, Long userId) {
        Map<String, Object> result = new HashMap<>();
        List<HomeTableDO> homeTable = unionDao.getHomeTable(userId, page * pageSize, pageSize);
        List<HomeTableVO> homeTableVOS = homeTable.stream().map(DataMapper.instance::DOtoVO).collect(Collectors.toList());
        homeTableVOS.forEach(this::dataWash);
        log.info("房屋数据={}",homeTableVOS);
        return homeTableVOS;
    }

    private String toDecimal(String sec) {
        StringBuilder sb = new StringBuilder(sec);
        if (sb.length() > 2) {
            sb.reverse();
            sb.insert(2, '.');
            sb.reverse();
        }
        if (sb.length() == 2) {
            sb.insert(0, "0.");
        }
        if (sb.length() == 1) {
            sb.insert(0, "0.0");
        }
        return sb.toString();
    }

    private void dataWash(HomeTableVO homeTableVO) {
        homeTableVO.setSize(homeTableVO.getSize()/100);
        homeTableVO.setElectric(homeTableVO.getElectric()/100);
        homeTableVO.setPrice(homeTableVO.getPrice()/100);
        homeTableVO.setNet(homeTableVO.getNet()/100);
        homeTableVO.setWater(homeTableVO.getWater()/100);
        homeTableVO.setRemark_price(homeTableVO.getRemark_price()/100);
    }

    @Override
    public Integer getTotal(Long userId) {
        try {
            return unionDao.getHomeTableTotal(userId);
        } catch (Exception e) {
            log.error("发生错误,e={}", e);
        }
        return 0;
    }

    @Override
    public Boolean delInfo(Long homeId, Long price) {
        try {
            Boolean h = homeManager.delhome(homeId);
            Boolean p = homePriceManager.delPrice(price);
            return h & p;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean saveOrUpdate(HomeTableVO homeTableVO) {
        HomeTableDO homeTableDO = DataMapper.instance.VOtoDO(homeTableVO);

        HomePriceDO homePriceDO = new HomePriceDO(homeTableDO.getPriceId(), homeTableDO.getPrice(), homeTableDO.getWater(), homeTableDO.getElectric(), homeTableDO.getNet(), homeTableDO.getRemarkPrice(), LocalDate.now());
        Long priceId = homePriceManager.updateOrSave(homePriceDO);

        HomeInfoDO homeInfoDO = new HomeInfoDO(homeTableDO.getHomeId(), homeTableDO.getHomeName(), priceId, homeTableDO.getRemarks(), homeTableDO.getHomeSize(), homeTableDO.getHouseId(), true);
        Long homeId = homeManager.updateOrSave(homeInfoDO);
        String rcId = homeTableVO.getRc_id();
        Integer rc = null;
        RentConsumeDO rDO = null;
        if(rcId != null){
            rc = Integer.parseInt(rcId);
            rDO  =  rentConsumeManager.getById(rc);
        }
        LocalDate now = LocalDate.now();

        LocalDate firstDayOfThisMonth = now .with(TemporalAdjusters.firstDayOfMonth());
        if(rDO!=null &&!(rDO.getWater().equals(homeTableVO.getDwater())&&rDO.getElectric().equals(homeTableVO.getDelectric()))){
            RentConsumeDO rentConsumeDO = new RentConsumeDO(rc,priceId,null,homeTableVO.getDwater(),homeTableVO.getDelectric(),LocalDate.now());
            rentConsumeManager.updateOrSave(rentConsumeDO);
        }else {
            RentConsumeDO rentConsumeDO = new RentConsumeDO(rc,priceId,null,homeTableVO.getDwater(),homeTableVO.getDelectric(),LocalDate.now());
            rentConsumeManager.updateOrSave(rentConsumeDO);
        }
        if(homeId !=null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List getHomeInfo(Long houseId) {
        List<HomeInfoDO> list = homeManager.getByCondition(houseId.toString(),"house", 0, 1000);
        return list.stream().map((data)->{Map<String,Object> m= new HashMap<>(); m.put("label",data.getHomeName()); m.put("value",data.getHomeId().toString()); return m;}).collect(Collectors.toList());
    }
}
