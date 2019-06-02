package com.murphyyi.homesystem.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

/**
 * @ClassName: ContractTableDO
 * @description:
 * @author: zhangyi
 * @since: 2019-05-26 05:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractTableVO {
    String home_name;
    String house_name;
    String contract_id;
    String content;
    LocalDate start;
    LocalDate end;
    String name;
    Integer date;
    Integer rate;
}
