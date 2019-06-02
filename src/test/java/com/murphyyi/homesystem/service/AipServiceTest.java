package com.murphyyi.homesystem.service;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

/**
 * @ClassName: AipServiceTest
 * @description:
 * @author: zhangyi
 * @since: 2019-04-20 17:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AipServiceTest {

    @Autowired
    AipService aipService;

    @Value("${baidu.app.id}")
    private String APP_ID;
    @Value("${baidu.api.key}")
    private String API_KEY;
    @Value("${baidu.secret.key}")
    private String SECRET_KEY;

    @Test
    public void test1(){
        try {
            aipService.idCard("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1111486802,1870602914&fm=26&gp=0.jpg","");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void test() {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);


        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
//        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        String path = "src/test.png";
        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        try {
            System.out.println(res.toString(2));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}