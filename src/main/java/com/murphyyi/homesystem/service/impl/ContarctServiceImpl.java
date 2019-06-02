package com.murphyyi.homesystem.service.impl;

import com.murphyyi.homesystem.dao.ContractDao;
import com.murphyyi.homesystem.dao.UnionDao;
import com.murphyyi.homesystem.manager.ContractManager;
import com.murphyyi.homesystem.model.DO.ContractDO;
import com.murphyyi.homesystem.model.DO.ContractTableDO;
import com.murphyyi.homesystem.model.DataMapper;
import com.murphyyi.homesystem.model.VO.ContractTableVO;
import com.murphyyi.homesystem.service.ContractService;
import org.apache.http.annotation.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: ContarctServiceImpl
 * @description:
 * @author: zhangyi
 * @since: 2019-05-26 03:56
 */
@Service
public class ContarctServiceImpl implements ContractService {
    @Resource
    ContractDao contractDao;

    @Resource
    UnionDao unionDao;

    @Autowired
    ContractManager contractManager;

    @Override
    public List getInfo(Integer page, Integer pageSize, Long userId) {
        List<ContractTableDO> list = unionDao.getContract(userId,page*pageSize,pageSize);
        List<ContractTableVO> res = list.stream().map( DataMapper.instance::DOtoVO).collect(Collectors.toList());
        return res;
    }

    @Override
    public ContractDO getInfoById(Long uid) {
        return contractManager.getById(uid);
    }

    @Override
    public Integer getTotal(Long uid) {
        return unionDao.getContractTotal(uid);
    }

    @Override
    public Long saveOrUpdate(ContractDO contractDO) {
        Long cid = contractManager.updateOrSave(contractDO);
        return cid;
    }
}
