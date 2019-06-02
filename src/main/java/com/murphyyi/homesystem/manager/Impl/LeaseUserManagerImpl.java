package com.murphyyi.homesystem.manager.Impl;

import com.murphyyi.homesystem.dao.LeaseholderDao;
import com.murphyyi.homesystem.manager.LeaseUserManager;
import com.murphyyi.homesystem.model.DO.LeaseholderDO;
import com.murphyyi.homesystem.utils.Snowflake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * @ClassName: LeaseUserManagerImpl
 * @description: 租户个人信息
 * @author: zhangyi
 * @since: 2019-04-23 00:45
 */
@Slf4j
@Service
public class LeaseUserManagerImpl implements LeaseUserManager {

    @Resource
    LeaseholderDao leaseholderDao;

    @Override
    public Long updateOrSave(LeaseholderDO leaseholderDO) {
        Boolean status = false;
        if (leaseholderDO.getUid() == null) {
            leaseholderDO.setUid(Snowflake.userId());
            status = leaseholderDao.insert(leaseholderDO);
        } else {
            status = leaseholderDao.update(leaseholderDO);
        }
        if (status) {
            log.info("存储成功,{}", toJSONString(leaseholderDO));
        } else {
            log.error("存储失败,{}", toJSONString(leaseholderDO));
        }
        return leaseholderDO.getUid();
    }

    @Override
    public List<LeaseholderDO> getByCondition(String condition, String type) {
        List<LeaseholderDO> result = new ArrayList<LeaseholderDO>();
        if ("id".equals(type)) {
            LeaseholderDO leaseholderDO = leaseholderDao.getOneByUid(Long.parseLong(condition));
            result.add(leaseholderDO);
            log.info("查询数据为{}", toJSONString(result));
            return result;
        }
        if ("name".equals(type)) {
            result = leaseholderDao.getByName(condition);
            log.info("查询数据为{}", toJSONString(result));
            return result;
        }
        if ("phone".equals(type)) {
            result = leaseholderDao.getByPhone(condition);
            log.info("查询数据为{}", toJSONString(result));
            return result;
        }
        return result;
    }

    @Override
    public LeaseholderDO getById(Long id) {
        LeaseholderDO leaseholderDO = leaseholderDao.getOneByUid(id);
        log.info(toJSONString(leaseholderDO));
        return leaseholderDO;
    }

    @Override
    public Boolean del(Long id) {
        return leaseholderDao.del(id);
    }
}
