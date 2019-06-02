package com.murphyyi.homesystem.service.impl;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.ocr.AipOcr;
import com.murphyyi.homesystem.constants.IdCardEnum;
import com.murphyyi.homesystem.model.VO.IdentityVO;
import com.murphyyi.homesystem.model.BO.idCard.IdCardInfoBO;
import com.murphyyi.homesystem.model.BO.idCard.IdCardInfoBackBO;
import com.murphyyi.homesystem.model.BO.idCard.IdCardMapper;
import com.murphyyi.homesystem.service.AipService;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import static com.alibaba.fastjson.JSON.toJSONString;
import static com.murphyyi.homesystem.utils.UrlUtils.ExchangeImageByFile;

/**
 * @ClassName: AipServiceImpl
 * @description:
 * @author: zhangyi
 * @since: 2019-04-20 14:38
 */
@Slf4j
@Service
@PropertySource(value = "classpath:baidu.properties")
public class AipServiceImpl implements AipService {
    @Value("${baidu.app.id}")
    private String APP_ID;
    @Value("${baidu.api.key}")
    private String API_KEY;
    @Value("${baidu.secret.key}")
    private String SECRET_KEY;

    @Override
    public IdentityVO idCard(String frontUrl, String backUrl) throws Exception {
        // 传入可选参数调用接口
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        options.put("detect_risk", "false");

        //身份证正面
        byte[] imageFront = ExchangeImageByFile(frontUrl);
        JSONObject front = client.idcard(imageFront, IdCardEnum.front.getValue(), options);
        JSONObject idInfo= front.getJSONObject("words_result");
        String frontInfo = idInfo.toString();
        IdCardInfoBO idCardInfoBo = JSON.parseObject(frontInfo, IdCardInfoBO.class);
        IdentityVO identityVO = IdCardMapper.instance.toVO(idCardInfoBo);

        //身份证反面
        byte[] imageBack = ExchangeImageByFile(backUrl);
        JSONObject back = client.idcard(imageBack, IdCardEnum.back.getValue(), options);
        JSONObject backIdInfo= back.getJSONObject("words_result");
        String backInfo = backIdInfo.toString();
        IdCardInfoBackBO idCardInfoBackBO = JSON.parseObject(backInfo, IdCardInfoBackBO.class);
        identityVO.setIdIssue(idCardInfoBackBO.getIssue().getWords());
        identityVO.setIdPermitStart(idCardInfoBackBO.getPermitStart().getWords());
        identityVO.setIdPermitEnd(idCardInfoBackBO.getPermitEnd().getWords());
        identityVO.setFrontUrl(frontUrl);
        identityVO.setBackUrl(backUrl);

        log.info("认证信息={}",toJSONString(identityVO));
        return  identityVO;
    }
}
