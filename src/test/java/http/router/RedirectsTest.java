package http.router;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RedirectsTest {

    @Test
    void isRedirect() {
        assertTrue(Redirects.isRedirect("/redirect"));
        assertFalse(Redirects.isRedirect("/"));
    }
}