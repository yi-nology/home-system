package com.murphyyi.homesystem.manager.Impl;

import com.murphyyi.homesystem.dao.ContractDao;
import com.murphyyi.homesystem.manager.ContractManager;
import com.murphyyi.homesystem.model.DO.ContractDO;

import com.murphyyi.homesystem.utils.Snowflake;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;

import java.util.List;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * @ClassName: ContractManagerImpl
 * @description:
 * @author: zhangyi
 * @since: 2019-05-07 00:47
 */
@Slf4j
@Service
public class ContractManagerImpl implements ContractManager {

    @Resource
    ContractDao contractDao;

    @Override
    public Long updateOrSave(ContractDO contractDO) {
        Boolean status = false;
        if (contractDO.getContractId() == null) {
            contractDO.setContractId(Snowflake.userId());
            status = contractDao.insert(contractDO);
        } else {
            status = contractDao.update(contractDO);
        }
        if (status) {
            log.info("存储成功,{}", toJSONString(contractDO));
        } else {
            log.error("存储失败,{}", toJSONString(contractDO));
        }
        return contractDO.getContractId();
    }

    @Override
    public ContractDO getById(Long id) {
        ContractDO result = contractDao.getById(id);
        log.info("查询数据为{}", toJSONString(result));
        return result;
    }
}
