package http.IO.file;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class FileIOTest {
    private FileIO fileIO;
    @BeforeEach
    void setUp() {
        fileIO = new FileIO("./public");
    }

    @Test
    void exists() {
        assertFalse(fileIO.exists("/foo"));
        assertTrue(fileIO.exists("/file1"));
    }

    @Test
    void isFile() {
        assertFalse(fileIO.isFile("/foo"));
        assertTrue(fileIO.isFile("/text-file.txt"));
    }

    @Test
    void isDirectory() {
        assertTrue(fileIO.isDirectory("/"));
        assertFalse(fileIO.isDirectory("/foo"));
    }
}