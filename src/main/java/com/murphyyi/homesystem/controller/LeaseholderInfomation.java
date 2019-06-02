package com.murphyyi.homesystem.controller;

import com.murphyyi.homesystem.dao.LeaseholderDao;
import com.murphyyi.homesystem.manager.CapacityMapper;
import com.murphyyi.homesystem.model.BO.PageBO;
import com.murphyyi.homesystem.model.DO.IdentityDO;
import com.murphyyi.homesystem.model.VO.IdentityVO;
import com.murphyyi.homesystem.model.DO.LeaseholderDO;
import com.murphyyi.homesystem.model.BO.idCard.IdCardMapper;
import com.murphyyi.homesystem.model.VO.LeaseholderNewUserVO;
import com.murphyyi.homesystem.model.VO.LeaseholderTableVO;
import com.murphyyi.homesystem.service.LeaseholderService;
import com.murphyyi.homesystem.service.TokenService;
import com.murphyyi.homesystem.service.VerifyService;
import com.murphyyi.homesystem.utils.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: Leaseholder
 * @description:
 * @author: zhangyi
 * @since: 2019-04-30 15:51
 */
@Slf4j
@RestController
@Api(tags = "租户信息补充")
@RequestMapping(value = "/leaseholder")
public class LeaseholderInfomation {
    private final
    HttpServletRequest request;
    private final
    TokenService tokenService;
    private final
    VerifyService verifyService;
    private final
    LeaseholderService leaseholderService;

    private final CapacityMapper capacityMapper;

    public LeaseholderInfomation(TokenService tokenService, CapacityMapper capacityMapper, LeaseholderService leaseholderService, HttpServletRequest request, VerifyService verifyService) {
        this.tokenService = tokenService;
        this.capacityMapper = capacityMapper;
        this.leaseholderService = leaseholderService;
        this.request = request;
        this.verifyService = verifyService;
    }

    @Resource
    LeaseholderDao leaseholderDao;


    @RequestMapping(value = {"getUserId"}, method = RequestMethod.GET)
    Result setUserInfo(@RequestParam(value = "uid") Long uid) {
        String token = tokenService.generateToken(uid, String.class, 86400);
        return Result.success(token, "创建数据成功");
    }

    @RequestMapping(value = {"setLeaseholder"}, method = RequestMethod.POST)
    Result save(@RequestBody LeaseholderNewUserVO lvo){
        Object obj = verifyService.getData(request);
        if (obj == null) {
            return Result.failErr();
        }
        Boolean flag = leaseholderService.setinfo(lvo);
        return Result.success("数据存储成功");
    }

    @RequestMapping(value = {"updateLeaseholder"}, method = RequestMethod.POST)
    Result update(@RequestBody LeaseholderTableVO lvo){
        Object obj = verifyService.getData(request);
        if (obj == null) {
            return Result.failErr();
        }
        Boolean flag = leaseholderService.setinfo(lvo);
        return Result.success("数据存储成功");
    }

    @RequestMapping(value = {"getList"}, method = RequestMethod.POST)
    Result getList(@RequestBody PageBO pageBO){
        Object obj = verifyService.getData(request);
        if (obj == null) {
            return Result.failErr();
        }
        Map user = (Map) obj;
        Long id = Long.parseLong(user.get("uid")+"");
        List list= leaseholderService.getList(id, pageBO.getName(),pageBO.getCurrentPage()-1,pageBO.getPageSize());
        Integer total = leaseholderService.getTotal(id, pageBO.getName());
        Map<String, Object> map = new HashMap(2);
        map.put("total",total);
        map.put("data", list);
        return Result.success(map,"数据获取成功");
    }

    @RequestMapping(value = {"delInfo/{id}"}, method = RequestMethod.GET)
    Result del(@PathVariable Long id){
        Object obj = verifyService.getData(request);
        if (obj == null) {
            return Result.failErr();
        }
        Boolean flag = leaseholderService.del(id);
        return  Result.success(flag, "删除成功");
    }

    @RequestMapping(value = {"setUserById/{token}"}, method = RequestMethod.GET)
    Result UserInfo(@RequestParam(value = "token") String token,
                    @ModelAttribute IdentityVO identityVO) {
        Map user =(Map) tokenService.verificationToken(token, Map.class);
        String uidStr = user.get("uid")+"";
        Long uid = Long.parseLong(uidStr);

        if (null == identityVO) {
            return Result.fail("获取数据失败");
        }
        IdentityDO identityDO = IdCardMapper.instance.VOtoDO(identityVO);

        Long id = capacityMapper.updateOrSave(identityDO);
        LeaseholderDO leaseholderDO = leaseholderDao.getOneByUid(uid);
        if(leaseholderDO==null){
            return Result.fail("数据错误");
        }
        leaseholderDO.setIdentityId(id);
        Boolean flag = leaseholderDao.update(leaseholderDO);
        tokenService.rejectToken(token);
        if(flag) {
            return Result.success("ok", "创建数据成功");
        }else {
            return Result.fail("数据更新错误");
        }
    }

}
