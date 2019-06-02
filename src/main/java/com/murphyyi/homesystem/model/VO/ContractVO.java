package com.murphyyi.homesystem.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class ContractVO {
    Long contract_id;
    String content;
    LocalDate start;
    LocalDate end;
    Integer date;
    Integer rate;
}
