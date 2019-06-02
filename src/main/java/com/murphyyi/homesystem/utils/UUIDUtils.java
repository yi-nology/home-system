package com.murphyyi.homesystem.utils;

import java.util.Base64;
import java.util.UUID;

/**
 * @ClassName: UUIDUtils
 * @description: uuid生成器
 * @author: zhangyi
 * @since: 2019-04-17 09:14
 */
public class UUIDUtils {
    /**
    * @Description: 创建uuid
    * @Param1:
    * @return: java.lang.String
    * @Author: zhangyi
    * @Date: 2019-04-17
    */
    public static String createUUID(){
       return UUID.randomUUID().toString();
    }

    /**
    * @Description: 剔除uuid横线
    * @Param1: uuid
    * @return: java.lang.String
    * @Author: zhangyi
    * @Date: 2019-04-17
    */
    public static String UUIDRemoveWire(String uuid){
        return uuid.replaceAll("-", "");
    }

    /**
    * @Description: 压缩uuid
    * @Param1: uuid
    * @return: java.lang.String
    * @Author: zhangyi
    * @Date: 2019-04-17
    */
    /**
     * 压缩
     * @param src uuid字符串，可带有{@code -}
     * @return base64字符串，length=22
     */
    public static String compressUUID(String src) {
        UUID uuid = UUID.fromString(src);
        long msb = uuid.getMostSignificantBits();
        long lsb = uuid.getLeastSignificantBits();

        byte[] b = new byte[16];
        for (int i = 0; i < 8; i++) {
            b[i] = (byte) (msb >>> (8 * (7-i)) & 0xff);
            b[i+8] = (byte) (lsb >>> (8 * (7-i)) & 0xff);
        }

        return Base64.getEncoder().withoutPadding().encodeToString(b);
    }

    /**
     * 解压
     * @param src base64字符串，length=22
     * @return uuid字符串，{@code -}分割
     */
    public static String decompressUUID(String src) {
        byte[] b = Base64.getDecoder().decode(src);

        long msb = 0;
        long lsb = 0;
        for (int i=0; i<8; i++) {
            msb = (msb << 8) | (b[i] & 0xff);
            lsb = (lsb << 8) | (b[i+8] & 0xff);
        }

        return new UUID(msb, lsb).toString();
    }
}
