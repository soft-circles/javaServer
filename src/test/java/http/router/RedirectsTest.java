package http.router;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RedirectsTest {

    @Test
    void isRedirect() {
        assertTrue(Redirects.isRedirect("/redirect"));
        assertFalse(Redirects.isRedirect("/"));
    }
}