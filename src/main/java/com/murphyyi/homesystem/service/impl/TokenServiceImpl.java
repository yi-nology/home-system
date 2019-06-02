package com.murphyyi.homesystem.service.impl;

import com.alibaba.fastjson.JSON;
import com.murphyyi.homesystem.model.SSOResposne;
import com.murphyyi.homesystem.service.RedisService;
import com.murphyyi.homesystem.service.TokenService;
import com.murphyyi.homesystem.utils.SSOException;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.alibaba.fastjson.JSON.toJSONString;
import static com.murphyyi.homesystem.utils.UUIDUtils.compressUUID;
import static com.murphyyi.homesystem.utils.UUIDUtils.createUUID;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @ClassName: TokenService
 * @description:
 * @author: zhangyi
 * @since: 2019-03-15 16:07
 */
@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    @Value("${token.default.time}")
    private int defaultTime;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    RedisService redisService;

    /**
     * @return
     * @author: zhangyi
     * description: 生成token
     * create time: 12:55 2019-03-17
     * @Param: null
     */

    @Override
    public String generateToken(Object object, Class clazz,
                                     int expirytime) {
        if (expirytime == 0) {
            expirytime = defaultTime;
        }
        if (object != null) {
            String uuid = null;
            while (true) {
                uuid = compressUUID(createUUID());
                Map data = (Map) redisService.get(uuid , clazz);
                if (data.size()==0) {
                    break;
                }
            }
            redisService.set(uuid, object, expirytime );
            log.info("token={}", uuid);
            return uuid;

        } else {

//            log.error(" <=========== Invalid UserName Password combination  ===================> \n "
//                    + " userName " + userName + " password " + password);
            throw new SSOException(HttpStatus.UNAUTHORIZED.toString());

        }
    }

    /**
     * @author: zhangyi
     * @description: token 认证
     * @date : 13:18 2019-03-17
     * @Param:
     */
    @Override
    public Object verificationToken(String token, Class clazz) {
        Object data = redisService.get(token, clazz);

        if (data != null) {
            return data;
        } else {
            return null;
        }

    }

    /**
     * @Description: 剔除token
     * @Param1: token
     * @return: java.lang.Boolean
     * @Author: zhangyi
     * @Date: 2019-04-18
     */
    @Override
    public Boolean rejectToken(String token) {
        return redisService.del(token);
    }

    /**
     * @Description: 置换token
     * @Param1: token
     * @Param2: object
     * @Param3: expirytime
     * @return: com.murphyyi.sso.model.SSOResposne
     * @Author: zhangyi
     * @Date: 2019-04-18
     */
    @Override
    public SSOResposne exchange(String token, Object object, int expirytime) {
        redisTemplate.opsForValue().set(token, toJSONString(object), expirytime, SECONDS);
        SSOResposne ssoResponse = new SSOResposne();
        ssoResponse.setStatus(HttpStatus.OK.value());
        ssoResponse.setToken(token);
        return ssoResponse;
    }
}
