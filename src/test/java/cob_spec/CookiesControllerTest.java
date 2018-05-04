package cob_spec;

import cob_spec.controllers.CookiesController;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CookiesControllerTest {

    public static final String BODY = "Eat";
    public static final String BODY_EAT_COOKIE = "mmmm chocolate\r";
    private static final String RAW_REQUEST_EAT_COOKIE = "GET /eat_cookie HTTP/1.1\r\nCookie: type=chocolate\r\n\r\n";
    private HttpResponse httpResponse;
    public static final String RAW_REQUEST_COOKIE = "GET /cookie?type=chocolate HTTP/1.1\r\n";
    private HttpResponse eatCookieHttpResponse;

    @BeforeEach
    void setUp() {
        CookiesController cookiesController = new CookiesController();
        httpResponse = cookiesController.generateResponse(cookieHttpRequest());
        eatCookieHttpResponse = cookiesController.generateResponse(eatCookieHttpRequest());
    }

    @Test
    void hasExpectedBodyContent() {
        assertEquals(BODY, httpResponse.getBodyAsString());
    }

    @Test
    void returns200Status() {
        assertEquals(Status.OK, httpResponse.getStatus());
    }

    @Test
    void hasExpectedBodyContentEatCookie() {
        assertEquals(BODY_EAT_COOKIE, eatCookieHttpResponse.getBodyAsString());
    }

    @Test
    void returns200StatusEatCookie() {
        assertEquals(Status.OK, eatCookieHttpResponse.getStatus());
    }

    private HttpRequest cookieHttpRequest() {
        return new HttpRequest(RAW_REQUEST_COOKIE);
    }

    private HttpRequest eatCookieHttpRequest() {
       return new HttpRequest(RAW_REQUEST_EAT_COOKIE);
    }
}