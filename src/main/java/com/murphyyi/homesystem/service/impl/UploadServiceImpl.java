package com.murphyyi.homesystem.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.murphyyi.homesystem.service.UploadService;
import com.murphyyi.homesystem.utils.OSSClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: UploadServiceImpl
 * @description:
 * @author: zhangyi
 * @since: 2019-04-28 02:20
 */
@Slf4j
@Service
@PropertySource(value = "classpath:alioss.properties")
public class UploadServiceImpl implements UploadService {

    // endpoint

    @Value(value = "${endpoint}")
    private String endpoint;
    // accessKey

    @Value("${accessKeyId}")
    private String accessKeyId;
    @Value("${accessKeySecret}")
    private String accessKeySecret;

    // 空间

    @Value("${bucketName}")
    private String bucketName;

    // 文件存储目录

    @Value("${filedir}")
    private String filedir;

    @Override
    public Map<String, Object> updateHead(MultipartFile file) throws OSSException {
        if (file == null || file.getSize() <= 0) {
            throw new OSSException("file不能为空");
        }
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        OSSClientUtil ossClient = new OSSClientUtil(client, filedir, bucketName);
        String name = ossClient.uploadImg2Oss(file);
        String imgUrl = ossClient.getImgUrl(name);
//        String[] split = imgUrl.split("\\?");
        Map<String, Object> res = new HashMap<>();
        res.put("url",imgUrl);
        client.shutdown();
        return res;
    }
}
