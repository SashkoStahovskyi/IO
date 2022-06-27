package com.stahocvskiy.fileanalyzer;

import com.stahocvskiy.starter.FileStatistics;
import org.assertj.core.util.VisibleForTesting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileAnalyzer {

    private static final Pattern SENTENCE_PATTERN = Pattern.compile("((?<=[.?!]))");

    public FileStatistics analyzer(String word, File path) {

        if (word == null) {
            throw new NullPointerException("Provide a valid word !");
        }
        if (path == null) {
            throw new NullPointerException("Provide a valid path !");
        }
        String contentResult = readContent(path);
        List<String> sentence = splitIntoSentence(contentResult);
        List<String> filterSentence = filter(sentence, word);
        int count = countWord(word, sentence);

        return new FileStatistics(word, filterSentence, count);
    }

    @VisibleForTesting
    public int countWord(String word, List<String> splitSentence) {
        Pattern pattern = Pattern.compile("\\b" + word + "\\b");
        int count = 0;
        for (String string : splitSentence) {
            Matcher matcher = pattern.matcher(string);
            while (matcher.find()) {
                count++;
            }
        }
        return count;
    }

    @VisibleForTesting
    public String readContent(File path) {
        String content;
        File file = new File(String.valueOf(path));
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            content = new String(fileInputStream.readAllBytes());
        } catch (IOException exception) {
            throw new RuntimeException(" Cant Read By Path !");
        }
        return content;
    }

    @VisibleForTesting
    public List<String> splitIntoSentence(String content) {
        List<String> list = new ArrayList<>();
        String[] sentences = SENTENCE_PATTERN.split(content);
        int i = 0;
        while (i < sentences.length - 1) {
            list.add(sentences[i]);
            i++;
        }
        return list;
    }

    @VisibleForTesting
    public List<String> filter(List<String> list, String word) {
        List<String> resultList = new ArrayList<>();
        for (String string : list) {
            if (string.contains(word)) {
                resultList.add(string);
            }
        }
        return resultList;
    }
}



