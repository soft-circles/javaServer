package http.handlers.cookie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CookieTest {

    public static final String NAME = "test";
    public static final String VALUE = "cookie";
    private Cookie cookie;

    @BeforeEach
    void setUp() {
        cookie = new Cookie(NAME, VALUE);
    }

    @Test
    void getName() {
        assertEquals(NAME, cookie.getName());
    }

    @Test
    void getValue() {
        assertEquals(VALUE, cookie.getValue());
    }

    @Test
    void setName() {
        cookie = new Cookie(VALUE, NAME);
        cookie.setName(NAME);
        assertEquals(NAME, cookie.getName());
    }

    @Test
    void setValue() {
        cookie = new Cookie(VALUE, NAME);
        cookie.setValue(VALUE);
        assertEquals(VALUE, cookie.getValue());
    }
}