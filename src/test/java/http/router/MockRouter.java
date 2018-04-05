package http.router;

import http.controllers.IController;
import http.handlers.auth.IAuth;
import http.method.httpMethod;
import http.request.HttpRequest;

import java.util.List;

public class MockRouter implements IRouter {
    @Override
    public void addRoute(String path, httpMethod method, IController handler) {

    }

    @Override
    public void addRoute(String path, List<httpMethod> methods, IController handler) {

    }

    @Override
    public void addRouteWithAuth(String path, httpMethod method, IController handler, IAuth auth) {

    }

    @Override
    public void addRouteWithAuth(String path, List<httpMethod> methods, IController handler, IAuth auth) {

    }

    @Override
    public Route getRoute(String path) {
        return null;
    }

    @Override
    public IController getController(String path, httpMethod httpMethod) {
        return null;
    }

    @Override
    public IAuth getAuth(String path) throws NoAuthOnRouteException {
        return null;
    }

    @Override
    public boolean hasAuth(String path) throws NoAuthOnRouteException {
        return false;
    }

    @Override
    public void removeRoute(String path) {

    }

    @Override
    public IController getControllerWithAuth(HttpRequest httpRequest) throws NoAuthOnRouteException {
        return null;
    }
}
