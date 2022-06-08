package com.stahovskyi.fileanalizer;

import com.stahocvskiy.fileanalyzer.FileAnalyzer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FileAnalyzerTest {

    private final String CONTENT = "this story is about a fox! mister fox? yes exactly,mister fox. about him, this story,";
    private final String WORD = "fox";
    private FileAnalyzer fileAnalyzer;
    private File path;

    @BeforeEach
    void before() {
        fileAnalyzer = new FileAnalyzer();
        path = new File(new File("Fox.txt").getAbsolutePath());
    }


    @Test
    @DisplayName("test getInnerPath From Array Work Correctly")
    public void testGetInnerPathFromArrayWorkCorrectly() {
        String[] array = new String[2];
        array[0] = "C:\\Users\\Admin\\IdeaProjects\\FileAnalyzerProject\\Fox.txt";
        array[1] = "Fox";
        String expectPath = "C:\\Users\\Admin\\IdeaProjects\\FileAnalyzerProject\\Fox.txt";

        File path = fileAnalyzer.getInnerPath(array);
        String actualPath = path.getAbsolutePath();
        assertEquals(expectPath, actualPath);
    }

    @Test
    @DisplayName("test getInnerPath Throw NullPointerException When Path Is Null")
    public void testGetPathThrowNullPointerExceptionPathIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            fileAnalyzer.getInnerPath(null);
        });
    }

    @Test
    @DisplayName("test Get Word From Array Work  Correctly")
    public void testGetWordFromArrayWorkCorrectly() {
        String[] array = new String[2];
        array[0] = "C:\\Users\\Admin\\IdeaProjects\\FileAnalyzerProject\\Fox.txt";
        array[1] = "Fox";
        String wordExpect = "Fox";

        String wordActual = fileAnalyzer.getWord(array);
        assertEquals(wordExpect, wordActual);
    }

    @Test
    @DisplayName("test Get Word Throw Null Pointer Exception When Array Is Null")
    public void testGetWordThrowNullPointerExceptionWhenArrayIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            fileAnalyzer.getWord(null);
        });
    }

    @Test
    @DisplayName("test Calculate Word Work Correctly")
    public void testCalculateWordWorkCorrectly() {
        int expect = 3;
        int actual = fileAnalyzer.calculateWord(WORD, CONTENT);
        assertEquals(expect, actual);
    }

    @Test
    @DisplayName("test Read Content Work Correctly")
    public void testReadContentWorkCorrectly() throws IOException {
        InputStream inputStream = new FileInputStream(path);
        String actualContent = fileAnalyzer.readContent(inputStream, path);
        assertEquals(CONTENT, actualContent);
    }

    @Test
    @DisplayName("test Split Sentence Work Correctly")
    public void testSplitSentenceWorkCorrectly() {
        String expected = "[this story is about a fox,  mister fox,  yes exactly,mister fox]";
        String actualContent = String.valueOf(fileAnalyzer.splitSentence(CONTENT));
        assertEquals(expected, actualContent);
    }

    @Test
    @DisplayName("testFilterSentenceWorkCorrectly")
    public void testFilterSentenceWorkCorrectly() {
        List<String> sentence = List.of("this fox", "tiger huge ", "mouse small", "here fox");
        List<String> expected = List.of("this fox", "here fox");

        assertEquals(expected, fileAnalyzer.filterSentence(sentence, WORD));
    }
}
