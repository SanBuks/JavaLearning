package org.learn.java.stream.d_streamwr;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class StreamWR {

    @Test
    void UTF82GBK() {
        File sourceFile = new File("poem.txt");
        File targetFile = new File("poem_gbk.txt");
        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(targetFile);
             InputStreamReader isr = new InputStreamReader(fis);
             OutputStreamWriter osw = new OutputStreamWriter(fos, Charset.forName("gbk"))) {
            char[] buff = new char[1024];
            int count;
            while ((count = isr.read(buff)) != -1) {
                osw.write(buff, 0, count);
            }
            osw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void GBK2UTF8() {
        File sourceFile = new File("poem_gbk.txt");
        File targetFile = new File("poem_utf8.txt");
        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(targetFile);
             InputStreamReader isr = new InputStreamReader(fis, Charset.forName("gbk"));
             OutputStreamWriter osw = new OutputStreamWriter(fos)) {
            char[] buff = new char[1024];
            int count;
            while ((count = isr.read(buff)) != -1) {
                osw.write(buff, 0, count);
            }
            osw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
