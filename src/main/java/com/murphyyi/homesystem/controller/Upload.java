package com.murphyyi.homesystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.murphyyi.homesystem.service.UploadService;
import com.murphyyi.homesystem.utils.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * @ClassName: Upload
 * @description:
 * @author: zhangyi
 * @since: 2019-04-28 01:56
 */
@Slf4j
@Api(tags = "图片上传")
@RestController
@RequestMapping(value = "/upload")
public class Upload {
    private final UploadService uploadService;

    public Upload(UploadService uploadService) {this.uploadService = uploadService;}

    @RequestMapping(value = "/headImgUpload",method = RequestMethod.POST)
    @ResponseBody
    public Map headImgUpload(@RequestParam("file") MultipartFile file) {


        try {
            Map url = uploadService.updateHead(file);
            log.info("图片路径{}", toJSONString(url));
            return url;
        } catch (Exception e) {
            return new HashMap();
        }
    }
}
