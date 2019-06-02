package com.murphyyi.homesystem.dao;

import com.murphyyi.homesystem.model.DO.ContractDO;
import com.murphyyi.homesystem.model.DO.HomeInfoDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Date;
import java.util.List;

/**
 * @ClassName: ContractDao
 * @description: 合同
 * @author: zhangyi
 * @since: 2019-05-07 02:33
 */
public interface ContractDao {
    @Select("SELECT * FROM contract WHERE contract_id=#{contractId} ")
    ContractDO getById(Long contractId);

    @Select("SELECT * FROM contract WHERE start>#{start} AND end <#{end} ")
    List<ContractDO> getByStartEnd(Date start, Date end);

    @Insert("INSERT INTO contract(contract_id, content, start, end, date, rate) VALUES(#{contractId}, #{content}, #{start}, #{end}, #{date}, #{rate})")
    Boolean insert(ContractDO contractDO);

    @Update("UPDATE contract SET content=#{content},`start`=#{start},`end`=#{end},date=#{date},rate=#{rate} WHERE contract_id=#{contractId}")
    Boolean update(ContractDO contractDO);
}
