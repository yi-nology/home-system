package com.murphyyi.homesystem.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName: AuthenticateVO
 * @description:
 * @author: zhangyi
 * @since: 2019-05-30 05:48
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class AuthenticateVO {
    String frontUrl;
    String backUrl;
}
