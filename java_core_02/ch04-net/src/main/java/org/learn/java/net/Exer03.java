package org.learn.java.net;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


// 例题3：从客户端发送文件给服务端，服务端保存到本地。并返回“发送成功”给客户端。并关闭相应的连接。
public class Exer03 {
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
             InputStream is = socket.getInputStream();
             FileInputStream fis = new FileInputStream(file);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = fis.read(buff)) != -1) {
                os.write(buff, 0, len);
            }
            // 提示服务端输出关闭, 避免陷入死循环
            socket.shutdownOutput();
            System.out.println("发送完成");

            while ( (len= is.read(buff)) != -1) {
                baos.write(buff, 0, len);
            }
            System.out.println(baos);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // 服务端
    @Test
    public void server() {
        File file = new File("receive_image2.jpg");
        // 接受 客户端 socket, accept 阻塞式方法
        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket socket = serverSocket.accept();
             InputStream is = socket.getInputStream();
             OutputStream os = socket.getOutputStream();
             FileOutputStream fos = new FileOutputStream(file)) {
            System.out.println("客户端开启");
            System.out.println(socket.getInetAddress());
            int len = 0;
            byte[] buff = new byte[1024];
            while ((len = is.read(buff)) != -1) {
                fos.write(buff, 0, len);
            }
            System.out.println("文件接受完毕");
            os.write("接受完毕!".getBytes());
            System.out.println("客户端关闭");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
