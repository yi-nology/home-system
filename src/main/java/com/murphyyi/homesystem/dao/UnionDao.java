package com.murphyyi.homesystem.dao;

import com.murphyyi.homesystem.model.DO.ContractTableDO;
import com.murphyyi.homesystem.model.DO.HomePriceTableDO;
import com.murphyyi.homesystem.model.DO.HomeTableDO;
import com.murphyyi.homesystem.model.DO.LeaseholderTableDO;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * @ClassName: UnionDao
 * @description:
 * @author: zhangyi
 * @since: 2019-05-24 01:10
 */
public interface UnionDao {
    @Select("SELECT  hp.price_id, rc.id as rc_id, rc.electric as delectric, rc.water as dwater,\n" +
            "h.home_id, h.house_id,h.home_name,h.home_size,h.remarks,hp.price,hp.water,hp.electric,hp.net,hp.remark_price, h.home_empty\n" +
            "FROM home_info h \n" +
            "LEFT JOIN home_price hp ON h.price_id = hp.price_id \n" +
            "LEFT JOIN  house_info hi ON h.house_id = hi.house_id\n" +
            "LEFT JOIN rent_consume rc ON rc.price_id = h.price_id \n" +
            "WHERE rc.date IS NULL AND hi.land_id=#{uid} AND h.state=1 AND hp.state=1 LIMIT #{page},#{pageSize}")
    List<HomeTableDO> getHomeTable(Long uid, Integer page, Integer pageSize);

    @Select("SELECT count(*) \n" +
            "FROM home_info h \n" +
            "LEFT JOIN home_price hp ON h.price_id = hp.price_id \n" +
            "LEFT JOIN  house_info hi ON h.house_id = hi.house_id \n" +
            "WHERE hi.land_id=#{uid} AND h.state=1 AND hp.state=1")
    Integer getHomeTableTotal(Long uid);

    @Select("SELECT  h.home_name, hi.house_name, c.contract_id, l.uid, c.content, c.start, c.end, l.rent_name as name, c.date, c.rate\n" +
            "FROM contract c\n" +
            "LEFT JOIN  leaseholder l ON c.contract_id = l.contract_id\n" +
            "LEFT JOIN\t home_info h ON h.home_id = l.home_id\n" +
            "LEFT JOIN  house_info hi ON h.house_id = hi.house_id\n" +
            "WHERE hi.land_id=#{uid} LIMIT #{page},#{pageSize};")
    List<ContractTableDO> getContract(Long uid, Integer page, Integer pageSize);

    @Select("SELECT count(*)\n" +
            "FROM contract c\n" +
            "LEFT JOIN  leaseholder l ON c.contract_id = l.contract_id\n" +
            "LEFT JOIN\t home_info h ON h.home_id = l.home_id\n" +
            "LEFT JOIN  house_info hi ON h.house_id = hi.house_id\n" +
            "WHERE hi.land_id=#{uid}")
    Integer getContractTotal(Long uid);

    @Select("SELECT  l.uid, h.home_id, i.id, c.contract_id, h.home_name, hi.house_id, l.rent_name as name,l.work,l.rent_phone as phone,i.back_url as back,i.front_url as front\n" +
            "FROM leaseholder l\n" +
            "LEFT JOIN  contract c ON c.contract_id = l.contract_id\n" +
            "LEFT JOIN\t home_info h ON h.home_id = l.home_id\n" +
            "LEFT JOIN  house_info hi ON h.house_id = hi.house_id\n" +
            "LEFT JOIN  identity i ON l.identity_id = i.id\n" +
            "WHERE hi.land_id=#{id}  AND l.state =1  LIMIT #{page},#{pageSize};")
    List<LeaseholderTableDO> getLeaseholder(Long id, Integer page, Integer pageSize);

    @Select("SELECT  count(*) \n" +
            "FROM leaseholder l\n" +
            "LEFT JOIN  contract c ON c.contract_id = l.contract_id\n" +
            "LEFT JOIN\t home_info h ON h.home_id = l.home_id\n" +
            "LEFT JOIN  house_info hi ON h.house_id = hi.house_id\n" +
            "LEFT JOIN  identity i ON l.identity_id = i.id\n" +
            "WHERE hi.land_id=#{id}  AND l.state =1 ")
    Integer getLeaseholderTotal(Long id);

    @Select("SELECT  l.uid, h.home_id, h.home_name, hi.house_id, i.id, c.contract_id,l.rent_name as name,l.work,l.rent_phone as phone,i.back_url as back,i.front_url as front\n" +
            "FROM leaseholder l\n" +
            "LEFT JOIN  contract c ON c.contract_id = l.contract_id\n" +
            "LEFT JOIN\t home_info h ON h.home_id = l.home_id\n" +
            "LEFT JOIN  house_info hi ON h.house_id = hi.house_id\n" +
            "LEFT JOIN  identity i ON l.identity_id = i.id\n" +
            "WHERE hi.land_id=#{id} AND l.rent_name LIKE '%${name}%' AND l.state =1 LIMIT #{page},#{pageSize};")
    List<LeaseholderTableDO> getLeaseholderByName(Long id, String name, Integer page, Integer pageSize);

    @Select("SELECT count(*)\n" +
            "FROM leaseholder l\n" +
            "LEFT JOIN  contract c ON c.contract_id = l.contract_id\n" +
            "LEFT JOIN\t home_info h ON h.home_id = l.home_id\n" +
            "LEFT JOIN  house_info hi ON h.house_id = hi.house_id\n" +
            "LEFT JOIN  identity i ON l.identity_id = i.id\n" +
            "WHERE hi.land_id=#{id} AND l.rent_name LIKE '%${name}%' AND l.state = 1")
    Integer getLeaseholderTotalByName(Long id, String name);

    @Select("SELECT  hp.price_id, rc.id as rc_id, h.home_name, hi.house_name, l.rent_name as name, c.rate, rc.electric, rc.water, rc.commit_date\n" +
            "FROM leaseholder l\n" +
            "LEFT JOIN  contract c ON c.contract_id = l.contract_id\n" +
            "LEFT JOIN\t home_info h ON h.home_id = l.home_id\n" +
            "LEFT JOIN  house_info hi ON h.house_id = hi.house_id\n" +
            "LEFT JOIN home_price hp ON h.price_id = hp.price_id \n" +
            "LEFT JOIN  rent_consume rc ON rc.price_id = hp.price_id\n" +
            "WHERE c.`end` > rc.date AND rc.state = 1 AND hi.land_id = #{userId} AND rc.date BETWEEN #{date} AND #{date1} LIMIT #{page},#{pageSize};")
    List<HomePriceTableDO> getHomePriceTable(LocalDate date, LocalDate date1 , Long userId, Integer page, Integer pageSize);

    @Select("SELECT count(*)\n" +
            "FROM leaseholder l\n" +
            "LEFT JOIN  contract c ON c.contract_id = l.contract_id\n" +
            "LEFT JOIN\t home_info h ON h.home_id = l.home_id\n" +
            "LEFT JOIN  house_info hi ON h.house_id = hi.house_id\n" +
            "LEFT JOIN home_price hp ON h.price_id = hp.price_id \n" +
            "LEFT JOIN  rent_consume rc ON rc.price_id = hp.price_id\n" +
            "WHERE c.`end` > rc.date AND rc.state = 1 AND hi.land_id = #{userId} AND rc.date BETWEEN #{date} AND #{date1}")
    Integer getHomePriceTotal(LocalDate date,LocalDate date1, Long userId);
}
