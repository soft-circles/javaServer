package http.IO;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ClientInputTest {
    private ClientInput clientInput;
    @BeforeEach
    void setUp() throws IOException {
        InputStream stubInputStream = IOUtils.toInputStream(testString(), "UTF-8");
        clientInput = new ClientInput(stubInputStream);
    }

    @Test
    void getRawRequestString() {
        assertEquals(testString(), clientInput.getRawRequestString());
    }

    @Test
    void getBytes() {
        assertTrue(Arrays.equals(testString().getBytes(), clientInput.getBytes(testStringByteArrayLength())));
    }

    private String testString() {
        return "some test data for my input stream\n";
    }

    private int testStringByteArrayLength() {
        return 35;
    }
}