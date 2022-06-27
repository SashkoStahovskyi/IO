package com.stahovskyi.fileanalizer;
import com.stahocvskiy.fileanalyzer.FileAnalyzer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileAnalyzerTest {

    private static final String CONTENT = "this story is about a fox! mister fox? yes exactly,mister fox. about him, this story,";
    private static final String WORD = "fox";
    private FileAnalyzer fileAnalyzer;

    @BeforeEach
    void before() {
        fileAnalyzer = new FileAnalyzer();
    }

    @DisplayName("test filter Filer On Sentence Content By Specified Word")
    @Test
    public void testFilterFilterOnSentenceContentBySpecifiedWord() {
        List<String> sentence = List.of("this fox", "tiger huge live in jungle ", "mouse small eat cheese", "here fox");
        List<String> expected = List.of("this fox", "here fox");
        List<String> actual = fileAnalyzer.filter(sentence, WORD);

        assertEquals(expected, actual);
    }

    @DisplayName("test splitIntoSentence Accept Content And Split Content By Specified Symbols")
    @Test
    public void testSplitIntoSentenceAcceptContentAndSplitContentBySpecifiedSymbols() {
        String expected = "[this story is about a fox!,  mister fox?,  yes exactly,mister fox.]";
        String actualContent = String.valueOf(fileAnalyzer.splitIntoSentence(CONTENT));
        assertEquals(expected, actualContent);
    }

    @DisplayName("test countWord Calculate Total Counts Of Specified Words In Content")
    @Test
    public void testCountWordCalculateTotalCountsOfSpecifiedWordsInContent() {
        int expect = 3;
        List<String> splitSentence = fileAnalyzer.splitIntoSentence(CONTENT);
        int actual = fileAnalyzer.countWord(WORD, splitSentence);
        assertEquals(expect, actual);
    }
}
