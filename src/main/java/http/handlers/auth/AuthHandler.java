package http.handlers.auth;

import java.util.Base64;

public class AuthHandler implements IAuth {

    private String userName;
    private String password;

    public AuthHandler(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public boolean authorized(String authString) {
        byte[] decodedBytes = Base64.getDecoder().decode(authString);
        return new String(decodedBytes).equals(userName + ":" + password);
    }

    public String authString() {
        return this.userName + ":" + this.password;
    }
}
