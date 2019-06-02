package com.murphyyi.homesystem.dao;

import com.murphyyi.homesystem.model.DO.LeaseholderDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @ClassName: LeaseholderDao
 * @description: 租客信息
 * @author: zhangyi
 * @since: 2019-04-23 00:25
 */
public interface LeaseholderDao {
    @Select("SELECT * FROM leaseholder")
    List<LeaseholderDO> getAll();

    @Select("SELECT * FROM leaseholder WHERE rent_name=#{name}")
    List<LeaseholderDO> getByName(String name);

    @Select("SELECT * FROM leaseholder WHERE phone=#{phone}")
    List<LeaseholderDO> getByPhone(String phone);

    @Select("SELECT * FROM leaseholder WHERE uid=#{uid}")
    LeaseholderDO getOneByUid(Long uid);

    @Insert("INSERT INTO leaseholder(uid,rent_name,rent_phone,work,contract_id,home_id,identity_id) VALUES(#{uid}, #{rentName}, #{rentPhone}, #{work},#{contractId},#{homeId},#{identityId})")
    Boolean insert(LeaseholderDO leaseholderDO);

    @Update("UPDATE leaseholder SET rent_name=#{rentName},rent_phone=#{rentPhone},work=#{work},contract_id=#{contractId},home_id=#{homeId},identity_id=#{identityId} WHERE uid=#{uid}")
    Boolean update(LeaseholderDO leaseholderDO);

    @Update("UPDATE leaseholder SET state=0 WHERE uid=#{uid}")
    Boolean del(Long uid);
}
