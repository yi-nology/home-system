package com.murphyyi.homesystem.dao;

import com.murphyyi.homesystem.model.DO.HouseInfoDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * @ClassName: HouseManagerDaoTest
 * @description:
 * @author: zhangyi
 * @since: 2019-04-21 22:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HouseManagerDaoTest {
    @Resource
    HouseInfoDao houseInfoDao;
    @Test
    public void test1(){
        HouseInfoDO houseInfoDO = new HouseInfoDO(123L,123L,"章鱼你","北京市朝阳");
        houseInfoDao.insert(houseInfoDO);
        System.out.println(toJSONString(houseInfoDao.getAll()));
    }
}