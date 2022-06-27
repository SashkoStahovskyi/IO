package com.stahocvskiy.starter;

import com.stahocvskiy.fileanalyzer.FileAnalyzer;
import java.io.File;
import java.util.List;

public class Starter {

    public static void main(String[] args) {

        File path = new File("Fox.txt");
        File innerPath = new File(path.getAbsolutePath());
        String word = "fox";


        FileAnalyzer fileAnalyzer = new FileAnalyzer();
        FileStatistics fileStatistics = fileAnalyzer.analyzer(word, innerPath);

        printWord(fileStatistics.getSpecifiedWord());

        printCountWord(fileStatistics.getCount());

        printSentence(fileStatistics.getSentences());
    }

    private static void printWord(String specifiedWord) {
        System.out.println(" Specified Word -> " + specifiedWord);
    }

    private static void printCountWord(int count) {
        System.out.println(" In text -> " + count + " specified word !");
    }

    private static void printSentence(List<String> filterSentence) {
        System.out.println(" All sentence with specified word -> " + filterSentence);
    }
}
