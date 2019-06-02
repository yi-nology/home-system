package com.murphyyi.homesystem.model.DO;

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
public class ContractTableDO {
    String homeName;
    String houseName;
    Long contractId;
    String content;
    LocalDate start;
    LocalDate end;
    String name;
    Integer date;
    Integer rate;
}
