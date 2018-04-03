package http.router;

import http.controllers.IController;
import http.handlers.auth.IAuth;
import http.method.httpMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Route {
    private IController controller;
    private List<httpMethod> httpMethods;
    private String path;
    private IAuth auth = null;

    public Route(String path, httpMethod httpMethod, IController controller) {
        this.path = path;
        this.httpMethods = new ArrayList<>();
        this.httpMethods.add(httpMethod);
        this.controller = controller;
    }

    public Route(String path, List<httpMethod> httpMethods, IController controller) {
       this.path = path;
       this.httpMethods = httpMethods;
       this.controller = controller;
    }

    public Route(String path, List<httpMethod> httpMethods, IController controller, IAuth auth) {
        this.path = path;
        this.httpMethods = httpMethods;
        this.controller = controller;
        this.auth = auth;
    }

    public Route(String path, httpMethod method, IController controller, IAuth auth) {
        this.path = path;
        this.httpMethods = new ArrayList<>();
        this.httpMethods.add(method);
        this.controller = controller;
        this.auth = auth;
    }

    public IController getController() {
        return controller;
    }

    public List<httpMethod> getHttpMethods() {
        return httpMethods;
    }

    public String getHttpMethodsAsString() {
        StringJoiner stringJoiner = new StringJoiner(", ");
        httpMethods.stream().forEach(method -> stringJoiner.add(String.valueOf(method)));
        return stringJoiner.toString();
    }

    public String getPath() {
        return path;
    }

    public IAuth getAuth() throws NoAuthOnRouteException {
        try {
            return auth;
        } catch(Exception e) {
            throw new NoAuthOnRouteException();
        }
    }
}
