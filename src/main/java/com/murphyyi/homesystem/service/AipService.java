package com.murphyyi.homesystem.service;

import com.murphyyi.homesystem.model.VO.IdentityVO;

/**
 * @ClassName: AipService
 * @description:
 * @author: zhangyi
 * @since: 2019-04-20 11:40
 */
public interface AipService {
    /** 
    * @Description: 身份证件信息 
    * @Param1: client 
    * @return: com.murphyyi.homesystem.utils.Result 
    * @Author: zhangyi
    * @Date: 2019-04-20 
    */
    IdentityVO idCard(String frontUrl, String backUrl) throws Exception;
}
