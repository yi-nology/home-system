package com.murphyyi.homesystem.model.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

/**
 * @ClassName: ContractDO
 * @description: 合同信息
 * @author: zhangyi
 * @since: 2019-04-21 20:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractDO {
    Long contractId;
    String content;
    LocalDate start;
    LocalDate end;
    Integer date;
    Integer rate;
}
