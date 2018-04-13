package http.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContentReaderTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void getFileType() {
        assertEquals("image/jpeg", ContentReader.getFileType(jpeg()));
        assertEquals("image/png", ContentReader.getFileType(png()));
        assertEquals("image/gif", ContentReader.getFileType(gif()));
        assertEquals("text/plain", ContentReader.getFileType(text()));
    }

    private String jpeg() {
        return "/newspaper/2/image2.jpeg";
    }

    private String png() {
        return "/newspaper/2/image3.png";
    }

    private String gif() {
        return "/newspaper/2/image4.gif";
    }

    private String text() {
        return "/newspaper/2/headline.txt";
    }
}