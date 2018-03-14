package FileIO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileIOTest {
    private FileIO fileIO;
    @BeforeEach
    void setUp() {
        fileIO = new FileIO(FileSystems.getDefault().getPath("./src/test/java/FileIO/").toAbsolutePath().toString());
    }

//    @Test
//    void exists() {
//        assertFalse(fileIO.exists("/foo"));
//        assertTrue(fileIO.exists("/TestDirectory.txt"));
//    }

//    @Test
//    void isFile() {
//        assertFalse(fileIO.isFile("/foo"));
//        assertTrue(fileIO.isFile("/TestDirectory.txt"));
//    }

//    @Test
//    void isDirectory() {
//        assertTrue(fileIO.isDirectory("/TestDirectory"));
//        assertFalse(fileIO.isDirectory("/foo"));
//    }
}