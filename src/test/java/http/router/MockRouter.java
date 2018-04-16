package http.router;

import http.controllers.IController;
import http.handlers.auth.IAuth;
import http.method.HttpMethod;
import http.request.HttpRequest;

import java.util.List;

public class MockRouter implements IRouter {
    @Override
    public void addRoute(String path, HttpMethod method, IController handler) {

    }

    @Override
    public void addRoute(String path, List<HttpMethod> methods, IController handler) {

    }

    @Override
    public void addRouteWithAuth(String path, HttpMethod method, IController handler, IAuth auth) {

    }

    @Override
    public void addRouteWithAuth(String path, List<HttpMethod> methods, IController handler, IAuth auth) {

    }

    @Override
    public Route getRoute(String path) {
        return null;
    }

    @Override
    public IController getController(String path, HttpMethod httpMethod) {
        return null;
    }

    @Override
    public IAuth getAuth(String path) {
        return null;
    }

    @Override
    public boolean hasAuth(String path) {
        return false;
    }

    @Override
    public void removeRoute(String path) {

    }

    @Override
    public IController getControllerWithAuth(HttpRequest httpRequest) {
        return null;
    }
}
