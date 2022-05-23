package com.stahovskyi.fileanalizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.stahocvskiy.fileanalizer.FileAnalyzer.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class FileAnalyzerTest {

    String content = "this story is about a fox! mister fox? yes exactly. about him, this story,";
    String word = "fox";

    @Test
    @DisplayName("test GetPath Inner Path From Array Work Correctly")
    public void testGetInnerPathFromArrayWorkCorrectly() {
        String[] array = new String[2];
        array[0] = "C:\\Users\\Admin\\IdeaProjects\\FileAnalizerProject\\Fox.txt";
        array[1] = "Fox";
        String expectPath = "C:\\Users\\Admin\\IdeaProjects\\FileAnalizerProject\\Fox.txt";

        File path = getInnerPath(array);
        String actualPath = path.getAbsolutePath();
        assertEquals(expectPath, actualPath);
    }

    @Test
    @DisplayName("test getPath throw NullPointerException When Path Is Null")
    public void testGetPathThrowNullPointerExceptionPathIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            File file = getInnerPath(null);
        });
    }

    @Test
    @DisplayName("test Get Word From Array Work  Correctly")
    public void testGetWordFromArrayWorkCorrectly() {
        String[] array = new String[2];
        array[0] = "C:\\Users\\Admin\\IdeaProjects\\FileAnalizerProject\\Fox.txt";
        array[1] = "Fox";
        String wordExpect = "Fox";

        String wordActual = getWord(array);
        assertEquals(wordExpect, wordActual);
    }

    @Test
    @DisplayName("test Get Word ThrowNull Pointer Exception When Array Is Null")
    public void testGetWordThrowNullPointerExceptionWhenArrayIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            String word = getWord(null);
        });
    }

    @Test
    @DisplayName("test Calculate Word Work Correctly")
    public void testCalculateWordWorkCorrectly() {
        int expect = 2;
        int actual = calculateWord(word, content);
        assertEquals(expect, actual);
    }

    @Test
    @DisplayName("test Read Content Work Correctly")
    public void testReadContentWorkCorrectly() throws IOException {
        File path = new File("C:\\Users\\Admin\\IdeaProjects\\FileAnalizerProject\\Fox.txt");
        InputStream inputStream = new FileInputStream(path);

        String actualContent = readContent(inputStream, path);
        assertEquals(content, actualContent);
    }

    @Test
    @DisplayName("test Split Sentence Work Correctly")
    public void testSplitSentenceWorkCorrectly() {
        List<String> expected = List.of("this story about fox,  mister fox,  yes exactly");
        assertEquals(expected, splitSentence(content));
    }

    @Test
    @DisplayName("testFilterSentenceWorkCorrectly")
    public void testFilterSentenceWorkCorrectly() {
        List<String> content = List.of("this fox", "tiger", "mouse", "here fox");
        List<String> expected = List.of("this fox", "here fox");

        assertEquals(expected,filterSentence(content, word));
    }
}
