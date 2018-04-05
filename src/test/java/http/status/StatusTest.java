package http.status;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusTest {

    public static final int CODE = 200;
    public static final String STATUS = "Not Found";

    @Test
    void messageFor() {
        assertEquals(Status.OK, Status.messageFor(CODE));
    }

    @Test
    void getCode() {
        assertEquals(CODE, Status.OK.getCode());
    }

    @Test
    void getStringTest() {
       assertEquals(STATUS, Status.Not_Found.getString());
    }
}