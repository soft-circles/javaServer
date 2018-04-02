package http.router;

import http.controllers.IController;
import http.controllers.InvalidMethodController;
import http.controllers.InvalidRequestController;
import http.method.httpMethod;

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

    public void removeRoute(String path) {
        routes.remove(path);
    }
}
