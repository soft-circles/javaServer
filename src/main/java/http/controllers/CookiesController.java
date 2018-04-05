package http.controllers;

import http.handlers.cookie.Cookie;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.Status;

public class CookiesController implements IController {
    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) {
        if (httpRequest.path().equals("/cookie")) {
            return handleCookie(httpRequest);
        } else {
            return handleEatCookie(httpRequest);
        }
    }

    private HttpResponse handleCookie(HttpRequest httpRequest) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus(Status.OK);
        httpResponse.setBody("Eat");
        httpResponse.addCookie(new Cookie("type", httpRequest.getParameters().get("type")));
        httpResponse.setCookieHeader();
        return httpResponse;
    }

    private HttpResponse handleEatCookie(HttpRequest httpRequest) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus(Status.OK);
        for (Cookie cookie : httpRequest.getCookies()) {
            httpResponse.addToBody("mmmm " + cookie.getValue());
        }
        return httpResponse;
    }
}
