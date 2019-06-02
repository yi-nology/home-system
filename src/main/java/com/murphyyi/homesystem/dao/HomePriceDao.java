package com.murphyyi.homesystem.dao;

import com.murphyyi.homesystem.model.DO.HomePriceDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @ClassName: HomePriceDao
 * @description:
 * @author: zhangyi
 * @since: 2019-04-23 20:38
 */
public interface HomePriceDao {
//    // id
//
//    Long homeId;
//
//    //房屋价钱ID
//
//    Long priceId;
//
//
//    //房屋价格
//
//    Integer homePrice;
//
//    //水费单价
//
//    Integer waterPrice;
//
//    //电费单价
//    Integer electricPrice;
//
//    //网络单价
//
//    Integer netPrice;
//
//    //备注价格
//
//    Integer remarkPrice;

    @Select("SELECT * FROM home_price WHERE price_id=#{priceId} ")
    HomePriceDO getOneByPriceId(Long priceId);

    @Select("SELECT * FROM home_price WHERE home_id=#{homeId} ORDER BY date DESC")
    List<HomePriceDO> getOneByHomeId(Long homeId);

    @Insert("INSERT INTO home_price(price_id,price,water,electric,net,remark_price,date) VALUES(#{priceId}, #{price}, #{water},#{electric},#{net},#{remarkPrice},#{date})")
    Boolean insert(HomePriceDO homePriceDO);

    @Update("UPDATE home_price SET price=#{price},water=#{water},electric=#{electric},net=#{net},remark_price=#{remarkPrice},date=#{date} WHERE price_id=#{priceId}")
    Boolean update(HomePriceDO homePriceDO);

    @Update("UPDATE home_price SET state = 0 WHERE price_id=#{priceId} ")
    Boolean del(Long priceId);

}
