package com.murphyyi.homesystem.service.impl;

import com.murphyyi.homesystem.service.RedisService;
import com.murphyyi.homesystem.service.VerifyService;
import com.murphyyi.homesystem.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ClassName: VerifyServiceImpl
 * @description:
 * @author: zhangyi
 * @since: 2019-05-16 02:56
 */
@Service
public class VerifyServiceImpl implements VerifyService {

    private final
    RedisService redisService;

    public VerifyServiceImpl(RedisService redisService) {this.redisService = redisService;}

    @Override
    public Object getData(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (null == token || "".equals(token)) {
            return null;
        }
        Object obj = redisService.get(token, Map.class);
        if (obj == null) {
            return null;
        }
        return obj;
    }
}
