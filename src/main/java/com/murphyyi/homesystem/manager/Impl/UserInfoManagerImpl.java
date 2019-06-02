package com.murphyyi.homesystem.manager.Impl;

import com.murphyyi.homesystem.dao.UserInfoDao;
import com.murphyyi.homesystem.manager.UserInfoManager;
import com.murphyyi.homesystem.model.DO.UserInfoDO;
import com.murphyyi.homesystem.utils.Snowflake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * @ClassName: UserInfoManagerImpl
 * @description:
 * @author: zhangyi
 * @since: 2019-05-05 02:06
 */
@Service
@Slf4j
public class UserInfoManagerImpl implements UserInfoManager {
    @Resource
    UserInfoDao userInfoDao;


    @Override
    public List<UserInfoDO> getByCondition(String condition, String type) {
        List<UserInfoDO> userInfoDOS= new ArrayList<>();
        if("id".equals(type)){
            userInfoDOS = userInfoDao.getOneByUid(Long.parseLong(condition));
        }
        if("name".equals(condition)){
            userInfoDOS =  userInfoDao.getByNickName(condition);
        }
        if("phone".equals(condition)){
            userInfoDOS =  userInfoDao.getByPhone(condition);
        }
        log.info("查询信息为{}",toJSONString(userInfoDOS));
        return userInfoDOS;
    }

    @Override
    public Long updateOrSave(UserInfoDO userInfoDO) {
        Boolean status = false;
        if (userInfoDO.getUid() == null) {
            userInfoDO.setUid(Snowflake.userId());
            status = userInfoDao.insert(userInfoDO);
        } else {
            List<UserInfoDO> userInfos = userInfoDao.getOneByUid(userInfoDO.getUid());
            if(userInfos.size()==0){
                status = userInfoDao.insert(userInfoDO);
            }else {
                status = userInfoDao.update(userInfoDO);
            }
        }
        if (status) {
            log.info("存储成功,{}", toJSONString(userInfoDO));
        } else {
            log.error("存储失败,{}", toJSONString(userInfoDO));
        }
        return userInfoDO.getUid();
    }
}
