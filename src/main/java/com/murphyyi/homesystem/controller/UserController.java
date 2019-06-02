package com.murphyyi.homesystem.controller;

import com.murphyyi.homesystem.dao.UserInfoDao;
import com.murphyyi.homesystem.model.BO.LoginBO;
import com.murphyyi.homesystem.model.DO.UserInfoDO;
import com.murphyyi.homesystem.model.SSOResposne;
import com.murphyyi.homesystem.model.User;
import com.murphyyi.homesystem.model.UserDTO;
import com.murphyyi.homesystem.model.VO.UserVO;
import com.murphyyi.homesystem.model.VO.LoginVO;
import com.murphyyi.homesystem.service.TokenService;
import com.murphyyi.homesystem.manager.UserManager;
import com.murphyyi.homesystem.utils.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.alibaba.fastjson.JSON.toJSONString;
import static com.murphyyi.homesystem.utils.MD5Utils.randomGenerate;

/**
 * @ClassName: UserController
 * @description:
 * @author: zhangyi
 * @since: 2019-04-15 00:04
 */

@Api(value = "userController", tags = "用户控制")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserManager userManager;

    private final TokenService tokenService;

    private final
    UserInfoDao userInfoDao;

    private final HttpServletRequest request;
    @Autowired
    public UserController(UserManager userManager, TokenService tokenService, UserInfoDao userInfoDao, HttpServletRequest request) {
        this.userManager = userManager;
        this.tokenService = tokenService;
        this.userInfoDao = userInfoDao;
        this.request = request;
    }

    /**
     * @Description: 用户注册
     * @Param1: user
     * @return: java.lang.String
     * @Author: zhangyi
     * @Date: 2019-04-18
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public Result register(@ModelAttribute UserVO userVO) {

        Boolean allowUser = userManager.checkUser(userVO.getName());
        Boolean allowEmail = userManager.checkEmail(userVO.getEmail());
        if (allowUser && allowEmail) {
            User user = new User();
            user.setUserName(userVO.getName());
            user.setEmail(userVO.getEmail());
            user.setPassWord(randomGenerate(userVO.getPasswd()));
            Long uid = userManager.register(user);
            User userResult = userManager.getUserByUid(uid);

            if (userResult != null) {
                return Result.success("","注册成功");
            }
            return Result.fail("创建失败");
        }
        return Result.fail("用户名重复");
    }

    /**
     * @Description: 用户登陆
     * @Param1: loginVO
     * @return: com.murphyyi.sso.SSOResposne
     * @Author: zhangyi
     * @Date: 2019-04-18
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Result Login(@RequestBody LoginBO loginBO) {
        User user = userManager.login(loginBO.getUsername(), loginBO.getPassword());
        if (user != null) {
            String token = tokenService.generateToken(user, Map.class,86400);
            Long uuid = user.getUid();
            List<UserInfoDO> userInfoDOS = userInfoDao.getOneByUid(uuid);
            String name = userInfoDOS.get(0).getNickName();
            LoginVO loginVO = new LoginVO(name,token,uuid);
            return Result.success(loginVO,"登陆成功");
        }

        return Result.failErr();
    }

    /**
     * @Description: 用户信息更新
     * @Param1: userDTO
     * @Param2: token
     * @return: com.murphyyi.sso.SSOResposne
     * @Author: zhangyi
     * @Date: 2019-04-18
     */
    @RequestMapping(value = "updateUser", method = RequestMethod.POST)
    public SSOResposne updateUser(@ModelAttribute UserDTO userDTO,
                                  @RequestParam String token) {
        User user = userManager.update(userDTO);
        log.info("用户信息更新={}", toJSONString(user));
        if (user != null) {
            return tokenService.exchange(token, user, 3600);
        }
        return new SSOResposne();
    }

    /**
     * @Description: 用户登出
     * @Param1: token
     * @return: java.lang.Boolean
     * @Author: zhangyi
     * @Date: 2019-04-18
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public Object logout() {
        String token = request.getHeader("token");
        log.info("登出token={}", token);
        Map user =(Map) tokenService.verificationToken(token, Map.class);
        log.info("登出用户信息user={}", toJSONString(user));
        Boolean flag = tokenService.rejectToken(token);
        if (flag) {
            return Result.success(flag, "token 已经清除");
        } else {
            return Result.fail("token 不存在");
        }
    }

    @RequestMapping(value = "getInfoByToken", method = RequestMethod.POST)
    public Object getUserInfo(@RequestParam String token){
        Map user =(Map) tokenService.verificationToken(token, Map.class);
        if(user!=null){
            return Result.success(user,"成功获取");
        }else {
            return Result.fail("获取失败");
        }
    }

}


