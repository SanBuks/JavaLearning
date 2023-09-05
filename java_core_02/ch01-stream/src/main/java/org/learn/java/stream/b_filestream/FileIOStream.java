package org.learn.java.stream.b_filestream;

import org.junit.jupiter.api.Test;

import java.io.*;

// 文件字节流
public class FileIOStream {

    @Test
    // 拷贝一个文件文本或非文本文件
    void fileCopyTest() {
        File sourceFile = new File("peppa_pig.jpg");
        File targetFile = new File("peppa_pig_copy.jpg");
        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(targetFile, false)) {
            byte[] buff = new byte[1024];
            int count;
            while((count = fis.read(buff)) != -1) {
                fos.write(buff, 0, count);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
