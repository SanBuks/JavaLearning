package org.learn.java.stream.filestream;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterReader {
    @Test
    // 每次读取一个字节
    void fileReadSimpleTest() {
        File file = new File("poem.txt");
        try (FileReader fr = new FileReader(file)) {
            int c;
            while ((c = fr.read()) != -1)
                System.out.print((char)c);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    // 每次读取多个字节
    void fileReadBuffTest() {
        File file = new File("poem.txt");
        try (FileReader fr = new FileReader(file)) {
            char[] buff = new char[1024];
            int count;
            while ((count = fr.read(buff)) != -1) {
                for (int i = 0; i < count; ++i) {
                    System.out.print(buff[i]);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    // 输出一个文件
    void fileWriteTest() {
        File file = new File("output.txt");
        try (FileWriter fw = new FileWriter(file, true)) {  // 追加到文件末段
            fw.write("abc\n");
            fw.write("def\n");
            fw.write("ghi\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    // 拷贝一个文件
    void fileCopyTest() {
        File sourceFile = new File("poem.txt");
        File targetFile = new File("poem_copy.txt");
        try (FileReader fr = new FileReader(sourceFile); FileWriter fw = new FileWriter(targetFile, false)) {
            char[] buff = new char[1024];
            int count;
            while((count = fr.read(buff)) != -1) {
                fw.write(buff, 0, count);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
