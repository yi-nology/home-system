package com.murphyyi.homesystem.manager;

import com.murphyyi.homesystem.model.DO.IdentityDO;
import com.murphyyi.homesystem.model.VO.IdentityVO;

/**
 * @ClassName: CapacityMapper
 * @description: 认证api
 * @author: zhangyi
 * @since: 2019-04-22 15:33
 */
public interface CapacityMapper {
    /**
    * @Description: 创建认证
    * @Param1:
    * @return: com.murphyyi.homesystem.model.VO.IdentityVO
    * @Author: zhangyi
    * @Date: 2019-04-22
    */
    IdentityVO setCertify(String frontUrl, String backUrl);

    /**
    * @Description: 更新认证
    * @Param1:
    * @return: java.uitl.Long
    * @Author: zhangyi
    * @Date: 2019-04-22
    */
    Long updateOrSave(IdentityDO identityDO);

    /** 
    * @Description: 读取认证信息 
    * @Param1: id 
    * @return: com.murphyyi.homesystem.model.DO.IdentityDO
    * @Author: zhangyi
    * @Date: 2019-04-22
    */
    IdentityDO getCertify(Long id);

}
