package http.handlers.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthHandlerTest {

    public static final String VALID_USER = "ValidUser";
    public static final String VALID_PASSWORD = "ValidPassword";
    public static final String INVALID_CREDS = "fake:badPassword";
    public static final String VALID_CREDS = "ValidUser:ValidPassword";

    private AuthHandler authHandler;


    private String encode(String authString) {
        return Base64.getEncoder().encodeToString(authString.getBytes());
    }

    @BeforeEach
    void setUp() {
        authHandler = new AuthHandler(VALID_USER, VALID_PASSWORD);
    }

    @Test
    void authorized() {
        assertTrue(authHandler.authorized(encode(VALID_CREDS)));
    }

    @Test
    void unauthorized() {
        assertFalse(authHandler.authorized(encode(INVALID_CREDS)));
    }
}