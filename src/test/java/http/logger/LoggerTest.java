package http.logger;

import http.request.HttpRequest;
import http.request.error.InvalidRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

class LoggerTest {

    private HttpRequest httpRequest1;
    private HttpRequest httpRequest2;
    private Logger logger;

    @BeforeEach
    void setUp() throws InvalidRequestException, UnsupportedEncodingException {
        httpRequest1 = new HttpRequest(rawRequest1());
        httpRequest2 = new HttpRequest(rawRequest2());
        logger = new Logger();
    }

    private String rawRequest2() {
        return "GET /test HTTP/1.1\n";
    }

    private String rawRequest1() {
        return "GET / HTTP/1.1\n";
    }

    @Test
    void getLog() {
        logger.addRequest(httpRequest1);
        logger.addRequest(httpRequest2);
        assertEquals(result(), logger.getLog());
    }

    private String result() {
        return "GET / HTTP/1.1\nGET /test HTTP/1.1\n";
    }
}