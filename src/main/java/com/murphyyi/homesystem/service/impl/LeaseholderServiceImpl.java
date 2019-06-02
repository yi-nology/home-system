package com.murphyyi.homesystem.service.impl;

import com.murphyyi.homesystem.dao.LeaseholderDao;
import com.murphyyi.homesystem.dao.RentConsumeDao;
import com.murphyyi.homesystem.dao.UnionDao;
import com.murphyyi.homesystem.manager.ContractManager;
import com.murphyyi.homesystem.manager.HomeManager;
import com.murphyyi.homesystem.manager.IdentityMapper;
import com.murphyyi.homesystem.manager.LeaseUserManager;
import com.murphyyi.homesystem.manager.RentConsumeManager;
import com.murphyyi.homesystem.model.DO.ContractDO;
import com.murphyyi.homesystem.model.DO.HomeInfoDO;
import com.murphyyi.homesystem.model.DO.IdentityDO;
import com.murphyyi.homesystem.model.DO.LeaseholderDO;
import com.murphyyi.homesystem.model.DO.LeaseholderTableDO;
import com.murphyyi.homesystem.model.DO.RentConsumeDO;
import com.murphyyi.homesystem.model.DataMapper;
import com.murphyyi.homesystem.model.VO.LeaseholderNewUserVO;
import com.murphyyi.homesystem.model.VO.LeaseholderTableVO;
import com.murphyyi.homesystem.service.LeaseholderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

import static com.alibaba.fastjson.JSON.toJSONString;
import static com.murphyyi.homesystem.utils.DateUtil.DataPlusMonth;

/**
 * @ClassName: LeaseholderServiceImpl
 * @description:
 * @author: zhangyi
 * @since: 2019-05-27 03:07
 */
@Service
@Slf4j
public class LeaseholderServiceImpl implements LeaseholderService {

    private final
    IdentityMapper identityMapper;
    private final
    ContractManager contractManager;
    private final
    LeaseUserManager leaseUserManager;
    private final
    RentConsumeManager rentConsumeManager;
    private final
    HomeManager homeManager;
    @Resource
    UnionDao unionDao;

    public LeaseholderServiceImpl(IdentityMapper identityMapper, ContractManager contractManager, LeaseUserManager leaseUserManager, RentConsumeManager rentConsumeManager, HomeManager homeManager) {
        this.identityMapper = identityMapper;
        this.contractManager = contractManager;
        this.leaseUserManager = leaseUserManager;
        this.rentConsumeManager = rentConsumeManager;
        this.homeManager = homeManager;
    }

    @Override
    public Boolean setinfo(LeaseholderNewUserVO lvo) {
        try {
            if ("".equals(lvo.getContract_id())) {
                lvo.setContract_id(null);
            }
            ContractDO contractDO = new ContractDO(lvo.getContract_id(), lvo.getContent(), lvo.getStartEnd().get(0), lvo.getStartEnd().get(1), lvo.getDate(), lvo.getRate());
            IdentityDO identityDO = new IdentityDO();
            identityDO.setFrontUrl(lvo.getId_card_front());
            identityDO.setBackUrl(lvo.getId_card_back());
            Long identity = identityMapper.updateOrSave(identityDO);
            Long contract = contractDO.getContractId();
            if (contractDO.getContractId() == null) {
                contract = contractManager.updateOrSave(contractDO);
            }
            LeaseholderDO leaseholderDO = new LeaseholderDO(null, lvo.getName(), lvo.getPhone(), lvo.getWork(), contract, lvo.getHome_id(), identity);
            leaseUserManager.updateOrSave(leaseholderDO);
            HomeInfoDO homeInfoDO = homeManager.getByCondition(lvo.getHome_id() + "", "id", 0, 10).get(0);
            LocalDate now = LocalDate.now();
            LocalDate firstDayOfThisMonth = now.with(TemporalAdjusters.firstDayOfMonth());
            LocalDate m = DataPlusMonth(firstDayOfThisMonth,lvo.getRate());
            RentConsumeDO rentConsumeDO = new RentConsumeDO(null, homeInfoDO.getPriceId(), m, null, null, null);
            rentConsumeManager.updateOrSave(rentConsumeDO);
            return true;
        } catch (Exception e) {
            log.error("租户用户创建失败,{},e={}", toJSONString(lvo), e);
            return false;
        }
    }

    @Override
    public Boolean setinfo(LeaseholderTableVO lvo) {
        try {
            LeaseholderDO leaseholderDO = leaseUserManager.getById(Long.parseLong(lvo.getUid()));
            leaseholderDO.setRentName(lvo.getName());
            leaseholderDO.setWork(lvo.getWork());
            leaseholderDO.setRentPhone(lvo.getPhone());
            leaseholderDO.setHomeId(Long.parseLong(lvo.getHome_id()));

            IdentityDO identityDO = identityMapper.getById(leaseholderDO.getIdentityId());
            if(identityDO == null){
                identityDO = new IdentityDO();
            }
            identityDO.setBackUrl(lvo.getId_card_back());
            identityDO.setFrontUrl(lvo.getId_card_front());
            Long i = identityMapper.updateOrSave(identityDO);
            leaseholderDO.setIdentityId(i);
            leaseUserManager.updateOrSave(leaseholderDO);
            log.info(toJSONString(leaseholderDO), toJSONString(identityDO));
            return true;
        } catch (Exception e) {
            log.error("e={}", e);
            return false;
        }
    }

    @Override
    public List<LeaseholderTableVO> getList(Long userId, String name, Integer cur, Integer pageSize) {
        if (name == null) {
            List<LeaseholderTableDO> tableDOS = unionDao.getLeaseholder(userId, cur * pageSize, pageSize);
            log.info(toJSONString(tableDOS));
            List<LeaseholderTableVO> lvo = tableDOS.stream().map(DataMapper.instance::DOtoVO).collect(Collectors.toList());
            return lvo;
        } else {
            List<LeaseholderTableDO> tableDOS = unionDao.getLeaseholderByName(userId, name, cur * pageSize, pageSize);
            log.info(toJSONString(tableDOS));
            List<LeaseholderTableVO> lvo = tableDOS.stream().map(DataMapper.instance::DOtoVO).collect(Collectors.toList());
            return lvo;
        }
    }

    @Override
    public Integer getTotal(Long userId, String name) {
        if (name == null) {
            return unionDao.getLeaseholderTotal(userId);
        } else {
            return unionDao.getLeaseholderTotalByName(userId, name);
        }
    }

    @Override
    public Boolean del(Long id) {
        try {
            LeaseholderDO leaseholderDO = leaseUserManager.getById(id);
            HomeInfoDO homeInfoDO = homeManager.getByCondition(leaseholderDO.getHomeId() + "", "id", 0, 10).get(0);
            RentConsumeDO r = rentConsumeManager.getByPriceIdNULL(homeInfoDO.getPriceId());
            LocalDate now = LocalDate.now();
            LocalDate firstDayOfThisMonth = now.with(TemporalAdjusters.firstDayOfMonth());
            RentConsumeDO p = rentConsumeManager.getByPriceAndDate(homeInfoDO.getPriceId(), firstDayOfThisMonth);
            r.setElectric(p.getElectric());
            r.setWater(p.getWater());
            rentConsumeManager.updateOrSave(r);
        } catch (Exception e) {
            log.error("e={}", e);
        }
        return leaseUserManager.del(id);
    }
}
