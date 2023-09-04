package org.learn.java.stream.a_file;

import org.junit.jupiter.api.Test;

import java.io.File;

public class FileCreate {
    @Test
    // 路径创建文件
    public void filePathCreate() {
        // 绝对路径创建文件: 盘符 + 文件路径
        File absoulteFile = new File("D:/test.txt");
        System.out.println(absoulteFile.getAbsolutePath());

        // 相对路径创建文件: 文件名 + 类型后缀
        // @Test: module 根目录下
        // main: project 根目录下
        File relativeFile = new File("test.txt");
        System.out.println(relativeFile.getAbsolutePath());

        // 绝对路径创建文件夹: 注意无类型后缀
        File absoluteDir = new File("D:/test");
        System.out.println(absoluteDir.getAbsolutePath());
    }
    @Test
    // 指定父目录创建文件
    public void fileParentCreate() {
        // 指定父目录 创建文件
        // 绝对
        File file01 = new File("D:/io", "test.txt");
        System.out.println(file01.getAbsolutePath());
        // 相对
        File file02 = new File("docs", "test.txt");
        System.out.println(file02.getAbsolutePath());

        // 指定父目录创建目录
        // 绝对
        File dir01 = new File("D:/docs", "test");
        System.out.println(dir01.getAbsolutePath());
        // 相对
        File dir02 = new File("docs", "test");
        System.out.println(dir02.getAbsolutePath());

        // parent参数 可以为 File 对象
        File file03 = new File(dir01, "test.txt");
        System.out.println(file03.getAbsolutePath());
        File dir03 = new File(dir02, "test");
        System.out.println(dir03.getAbsolutePath());
    }
}
