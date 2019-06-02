package com.murphyyi.homesystem.controller;

import com.murphyyi.homesystem.manager.HomeManager;
import com.murphyyi.homesystem.manager.HouseManager;
import com.murphyyi.homesystem.model.VO.HomePriceTableVO;
import com.murphyyi.homesystem.model.VO.HomeSearchVO;
import com.murphyyi.homesystem.model.VO.HomeTableVO;
import com.murphyyi.homesystem.service.HomePriceService;
import com.murphyyi.homesystem.service.VerifyService;
import com.murphyyi.homesystem.utils.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: HomePriceInfomation
 * @description:
 * @author: zhangyi
 * @since: 2019-05-28 19:31
 */
@Slf4j
@RestController
@Api(tags = "房屋缴费")
@RequestMapping(value = "/homePrice")
public class HomePriceInfomation {
    @Autowired
    HttpServletRequest request;
    @Autowired
    HomeManager homeManager;
    @Autowired
    VerifyService verifyService;
    @Autowired
    HomePriceService homePriceService;

    @RequestMapping(value = {"saveOrUpdatePrice"}, method = RequestMethod.POST)
    Result saveOrUpdateHomePrice(@RequestBody HomePriceTableVO homePriceTableVO) {
        Object obj = verifyService.getData(request);
        if (obj == null) {
            return Result.failErr();
        }
        Boolean flag = homePriceService.update(homePriceTableVO);
        return flag ? Result.success(flag, "数据更新保存成功") : Result.fail("失败");
    }

    @RequestMapping(value = {"getInfo"}, method = RequestMethod.POST)
    Result getPrice(@RequestBody HomeSearchVO homeSearchVO) {
        Object obj = verifyService.getData(request);
        if (obj == null) {
            return Result.failErr();
        }
        Map user= (Map) obj;
        String id= user.get("uid")+"";
        Long uid = Long.parseLong(id);
        LocalDate now = LocalDate.now();
        LocalDate firstDayOfThisMonth = now.with(TemporalAdjusters.firstDayOfMonth());
        List list = homePriceService.getInfo(uid,firstDayOfThisMonth,homeSearchVO.getCurrentPage()-1,homeSearchVO.getPageSize());
        Integer total = homePriceService.getTotal(uid,firstDayOfThisMonth);
        Map<String, Object> map = new HashMap<>();
        map.put("data",list);
        map.put("total", total);
        return Result.success(map , "查询成功");
    }

    @RequestMapping(value = {"delInfo/{id}"}, method = RequestMethod.GET)
    Result del(@PathVariable Integer id){
        Object obj = verifyService.getData(request);
        if (obj == null) {
            return Result.failErr();
        }
        Boolean flag = homePriceService.del(id);
        return  Result.success(flag, "删除成功");
    }

    @RequestMapping(value = {"getDetail"}, method = RequestMethod.POST)
    Result getDetail(@RequestBody HomePriceTableVO homePriceTableVO) {
        Object obj = verifyService.getData(request);
        if (obj == null) {
            return Result.failErr();
        }
        Map map = homePriceService.getDetail(homePriceTableVO);
        return Result.success(map , "查询成功");
    }
}
