package com.murphyyi.homesystem.manager;

import com.murphyyi.homesystem.model.User;
import com.murphyyi.homesystem.model.UserDTO;

/**
 * @ClassName: UserManager
 * @description: 用户登录相关
 * @author: zhangyi
 * @since: 2019-04-17 09:26
 */
public interface UserManager {
    /**
    * @Description: 用户注册
    * @Param1: user
    * @return: java.lang.String
    * @Author: zhangyi
    * @Date: 2019-04-17
    */
    Long register(User user);

    /**
    * @Description: 检查用户名是否重复
    * @Param1: name
    * @return: java.lang.Boolean
    * @Author: zhangyi
    * @Date: 2019-04-17
    */
    Boolean checkUser(String name);

    Boolean checkEmail(String name);
    /**
    * @Description: 用户登陆
    * @Param1: userName
    * @Param2: passWord
    * @return: com.murphyyi.sso.model.User
    * @Author: zhangyi
    * @Date: 2019-04-18
    */
    User login(String userName, String passWord);

    /**
    * @Description: 更新用户信息
    * @Param1: userDTO
    * @return: com.murphyyi.sso.model.User
    * @Author: zhangyi
    * @Date: 2019-04-18
    */
    User update(UserDTO userDTO);

    /**
    * @Description: 通过uid得到用户信息
    * @Param1: uid
    * @return: com.murphyyi.sso.model.User
    * @Author: zhangyi
    * @Date: 2019-04-18
    */
    User getUserByUid(Long uid);
}
