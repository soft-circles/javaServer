package http.server;
import http.controllers.*;
import http.IO.ClientInput;
import http.IO.ClientOutput;
import http.IO.file.FileIO;
import http.IO.file.InvalidPathException;
import http.handlers.auth.AuthHandler;
import http.handlers.auth.IAuth;
import http.method.httpMethod;
import http.request.HttpRequest;
import http.request.error.InvalidRequestException;
import http.response.HttpResponse;
import http.response.HttpResponseWriter;
import http.router.NoAuthOnRouteException;
import http.router.Router;
import http.socket.IClient;
import http.socket.Client;
import http.status.InvalidStatusCodeException;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Arrays;

public class Server {
    private Router router;

    public Server(int portNum, String directory) throws IOException, InvalidRequestException, InvalidPathException, InvalidStatusCodeException, NoAuthOnRouteException {
        router = configServer(new FileIO(directory));
        System.out.println("Server starting on port: " + String.valueOf(portNum) + " " + "with public directory: " + directory);
        ServerSocket socket = new ServerSocket(portNum);
        while (true) {
            IClient client = getiClient(socket);
            System.out.println("Client connected");
            ClientInput clientInput = new ClientInput(client.getInputStream());
            String rawRequest = clientInput.getRawRequestString();
            HttpRequest httpRequest = new HttpRequest(rawRequest);
            if (httpRequest.getContentLength() > 0) {
                httpRequest.setBody(clientInput.getBytes(httpRequest.getContentLength()));
            }
            System.out.println(rawRequest);
            HttpResponse httpResponse = null;
            if (router.hasAuth(httpRequest.path())) {
                httpResponse = getHttpResponseWithAuth(httpRequest);
            } else {
                httpResponse = getHttpResponse(httpRequest);
            }
            ClientOutput clientOutput = new ClientOutput(client.getOutputStream());
            byte[] byteResponse = new HttpResponseWriter().sendHttpResponse(httpResponse);
            client.getOutputStream().write(byteResponse);
            client.closeConnection();
            System.out.println(httpResponse.fullResponse());
        }
    }

    private HttpResponse getHttpResponse(HttpRequest httpRequest) throws IOException, InvalidPathException, InvalidStatusCodeException, NoAuthOnRouteException {
        IController handler = router.getController(httpRequest.path(), httpRequest.method());
        return handler.generateResponse(httpRequest);
    }

    private HttpResponse getHttpResponseWithAuth(HttpRequest httpRequest) throws InvalidPathException, InvalidStatusCodeException, IOException, NoAuthOnRouteException {
        IController handler = router.getControllerWithAuth(httpRequest);
        return handler.generateResponse(httpRequest);
    }


    private boolean routeHasAuth(String path) throws NoAuthOnRouteException {
        return router.hasAuth(path);
    }

    private IClient getiClient(ServerSocket socket) {
        System.out.println("Waiting for client...");
        return new Client(socket);
    }

    private Router configServer(FileIO fileIO) {
        Router router = new Router();
        DirectoryController dirHandler = new DirectoryController(fileIO);
        PostFileContentsController postFileHandler = new PostFileContentsController(router, fileIO);
        EditFileController editFileController = new EditFileController(fileIO);
        LogsController logsController = new LogsController();
        IAuth logAuthHandler = new AuthHandler("admin", "hunter2");
        CookiesController cookiesController = new CookiesController();
        PatchController patchController = new PatchController(fileIO);

        router.addRoute("/", Arrays.asList(httpMethod.GET, httpMethod.HEAD), dirHandler);
        router.addRoute("/coffee", httpMethod.GET, new TeaPotController());
        router.addRoute("/tea", httpMethod.GET, new TeaPotController());
        router.addRoute("/form", httpMethod.POST, postFileHandler);
        router.addRoute("/put-target", httpMethod.PUT, editFileController);
        router.addRoute("/redirect", httpMethod.GET, new RedirectController());
        router.addRoute("/file1", httpMethod.GET, dirHandler);
        router.addRoute("/text-file.txt", httpMethod.GET, dirHandler);
        router.addRoute("/image.jpeg", httpMethod.GET, dirHandler);
        router.addRoute("/image.gif", httpMethod.GET, dirHandler);
        router.addRoute("/image.png", httpMethod.GET, dirHandler);
        router.addRoute("/cat-form", Arrays.asList(httpMethod.GET, httpMethod.POST), new CatFormController(router, fileIO));
        router.addRoute("/method_options", Arrays.asList(httpMethod.OPTIONS, httpMethod.GET, httpMethod.PUT, httpMethod.POST, httpMethod.HEAD), new MethodOptionsController(router));
        router.addRoute("/method_options2", Arrays.asList(httpMethod.GET, httpMethod.OPTIONS, httpMethod.HEAD), new MethodOptionsController(router));
        router.addRoute("/parameters", httpMethod.GET, new ParameterDecodeController());
        router.addRouteWithAuth("/logs", httpMethod.GET, logsController, logAuthHandler);
        router.addRoute("/cookie", httpMethod.GET, cookiesController);
        router.addRoute("/eat_cookie", httpMethod.GET, cookiesController);
        router.addRoute("/patch-content.txt", Arrays.asList(httpMethod.GET, httpMethod.PATCH), patchController);
        return router;
    }
}




