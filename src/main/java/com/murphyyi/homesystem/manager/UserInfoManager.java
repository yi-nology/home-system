package com.murphyyi.homesystem.manager;

import com.murphyyi.homesystem.dao.UserInfoDao;
import com.murphyyi.homesystem.model.BO.UserSearchBO;
import com.murphyyi.homesystem.model.DO.UserInfoDO;

import java.util.List;

/**
 * @ClassName: UserInfoManager
 * @description:
 * @author: zhangyi
 * @since: 2019-05-05 02:06
 */
public interface UserInfoManager {

    /** 
    * @Description: 获得用户信息 
    * @Param1: userSearchBO 
    * @return: java.util.List<com.murphyyi.homesystem.model.DO.UserInfoDO> 
    * @Author: zhangyi
    * @Date: 2019-05-05 
    */
    List<UserInfoDO> getByCondition(String condition, String type);

    /** 
    * @Description: 更新或创建
    * @Param1: userInfoDO 
    * @return: java.lang.Long 
    * @Author: zhangyi
    * @Date: 2019-05-05 
    */
    Long updateOrSave(UserInfoDO userInfoDO);
}
