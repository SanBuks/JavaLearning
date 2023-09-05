package org.learn.java.stream.e_datastream;

import org.junit.jupiter.api.Test;

import java.io.*;

public class ObjectStream {

    @Test
    void ObjectStreamTest() {
        File file = new File("data.txt");
        try (FileOutputStream fos = new FileOutputStream(file);
             FileInputStream fis = new FileInputStream(file);
             DataOutputStream dos = new DataOutputStream(fos);
             DataInputStream dis = new DataInputStream(fis)) {
            dos.writeUTF("123test");
            dos.flush();

            String str = dis.readUTF();
            System.out.println(str);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void ClassObjectStreamTest() {
        File file = new File("data.txt");
        try (FileOutputStream fos = new FileOutputStream(file);
             FileInputStream fis = new FileInputStream(file);
             ObjectOutputStream dos = new ObjectOutputStream(fos);
             ObjectInputStream dis = new ObjectInputStream(fis)) {
            dos.writeUTF("123test");
            dos.flush();

            String str = dis.readUTF();
            System.out.println(str);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void ClassObjectStreamRead() {

    }

    @Test
    void ClassObjectStreamWrite() {

    }
}

// Serializable 是标识接口, 标志可序列化
class User implements Serializable {

    @Serial
    private static final long serialVersionUID = -8560156143466882275L;

    private Long id;
    private String name;

    public User() {}

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
