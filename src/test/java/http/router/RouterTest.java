package http.router;

import http.Controller.MockController;
import http.method.HttpMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RouterTest {

    public static final String PATH = "/path";
    public static final String PATH2 = "/path2";
    public static final MockController CONTROLLER = new MockController();
    public static final HttpMethod METHOD = HttpMethod.GET;
    public static final HttpMethod METHOD2 = HttpMethod.PUT;
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
}