package com.stahovskyi.filemanager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import static com.stahocvskiy.filemanager.FileManager.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileManagerITest {

    @BeforeEach
    public void before() {

        new File("TestIO").mkdir();  // count file & dirs
        new File("TestIO\\SourceFileForCopy.txt").mkdir(); // copy file source
        new File("TestIO\\FolderForCopyFileHere").mkdir();  // copy file dest
        new File("TestIO\\DirForCopy").mkdir();  // copy dir source
        new File("TestIO\\FolderForCopyFileHere").mkdir();  // copy dir dest
        new File("TestIO\\moveSource\\FolderForMoveSource\\moveFile.txt").mkdir(); // move file source
        new File("TestIO\\moveDestination").mkdir();  // move file dest
        new File("TestIO\\moveSource\\DirForMove").mkdir();  // move dirs source
        new File("TestIO\\moveDestination").mkdir();  // move dirs dest
    }

    @Test
    public void testCountFilesWorkCorrectly() {
        int expected = 7;
        int actual = countFiles("TestIO");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("countFiles Throw Null Point Exception When File Not Exist")
    public void testCountFilesThrowNullPointExceptionWhenCountNotExist() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            countFiles("this file not exist !");
        });
    }

    @Test
    public void testCountDirsWorkCorrectly() {
        int expected = 7;
        int actual = countDirs("TestIO");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("countDirs Throw Null Point Exception When Dirs Not Exist")
    public void testCountDirsThrowNullPointExceptionWhenDirsNotExist() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            countDirs("this dirs not exist !");
        });
    }

    @Test
    public void testCopyFileWorkCorrectly() {
        String from = "TestIO\\SourceFileForCopy.txt";
        String to = "TestIO\\FolderForCopyFileHere";
        String countFilesFrom = "TestIO";

        assertEquals(6, countFiles(countFilesFrom));
        copy(from, to);
        assertEquals(7, countFiles(countFilesFrom));
    }

    @Test
    public void testCopyDirsWorkCorrectly() {
        String from = "TestIOTaskIO\\DirForCopy";
        String to = "TestIO\\FolderForCopyFileHere";
        String countDirsFrom = "TestIO";

        assertEquals(7, countDirs(countDirsFrom));
        copy(from, to);
        assertEquals(7, countDirs(countDirsFrom));
    }

    @Test
    @DisplayName("test Move File Or Dirs Work Correctly")
    public void testMoveFileOrDirsWorkCorrectly() {
        String from = "TestIO\\moveSource\\FolderForMoveSource\\moveFile.txt";
        String to = "TestIO\\moveDestination";
        int countSourceBeforeMove = countFiles("TestIO\\moveSource\\FolderForMoveSource"); // check source count
        int countDestBeforeMove = countFiles("TestIO\\moveDestination"); // check dest count

        assertEquals(1,countSourceBeforeMove);
        assertEquals(0,countDestBeforeMove);
        move(from,to);
        int countSourceAfterMove = countFiles("TestIO\\moveSource\\FolderForMoveSource"); // check source count after move
        int countDestAfterMove = countFiles("TestIO\\moveSource\\FolderForMoveSource");  // check dest after move
        assertEquals(0,countSourceAfterMove);
        assertEquals(1,countDestAfterMove);
    }
}

