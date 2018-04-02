package http.handlers.auth;

import http.controllers.IController;
import http.request.HttpRequest;

import java.util.Base64;

public class AuthHandler implements IAuth {

    private String userName;
    private String password;

    public AuthHandler() {
        this.userName = "";
        this.password = "";
    }

    @Override
    public void setAuthCredentials(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public boolean authorized(HttpRequest httpRequest) {
        try {
            String auth = httpRequest.headers().get("Authorization").split(" ")[1];
            byte[] decodedBytes = Base64.getDecoder().decode(auth);
            String thing = new String(decodedBytes);
            return new String(decodedBytes).equals(userName + ":" + password);
        } catch(Exception e) {
            return false;
        }
    }

    public String authString() {
        return this.userName + ":" + this.password;
    }
}
