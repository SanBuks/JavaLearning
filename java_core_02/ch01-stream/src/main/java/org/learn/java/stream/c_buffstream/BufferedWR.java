package org.learn.java.stream.c_buffstream;

import org.junit.jupiter.api.Test;

import java.io.*;

public class BufferedWR {

    @Test
    void BufferedWRTest() {
        File sourceFile = new File("poem.txt");
        File targetFile = new File("poem_copy.txt");
        System.out.println(targetFile.getAbsolutePath());
        try (FileReader fr = new FileReader(sourceFile);
             FileWriter fw = new FileWriter(targetFile, false);
             BufferedReader br = new BufferedReader(fr);
             BufferedWriter bw = new BufferedWriter(fw)) {
            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }
            bw.flush(); // 一定要刷新
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
