package com.stahocvskiy.starter;

import java.util.List;

public class FileStatistics {

    private final int wordCount;
    private final List<String> sentences;
    private final String specifiedWord;

    public FileStatistics(String specifiedWord, List<String> sentences, int wordCount) {
        this.specifiedWord = specifiedWord;
        this.wordCount = wordCount;
        this.sentences = sentences;
    }

    public int getCount() {
        return wordCount;
    }

    public List<String> getSentences() {
        return sentences;
    }

    public String getSpecifiedWord() {
        return specifiedWord;
    }
}
