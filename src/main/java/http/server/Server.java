package http.server;
import http.IO.IFileIO;
import http.connectionProcess.ConnectionProcessMultiThread;
import http.connectionProcess.HttpConnectionToProcess;
import http.controllers.*;
import http.IO.FileIO;
import http.handlers.auth.AuthHandler;
import http.handlers.auth.IAuth;
import http.method.HttpMethod;
import http.response.HttpResponseWriter;
import http.router.Router;
import http.client.IClient;
import http.client.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Arrays;

public class Server {
    private Router router;

    public Server(int portNum, String directory) throws IOException {
        router = configServer(new FileIO(directory));
        System.out.println("Server starting on port: " + String.valueOf(portNum) + " " + "with public directory: " + directory);
        ServerSocket socket = new ServerSocket(portNum);
        ConnectionProcessMultiThread connectionProcessMultiThread = new ConnectionProcessMultiThread();
        while (true) {
            IClient client = getiClient(socket);
            System.out.println("Client connected");
            HttpConnectionToProcess httpConnectionToProcess = new HttpConnectionToProcess(client, router, new HttpResponseWriter());
            connectionProcessMultiThread.execute(httpConnectionToProcess);
        }
    }

    private IClient getiClient(ServerSocket socket) {
        System.out.println("Waiting for client...");
        return new Client(socket);
    }

    private Router configServer(IFileIO IFileIO) {
        Router router = new Router();
        DirectoryController dirHandler = new DirectoryController(IFileIO);
        PostFileContentsController postFileHandler = new PostFileContentsController(IFileIO);
        EditFileController editFileController = new EditFileController(IFileIO);
        LogsController logsController = new LogsController();
        IAuth logAuthHandler = new AuthHandler("admin", "hunter2");
        CookiesController cookiesController = new CookiesController();
        PatchController patchController = new PatchController(IFileIO);
        PartialContentController partialContentController = new PartialContentController(IFileIO);

        router.addRoute("/", Arrays.asList(HttpMethod.GET, HttpMethod.HEAD), dirHandler);
        router.addRoute("/coffee", HttpMethod.GET, new TeaPotController());
        router.addRoute("/tea", HttpMethod.GET, new TeaPotController());
        router.addRoute("/form", Arrays.asList(HttpMethod.GET, HttpMethod.POST), postFileHandler);
        router.addRoute("/put-target", Arrays.asList(HttpMethod.GET, HttpMethod.PUT), editFileController);
        router.addRoute("/redirect", HttpMethod.GET, new RedirectController());
        router.addRoute("/file1", HttpMethod.GET, dirHandler);
        router.addRoute("/file2", HttpMethod.GET, dirHandler);
        router.addRoute("/text-file.txt", HttpMethod.GET, dirHandler);
        router.addRoute("/image.jpeg", HttpMethod.GET, dirHandler);
        router.addRoute("/image.gif", HttpMethod.GET, dirHandler);
        router.addRoute("/image.png", HttpMethod.GET, dirHandler);
        router.addRoute("/cat-form", Arrays.asList(HttpMethod.GET, HttpMethod.POST), new CatFormController(router, IFileIO));
        router.addRoute("/method_options", Arrays.asList(HttpMethod.OPTIONS, HttpMethod.GET, HttpMethod.PUT, HttpMethod.POST, HttpMethod.HEAD), new MethodOptionsController(router));
        router.addRoute("/method_options2", Arrays.asList(HttpMethod.GET, HttpMethod.OPTIONS, HttpMethod.HEAD), new MethodOptionsController(router));
        router.addRoute("/parameters", HttpMethod.GET, new ParameterDecodeController());
        router.addRouteWithAuth("/logs", HttpMethod.GET, logsController, logAuthHandler);
        router.addRoute("/cookie", HttpMethod.GET, cookiesController);
        router.addRoute("/eat_cookie", HttpMethod.GET, cookiesController);
        router.addRoute("/patch-content.txt", Arrays.asList(HttpMethod.GET, HttpMethod.PATCH), patchController);
        router.addRoute("/partial_content.txt", HttpMethod.GET, partialContentController);
        return router;
    }
}




