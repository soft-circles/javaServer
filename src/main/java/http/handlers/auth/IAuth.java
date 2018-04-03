package http.handlers.auth;

public interface IAuth {
    boolean authorized(String authString);
    String authString();
}
