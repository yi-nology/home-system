package com.murphyyi.homesystem.model.BO.idCard;

import com.murphyyi.homesystem.model.DO.IdentityDO;
import com.murphyyi.homesystem.model.VO.IdentityVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @ClassName: IdCardMapper
 * @description:
 * @author: zhangyi
 * @since: 2019-04-21 00:39
 */
@Mapper
public interface IdCardMapper {
    IdCardMapper instance = Mappers.getMapper(IdCardMapper.class);
    @Mappings({
            @Mapping(source = "name.words", target = "idName"),
            @Mapping(source = "nation.words", target = "idNation"),
            @Mapping(source = "address.words", target = "idAddress"),
            @Mapping(source = "idCode.words", target = "idCode"),
            @Mapping(source = "sex.words", target = "idSex")
    }
    )
    IdentityVO toVO(IdCardInfoBO idCardInfoBO);

    @Mappings({
            @Mapping(source = "idName", target = "idName"),
            @Mapping(source = "idNation", target = "idNation"),
            @Mapping(source = "idAddress", target = "idAddress"),
            @Mapping(source = "idCode", target = "idCode"),
            @Mapping(source = "idSex", target = "idSex"),
            @Mapping(source = "idIssue", target = "idIssue"),
            @Mapping(source = "idPermitEnd", target = "idPermitEnd"),
            @Mapping(source = "idPermitStart", target = "idPermitStart")
    }
    )
    IdentityDO VOtoDO(IdentityVO identityVO);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "idName", target = "idName"),
            @Mapping(source = "idNation", target = "idNation"),
            @Mapping(source = "idAddress", target = "idAddress"),
            @Mapping(source = "idCode", target = "idCode"),
            @Mapping(source = "idSex", target = "idSex"),
            @Mapping(source = "idIssue", target = "idIssue"),
            @Mapping(source = "idPermitEnd", target = "idPermitEnd"),
            @Mapping(source = "idPermitStart", target = "idPermitStart")
    }
    )
    IdentityVO DOtoVO(IdentityDO identityVO);
}
