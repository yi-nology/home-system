package com.murphyyi.homesystem.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: UrlUtils
 * @description: Url工具包
 * @author: zhangyi
 * @since: 2019-04-20 21:03
 */
public class UrlUtils {
    /** 
    * @Description: Url转byte[] 
    * @Param1: OriginalUrl 
    * @return: byte[] 
    * @Author: zhangyi
    * @Date: 2019-04-20 
    */
    public static byte[] ExchangeImageByFile(String OriginalUrl) throws Exception {
        OriginalUrl = OriginalUrl.replaceAll("\\\\", "/");
        Matcher slashMatcher = Pattern.compile("/").matcher(OriginalUrl);
        int mIdx = 0;
        while (slashMatcher.find()) {
            mIdx++;
            //当"/"符号第三次出现的位置
            if (mIdx == 3) {
                break;
            }
        }
        int start = slashMatcher.start();
        String substring1 = OriginalUrl.substring(0, start + 1);
        String substring2 = OriginalUrl.substring(start + 1, OriginalUrl.length());
        substring2 = new String(substring2.getBytes("utf-8"));
        //new一个URL对象
        String encode = substring1 + URLEncoder.encode(substring2, "utf-8");
        encode = encode.replaceAll("%2F", "/");
        encode = encode.replaceAll("\\+", "%20");
        URL url = new URL(encode);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置请求方式为"GET"
        conn.setRequestMethod("GET");
        //超时响应时间为5秒
        conn.setConnectTimeout(5 * 1000);
        //通过输入流获取图片数据
        InputStream inStream = conn.getInputStream();
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性
        byte[] data = readInputStream(inStream);
        return data;
    }

    /** 
    * @Description: 将输入流转为字节数组 
    * @Param1: inStream 
    * @return: byte[] 
    * @Author: zhangyi
    * @Date: 2019-04-20 
    */
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }
}
