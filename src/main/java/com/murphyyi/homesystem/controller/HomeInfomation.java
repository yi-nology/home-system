package com.murphyyi.homesystem.controller;


import com.murphyyi.homesystem.dao.UnionDao;
import com.murphyyi.homesystem.manager.HomeManager;
import com.murphyyi.homesystem.manager.HouseManager;
import com.murphyyi.homesystem.model.DO.HouseInfoDO;
import com.murphyyi.homesystem.model.DataMapper;
import com.murphyyi.homesystem.model.VO.HomeSearchVO;
import com.murphyyi.homesystem.model.VO.HomeTableVO;
import com.murphyyi.homesystem.model.VO.HouseInfoVO;
import com.murphyyi.homesystem.service.HomeService;
import com.murphyyi.homesystem.service.VerifyService;
import com.murphyyi.homesystem.utils.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: HouseManager
 * @description: 房屋
 * @author: zhangyi
 * @since: 2019-04-23 11:05
 */
@Slf4j
@RestController
@Api(tags = "房屋管理")
@RequestMapping(value = "/home")
public class HomeInfomation {
    private final HttpServletRequest request;
    private final VerifyService verifyService;

    private final HomeService homeService;
    @Resource
    UnionDao unionDao;
    public HomeInfomation(HttpServletRequest request, VerifyService verifyService, HomeService homeService) {
        this.request = request;
        this.verifyService = verifyService;
        this.homeService = homeService;
    }




    @RequestMapping(value = {"getInfo"}, method = RequestMethod.POST)
    Result getFamily(@RequestBody HomeSearchVO homeSearchVO) {
        Object obj = verifyService.getData(request);
        if (obj == null) {
            return Result.failErr();
        }
        Map user= (Map) obj;
        String id= user.get("uid")+"";
        Long uid = Long.parseLong(id);
        List list = homeService.getInfo(homeSearchVO.getCurrentPage()-1,homeSearchVO.getPageSize(),uid);
        Integer total = homeService.getTotal(uid);
        Map<String, Object> map = new HashMap<>();
        map.put("data",list);
        map.put("total", total);
        return Result.success(map , "查询成功");
    }

    @RequestMapping(value = {"saveOrUpdateHome"}, method = RequestMethod.POST)
    Result saveOrUpdateHome(@RequestBody HomeTableVO homeTableVO) {
        Object obj = verifyService.getData(request);
        if (obj == null) {
            return Result.failErr();
        }
        if("".equals(homeTableVO.getHome_id())){
            homeTableVO.setHome_id(null);
            homeTableVO.setPrice_id(null);
            homeTableVO.setRc_id(null);
        }
        Boolean flag = homeService.saveOrUpdate(homeTableVO);

        return flag?Result.success(flag,"数据更新保存成功"):Result.fail("失败");
    }
    @RequestMapping(value = {"delInfo"}, method = RequestMethod.POST)
    Result delInfo(@RequestBody HomeTableVO homeTableVO) {
        Boolean flag = homeService.delInfo(Long.parseLong(homeTableVO.getHome_id()),Long.parseLong(homeTableVO.getPrice_id()));
        return Result.success(flag,"");
    }
    @RequestMapping(value = {"getHomeInfo/{key}"}, method = RequestMethod.GET)
    List getHomeInfo(@PathVariable Long key){
        return homeService.getHomeInfo(key);
    }
}
