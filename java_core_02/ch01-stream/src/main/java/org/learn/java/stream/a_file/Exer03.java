package org.learn.java.stream.a_file;

//遍历指定文件目录下的所有文件的名称，包括子文件目录中的文件。
//拓展1：删除指定文件目录及其下的所有文件
//拓展2：计算指定文件目录占用空间的大小

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.*;

public class Exer03 {
    // 递归打印所有文件名
    public void printFileName(File file) {
        if (file.isFile()) {
            System.out.println(file.getName());
            return ;
        }
        var files = file.listFiles();
        if (files == null) {
            System.out.println("获取下级文件错误！");
            return ;
        }
        for (var item : files) {
            printFileName(item);
        }
    }

    // 层次遍历删除
    public void deleteDirectory(File file) {
        final String kMessage = "删除失败";
        if (file.isFile()) {
            if (!file.delete())
                System.out.println(kMessage);
            return;
        }

        // 从底层至上层删除目录
        Stack<File> dirs = new Stack<>();
        // BFS-queue
        Queue<File> queue = new ArrayDeque<>();
        queue.add(file);
        while (!queue.isEmpty()) {
            var size = queue.size();
            for (int i = 0; i < size; ++i) {
                var dir = queue.poll(); assert(dir != null);
                var files = dir.listFiles(File::isFile); assert(files != null);
                for (var item : files) {
                    if (!item.delete())
                        System.out.println(kMessage);
                }
                dirs.add(dir);
                var subDirs = dir.listFiles(File::isDirectory); assert(subDirs != null);
                Collections.addAll(queue, subDirs);
            }
        }
        while (!dirs.empty()) {
            var dir = dirs.pop();
            if (!dir.delete()) System.out.println(kMessage);
        }
    }

    // 层次遍历获取文件大小
    public long getDirectorySize(File file) {
        if (file.isFile()) {
            return file.length();
        }

        long sum = 0;
        Queue<File> queue = new ArrayDeque<>();
        queue.add(file);
        while (!queue.isEmpty()) {
            var size = queue.size();
            for (int i = 0; i < size; ++i) {
                var dir = queue.poll(); assert(dir != null);
                var files = dir.listFiles(File::isFile); assert(files != null);
                sum += Arrays.stream(files).mapToLong(File::length).sum();
                var subDirs = dir.listFiles(File::isDirectory); assert(subDirs != null);
                Collections.addAll(queue, subDirs);
            }
        }
        return sum;
    }

    @Test
    public void test() {
        File file = new File("C:\\Users\\SanBu\\Desktop\\DeleteTestFile");
//        deleteDirectory(file);
//        printFileName(file);
//        System.out.println(getDirectorySize(file));
    }
}
