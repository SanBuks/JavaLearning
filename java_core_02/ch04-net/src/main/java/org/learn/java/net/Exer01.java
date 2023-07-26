package org.learn.java.net;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

// 例题1：客户端发送内容给服务端，服务端将内容打印到控制台上。

public class Exer01 {

    // 客户端
    @Test
    public void client() throws IOException {
        int port = 8989;
        InetAddress address = InetAddress.getByName("localhost");
        try (Socket socket = new Socket(address, port);
             OutputStream os = socket.getOutputStream()) {
            // 可能产生编码问题
            os.write("你好 socket!".getBytes());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // 服务端
    @Test
    public void server() {
        int port = 8989;
        // 接受 客户端 socket, accept 阻塞式方法
        try (ServerSocket serverSocket= new ServerSocket(port);
             Socket socket = serverSocket.accept();
             InputStream os = socket.getInputStream()) {
            System.out.println("服务端开启");
            System.out.println(socket.getInetAddress());
            int len = 0;
             byte[] buff = new byte[1024];
            // 如果 buff 的大小不够会产生截断问题
            // 采用 ByteArrayOutputStream 拼接 获取的数据
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((len = os.read(buff)) != -1) {
                // String str = new String(buff, 0, len);
                // System.out.println(str);
                baos.write(buff, 0, len);
            }
            System.out.println(baos);
            System.out.println("服务端关闭");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
