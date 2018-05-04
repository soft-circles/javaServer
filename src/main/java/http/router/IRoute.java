package http.router;

import cob_spec.controllers.IController;
import http.handlers.auth.IAuth;
import http.method.HttpMethod;

import java.util.List;

public interface IRoute {
    IController getController();
    List<HttpMethod> getHttpMethods();
    String getHttpMethodsAsString();
    String getPath();
    IAuth getAuth() throws NoAuthOnRouteException;
}
