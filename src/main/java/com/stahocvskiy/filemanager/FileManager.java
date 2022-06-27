package com.stahocvskiy.filemanager;

import java.io.*;

public class FileManager {

    // public static int countFiles(String path) - принимает путь к папке,
    // возвращает количество файлов в папке и всех подпапках по пути
    public static int countFiles(String path) {
        File filePath = new File(path);
        int count;
        count = fileCounter(filePath);
        return count;
    }

    //public static int countDirs(String path) - принимает путь к папке,
    // возвращает количество папок в папке и всех подпапках по пути
    public static int countDirs(String path) {
        File filePath = new File(path);
        int count;
        count = dirsCounter(filePath);
        return count;
    }

    //public static void copy(String from, String to) - метод по копированию папок и файлов.
    public static void copy(String from, String to) {
        File pathFrom = new File(from);
        File pathTo = new File(to, pathFrom.getName());
        try {
            if (pathFrom.isDirectory()) {
                pathTo.mkdirs();
                File[] files = pathFrom.listFiles();
                checkNull(files);
                for (File file : files) {
                    copy(file.getAbsolutePath(), pathTo.getAbsolutePath());
                }
            } else if (pathFrom.isFile()) {
                copyFile(pathFrom, pathTo);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(" File or directory is not found ! ");
        }
    }

    // метод переносит файлы и папки
    public static void move(String from, String to) {
        File path = new File(from);
        copy(from, to);
        deleteSource(path);
    }

    private static void deleteSource(File file) {
        File[] list = file.listFiles();
        if (list != null) {
            for (File files : list) {
                if (files.isDirectory()) {
                    File deleteDir = new File(files + File.separator + files.getName());
                    deleteSource(deleteDir);
                }
                files.delete();
            }
        }
        file.delete();
    }

    private static int dirsCounter(File path) {
        int count = 0;
        int result = 0;
        File[] filePath = path.listFiles();
        if (filePath != null) {
            for (File file : filePath) {
                if (file.isDirectory()) {
                    count++;
                    result += dirsCounter(file);
                }
            }
        }
        return result + count;
    }

    private static int fileCounter(File path) {
        int count = 0;
        File[] files = path.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    count += fileCounter(file);
                } else if (file.isFile()) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void checkNull(File[] file) {
        if (file == null) {
            throw new NullPointerException(" File not exist or you don't have permit !");
        }
    }

    private static void copyFile(File from, File to) throws IOException {
        InputStream inputContent = new FileInputStream(from);
        OutputStream outputContent = new FileOutputStream(to);
        int arrayLength = (int) from.length();
        byte[] buffer = new byte[arrayLength];
        inputContent.read(buffer);
        outputContent.write(buffer);
        inputContent.close();
        outputContent.close();
    }
}
