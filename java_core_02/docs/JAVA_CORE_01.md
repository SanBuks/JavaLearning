# 01. File
## 1.1 在内存中创建对象
- public File(String pathname)
- public File(String parent, String child)
- public File(File parent, String child)

## 1.2 常用方法
- public String getName(): 获取文件名称
- public String getPath(): 获取相对路径
- public String getAbsolutePath(): 获取绝对路径
- public File getAbsoluteFile(): 获取绝对路径表示的文件
- public String getParent(): 获取上层文件目录路径。若无，返回null
- public long length():获取文件长度（即：字节数）。不能获取目录的长度。
- public long lastModified():获取最后一次的修改时间，毫秒值
列出目录的下一级
* public String[] list() ：返回一个String数组，表示该File目录中的所有子文件或目录。
* public File[] listFiles() ：返回一个File数组，表示该File目录中的所有的子文件或目录。
