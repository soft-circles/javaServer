package http.utils.logger;

import http.request.error.InvalidRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoggerTest {

    public static final String GET_TEST = "GET /test HTTP/1.1\n";
    public static final String GET_TEST_2 = "GET / HTTP/1.1\n";
    private Logger logger;

    @BeforeEach
    void setUp() throws InvalidRequestException, UnsupportedEncodingException {
        logger = new Logger();
    }

    @Test
    void getLog() {
        logger.addRequest(GET_TEST);
        logger.addRequest(GET_TEST_2);
        assertEquals(result(), logger.getLog());
    }

    private String result() {
        return GET_TEST + GET_TEST_2;
    }
}