package org.learn.java.stream.b_filestream;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// 文件字符流
public class FileWR {
    @Test
    // 每次读取一个字符, char 以 UTF-16 形式表示
    void fileReadSimpleTest() {
        File file = new File("poem.txt");
        try (FileReader fr = new FileReader(file)) {
            int c;
            while ((c = fr.read()) != -1) {
                // 如果截断 '𐐷' \U10437 (\uD801\uDC37) 只打印高字节则表示为 ?
                System.out.print((char)c);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    // 每次读取多个字符, 用 buff 缓存
    void fileReadBuffTest() {
        File file = new File("poem.txt");
        try (FileReader fr = new FileReader(file)) {
            char[] buff = new char[1024];
            int count;
            while ((count = fr.read(buff)) != -1) {
                System.out.println(count);
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
        try (FileReader fr = new FileReader(sourceFile);
             FileWriter fw = new FileWriter(targetFile, false)) {
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
