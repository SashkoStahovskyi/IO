package com.stahovskyi.filemanager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;

import static com.stahocvskiy.filemanager.FileManager.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileManagerITest {

    private static final String RESOURCES = "src\\test\\Resources\\";
    private boolean create;

    @BeforeEach
    public void before() throws IOException {

        File path = new File(RESOURCES + "File.txt");
        create = path.createNewFile();
        File path1 = new File(RESOURCES + "DirForCopyFile");
        create = path1.mkdir();
        File path2 = new File(RESOURCES + "DirForCopy");
        create = path2.mkdir();
        File path3 = new File(RESOURCES + "DirForCopy\\DirForCopy1");
        create = path3.mkdir();
        File path4 = new File(RESOURCES + "DirForCopyDirs");
        create = path4.mkdir();
        File path5 = new File(RESOURCES + "MoveSourceDir");
        create = path5.mkdir();
        File path6 = new File(RESOURCES + "MoveSourceDir\\MoveFile.txt");
        create = path6.createNewFile();
        File path7 = new File(RESOURCES + "MoveDestinationDir");
        create = path7.mkdir();
        File path8 = new File(RESOURCES + "MoveDirWithFile2");
        create = path8.mkdir();
        File path9 = new File(RESOURCES + "MoveDirWithFile2\\MoveDirWithFile");
        create = path9.mkdir();
        File path10 = new File(RESOURCES + "MoveDirWithFile2\\MoveDirWithFile\\MoveFile1.txt");
        create = path10.createNewFile();
        File path11 = new File(RESOURCES + "MoveDestinationToDirWithFile\\MoveDirWithFile\\MoveFile1.txt");
        create = path11.delete();
        File path12 = new File(RESOURCES + "MoveDestinationToDirWithFile\\MoveDirWithFile");
        create = path12.delete();
        File path13 = new File(RESOURCES + "MoveDestinationToDirWithFile");
        create = path13.delete();
        File path14 = new File(RESOURCES + "CopyDirWithFile2");
        create = path14.mkdir();
        File path15 = new File(RESOURCES + "CopyDirWithFile2\\CopyDirWithFile");
        create = path15.mkdir();
        File path16 = new File(RESOURCES + "CopyDirWithFile2\\CopyDirWithFile\\CopyFile1.txt");
        create = path16.createNewFile();
        File path17 = new File(RESOURCES + "CopyDestinationWithFilesAndDir\\CopyDirWithFile\\CopyFile1.txt");
        create = path17.delete();
        File path18 = new File(RESOURCES + "CopyDestinationWithFilesAndDir\\MoveDirWithFile");
        create = path18.delete();
        File path19 = new File(RESOURCES + "CopyDestinationWithFilesAndDir");
        create = path19.delete();
    }

    @AfterEach
    public void after() {

        File path = new File(RESOURCES + "DirForCopyFile\\File.txt");
        create = path.delete();
        File path1 = new File(RESOURCES + "File.txt");
        create = path1.delete();
        File path2 = new File(RESOURCES + "DirForCopyFile");
        create = path2.delete();
        File path3 = new File(RESOURCES + "DirForCopyDirs\\DirForCopy\\DirForCopy1");
        create = path3.delete();
        File path4 = new File(RESOURCES + "DirForCopyDirs\\DirForCopy");
        create = path4.delete();
        File path5 = new File(RESOURCES + "DirForCopyDirs");
        create = path5.delete();
        File path6 = new File(RESOURCES + "DirForCopy\\DirForCopy1");
        create = path6.delete();
        File path7 = new File(RESOURCES + "DirForCopy");
        create = path7.delete();
        File path8 = new File(RESOURCES + "MoveDestinationDir\\MoveFile.txt");
        create = path8.delete();
        File path9 = new File(RESOURCES + "MoveDestinationDir");
        create = path9.delete();
        File path10 = new File(RESOURCES + "MoveSourceDir\\MoveFile.txt");
        create = path10.delete();
        File path11 = new File(RESOURCES + "MoveSourceDir");
        create = path11.delete();
        File path12 = new File(RESOURCES + "MoveDirWithFile2\\MoveDirWithFile\\MoveFile1.txt");
        create = path12.delete();
        File path13 = new File(RESOURCES + "MoveDirWithFile2\\MoveDirWithFile");
        create = path13.delete();
        File path14 = new File(RESOURCES + "MoveDirWithFile2");
        create = path14.delete();
        File path15 = new File(RESOURCES + "MoveDestinationToDirWithFile\\MoveDirWithFile\\MoveFile1.txt");
        create = path15.delete();
        File path16 = new File(RESOURCES + "MoveDestinationToDirWithFile\\MoveDirWithFile");
        create = path16.delete();
        File path17 = new File(RESOURCES + "MoveDestinationToDirWithFile");
        create = path17.delete();
        File path18 = new File(RESOURCES + "CopyDirWithFile2\\CopyDirWithFile\\CopyFile1.txt");
        create = path18.delete();
        File path19 = new File(RESOURCES + "CopyDirWithFile2\\CopyDirWithFile");
        create = path19.delete();
        File path20 = new File(RESOURCES + "CopyDirWithFile2");
        create = path20.delete();
        File path21 = new File(RESOURCES + "CopyDestinationWithFilesAndDir\\CopyDirWithFile\\CopyFile1.txt");
        create = path21.delete();
        File path22 = new File(RESOURCES + "CopyDestinationWithFilesAndDir\\CopyDirWithFile");
        create = path22.delete();
        File path23 = new File(RESOURCES + "CopyDestinationWithFilesAndDir");
        create = path23.delete();
    }


    @DisplayName("test copy Copy File From Specified Dir And Paste File To Other Dir")
    @Test
    public void testCopyFileFromSpecifiedDirAndPasteFileToOtherDir() {
        String from = RESOURCES + "File.txt";
        String to = RESOURCES + "DirForCopyFile";

        assertEquals(0, countFiles(RESOURCES + "DirForCopyFile"));
        copy(from, to);
        assertEquals(1, countFiles(RESOURCES + "DirForCopyFile"));
    }

    @DisplayName("test copy Copy Empty Dir And Paste Dir In Other Specified Dir")
    @Test
    public void testCopyCopyCopyEmptyDirAndPasteDirInOtherSpecifiedDir() {
        String from = RESOURCES + "DirForCopy";
        String to = RESOURCES + "DirForCopyDirs";

        assertEquals(0, countDirs(RESOURCES + "DirForCopyDirs"));
        copy(from, to);
        assertEquals(2, countDirs(RESOURCES + "DirForCopyDirs"));
    }

    @DisplayName("test copy Copy Dir With Files And Dir Inside And Paste Dirs In Other Specified Dir")
    @Test
    public void testCopyCopyDirWithFilesAndDirInsideAndPasteDirsInOtherSpecifiedDir() {
        String from = RESOURCES + "CopyDirWithFile2\\CopyDirWithFile";
        String to = RESOURCES + "CopyDestinationWithFilesAndDir";
        int countDestFileBeforeCopy = countFiles(RESOURCES + "CopyDestinationWithFilesAndDir");
        int countDestDirBeforeCopy = countDirs(RESOURCES + "CopyDestinationWithFilesAndDir");

        assertEquals(0, countDestFileBeforeCopy);
        assertEquals(0, countDestDirBeforeCopy);

        copy(from, to);

        int countDestFileAfterMove = countFiles(RESOURCES + "CopyDestinationWithFilesAndDir");
        int countDestDirAfterMove = countDirs(RESOURCES + "CopyDestinationWithFilesAndDir");
        assertEquals(1, countDestFileAfterMove);
        assertEquals(1, countDestDirAfterMove);
    }

    @DisplayName("test move Moves Dir With Files To Other Dir")
    @Test
    public void testMoveMovesDirWithFiles() {
        String from = RESOURCES + "MoveDirWithFile2\\MoveDirWithFile";
        String to = RESOURCES + "MoveDestinationToDirWithFile";
        int countDirWithDirBeforeMove = countDirs(RESOURCES + "MoveDirWithFile2");
        int countDirWithFilesBeforeMove = countFiles(RESOURCES + "MoveDirWithFile2");
        int countDestFileBeforeMove = countFiles(RESOURCES + "MoveDestinationToDirWithFile");
        int countDestDirBeforeMove = countDirs(RESOURCES + "MoveDestinationToDirWithFile");

        assertEquals(1, countDirWithDirBeforeMove);
        assertEquals(1, countDirWithFilesBeforeMove);

        assertEquals(0, countDestFileBeforeMove);
        assertEquals(0, countDestDirBeforeMove);

        move(from, to);

        int countDirWithDirAfterMove = countDirs(RESOURCES + "MoveDirWithFile2");
        int countDirWithFilesAfterMove = countFiles(RESOURCES + "MoveDirWithFile2");
        int countDestFileAfterMove = countFiles(RESOURCES + "MoveDestinationToDirWithFile");
        int countDestDirAfterMove = countDirs(RESOURCES + "MoveDestinationToDirWithFile");

        assertEquals(0, countDirWithDirAfterMove);
        assertEquals(0, countDirWithFilesAfterMove);

        assertEquals(1, countDestFileAfterMove);
        assertEquals(1, countDestDirAfterMove);
    }

    @DisplayName("test move Moves File To Other Dir")
    @Test
    public void testMoveMovesFileToOtherDir() {
        String from = RESOURCES + "MoveSourceDir\\MoveFile.txt";
        String to = RESOURCES + "MoveDestinationDir";

        int countFilesInSourceDirBeforeMove = countFiles(RESOURCES + "MoveSourceDir");
        int countFilesInDestDirBeforeMove = countFiles(RESOURCES + "MoveDestinationDir");
        assertEquals(1, countFilesInSourceDirBeforeMove);
        assertEquals(0, countFilesInDestDirBeforeMove);

        move(from, to);

        int countFilesInSourceDirAfterMove = countFiles(RESOURCES + "MoveSourceDir");
        int countFilesInDestDirAfterMove = countFiles(RESOURCES + "MoveDestinationDir");
        assertEquals(0, countFilesInSourceDirAfterMove);
        assertEquals(1, countFilesInDestDirAfterMove);
    }

    @DisplayName("test countDirs Returns The Number Of Files In A Dir And All Files Along The Path")
    @Test
    public void testCountDirsReturnsTheNumberOfFilesInADirAndAllFilesAlongThePath() {
        int expected = 4;
        int actual = countFiles(RESOURCES);
        assertEquals(expected, actual);
    }

    @DisplayName("test countDirs Returns The Number Of Dirs In A Dir and all Dirs Along The Path")
    @Test
    public void testCountDirsReturnsTheNumberOfDirsInADirAndAllDirsAlongThePath() {
        int expected = 10;
        int actual = countDirs(RESOURCES);
        assertEquals(expected, actual);
    }
}








