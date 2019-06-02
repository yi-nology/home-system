package com.murphyyi.homesystem.service.impl;

import com.murphyyi.homesystem.dao.HomeInfoDao;
import com.murphyyi.homesystem.dao.HouseInfoDao;
import com.murphyyi.homesystem.dao.UserInfoDao;
import com.murphyyi.homesystem.model.DO.HouseInfoDO;
import com.murphyyi.homesystem.model.DO.UserInfoDO;
import com.murphyyi.homesystem.model.VO.SearchHouseVO;
import com.murphyyi.homesystem.service.SearchHouseService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SearchHouseServiceImpl
 * @description:
 * @author: zhangyi
 * @since: 2019-05-21 05:39
 */
@Service
public class SearchHouseServiceImpl implements SearchHouseService {

    @Resource
    HouseInfoDao houseInfoDao;
    @Resource
    HomeInfoDao homeInfoDao;
    @Resource
    UserInfoDao userInfoDao;


    @Override
    public Map searchByAddress(String address, Integer page, Integer pageSize) {
        List<HouseInfoDO> houseInfoDOS = new ArrayList<>();
        if(address.equals("")){
            houseInfoDOS = houseInfoDao.getByHouse();
        }else {
            houseInfoDOS = houseInfoDao.getByHouseAddress(address, page, pageSize);
        }
        List<SearchHouseVO> list = new ArrayList<>();
        for(HouseInfoDO houseInfoDO:houseInfoDOS){
            SearchHouseVO s = getInfo(houseInfoDO);
            if(s.getNumber()!=0){
                list.add(s);
            }
        }

        Map<String, Object> result = new HashMap<>();

        result.put("page", page);
        result.put("data", list);
        return result;
    }

    private Integer getHomeInfoListByHouseId(Long houseId){
        return homeInfoDao.getEmptyNumberByHouseId(houseId);
    }

    private UserInfoDO getUserInfoListByHouseId(Long userId){
        return userInfoDao.getOneByUid(userId).get(0);
    }

    private SearchHouseVO getInfo(HouseInfoDO houseInfoDO){
        UserInfoDO userInfoDO = getUserInfoListByHouseId(houseInfoDO.getLandId());
        Integer count = getHomeInfoListByHouseId(houseInfoDO.getHouseId());
        return new SearchHouseVO(userInfoDO.getNickName(),houseInfoDO.getHouseAddress(),userInfoDO.getPhone(),count,houseInfoDO.getHouseName());
    }
}
