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
  - public boolean delete(): 删除文件或者文件夹(文件目录内不能包含文件或者文件目录)
