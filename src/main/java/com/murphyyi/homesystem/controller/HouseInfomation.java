package com.murphyyi.homesystem.controller;

import com.murphyyi.homesystem.manager.HomeManager;
import com.murphyyi.homesystem.manager.HouseManager;
import com.murphyyi.homesystem.model.DO.HouseInfoDO;
import com.murphyyi.homesystem.model.DataMapper;
import com.murphyyi.homesystem.model.VO.HouseInfoVO;
import com.murphyyi.homesystem.model.VO.HouseVO;
import com.murphyyi.homesystem.service.VerifyService;
import com.murphyyi.homesystem.utils.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: HouseManager
 * @description:
 * @author: zhangyi
 * @since: 2019-05-06 23:08
 */
@Slf4j
@RestController
@Api(tags = "房屋信息")
@RequestMapping(value = "/house")
public class HouseInfomation {
    private final
    HttpServletRequest request;

    private final
    HomeManager homeManager;
    private final
    VerifyService verifyService;
    private final HouseManager houseManager;

    public HouseInfomation(HttpServletRequest request, HouseManager houseManager,  HomeManager homeManager, VerifyService verifyService) {
        this.request = request;
        this.houseManager = houseManager;
        this.homeManager = homeManager;
        this.verifyService = verifyService;
    }

    @RequestMapping(value = {"getInfo"}, method = RequestMethod.POST)
    public Result getHouse() {
        Object obj = verifyService.getData(request);
        if (obj == null) {
            return Result.failErr();
        }
        Map user = (Map) obj;
        String id = user.get("uid") + "";
        List<HouseInfoDO> houseInfoDOList = houseManager.getByCondition(id, "user");
        List<HouseVO> result = new ArrayList<>(houseInfoDOList.size());
        for (HouseInfoDO houseInfoDO : houseInfoDOList) {
            List<Integer> list = homeManager.getAllEmptyRoom(houseInfoDO.getHouseId());
            result.add(DataMapper.instance.MaketoVO(list.get(0), list.get(1), houseInfoDO));
        }

        return Result.success(result, "获取数据成功");
    }

    @RequestMapping(value = {"setInfo"}, method = RequestMethod.POST)
    public Result setHouse(@RequestBody HouseInfoVO houseInfoVO) {
        Object obj = verifyService.getData(request);
        if (obj == null) {
            return Result.failErr();
        }
        Map user = (Map) obj;
        String id = user.get("uid") + "";
        houseInfoVO.setLand_id(Long.parseLong(id));
        HouseInfoDO houseInfoDO = DataMapper.instance.VOtoDO(houseInfoVO);
        Long uid = houseManager.updateOrSave(houseInfoDO);
       if(uid==null){
           return Result.fail("存储失败");
       }
        return Result.success( "存储成功");
    }

    @RequestMapping(value = {"delInfo"}, method = RequestMethod.POST)
    public Result delHouse(@RequestBody HouseInfoVO houseInfoVO) {
        Object obj = verifyService.getData(request);
        if (obj == null) {
            return Result.failErr();
        }
        HouseInfoDO houseInfoDO = DataMapper.instance.VOtoDO(houseInfoVO);
        Boolean state =  houseManager.del(houseInfoDO);
        if(state==null){
            return Result.fail("存储失败");
        }
        return Result.success( "存储成功");
    }
}
