package org.learn.java.stream.e_datastream;

import org.junit.jupiter.api.Test;

import java.io.*;

public class DataStream {

    @Test
    void DataStreamTest() {
        File file = new File("data.txt");
        try (FileOutputStream fos = new FileOutputStream(file);
             FileInputStream fis = new FileInputStream(file);
             DataOutputStream dos = new DataOutputStream(fos);
             DataInputStream dis = new DataInputStream(fis)) {
            char c = '牛';
            dos.writeChar(c);
            c = dis.readChar();
            System.out.println(c);
            dos.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
