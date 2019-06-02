package com.murphyyi.homesystem.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * @ClassName: LeaseholderNewUserVO
 * @description:
 * @author: zhangyi
 * @since: 2019-05-26 20:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaseholderNewUserVO {
    String content;
    Integer date;
    Long contract_id;
    Long home_id;
    Long house_id;
    String id_card_back;
    String id_card_front;
    String name;
    String phone;
    Integer rate;
    String work;
    List<LocalDate> startEnd;
}

