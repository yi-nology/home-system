package com.murphyyi.homesystem.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @ClassName: unionDaoTest
 * @description:
 * @author: zhangyi
 * @since: 2019-05-24 01:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class unionDaoTest {
    @Resource
    UnionDao u;

    @Test
    void getinfo(){
        Object o =u.getHomeTable(178541218110636032L,0,10);
        System.out.println(o);
    }

}