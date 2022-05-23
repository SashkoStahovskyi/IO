package com.stahocvskiy.fileanalizer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class FileAnalyzer {

    public static void main(String[] args) throws IOException {

        fileAnalyzer(args);
    }

    public static void fileAnalyzer(String[] content) throws IOException {
        File filePath = getInnerPath(content); // get path
        String specifiedWord = getWord(content);  // get word

        checkPathNotNull(filePath);   // check path to Null before reading
        InputStream inputStream = new FileInputStream(filePath);   // prepare reading
        String contentResult = readContent(inputStream, filePath); // read content from path

        int count = calculateWord(specifiedWord, contentResult); // calculate specified word
        System.out.println("In text " + count + " word " + specifiedWord);

        List<String> splitSentence = splitSentence(contentResult);  // split content to array sentence
        List<String> filterSentence = filterSentence(splitSentence, specifiedWord);  // filter sentence array
        System.out.println("All sentence with specified word " + specifiedWord + " ->  " + filterSentence);
    }

    public static File getInnerPath(String[] array) {
        checkArrayIsNotNull(array);
        for (String path : array) {
            return new File(path);
        }
        return null;
    }

    public static String getWord(String[] args) {
       checkArrayIsNotNull(args);
        for (int i = 0; i < args.length; i++) {
            if (i == 1) {
                return args[i];
            }
        }
        return null;
    }

    public static int calculateWord(String word, String content) {
        int count = 0;
        for (int i = 0; i < content.length(); i++) {
            int currentValue = 0;
            while (currentValue < word.length() && word.charAt(currentValue) == content.charAt(currentValue + i) && i + currentValue < content.length()) {
                currentValue++;  // algorithm Morrison - Prat
            }
            if (currentValue == word.length()) {
                count++;
            }
        }
        return count;
    }

    public static String readContent(InputStream content, File path) throws IOException {
        byte[] arrayContent = new byte[(int) path.length()];
        int contentValue;
        int index = 0;
        while (true) {
            contentValue = content.read();
            if (contentValue == -1) {
                break;
            }
            arrayContent[index] = (byte) contentValue;
            index++;
        }
        return new String(arrayContent);
    }

    public static List<String> splitSentence(String content) {
        List<String> list = new ArrayList<>();
        String[] sentence = content.split("\\.|!|\\?");
        for (int i = 0; i < sentence.length - 1; i++) {
            list.add(sentence[i]);
        }
        return list;
    }

    public static List<String> filterSentence(List<String> list, String word) {
        List<String> result = new ArrayList<>();
        for (String str : list) {
            if (str.contains(word)) {
                result.add(str);
            }
        }
        return result;
    }

    private static void checkPathNotNull(File path) {
        if (path == null) {
            throw new NullPointerException(" Path is not exist ! ");
        }
    }

    private static void checkArrayIsNotNull(String [] array ) {
        if (array == null) {
            throw new NullPointerException (" array is null! ");
        }
    }
}



