package http.router;

import cob_spec.controllers.IController;
import http.handlers.auth.IAuth;
import http.method.HttpMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Route implements IRoute {
    private IController controller;
    private List<HttpMethod> httpMethods;
    private String path;
    private IAuth auth = null;

    Route(String path, HttpMethod httpMethod, IController controller) {
        this.path = path;
        this.httpMethods = new ArrayList<>();
        this.httpMethods.add(httpMethod);
        this.controller = controller;
    }

    Route(String path, List<HttpMethod> httpMethods, IController controller) {
       this.path = path;
       this.httpMethods = httpMethods;
       this.controller = controller;
    }

    Route(String path, List<HttpMethod> httpMethods, IController controller, IAuth auth) {
        this.path = path;
        this.httpMethods = httpMethods;
        this.controller = controller;
        this.auth = auth;
    }

    Route(String path, HttpMethod method, IController controller, IAuth auth) {
        this.path = path;
        this.httpMethods = new ArrayList<>();
        this.httpMethods.add(method);
        this.controller = controller;
        this.auth = auth;
    }

    public IController getController() {
        return controller;
    }

    public List<HttpMethod> getHttpMethods() {
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
