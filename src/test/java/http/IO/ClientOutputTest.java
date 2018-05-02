package http.IO;

import http.client.ClientOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientOutputTest {
    ClientOutput clientOutput;
    ByteArrayOutputStream output;
    @BeforeEach
    void setUp() {
        output = new ByteArrayOutputStream();
        clientOutput = new ClientOutput(output);
        clientOutput.writeTo(testString());
    }

    @Test
    void writesToOutputStream() {
        String string = new String(output.toByteArray());
        assertEquals(testString() + "\n", string);
    }

    private String testString() {
        return "test string";
    }
}