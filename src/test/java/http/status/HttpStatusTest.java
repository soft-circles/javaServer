package http.status;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpStatusTest {
    @Test
    void message() throws InvalidStatusCodeException {
        assertEquals("OK", HttpStatus.message("200"));
        assertEquals("Not Found", HttpStatus.message("404"));
        assertEquals("Internal Server Error", HttpStatus.message("500"));
    }

    @Test
    void isErrorStatus() {
        assertTrue(HttpStatus.isErrorStatus(500));
        assertFalse(HttpStatus.isErrorStatus(200));
    }

    @Test
    void isInfoStatus() {
        assertTrue(HttpStatus.isInfoStatus(100));
        assertFalse(HttpStatus.isInfoStatus(404));
    }

    @Test
    void isRedirect() {
        assertTrue(HttpStatus.isRedirect(303));
        assertFalse(HttpStatus.isRedirect(202));
    }

    @Test
    void isServerError() {
        assertTrue(HttpStatus.isServerError(500));
        assertFalse(HttpStatus.isServerError(200));
    }

    @Test
    void isSuccess() {
        assertTrue(HttpStatus.isSuccess(200));
        assertFalse(HttpStatus.isSuccess(100));
    }
}