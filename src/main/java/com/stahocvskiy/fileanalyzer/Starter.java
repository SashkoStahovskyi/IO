package com.stahocvskiy.fileanalyzer;

import java.io.IOException;

public class Starter {

    public static void main(String[] args) throws IOException {

        FileAnalyzer fileAnalyzer = new FileAnalyzer();
        fileAnalyzer.analyzer(args);
    }
}
