package com.murphyyi.homesystem.controller;

import com.murphyyi.homesystem.service.RedisService;
import com.murphyyi.homesystem.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xbill.DNS.TextParseException;

import java.util.concurrent.atomic.AtomicLong;

import static com.murphyyi.homesystem.utils.GetIP.getRealIpV4;

/**
 * @ClassName: tool
 * @description:
 * @author: zhangyi
 * @since: 2019-02-28 18:43
 */
@Slf4j
@RestController
@Api(tags = "工具")
@RequestMapping(value = "/tool")
public class TOOL {
    private final
    RedisService redisService;
    public TOOL(RedisService redisService) {this.redisService = redisService;}

    //    @Scheduled(fixedRate = 1000)
    @ApiOperation(value = "获取出网口IP", notes = "根据域名得到IP")
    @ApiImplicitParam(name = "url", value = "真实ip", required = true, dataType = "String")
//    @RequestMapping(name="/greeting",method=GET)
    @RequestMapping(value = {"/ip"}, method = RequestMethod.GET)
    public Result<Object> reportCurrentTime(@RequestParam String url) {
        try {
            if ("".equals(url)){
                return Result.fail("request error, url=" + url );
            }
                return Result.success(getRealIpV4(url), "");
        } catch (Exception e) {
            return Result.fail("request error, url=" + url + "" + e);
        }
    }


    @RequestMapping(value = {"/redisSet"},method = RequestMethod.GET)
    public void set(@RequestParam String token,@RequestParam String num) {
        if("".equals(num)) {
            num = "174200668691628032";
        }
        redisService.set(token,num,1000000);
    }
}
