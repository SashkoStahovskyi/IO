package com.stahocvskiy.filemanager;
import java.io.*;

public class FileManager {

    // public static int countFiles(String path) - принимает путь к папке,
    // возвращает количество файлов в папке и всех подпапках по пути
    public static int countFiles(String path) {
        File filePath = new File(path);
        int count = 0;
        count = fileCounter(filePath);
        return count;
    }

    //public static int countDirs(String path) - принимает путь к папке,
    // возвращает количество папок в папке и всех подпапках по пути
    public static int countDirs(String path) {
        File filePath = new File(path);
        int count = 0;
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
            throw new IllegalArgumentException("File or directory is not found");
        }
    }

    // метод переносит файлы и папки
    public static void move(String from, String to) {
        File path = new File(from);
        copy(from, to);
        deleteSource(path);
    }

    // ======================= //

    private static void deleteSource(File file) {
        File[] list = file.listFiles();
        checkNull(list);
        for (File files : list) {
            if (files.isDirectory()) {
                File deleteDir = new File(files + File.separator + files.getName());
                deleteSource(deleteDir);
            }
            files.delete();
        }
        file.delete();
    }

    private static int dirsCounter(File path) {
        int count = 0;
        int result = 0;
        File[] filePath = path.listFiles();
        checkNull(filePath);
        for (File f : filePath) {
            if (f.isDirectory()) {
                count++;
                result += dirsCounter(f);
            }
        }
        return result + count;
    }

    private static int fileCounter(File path) {
        int count = 0;
        File[] file = path.listFiles();
        checkNull(file);
        for (File f : file) {
            if (f.isDirectory()) {
                count += fileCounter(f);
            } else if (f.isFile()) {
                count++;
            }
        }
        return count;
    }

    private static void writeContent(String to, String content) throws IOException {
        File path = new File(to);
        OutputStream outputStream = new FileOutputStream(path);
        outputStream.write(Integer.parseInt(content));
        outputStream.close();
    }

    public static String readContent(String path) throws IOException {
        File pathFile = new File(path);
        InputStream inputStream = new FileInputStream(pathFile);
        int fileLength = (int) pathFile.length();
        byte[] array = new byte[fileLength];
        inputStream.read(array);
        inputStream.close();
        return new String(array);
    }

    private static void checkNull(File[] file) {
        if (file == null) {
            throw new NullPointerException(" File or dirs not exist  !");
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
