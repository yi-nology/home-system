package com.murphyyi.homesystem.dao;

import com.murphyyi.homesystem.model.DO.UserInfoDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @ClassName: UserInfoDao
 * @description:
 * @author: zhangyi
 * @since: 2019-04-23 20:39
 */
public interface UserInfoDao {
    //    Long uid;
//    String nickName;
//    String identityId;
//    String phone;
//    String headerUrl;

    @Select("SELECT * FROM users_info WHERE uid = #{uid}")
    List<UserInfoDO> getOneByUid(Long uid);

    @Select("SELECT * FROM users_info WHERE nick_name LIKE '#{nickName}%'")
    List<UserInfoDO> getByNickName(String nickName);

    @Select("SELECT * FROM users_info WHERE phone LIKE '#{phone}%'")
    List<UserInfoDO> getByPhone(String phone);

    @Insert("INSERT INTO users_info(uid,nick_name,identity_id,phone,header_url, address) VALUES(#{uid}, #{nickName}, #{identityId}, #{phone},#{headerUrl},#{address})")
    Boolean  insert(UserInfoDO userInfoDO);

    @Update("UPDATE users_info SET identity_id=#{identityId},nick_name=#{nickName},phone=#{phone},header_url=#{headerUrl}, address=#{address} WHERE uid =#{uid} ")
    Boolean update(UserInfoDO userInfoDO);

}
