package com.murphyyi.homesystem.dao;

import com.murphyyi.homesystem.model.DO.HomeInfoDO;
import com.murphyyi.homesystem.model.DO.HouseInfoDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @ClassName: HomeInfoDao
 * @description:
 * @author: zhangyi
 * @since: 2019-04-23 20:36
 */
public interface HomeInfoDao {
//    Long homeId;
//    //房屋名称
//
//    String homeName;
//    //房屋价钱ID
//
//    Long priceId;
//    //房屋备注
//
//    String remarks;
//    //房屋面积
//
//    Integer homeSize;

    @Select("SELECT * FROM home_info WHERE state=1 AND home_id=#{homeId} limit #{page} , #{pageSize} ")
    List<HomeInfoDO> getOneByHomeId(Long homeId, Integer page, Integer pageSize);

    @Select("SELECT * FROM home_info WHERE state=1 AND home_name=#{homeName} limit #{page} , #{pageSize} ")
    List<HomeInfoDO> getByHomeName(String homeName, Integer page, Integer pageSize);

    @Select("SELECT * FROM home_info WHERE state=1 AND price_id=#{priceId} limit #{page} , #{pageSize}  ")
    List<HomeInfoDO> getByPriceId(Long priceId, Integer page, Integer pageSize);

    @Select("SELECT * FROM home_info WHERE state=1 AND house_id=#{houseId} limit #{page} , #{pageSize}  ")
    List<HomeInfoDO> getByHouseId(Long houseId, Integer page, Integer pageSize);

    @Insert("INSERT INTO home_info(home_id,price_id, house_id,home_name,remarks,home_size) VALUES(#{homeId}, #{priceId}, #{houseId}, #{homeName},#{remarks},#{homeSize})")
    Boolean insert(HomeInfoDO homeInfoDO);

    @Update("UPDATE home_info SET home_name=#{homeName},price_id=#{priceId},remarks=#{remarks},home_size=#{homeSize} WHERE home_id=#{homeId}")
    Boolean update(HomeInfoDO homeInfoDO);

    @Select("SELECT COUNT(house_id) FROM home_info WHERE state=1 AND house_id=#{houseId}")
    Integer getAllNumberByHouseId(Long houseId);

    @Select("SELECT COUNT(house_id) FROM home_info WHERE state=1 AND house_id=#{houseId} AND home_empty=1")
    Integer getEmptyNumberByHouseId(Long houseId);

    @Update("UPDATE home_info SET state = 0 WHERE home_id=#{homeId} ")
    Boolean del(Long homeId);
}
