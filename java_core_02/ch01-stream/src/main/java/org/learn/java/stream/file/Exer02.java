package org.learn.java.stream.file;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

// 判断指定目录下是否有后缀名为.jpg的文件，如果有，就输出该文件名称
public class Exer02 {

    public void printJpgFile1(String pathDir) {
        File file = new File(pathDir);
        var fileNames = file.list();
        if (fileNames == null) {
            System.out.println("不是目录路径或出错");
            return;
        }
        Arrays.stream(fileNames).forEach(item -> {
            if (item.endsWith(".jpg"))
                System.out.println(item);
        });
    }
    public void printJpgFile2(String pathDir) {
        File file = new File(pathDir);
        var fileNames = file.list((dir, name) -> name.endsWith(".jpg"));
        if (fileNames == null) {
            System.out.println("不是目录路径或出错");
            return;
        }
        for (var item : fileNames) {
            System.out.println(item);
        }
    }

    @Test
    public void exer() {
        printJpgFile1("C:\\Users\\San\\Pictures");
        printJpgFile2("C:\\Users\\San\\Pictures");
    }
}
