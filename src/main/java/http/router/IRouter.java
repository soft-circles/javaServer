package http.router;

import http.controllers.IController;
import http.handlers.auth.IAuth;
import http.method.HttpMethod;
import http.request.HttpRequest;

import java.util.List;

public interface IRouter {

    void addRoute(String path, HttpMethod method, IController handler);
    void addRoute(String path, List<HttpMethod> methods, IController handler);
    void addRouteWithAuth(String path, HttpMethod method, IController handler, IAuth auth);
    void addRouteWithAuth(String path, List<HttpMethod> methods, IController handler, IAuth auth);
    Route getRoute(String path);
    IController getController(String path, HttpMethod httpMethod);
    IAuth getAuth(String path) throws NoAuthOnRouteException;
    boolean hasAuth(String path) throws NoAuthOnRouteException;
    void removeRoute(String path);
    IController getControllerWithAuth(HttpRequest httpRequest) throws NoAuthOnRouteException;
}
