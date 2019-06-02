package com.murphyyi.homesystem.manager.Impl;

import com.murphyyi.homesystem.dao.IdentityDao;
import com.murphyyi.homesystem.manager.CapacityMapper;
import com.murphyyi.homesystem.model.DO.IdentityDO;
import com.murphyyi.homesystem.model.VO.IdentityVO;

import com.murphyyi.homesystem.service.AipService;
import com.murphyyi.homesystem.utils.Snowflake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.alibaba.fastjson.JSON.toJSONString;


/**
 * @ClassName: capacityImpl
 * @description:
 * @author: zhangyi
 * @since: 2019-04-22 15:34
 */
@Service
@Slf4j
public class CapacityMapperImpl implements CapacityMapper {

    private final
    AipService aipService;

    @Resource
    IdentityDao identityDao;

    public CapacityMapperImpl(AipService aipService) {this.aipService = aipService;}

    @Override
    public IdentityVO setCertify(String frontUrl, String backUrl) {
        IdentityVO identityVO = null;
        try {
            return aipService.idCard(frontUrl, backUrl);
        } catch (Exception e) {
            log.error("身份认证服务异常,e={ }", e);
        }
        return identityVO;
    }

    @Override
    public Long updateOrSave(IdentityDO identityDO) {
        Boolean status = false;
        if (identityDO.getId() == null) {
            identityDO.setId(Snowflake.userId());
            status = identityDao.insert(identityDO);
        } else {
            status = identityDao.update(identityDO);
        }
        if (status) {
            log.info("存储成功,{}", toJSONString(identityDO));
        } else {
            log.error("存储失败,{}", toJSONString(identityDO));
        }
        return identityDO.getId();
    }

    @Override
    public IdentityDO getCertify(Long id) {
        return identityDao.getOneById(id);
    }
}
