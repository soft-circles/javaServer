package http.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainArgumentParserTest {
    private String[] args;
    private MainArgumentParser argumentParser;
    @BeforeEach
    void setUp() {
        args = new String[]{"-p", portNumber(), "-d", directory()};
        argumentParser = new MainArgumentParser(args);
    }

    @Test
    void getPortNumber() {
        assertEquals(portNumberInt(), argumentParser.getPortNumber());
    }

    @Test
    void getWorkingDirectory() {
        assertEquals(directory(), argumentParser.getWorkingDirectory());
    }

    private String portNumber() {
        return "5000";
    }

    private int portNumberInt() {
        return Integer.parseInt(portNumber());
    }

    private String directory() {
        return "./test_dir";
    }
}