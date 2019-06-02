package com.murphyyi.homesystem.service;

import com.aliyun.oss.OSSException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @ClassName: UploadService
 * @description:
 * @author: zhangyi
 * @since: 2019-04-28 02:18
 */
public interface UploadService {
    Map<String, Object> updateHead(MultipartFile file) throws OSSException;
}
