package http.handlers.auth;

import http.controllers.IController;
import http.request.HttpRequest;

public interface IAuth {
    void setAuthCredentials(String userName, String password);
    boolean authorized(HttpRequest httpRequest);
    String authString();
}
