package com.murphyyi.homesystem.service;

import com.murphyyi.homesystem.model.VO.HomePriceTableVO;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: HomePriceService
 * @description:
 * @author: zhangyi
 * @since: 2019-05-28 20:36
 */
public interface HomePriceService {
    /** 
    * @Description:  
    * @Param1: uid
    * @Param2: date
    * @Param3: page
    * @Param4: pageSize 
    * @return: java.util.List 
    * @Author: zhangyi
    * @Date: 2019-05-29 
    */
    List getInfo(Long uid, LocalDate date, Integer page, Integer pageSize);

    /** 
    * @Description:  
    * @Param1: homePriceTableVO 
    * @return: java.util.List 
    * @Author: zhangyi
    * @Date: 2019-05-29 
    */
    Map getDetail(HomePriceTableVO homePriceTableVO);
    /** 
    * @Description:  
    * @Param1: uid
    * @Param2: date 
    * @return: java.lang.Integer 
    * @Author: zhangyi
    * @Date: 2019-05-29 
    */
    Integer getTotal(Long uid, LocalDate date);
    /** 
    * @Description:  
    * @Param1: id 
    * @return: java.lang.Boolean 
    * @Author: zhangyi
    * @Date: 2019-05-29 
    */
    Boolean del(Integer id);
    /** 
    * @Description:  
    * @Param1: homePriceTableVO 
    * @return: java.lang.Boolean 
    * @Author: zhangyi
    * @Date: 2019-05-29 
    */
    Boolean update(HomePriceTableVO homePriceTableVO);
}
