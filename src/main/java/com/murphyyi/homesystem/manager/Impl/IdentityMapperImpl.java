package com.murphyyi.homesystem.manager.Impl;

import com.murphyyi.homesystem.dao.IdentityDao;
import com.murphyyi.homesystem.manager.IdentityMapper;
import com.murphyyi.homesystem.model.DO.IdentityDO;
import com.murphyyi.homesystem.utils.Snowflake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * @ClassName: IdentityMapperImpl
 * @description:
 * @author: zhangyi
 * @since: 2019-05-27 03:22
 */
@Slf4j
@Service
public class IdentityMapperImpl implements IdentityMapper {
    @Resource
    IdentityDao identityDao;
    @Override
    public Long updateOrSave(IdentityDO identityDO) {
        Boolean status = false;
        if (identityDO.getId() == null) {
            identityDO.setId(Snowflake.userId());
            status = identityDao.insert(identityDO);
            log.info("存储成功,{}", toJSONString(identityDO));
        } else {
            status = identityDao.update(identityDO);
            log.error("存储失败,{}", toJSONString(identityDO));
        }
        return identityDO.getId();
    }

    @Override
    public IdentityDO getById(Long id) {
        IdentityDO identityDO =  identityDao.getOneById(id);
        log.info("身份信息查询结果为{}" , identityDO);
        return identityDO;
    }
}
