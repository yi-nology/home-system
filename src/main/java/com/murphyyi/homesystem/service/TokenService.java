package com.murphyyi.homesystem.service;

import com.murphyyi.homesystem.model.SSOResposne;

/**
 * @ClassName: TokenService
 * @description:
 * @author: zhangyi
 * @since: 2019-03-15 16:11
 */
public interface TokenService {
    /**
     * @Description: 生成token
     * @Param1: user
     * @Param2: expirytime
     * @return: com.murphyyi.sso.model.SSOResposne
     * @Author: zhangyi
     * @Date: 2019-04-18
     */
    String generateToken(Object object, Class clazz, int expirytime);

    /**
     * @Description: token认证
     * @Param1: token
     * @return: java.lang.Object
     * @Author: zhangyi
     * @Date: 2019-04-18
     */
    Object verificationToken(String token,Class clazz);

    /**
     * @Description: 摧毁token
     * @Param1: token
     * @return: java.lang.Boolean
     * @Author: zhangyi
     * @Date: 2019-04-18
     */
    Boolean rejectToken(String token);

    /**
     * @Description: 置换token
     * @Param1: token
     * @Param2: user
     * @Param3: expirytime
     * @return: com.murphyyi.sso.model.SSOResposne
     * @Author: zhangyi
     * @Date: 2019-04-18
     */
    SSOResposne exchange(String token, Object object, int expirytime);
}
