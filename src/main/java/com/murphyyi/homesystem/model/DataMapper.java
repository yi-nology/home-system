package com.murphyyi.homesystem.model;

import com.murphyyi.homesystem.model.DO.ContractDO;
import com.murphyyi.homesystem.model.DO.ContractTableDO;
import com.murphyyi.homesystem.model.DO.HouseInfoDO;
import com.murphyyi.homesystem.model.DO.LeaseholderDO;
import com.murphyyi.homesystem.model.DO.LeaseholderTableDO;
import com.murphyyi.homesystem.model.DO.UserInfoDO;
import com.murphyyi.homesystem.model.DO.HomeTableDO;
import com.murphyyi.homesystem.model.VO.ContractTableVO;
import com.murphyyi.homesystem.model.VO.ContractVO;
import com.murphyyi.homesystem.model.VO.HomeTableVO;
import com.murphyyi.homesystem.model.VO.HouseInfoVO;
import com.murphyyi.homesystem.model.VO.HouseVO;
import com.murphyyi.homesystem.model.VO.LeaseholderTableVO;
import com.murphyyi.homesystem.model.VO.LeaseholderVO;
import com.murphyyi.homesystem.model.VO.UserInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @ClassName: DataMapper
 * @description:
 * @author: zhangyi
 * @since: 2019-04-23 01:30
 */
@Mapper
public interface DataMapper {
    DataMapper instance = Mappers.getMapper(DataMapper.class);

    @Mappings({
            @Mapping(source = "uid", target = "uid"),
            @Mapping(source = "rentName", target = "rentName"),
            @Mapping(source = "rentPhone", target = "rentPhone"),
            @Mapping(source = "work", target = "work"),
            @Mapping(source = "contractId", target = "contractId"),
            @Mapping(source = "homeId", target = "homeId"),
            @Mapping(source = "identityId", target = "identityId"),
    }
    )
    LeaseholderDO VOtoDO(LeaseholderVO leaseholderVO);

    @Mappings({
            @Mapping(source = "house_id", target = "houseId"),
            @Mapping(source = "land_id", target = "landId"),
            @Mapping(source = "house_name", target = "houseName"),
            @Mapping(source = "house_address", target = "houseAddress")
    }
    )
    HouseInfoDO VOtoDO(HouseInfoVO houseInfoVO);

    @Mappings({
            @Mapping(source = "houseId", target = "house_id"),
            @Mapping(source = "landId", target = "land_id"),
            @Mapping(source = "houseName", target = "house_name"),
            @Mapping(source = "houseAddress", target = "house_address")
    }
    )
    HouseInfoVO DOtoVO(HouseInfoDO houseInfoDO);

    @Mappings({
            @Mapping(source = "all", target = "home_number"),
            @Mapping(source = "empty", target = "home_empty"),
            @Mapping(source = "houseInfoDO.houseId", target = "house_id"),
            @Mapping(source = "houseInfoDO.houseName", target = "house_name"),
            @Mapping(source = "houseInfoDO.houseAddress", target = "house_address")
    }
    )
    HouseVO MaketoVO(Integer all, Integer empty, HouseInfoDO houseInfoDO);

    @Mappings({
            @Mapping(source = "name", target = "nickName"),
            @Mapping(source = "identity_id", target = "identityId"),
            @Mapping(source = "phone", target = "phone"),
            @Mapping(source = "address", target = "address"),
            @Mapping(source = "header_url", target = "headerUrl"),
    })
    UserInfoDO VOtoDO(UserInfoVO userInfoVO);

    @Mappings({
            @Mapping(source = "nickName", target = "name"),
            @Mapping(source = "identityId", target = "identity_id"),
            @Mapping(source = "phone", target = "phone"),
            @Mapping(source = "address", target = "address"),
            @Mapping(source = "headerUrl", target = "header_url"),
    })
    UserInfoVO DOtoVO(UserInfoDO userInfoDO);

    @Mappings({
            @Mapping(source = "priceId", target = "price_id"),
            @Mapping(source = "homeId", target = "home_id"),
            @Mapping(source = "houseId", target = "house_id"),
            @Mapping(source = "homeName", target = "name"),
            @Mapping(source = "homeSize", target = "size"),
            @Mapping(source = "remarks", target = "remarks"),
            @Mapping(source = "price", target = "price"),
            @Mapping(source = "water", target = "water"),
            @Mapping(source = "electric", target = "electric"),
            @Mapping(source = "net", target = "net"),
            @Mapping(source = "remarkPrice", target = "remark_price"),
            @Mapping(source = "homeEmpty", target = "home_empty"),
            @Mapping(source = "rcId", target = "rc_id"),
            @Mapping(source = "delectric", target = "delectric"),
            @Mapping(source = "dwater", target = "dwater"),
    })
    HomeTableVO DOtoVO(HomeTableDO homeTableDO);


    @Mappings({
            @Mapping(source = "price_id", target = "priceId"),
            @Mapping(source = "home_id", target = "homeId"),
            @Mapping(source = "house_id", target = "houseId"),
            @Mapping(source = "name", target = "homeName"),
            @Mapping(source = "size", target = "homeSize"),
            @Mapping(source = "remarks", target = "remarks"),
            @Mapping(source = "price", target = "price"),
            @Mapping(source = "water", target = "water"),
            @Mapping(source = "electric", target = "electric"),
            @Mapping(source = "net", target = "net"),
            @Mapping(source = "remark_price", target = "remarkPrice"),
            @Mapping(source = "home_empty", target = "homeEmpty"),
            @Mapping(source = "rc_id", target = "rcId"),
            @Mapping(source = "delectric", target = "delectric"),
            @Mapping(source = "dwater", target = "dwater"),
    })
    HomeTableDO VOtoDO(HomeTableVO homeTableVO);

    @Mappings({
            @Mapping(source = "contract_id", target = "contractId"),
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "rate", target = "rate"),
            @Mapping(source = "start", target = "start"),
            @Mapping(source = "end", target = "end"),
    })
    ContractDO VOtoDO(ContractVO contractVO);

    @Mappings({
            @Mapping(source = "homeName", target = "home_name"),
            @Mapping(source = "houseName", target = "house_name"),
            @Mapping(source = "contractId", target = "contract_id"),
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "start", target = "start"),
            @Mapping(source = "end", target = "end"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "rate", target = "rate"),
    })
    ContractTableVO DOtoVO(ContractTableDO contractTableDO);

    @Mappings({
            @Mapping(source = "uid", target = "uid"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "homeName", target = "home_name"),
            @Mapping(source = "houseId", target = "house_id"),
            @Mapping(source = "homeId", target = "home_id"),
            @Mapping(source = "work", target = "work"),
            @Mapping(source = "back", target = "id_card_back"),
            @Mapping(source = "front", target = "id_card_front"),
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "contractId", target = "contract_id"),
    })
    LeaseholderTableVO DOtoVO(LeaseholderTableDO leaseholderTableDO);

}
