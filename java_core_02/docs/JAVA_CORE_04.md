# 01. 网络基本概念
## 1.1 三要素
- IP(标识网络设备): IPv4 4 个字节, IPv6 16 个字节, 192.168 为局域网地址, 127.0.0.1 为回路地址, 域名, DNS 解析
- 端口号(标识进程): 2 个字节, 公认端口 0~1023, 注册端口 1024~49151, 动态/私有端口 49152~65535
- 网络通信协议(制定连接通信的规则): OSI 参考模型, TCP/IP 参考模型
## 1.2 DNS 解析流程
## 1.3 TCP/IP 模型
## 1.4 TCP 协议
- 传输控制协议 (Transmission Control Protocol)
- 建立TCP连接, 形成基于字节流的传输数据通道, 可以大数据量的传输
- 三次握手方式, 点对点通信, 是可靠的, 存在重发机制
- 需要释放资源, 开销大
## 1.5 UDP 协议
- 用户数据报协议(User Datagram Protocol)
- 每个数据报的大小限制在 64K 内
- 不建立连接, 不保证完整性, 不可靠
- 无需释放资源, 开销小, 通信效率高
## 1.6 三次握手
- 第一次握手: seq=x, SYN=1, 客户端处于同步已发送状态(不含应用层数据)
- 第二次握手: seq=y, ACK=x+1, 服务端处于同步已接收状态(不含应用层数据)
- 第三次握手: ACK=y+1, seq=x+1, 客户端处于连接已建立状态, 服务器收到报文后进入连接已建立状态(含应用层数据)
- 问题: 
  - 为什么要有 seq?
  - 为什么不能两次握手? 
  - 服务端如何产生 seq?
## 1.7 四次挥手

# 02. InetAddress
## 创建
- public static InetAddress getLocalHost()
- public static InetAddress getByName(String host)
- public static InetAddress getByAddress(byte[] addr)
## 常用方法 
- public String getHostAddress(): 返回 IP 地址字符串（以文本表现形式）
- public String getHostName(): 获取此 IP 地址的主机名
- public boolean isReachable(int timeout): 测试是否可以达到该地址

# 03 套接字
## 3.1 通信模型
- Server:
  - ServerSocket(int port) 监听窗口 
  - Socket accept() 程序阻塞, 接受建立连接请求, 返回应答建立连接
  - getOutputStream(), getInputStream() 数据双向传递
  - 关闭 socket
- Client:
  - Socket(InetAddress address, int port) 
  - 发送连接请求, 建立连接
  - getOutputStream(), getInputStream() 数据双向传递
  - 关闭 socket
## 3.2 注意事项
- ByteArrayOutputStream 汇总接受的文本数据防止截断产生乱码
- socket.shutdownOutput() 注意及时关闭输出, 防止通信陷入僵持
## 3.3 

## URL
```java
String uri = "http://www.baidu.com";
try {
    // 打开 url 
    URL url = new URL(uri);
    InputStream is = url.openStream();
    Scanner sc = new Scanner(is, "UTF-8");
    while(sc.hasNextLine()) {
        System.out.println(sc.nextLine());
    }
} catch (Exception e) {
    e.printStackTrace();
}
```

# 03. Socket
## 3.1 流程
- 