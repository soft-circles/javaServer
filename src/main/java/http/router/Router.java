package http.router;

import http.controllers.IController;
import http.controllers.InvalidMethodController;
import http.controllers.InvalidRequestController;
import http.controllers.UnauthorizedController;
import http.handlers.auth.IAuth;
import http.method.httpMethod;
import http.request.HttpRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Router {
    private Map<String, Route> routes;

    public Router() {
        this.routes = new HashMap<>();
    }

    public void addRoute(String path, httpMethod method, IController handler) {
        Route route = new Route(path, method, handler);
        routes.put(path, route);
    }

    public void addRoute(String path, List<httpMethod> methods, IController handler) {
        Route route = new Route(path, methods, handler);
        routes.put(path, route);
    }

    public void addRouteWithAuth(String path, List<httpMethod> methods, IController handler, IAuth auth) {
        Route route = new Route(path, methods, handler, auth);
        routes.put(path, route);
    }

    public void addRouteWithAuth(String path, httpMethod method, IController handler, IAuth auth) {
        Route route = new Route(path, method, handler, auth);
        routes.put(path, route);
    }

    public int size() {
        return routes.size();
    }

    public Route getRoute(String path) {
        return routes.get(path);
    }

    public IController getController(String path, httpMethod httpMethod) {
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

    public IAuth getAuth(String path) throws NoAuthOnRouteException {
        return routes.get(path).getAuth();
    }

    public boolean hasAuth(String path) throws NoAuthOnRouteException {
        return routes.containsKey(path) && routes.get(path).getAuth() != null;
    }

    public void removeRoute(String path) {
        routes.remove(path);
    }

    public IController getControllerWithAuth(HttpRequest httpRequest) throws NoAuthOnRouteException {
        try {
            Route route = routes.get(httpRequest.path());
            if (route.getAuth().authorized(httpRequest)) {
                if (route.getHttpMethods().contains(httpRequest.method())) {
                    return route.getController();
                } else {
                    return new InvalidMethodController();
                }
            } else {
                return new UnauthorizedController(route.getAuth().authString());
            }
        } catch(Exception e) {
            return new InvalidRequestController();
        }
    }
}
