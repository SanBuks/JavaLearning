package org.learn.java.net;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

// 例题2：客户端发送文件给服务端，服务端将文件保存在本地。
public class Exer02 {
    private final int port = 8990;
    // 客户端
    @Test
    public void client() throws IOException {
        InetAddress address = InetAddress.getByName("localhost");
        File file = new File("peppa_pig.jpg");
        if (!file.exists()) {
            System.out.println("文件不存在!");
            return;
        }

        try (Socket socket = new Socket(address, port);
             OutputStream os = socket.getOutputStream();
             FileInputStream fis = new FileInputStream(file)) {
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = fis.read(buff)) != -1) {
                os.write(buff, 0, len);
            }
            System.out.println("发送完成");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // 服务端
    @Test
    public void server() {
        File file = new File("receive_image.jpg");
        // 接受 客户端 socket, accept 阻塞式方法
        try (ServerSocket serverSocket= new ServerSocket(port);
             Socket socket = serverSocket.accept();
             InputStream os = socket.getInputStream();
             FileOutputStream fos = new FileOutputStream(file)) {
            System.out.println("客户端开启");
            System.out.println(socket.getInetAddress());
            int len = 0;
            byte[] buff = new byte[1024];
            while ((len = os.read(buff)) != -1) {
                fos.write(buff, 0, len);
            }
            System.out.println("文件接受完毕");
            System.out.println("客户端关闭");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
