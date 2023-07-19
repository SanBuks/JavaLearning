package org.learn.java.stream.file;

import org.junit.jupiter.api.Test;

import java.io.File;

public class FileFunc {

    @Test
    public void fileFuncTest() {
        File file = new File("test.txt");
        System.out.println(file.getName()); // 文件名
        System.out.println(file);           // 调用 getPath()
        System.out.println(file.getPath()); // 返回文件路径

        System.out.println(file.getAbsolutePath()); // 绝对路径名
        System.out.println(file.getAbsoluteFile()); // 返回以绝对路径表示的文件,

        System.out.println(file.length());
        System.out.println(file.lastModified());
    }

}
