package com.murphyyi.homesystem.manager.Impl;

import com.murphyyi.homesystem.dao.UserMapper;
import com.murphyyi.homesystem.manager.UserInfoManager;
import com.murphyyi.homesystem.model.DO.UserInfoDO;
import com.murphyyi.homesystem.model.User;
import com.murphyyi.homesystem.model.UserDTO;

import com.murphyyi.homesystem.manager.UserManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.alibaba.fastjson.JSON.toJSONString;
import static com.murphyyi.homesystem.utils.MD5Utils.randomGenerate;
import static com.murphyyi.homesystem.utils.MD5Utils.randomVerify;
import static com.murphyyi.homesystem.utils.Snowflake.userId;

/**
 * @ClassName: UserManagerImpl
 * @description:
 * @author: zhangyi
 * @since: 2019-04-17 09:27
 */
@Slf4j
@Service
public class UserManagerImpl implements UserManager {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserInfoManager userInfoManager;


    /**
     * @Description: 用户注册
     * @Param1: user
     * @return: java.lang.String
     * @Author: zhangyi
     * @Date: 2019-04-17
     */
    @Override
    public Long register(User user) {
        Long uid = userId();
        user.setUid(uid);
        userMapper.insert(user);
        User user1 = userMapper.getOneByUid(uid);
        UserInfoDO userInfoDO = new UserInfoDO();
        userInfoDO.setUid(uid);
        userInfoDO.setNickName(user.getUserName());
        userInfoManager.updateOrSave(userInfoDO);
        log.info("uid={}", uid);
        if (userInfoManager != null) {
            log.info("user={}", toJSONString(user1));
        } else {
            log.error("user=null,uid={}", uid);
        }
        return uid;
    }

    /**
     * @Description: 检查用户名是否重复
     * @Param1: name
     * @return: java.lang.Boolean
     * @Author: zhangyi
     * @Date: 2019-04-17
     */
    @Override
    public Boolean checkUser(String name) {
        return null == userMapper.getOneByName(name);
    }

    @Override
    public Boolean checkEmail(String email) {
        return null == userMapper.getOneByEmail(email);
    }

    /**
     * @Description: 用户登陆
     * @Param1: userName
     * @Param2: passWord
     * @return: com.murphyyi.sso.model.User
     * @Author: zhangyi
     * @Date: 2019-04-18
     */
    @Override
    public User login(String userName, String passWord) {
        User user = userMapper.getOneByName(userName);
        if(user==null){
            return null;
        }
        boolean staus = randomVerify(passWord, user.getPassWord());
        return staus ? user : null;
    }

    @Override
    public User update(UserDTO userDto) {
        if (null != userDto) {
            User user = userMapper.getOneByUid(userDto.getUid());
            if (null != user) {
                user.setEmail("".equals(userDto.getEmail()) ? user.getEmail() : userDto.getEmail());
                user.setNickName("".equals(userDto.getNickName()) ? user.getNickName() : userDto.getNickName());
                if (null != userDto.getPassWord()) {
                    user.setPassWord(randomGenerate(userDto.getPassWord()));
                }
                user.setJurisdiction(null != userDto.getJurisdiction() ? user.getJurisdiction() : userDto.getJurisdiction());
                if (null != userDto.getWechat()) {
                    user.setWechat(userDto.getWechat());
                }
            }
        }

        return null;
    }

    @Override
    public User getUserByUid(Long uid) {
        User user = null;
        if(uid!=null){
            user = userMapper.getOneByUid(uid);
            log.info("通过uid，查询到{}",toJSONString(user));
        }
        if(null!=user){
            return user;
        }
        return null;
    }

}
