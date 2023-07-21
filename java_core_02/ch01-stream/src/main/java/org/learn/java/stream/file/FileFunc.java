package org.learn.java.stream.file;

import org.junit.jupiter.api.Test;

import java.io.File;

public class FileFunc {

    @Test
    // 基本属性
    public void fileBasicAttributeFuncTest() {
        File file = new File("test.txt");
        System.out.println(file.getName());          // 文件名
        System.out.println(file);                    // 调用 getPath(), 返回文件路径
        System.out.println("-------------------------------");

        System.out.println(file.getAbsolutePath());  // 绝对路径名
        System.out.println(file.getAbsoluteFile());  // 返回以绝对路径表示的文件,
        System.out.println("-------------------------------");

        System.out.println(file.length());           // 返回 字节数, 目录字节数为 0
        System.out.println(file.lastModified());     // 返回 最新更新时间戳
        System.out.println("-------------------------------");

        System.out.println(file.getParent());        // 获取父目录 由于是相对路径的文件, 返回 null
        System.out.println(file.getAbsoluteFile().getParent()); // 获取父目录
    }
    @Test
    // 列出目录下所有文件
    public void fileListFuncTest() {
        File file = new File("src/../..");

        // 获取文件/目录名称
        for (String s : file.list()) {
            System.out.println(s);
        }

        // 获取文件/目录名称 (以相对路径方式)
        var list = file.listFiles();
        for (File item : list) {
            System.out.println(item);
        }
    }
    @Test
    // 重命名
    public void fileRenameFuncTest() {
        File file = new File("test.txt");
        File newFile = new File("D://abc.txt");
        var result = file.renameTo(newFile);
        System.out.println(result ? "成功" : "失败");
    }
    @Test
    public void fileJudgeFuncTest() {
        File file = new File("test.txt").getAbsoluteFile().getParentFile();

        System.out.println(file.isFile());
        System.out.println(file.isDirectory());
        System.out.println(file.exists());
        System.out.println("--------------------------------");

        System.out.println(file.isHidden());
        System.out.println(file.canRead());
        System.out.println(file.canWrite());
    }
    @Test
    public void fileModifierFuncTest() {
        File file = new File("D://io.txt");
        if (file.exists()) {
            System.out.println("文件/目录存在");
            if (file.delete()) System.out.println("删除成功");
            else System.out.println("删除失败");
        } else {
            try {
                if (file.createNewFile()) System.out.println("创建成功");
                else System.out.println("创建失败");
                if (file.delete()) System.out.println("删除成功");
                else System.out.println("删除失败");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    @Test
    public void dirModifierFuncTest() {
        File file = new File("D://io/io");
        if (file.exists()) {
            System.out.println("文件/目录存在");
            if (file.delete()) System.out.println("删除成功");
            else System.out.println("删除失败");
        } else {
            try {
                if (file.mkdirs()) System.out.println("创建成功");
                else System.out.println("创建失败");
                if (file.delete()) System.out.println("删除成功");
                else System.out.println("删除失败");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
