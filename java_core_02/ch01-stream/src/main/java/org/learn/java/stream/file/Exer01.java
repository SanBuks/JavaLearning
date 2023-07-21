package org.learn.java.stream.file;

import org.junit.jupiter.api.Test;

import java.io.File;

//创建一个与hello.txt文件在相同文件目录下的另一个名为abc.txt文件
public class Exer01 {
    @Test
    public void Exer() {
        File file = new File("text1.txt");
        File fileAnother = new File(file.getAbsoluteFile().getParent(), "text2.txt");
        System.out.println(file.getAbsolutePath());
        System.out.println(fileAnother.getAbsolutePath());
    }
}
