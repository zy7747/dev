package com.example.system.utils;

import cn.hutool.extra.servlet.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class IPUtil {
    /**
     * 从HttpServletRequest中获取浏览器客户端的请求ip
     *
     * @param request HttpServletRequest
     * @return String ip地址
     */
    public static String getIp(HttpServletRequest request) {
        String clientIP = ServletUtil.getClientIP(request);
        String ip = "0:0:0:0:0:0:0:1".equals(clientIP) ? "127.0.0.1" : clientIP;
        ip = ip.trim();
        return ip;
    }

    /**
     * 根据ip地址查询IP所属的实际地址信息
     *
     * @param ip String  ip地址,如 223.120.23.106
     * @return String  xx省xx市xx区移动/联通/电信
     */
    public static String getAdd(String ip) throws Exception {
        try {
            InputStream ris = IPUtil.class.getResourceAsStream("/ip2region/ip2region.xdb");
            byte[] dbBinStr = FileCopyUtils.copyToByteArray(ris);
            Searcher searcher = Searcher.newWithBuffer(dbBinStr);
            //注意：不能使用文件类型，打成jar包后，会找不到文件
            String region = searcher.searchByStr(ip);

            String add = region.replace("0|", "").replace("|0", "");
            searcher.close();

            return add;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}