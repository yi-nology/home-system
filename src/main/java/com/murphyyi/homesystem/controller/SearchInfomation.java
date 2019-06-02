package com.murphyyi.homesystem.controller;

import com.murphyyi.homesystem.model.VO.SearchVO;
import com.murphyyi.homesystem.service.SearchHouseService;
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

import java.util.Map;

/**
 * @ClassName: OutInfomation
 * @description:
 * @author: zhangyi
 * @since: 2019-05-13 05:50
 */
@Slf4j
@RestController
@Api(tags = "对外信息展示接口")
@RequestMapping(value = "/search")
public class SearchInfomation {

    @Autowired
    SearchHouseService searchHouseService;

    @RequestMapping(value = {"getInfo"}, method = RequestMethod.POST)
    public Result getHouse(@RequestBody SearchVO searchVO) {
        if(searchVO.getInput()==null || searchVO.getPage() == null || searchVO.getSize() == null){
            return Result.fail("查询失败");
        }
        Map map = searchHouseService.searchByAddress(searchVO.getInput(),searchVO.getPage(),searchVO.getSize());
        return Result.success(map, "获取数据成功");
    }

}
