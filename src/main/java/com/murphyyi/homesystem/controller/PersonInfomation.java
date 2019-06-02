package com.murphyyi.homesystem.controller;

import com.murphyyi.homesystem.manager.CapacityMapper;
import com.murphyyi.homesystem.manager.LeaseUserManager;
import com.murphyyi.homesystem.model.DataMapper;
import com.murphyyi.homesystem.model.DO.IdentityDO;
import com.murphyyi.homesystem.model.VO.AuthenticateVO;
import com.murphyyi.homesystem.model.VO.IdentityVO;
import com.murphyyi.homesystem.model.BO.LeaseSearchBO;
import com.murphyyi.homesystem.model.DO.LeaseholderDO;
import com.murphyyi.homesystem.model.VO.LeaseholderVO;
import com.murphyyi.homesystem.model.BO.idCard.IdCardMapper;
import com.murphyyi.homesystem.service.TokenService;
import com.murphyyi.homesystem.service.VerifyService;
import com.murphyyi.homesystem.utils.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: personInfo
 * @description:
 * @author: zhangyi
 * @since: 2019-04-22 15:25
 */
@Slf4j
@RestController
@Api(tags = "身份信息采集")
@RequestMapping(value = "/capacity")
public class PersonInfomation {

    private final CapacityMapper capacityMapper;

    private final LeaseUserManager leaseUserManager;

    private final
    VerifyService verifyService;

    private final
    TokenService tokenService;

    private final
    HttpServletRequest request;

    public PersonInfomation(CapacityMapper capacityMapper, LeaseUserManager leaseUserManager, TokenService tokenService, HttpServletRequest request, VerifyService verifyService) {
        this.capacityMapper = capacityMapper;
        this.leaseUserManager = leaseUserManager;
        this.tokenService = tokenService;
        this.request = request;
        this.verifyService = verifyService;
    }

    @RequestMapping(value = {"getAuthenticateInfo/{id}"}, method = RequestMethod.GET)
    Result getAuthenticate(@PathVariable(value = "id") Long id) {
        Object obj = verifyService.getData(request);
        if (obj == null) {
            return Result.failErr();
        }
        IdentityDO identityDO = capacityMapper.getCertify(id);
        IdentityVO identityVO = IdCardMapper.instance.DOtoVO(identityDO);
        return Result.success(identityVO, "获取数据成功");
    }

    @RequestMapping(value = {"getAuthenticateByToken/{token}"}, method = RequestMethod.GET)
    Result getAuthenticateByToken(@PathVariable(value = "token") String token) {
        Object u = tokenService.verificationToken(token, String.class);
        if (u == null) {
            return Result.fail("链接失效");
        }
        String uid = u + "";
        Long id = Long.parseLong(uid);
        IdentityDO identityDO = capacityMapper.getCertify(id);
        IdentityVO identityVO = IdCardMapper.instance.DOtoVO(identityDO);
        return Result.success(identityVO, "获取数据成功");
    }

    @RequestMapping(value = {"getIdInfo"}, method = RequestMethod.POST)
    Result setInfo(@RequestBody AuthenticateVO authenticateVO) {
        IdentityVO identityVO = capacityMapper.setCertify(authenticateVO.getFrontUrl(), authenticateVO.getBackUrl());
        if (null == identityVO) {
            return Result.fail("获取数据失败");
        }
        return Result.success(identityVO, "获取数据成功");
    }

    @RequestMapping(value = {"setInfo/{token}"}, method = RequestMethod.POST)
    Result setInfo(@RequestBody IdentityVO identityVO, @PathVariable(value = "token") String token) {
        Object u = tokenService.verificationToken(token, String.class);
        if (u == null) {
            return Result.fail("链接失效");
        }
        if (null == identityVO) {
            return Result.fail("获取数据失败");
        }
        tokenService.rejectToken(token);
        return Result.success(identityVO, "获取数据成功");
    }

    @RequestMapping(value = {"createToken/{uid}"}, method = RequestMethod.GET)
    Result create( @PathVariable(value = "uid") String uid) {
        Map map = new HashMap();
        map.put("id",uid);
        String  s = tokenService.generateToken(map,Map.class, 1800);
        return Result.success(s, "获取数据成功");
    }

    @RequestMapping(value = {"setAuthenticateInfo"}, method = RequestMethod.GET)
    Result setAuthenticate(@RequestParam String frontUrl, @RequestParam String backUrl) {
        IdentityVO identityVO = capacityMapper.setCertify(frontUrl, backUrl);
        if (null == identityVO) {
            return Result.fail("获取数据失败");
        }
        return Result.success(identityVO, "获取数据成功");
    }

    @RequestMapping(value = {"saveOrUpdateAuthenticate"}, method = RequestMethod.POST)
    Result saveOrUpdateAuthenticate(@ModelAttribute IdentityVO identityVO, @RequestParam Long uid) {

        if (null == identityVO) {
            return Result.fail("获取数据失败");
        }
        IdentityDO identityDO = IdCardMapper.instance.VOtoDO(identityVO);
        if (uid != null && !uid.equals(0L)) {
            identityDO.setId(uid);
        }
        Long id = capacityMapper.updateOrSave(identityDO);
        return Result.success(id, "获取数据成功");
    }

    @RequestMapping(value = {"getLeaseInfo"}, method = RequestMethod.GET)
    Result getPreson(@ModelAttribute LeaseSearchBO leaseSearchBO) {
        List<LeaseholderDO> leaseholderDOList = leaseUserManager.getByCondition("", "");
        return Result.success(leaseholderDOList, "查询成功");
    }

    @RequestMapping(value = {"saveOrUpdateLeaseInfo"}, method = RequestMethod.POST)
    Result saveOrUpdatePreson(@ModelAttribute LeaseholderVO leaseholderVO) {

        if (null == leaseholderVO) {
            return Result.fail("获取数据失败");
        }
        LeaseholderDO leaseholderDO = DataMapper.instance.VOtoDO(leaseholderVO);
        Long uid = leaseUserManager.updateOrSave(leaseholderDO);
        return Result.success(uid + "", "获取数据成功");
    }
}
