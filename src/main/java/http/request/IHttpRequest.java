package http.request;

import http.method.httpMethod;

public interface IHttpRequest {
    String requestLine();
    httpMethod method();
    String path();
    String version();
}
