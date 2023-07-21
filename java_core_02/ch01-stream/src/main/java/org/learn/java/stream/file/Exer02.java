package org.learn.java.stream.file;

import org.junit.jupiter.api.Test;

import java.io.File;

// 判断指定目录下是否有后缀名为.jpg的文件，如果有，就输出该文件名称
public class Exer02 {

    @Test
    public void exer() {
       File file = new File("text.txt");
       System.out.println(file.getAbsoluteFile().getAbsolutePath());
    }

//    提示：File类提供了文件过滤器方法(拓展)
//    public String[] list(FilenameFilter filter)
}
