package http.request;

import http.method.HttpMethod;

public interface IHttpRequest {
    String requestLine();
    HttpMethod method();
    String path();
    String version();
}
