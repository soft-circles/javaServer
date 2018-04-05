package http.IO;

import http.client.ClientOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ClientOutputTest {
    ClientOutput clientOutput;
    ByteArrayOutputStream output;
    @BeforeEach
    void setUp() throws IOException {
        output = new ByteArrayOutputStream();
        clientOutput = new ClientOutput(output);
        clientOutput.writeTo(testString());
    }

    @Test
    void writeTo() {
        String string = new String(output.toByteArray());
        assertEquals(testString() + "\n", string);
    }

    private String testString() {
        return "test string";
    }
}