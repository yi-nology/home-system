package com.murphyyi.homesystem.controller;

import com.murphyyi.homesystem.manager.HouseManager;
import com.murphyyi.homesystem.manager.UserInfoManager;
import com.murphyyi.homesystem.model.DO.HouseInfoDO;
import com.murphyyi.homesystem.model.DO.UserInfoDO;
import com.murphyyi.homesystem.model.DataMapper;
import com.murphyyi.homesystem.model.VO.UserInfoVO;
import com.murphyyi.homesystem.service.RedisService;
import com.murphyyi.homesystem.service.VerifyService;
import com.murphyyi.homesystem.utils.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.murphyyi.homesystem.utils.ObjectUtils.objectToMap;

/**
 * @ClassName: ProfileInfo
 * @description:
 * @author: zhangyi
 * @since: 2019-05-05 01:58
 */
@Slf4j
@RestController
@Api(tags = "个人信息")
@RequestMapping(value = "/profile")
public class ProfileInfomation {

    private final HouseManager houseManager;
    private final UserInfoManager userInfoManager;
    private final HttpServletRequest request;
    private final VerifyService verifyService;

    public ProfileInfomation(HouseManager houseManager, UserInfoManager userInfoManager, HttpServletRequest request, VerifyService verifyService) {
        this.houseManager = houseManager;
        this.userInfoManager = userInfoManager;
        this.request = request;
        this.verifyService = verifyService;
    }

    @RequestMapping(value = {"getInfo"}, method = RequestMethod.POST)
    Result getProfile() {
        Object obj = verifyService.getData(request);
        if (obj == null) {
            return Result.failErr();
        }
        Map user = (Map) obj;
        String id = user.get("uid") + "";
        List<UserInfoDO> userInfoDOList = userInfoManager.getByCondition(id, "id");
        List<HouseInfoDO> houseInfoDOList = houseManager.getByCondition(id, "user");
        Map<String, Object> map = new HashMap<>();
        try {
            map = objectToMap(DataMapper.instance.DOtoVO(userInfoDOList.get(0)));
            map.put("house", houseInfoDOList);
        } catch (IllegalAccessException e) {
            log.error("数据转换异常 error={ }", e);
        }

        return Result.success(map, "查询成功");
    }

    @RequestMapping(value = {"setInfo"}, method = RequestMethod.POST)
    Result setProfile(@RequestBody UserInfoVO userInfoVO) {
        Object obj = verifyService.getData(request);
        if (obj == null) {
            return Result.failErr();
        }
        Map user = (Map) obj;

        UserInfoDO userInfoDO = DataMapper.instance.VOtoDO(userInfoVO);
        userInfoDO.setUid(Long.parseLong(user.get("uid") + ""));

        Long uid = userInfoManager.updateOrSave(userInfoDO);
        if (uid == null) {
            return Result.fail("更新失败");
        }
        return Result.success("更新成功");
    }
}
