package http.utils;

import http.IO.file.FileIO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileFetcherTest {

    List<String> files;
    FileIO fileIO;
    @BeforeEach
    void setUp() {
        fileIO = new FileIO("./public");
        files = DirectoryContentsUtility.listDirectoryContents("");
    }

    @Test
    void fetch() {
        assertTrue(files.containsAll(testFiles()));
    }

    @Test
    void parseTextFile() throws IOException {
        assertEquals(textFileContents(), DirectoryContentsUtility.parseTextFile("/text-file.txt", fileIO));
    }

    private List<String> testFiles() {
        List<String> list = new ArrayList<>();
        list.add("coffee");
        list.add("file1");
        list.add("file2");
        list.add("form");
        list.add("image.gif");
        list.add("image.jpeg");
        list.add("image.png");
        list.add("partial_content.txt");
        list.add("patch-content.txt");
        list.add("put-target");
        list.add("tea");
        list.add("test");
        list.add("text-file.txt");
        return list;
    }

    private String textFileContents() {
        return "file1 contents";
    }
}