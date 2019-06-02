package com.murphyyi.homesystem.dao;

import com.murphyyi.homesystem.model.DO.RentConsumeDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * @ClassName: RentConsumeDao
 * @description: 房屋消耗
 * @author: zhangyi
 * @since: 2019-04-23 20:41
 */
public interface RentConsumeDao {
//    Integer water;
//    Integer electric;
//    Long homeId;
//    Long uid;
//    Date commitDate;
//    Boolean state;

    //id
    @Select("SELECT * FROM rent_consume WHERE id = #{id}")
    RentConsumeDO getOneById(Integer id);

    @Select("SELECT * FROM rent_consume WHERE price_id = #{priceId} AND date = #{date}")
    RentConsumeDO getOneByPriceIdAndDate(Long prcieId, LocalDate date);

    @Select("SELECT * FROM rent_consume WHERE price_id = #{priceId} AND date IS NULL")
    RentConsumeDO getByByPrice(Long priceId);

    @Insert("INSERT INTO rent_consume(price_id,water,electric,date,commit_date) VALUES(#{priceId}, #{water}, #{electric},#{date}, #{commitDate})")
    Boolean insert(RentConsumeDO rentConsumeDO);

    @Update("UPDATE rent_consume SET price_id=#{priceId},water=#{water},electric=#{electric},date=#{date},commit_date=#{commitDate} WHERE id =#{id}")
    Boolean update(RentConsumeDO rentConsumeDO);

    @Update("UPDATE rent_consume SET commit_date=#{commitDate} WHERE id =#{id}")
    Boolean commit(RentConsumeDO rentConsumeDO);

    @Update("UPDATE rent_consume SET state=0 WHERE id =#{id}")
    Boolean del(Integer id);
}
