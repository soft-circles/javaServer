package http.handlers.auth;

import http.request.HttpRequest;
import http.request.error.InvalidRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

class AuthHandlerTest {

    private AuthHandler authHandler;
    private HttpRequest httpRequest;

    @BeforeEach
    void setUp() throws InvalidRequestException, UnsupportedEncodingException {
        authHandler = new AuthHandler();
        authHandler.setAuthCredentials("testUser", "password");
        System.out.println(rawRequest());
        httpRequest = new HttpRequest(rawRequest());
    }

    private String rawRequest() {
        return "GET /secret HTTP/1.1\n" +
                "Authorization: Basic " +
                auth();
    }

    private String auth() {
        String authString = "testUser:password";
        return Base64.getEncoder().encodeToString(authString.getBytes());
    }

    @Test
    void authorized() {
        assertTrue(authHandler.authorized(httpRequest));
    }
}