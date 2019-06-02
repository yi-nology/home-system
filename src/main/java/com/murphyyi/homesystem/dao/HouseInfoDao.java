package com.murphyyi.homesystem.dao;

import com.murphyyi.homesystem.model.DO.HouseInfoDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @ClassName: HouseInfoDao
 * @description: 房屋信息
 * @author: zhangyi
 * @since: 2019-04-21 22:14
 */
public interface HouseInfoDao {
    @Select("SELECT * FROM house_info WHERE state = 1")
    List<HouseInfoDO> getAll();

    @Insert("INSERT INTO house_info(house_id,house_name,house_address,land_id) VALUES(#{houseId}, #{houseName}, #{houseAddress}, #{landId})")
    Boolean insert(HouseInfoDO houseInfoDO);

    @Update("UPDATE house_info SET house_name=#{houseName},house_address=#{houseAddress},land_id=#{landId} WHERE house_id=#{houseId}")
    Boolean update(HouseInfoDO houseInfoDO);

    @Select("SELECT * FROM house_info WHERE house_id=#{houseId} AND state = 1")
    List<HouseInfoDO> getOnebyHouseId(Long houseId);

    @Select("SELECT * FROM house_info WHERE land_id=#{userId} AND state = 1")
    List<HouseInfoDO> getOneByUserId(Long userId);

    @Select("SELECT * FROM house_info WHERE LIKE home_name='#{houseName}%' AND state = 1 ")
    List<HouseInfoDO> getOneByHouseName(String houseName);

    @Select("SELECT * FROM house_info WHERE house_address LIKE '%${houseAddress}%' AND state = 1 limit #{page} , #{pageSize}  ")
    List<HouseInfoDO> getByHouseAddress(String houseAddress, Integer page, Integer pageSize);

    @Select("SELECT * FROM house_info WHERE state = 1 limit 0 , 10  ")
    List<HouseInfoDO> getByHouse();

    @Update("UPDATE house_info SET state = 0 WHERE house_id=#{houseId} ")
    Boolean del(HouseInfoDO houseInfoDO);

}
