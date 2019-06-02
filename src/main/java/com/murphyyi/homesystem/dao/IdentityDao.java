package com.murphyyi.homesystem.dao;

import com.murphyyi.homesystem.model.DO.IdentityDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @ClassName: IdentityDao
 * @description: 认证信息
 * @author: zhangyi
 * @since: 2019-04-21 23:29
 */
public interface IdentityDao {
    @Insert("INSERT INTO identity(id,id_name,id_code,id_nation,id_address,id_sex,id_issue,id_permit_start,id_permit_end,front_url,back_url) VALUES(#{id}, #{idName}, #{idCode}, #{idNation},#{idAddress},#{idSex},#{idIssue},#{idPermitStart},#{idPermitEnd},#{frontUrl},#{backUrl})")
    Boolean insert(IdentityDO identityDO);

    @Update("UPDATE identity SET id_name=#{idName},id_code=#{idCode},id_nation=#{idNation},id_address=#{idAddress},id_sex=#{idSex},id_issue=#{idIssue},id_permit_start=#{idPermitStart},id_permit_end=#{idPermitEnd},front_url=#{frontUrl},back_url=#{backUrl}  WHERE id =#{id}")
    Boolean update(IdentityDO identityDO);

    @Select("SELECT * FROM identity WHERE id = #{id}")
    IdentityDO getOneById(Long id);
}
