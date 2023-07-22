# 01. File
## 1.1 在内存中创建对象
- public File(String pathname)
- public File(String parent, String child)
- public File(File parent, String child)

## 1.2 常用方法
- File 获取基本属性函数
  - public String getName(): 获取文件名称
  - public String getPath(): 获取相对路径
  - public String getAbsolutePath(): 获取绝对路径
  - public File getAbsoluteFile(): 获取绝对路径表示的文件
  - public String getParent(): 获取上层文件目录路径, 若无, 返回null
  - public long length(): 获取文件字节数, 不能获取目录的长度
  - public long lastModified(): 获取最后一次的修改时间, 毫秒值
- File 列出下一级函数
  - public String[] list(): 返回一个String数组, 表示该File目录中的所有子文件或目录
  - public File[] listFiles(): 返回一个File数组, 表示该File目录中的所有的子文件或目录
- File 重命名函数
  - public boolean renameTo(File dest): 把文件重命名为指定的文件路径 (需要 dest 文件不存在且 dest 目录存在)
- File 判断函数
  - public boolean exists(): 此File表示的文件或目录是否实际存在
  - public boolean isDirectory(): 此File表示的是否为目录
  - public boolean isFile(): 此File表示的是否为文件
  - public boolean canRead(): 判断是否可读
  - public boolean canWrite(): 判断是否可写
  - public boolean isHidden(): 判断是否隐藏
- File 修改函数
  - public boolean createNewFile(): 创建文件, 若文件存在, 则不创建, 返回false
  - public boolean mkdir(): 创建文件目录, 如果此文件目录存在, 就不创建, 如果此文件目录的上层目录不存在, 也不创建
  - public boolean mkdirs(): 创建文件目录, 如果上层文件目录不存在, 一并创建
  - public boolean delete(): 删除文件或目录(文件目录内不能包含文件或目录)

# 02 IO流
## 2.1 概念
- 输入输出: 站在内存(输入则向内存输入, 输出则向内存外输出)
- 字节/字符流: 随着时间, 数据以字节/字符为单位被不断有序消耗和产生的队列结构
- 节点流: 直接从数据源和目标端读取数据
- 处理流: 连接到已存在的节点流/处理流上, 提供增强功能(装饰)
- 框架(4 大基类): 
  - InputStream(字节)
  - OutputStream(字节)
  - Reader(字符)
  - Writer(字符)
## 2.2 文件字符流
- 新建和关闭都需要 try-catch
- FileReader fr = new FileReader(sourceFile); 
- FileWriter fw = new FileWriter(targetFile, false);
- fw.write(byteArray, off, count);
- fr.read(byteArray);
- fw.close();
- fr.close();
## 2.3 文件字节流
- 同上, 注意字符流无法处理非文本文件, 引文字符流读取数据时会根据字符集进行转换, 写入时会发生数据丢失
