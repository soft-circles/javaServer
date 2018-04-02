package http.router;

import http.controllers.IController;
import http.method.httpMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Route {
    private final IController controller;
    private List<httpMethod> httpMethods;
    private final String path;

    public Route(String path, httpMethod httpMethod, IController controller) {
        this.path = path;
        this.httpMethods =  new ArrayList<>();
        this.httpMethods.add(httpMethod);
        this.controller = controller;
    }

    public Route(String path, List<httpMethod> httpMethods, IController controller) {
       this.path = path;
       this.httpMethods = httpMethods;
       this.controller = controller;
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
}
