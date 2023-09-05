package org.learn.java.stream.c_buffstream;

import org.junit.jupiter.api.Test;

import java.io.*;

public class BufferedIOStream {

    @Test
    void BufferedIOStreamTest() {
        File sourceFile = new File("peppa_pig.jpg");
        File targetFile = new File("peppa_pig_copy.jpg");

        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(targetFile);
             BufferedInputStream bis = new BufferedInputStream(fis);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            byte[] buff = new byte[1024];
            int count;
            while ((count = bis.read(buff)) != -1) {
                bos.write(buff, 0, count);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
