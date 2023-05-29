## 套接字
- Socket(String host, int port) 
- getOutputStream() 
- getInputStream() 
```java
try {
    // 创建空套接字, 防止建立初始连接无限超时
    Socket s = new Socket();
    // 创建新连接, 设置超时时间
    s.connect(new InetSocketAddress("time-a.nist.gov", 13), 10000);
    // 获取输入流
    InputStream is = s.getInputStream();
    // 绑定输入流
    Scanner sc = new Scanner(is, "UTF-8");
    // 输入每一行
    while (sc.hasNextLine()) {
        String str = sc.nextLine();
        System.out.println(str);
    }
    if (s.isConnected()) {
        // 关闭套接字
        s.close();
    }
} catch (InterruptedIOException ie) {
    ie.printStackTrace();
} catch (Exception e) {
    e.printStackTrace();
}
```

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