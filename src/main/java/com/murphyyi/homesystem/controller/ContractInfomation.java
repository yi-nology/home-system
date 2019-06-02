package com.murphyyi.homesystem.controller;

import com.murphyyi.homesystem.model.BO.PageBO;
import com.murphyyi.homesystem.model.DO.ContractDO;
import com.murphyyi.homesystem.model.DataMapper;
import com.murphyyi.homesystem.model.VO.ContractVO;
import com.murphyyi.homesystem.service.ContractService;
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

/**
 * @ClassName: ContractInfomation
 * @description:
 * @author: zhangyi
 * @since: 2019-05-26 04:54
 */
@Slf4j
@RestController
@Api(tags = "房屋管理")
@RequestMapping(value = "/contract")
public class ContractInfomation {
    @Autowired
    HttpServletRequest request;

    @Autowired
    VerifyService verifyService;

    @Autowired
    ContractService contractService;


    @RequestMapping(value = {"getInfo"}, method = RequestMethod.POST)
    Result getContract(@RequestBody PageBO pageBO){
        Object obj = verifyService.getData(request);
        if (obj == null) {
            return Result.failErr();
        }
        Map user= (Map) obj;
        String id= user.get("uid")+"";
        Long uid = Long.parseLong(id);
        List list = contractService.getInfo(pageBO.getCurrentPage()-1, pageBO.getPageSize(),uid);

        Integer total = contractService.getTotal(uid);
        Map<String, Object> map = new HashMap<>();
        map.put("data",list);
        map.put("total", total);
        return Result.success(map , "查询成功");
    }

    @RequestMapping(value = {"saveOrUpdate"}, method = RequestMethod.POST)
    Result saveOrUpdate(@RequestBody ContractVO contractVO){
        ContractDO contractDO = DataMapper.instance.VOtoDO(contractVO);
        Long id = contractService.saveOrUpdate(contractDO);
        return id!=null?Result.success("更新成功"):Result.fail("更新失败");
    }
}
