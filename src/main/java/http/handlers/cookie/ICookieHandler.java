package http.handlers.cookie;

public interface ICookieHandler {
    String getName();
    String getValue();
    void setName(String name);
    void setValue(String value);
}
