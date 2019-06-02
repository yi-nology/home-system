package com.murphyyi.homesystem.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.TextParseException;

/**
 * @ClassName: GetIP
 * @description: 获得ip
 * @author: zhangyi
 * @since: 2019-02-28 17:28
 */
public class GetIP {
    private static final Logger log = LoggerFactory.getLogger(GetIP.class);
    public static Record[]  getRealIpV4(String url) throws TextParseException {
//        InetAddress address = InetAddress.getByName("baidu.com");
//        System.out.println(address.getHostAddress());

        //查询域名对应的IP地址
        Lookup lookup = new Lookup(url);
        lookup.run();
        if (lookup.getResult() != Lookup.SUCCESSFUL){
            log.error("ERROR: ",lookup.getErrorString());
            return null;
        }
        return lookup.getAnswers();
    }
}
