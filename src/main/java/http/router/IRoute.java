package http.router;

import http.controllers.IController;
import http.handlers.auth.IAuth;
import http.method.httpMethod;

import java.util.List;

public interface IRoute {
    IController getController();
    List<httpMethod> getHttpMethods();
    String getHttpMethodsAsString();
    String getPath();
    IAuth getAuth() throws NoAuthOnRouteException;
}
