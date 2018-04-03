package http.controllers;

import http.IO.file.InvalidPathException;
import http.handlers.cookie.Cookie;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.HttpStatus;
import http.status.InvalidStatusCodeException;

import java.io.IOException;

public class CookiesController implements IController {
    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) throws IOException, InvalidPathException, InvalidStatusCodeException {
        if (httpRequest.path().equals("/cookie")) {
            return handleCookie(httpRequest);
        } else {
            return handleEatCookie(httpRequest);
        }
    }

    private HttpResponse handleCookie(HttpRequest httpRequest) throws InvalidStatusCodeException {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus("200");
        httpResponse.setBody("Eat");
        httpResponse.addCookie(new Cookie("type", httpRequest.getParameters().get("type")));
        httpResponse.setCookieHeader();
        httpResponse.setReasonPhrase(HttpStatus.message(httpResponse.getStatus()));
        return httpResponse;
    }

    private HttpResponse handleEatCookie(HttpRequest httpRequest) throws InvalidStatusCodeException {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus("200");
        httpResponse.setReasonPhrase(HttpStatus.message(httpResponse.getStatus()));
        for (Cookie cookie : httpRequest.getCookies()) {
            httpResponse.addToBody("mmmm " + cookie.getValue());
        }
        return httpResponse;
    }
}
