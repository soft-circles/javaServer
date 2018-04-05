package http.router;

import http.controllers.IController;
import http.controllers.InvalidMethodController;
import http.controllers.InvalidRequestController;
import http.controllers.UnauthorizedController;
import http.handlers.auth.IAuth;
import http.method.HttpMethod;
import http.request.HttpRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Router implements IRouter {
    private Map<String, Route> routes;

    public Router() {
        this.routes = new HashMap<>();
    }

    @Override
    public void addRoute(String path, HttpMethod method, IController handler) {
        Route route = new Route(path, method, handler);
        routes.put(path, route);
    }

    @Override
    public void addRoute(String path, List<HttpMethod> methods, IController handler) {
        Route route = new Route(path, methods, handler);
        routes.put(path, route);
    }

    @Override
    public void addRouteWithAuth(String path, List<HttpMethod> methods, IController handler, IAuth auth) {
        Route route = new Route(path, methods, handler, auth);
        routes.put(path, route);
    }

    @Override
    public void addRouteWithAuth(String path, HttpMethod method, IController handler, IAuth auth) {
        Route route = new Route(path, method, handler, auth);
        routes.put(path, route);
    }

    public int size() {
        return routes.size();
    }

    @Override
    public Route getRoute(String path) {
        return routes.get(path);
    }

    @Override
    public IController getController(String path, HttpMethod httpMethod) {
        try {
            Route route = routes.get(path);
            if (route.getHttpMethods().contains(httpMethod)) {
                return route.getController();
            } else  {
                return new InvalidMethodController();
            }
        } catch(Exception e) {
            return new InvalidRequestController();
        }
    }

    @Override
    public IAuth getAuth(String path) throws NoAuthOnRouteException {
        return routes.get(path).getAuth();
    }

    @Override
    public boolean hasAuth(String path) throws NoAuthOnRouteException {
        return routes.containsKey(path) && routes.get(path).getAuth() != null;
    }

    @Override
    public void removeRoute(String path) {
        routes.remove(path);
    }

    @Override
    public IController getControllerWithAuth(HttpRequest httpRequest) throws NoAuthOnRouteException {
        Route route = routes.get(httpRequest.path());
        if (route.getAuth().authorized(authorizationHeader(httpRequest))) {
            if (route.getHttpMethods().contains(httpRequest.method())) {
                return route.getController();
            } else {
                return new InvalidMethodController();
            }
        } else {
            return new UnauthorizedController(route.getAuth());
        }
    }

    private String authorizationHeader(HttpRequest httpRequest) {
        try {
            return httpRequest.headers().get("Authorization").split(" ")[1];
        } catch (Exception e) {
            return "";
        }
    }
}
