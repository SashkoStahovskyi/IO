
package com.stahovskyi.fileanalizer;
import com.stahocvskiy.fileanalyzer.FileAnalyzer;
import com.stahocvskiy.starter.FileStatistics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileAnalyzerITest {

    private static final String CONTENT = "this story is about a fox! mister fox? yes exactly,mister fox. about him, this story,";
    private static final String WORD = "fox";
    private FileAnalyzer fileAnalyzer;
    private File path;

    @BeforeEach
    void before() {
        fileAnalyzer = new FileAnalyzer();
        path = new File(new File("Fox.txt").getAbsolutePath());
    }

    @DisplayName("the test analyzer Analyzes The File By The Number Of The Specified Word")
    @Test
    public void testAnalyzerAnalyzesTheFileByTheNumberOfTheSpecifiedWord() {
        int expectCount = 3;
        FileStatistics fileStatistics = fileAnalyzer.analyzer(WORD, path);
        int actualCount = fileStatistics.getCount();

        assertEquals(expectCount, actualCount);
    }

    @DisplayName("the test analyzer Analyzes The File And Filter Sentence By Specified Symbols")
    @Test
    public void testAnalyzerAnalyzesTheFileAndFilterContentOnSentenceBySpecifiedSymbols() {
        String expect = "[this story is about a fox!,  mister fox?,  yes exactly,mister fox.]";
        FileStatistics fileStatistics = fileAnalyzer.analyzer(WORD, path);
        List<String> actual = fileStatistics.getSentences();

        assertEquals(expect, actual.toString());
    }

    @DisplayName("test analyzer Throw NullPointerException When Word Is Null")
    @Test
    public void testAnalyzerThrowNullPointerExceptionWhenWordIsNull() {

        Assertions.assertThrows(NullPointerException.class, () -> {
            fileAnalyzer.analyzer(null, path);
        });
    }

    @DisplayName("test analyzer Throw NullPointerException When Path Is Null")
    @Test
    public void testAnalyzerThrowNullPointerExceptionWhenPathIsNull() {

        Assertions.assertThrows(NullPointerException.class, () -> {
            fileAnalyzer.analyzer(WORD, null);
        });
    }

    @DisplayName("test readContent Accept Path To File And Read Content From This File")
    @Test
    public void testReadContentAcceptPathAndReadContentFromThisFile() {
        String actualContent = fileAnalyzer.readContent(path);

        assertEquals(CONTENT, actualContent);
    }

    @DisplayName("test readContent Throw RuntimeException When Cant Raed Content By Path")
    @Test
    public void testReadContentThrowRuntimeExceptionWhenCantReedContentByPath() {
        File notCorrectPath = new File("notCorrectPath");

        Assertions.assertThrows(RuntimeException.class, () -> {
            fileAnalyzer.readContent(notCorrectPath);
        });
    }
}




