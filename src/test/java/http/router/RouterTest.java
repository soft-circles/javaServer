package http.router;

import http.Controller.MockController;
import http.handlers.auth.AuthHandler;
import http.handlers.auth.IAuth;
import http.method.HttpMethod;
import http.request.HttpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RouterTest {

    public static final String PATH = "/path";
    public static final MockController CONTROLLER = new MockController();
    public static final IAuth AUTH = new AuthHandler("test", "test");
    public static final HttpMethod METHOD = HttpMethod.GET;
    public static final HttpMethod METHOD2 = HttpMethod.PUT;
    public static final String ENCODED = Base64.getEncoder().encodeToString("test:test".getBytes());
    public static final HttpRequest REQUEST = new HttpRequest("GET " + PATH + " HTTP/1/1\r\nAuthorization: Basic " + ENCODED);
    private Router router;

    private

    @BeforeEach
    void setUp() {
        router = new Router();
    }

    @Test
    void itAddsARouteWithListOfMethods() {
        router.addRoute(PATH, listOfMethods(), CONTROLLER);
        assertEquals(1, router.size());
    }

    @Test
    void itAddsARoute() {
        router.addRoute(PATH, METHOD, CONTROLLER);
        assertEquals(1, router.size());
    }

    @Test
    void getsRouteFromPath() {
        router.addRoute(PATH, METHOD, CONTROLLER);
        Route route = new Route(PATH, METHOD, CONTROLLER);
        assertEquals(route.getPath(), router.getRoute(PATH).getPath());
        assertEquals(route.getController(), router.getRoute(PATH).getController());
        assertEquals(route.getHttpMethods(), router.getRoute(PATH).getHttpMethods());
    }

    @Test
    void getController() {
        router.addRoute(PATH, METHOD, CONTROLLER);
        assertEquals(CONTROLLER, router.getController(PATH, METHOD));
    }

    private List<HttpMethod> listOfMethods() {
        List<HttpMethod> methods = new ArrayList<>();
        methods.add(HttpMethod.GET);
        methods.add(HttpMethod.PUT);
        methods.add(HttpMethod.PUT);
        return methods;
    }

    @Test
    void getHttpMethodsAsString() {
        router.addRoute(PATH, Arrays.asList(METHOD, METHOD2), CONTROLLER);
        assertEquals("GET, PUT", router.getRoute(PATH).getHttpMethodsAsString());
    }

    @Test
    void addRouteWithAuth() {
        router.addRouteWithAuth(PATH, METHOD, CONTROLLER, AUTH);
        assertEquals(1, router.size());
    }

    @Test
    void addRouteWithAuthWithMultipleMethods() {
        router.addRouteWithAuth(PATH, Arrays.asList(METHOD, METHOD2), CONTROLLER, AUTH);
        assertEquals(1, router.size());
    }

    @Test
    void getControllerWithAuth() throws NoAuthOnRouteException {
        router.addRouteWithAuth(PATH, METHOD, CONTROLLER, AUTH);
        assertEquals(CONTROLLER, router.getControllerWithAuth(REQUEST));
    }

}