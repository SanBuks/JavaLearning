package org.learn.java.net;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetAddress;

public class CreateInetAddress {

    @Test
    public void createTest() throws IOException {
        // 创建方式
        // 通过 获取本地地址创建
        var address1 = InetAddress.getLocalHost(); // 127.0.0.1
        // 通过 地址/域名创建
        var address2 = InetAddress.getByName("192.168.0.1");
        var address3 = InetAddress.getByName("www.baidu.com");

        System.out.println(address1);
        System.out.println(address2);
        System.out.println(address3);

        // 常用方法
        System.out.println(address3.getHostName());    // 获取域名
        System.out.println(address3.getHostAddress()); // 获取 IP 地址
    }
}
