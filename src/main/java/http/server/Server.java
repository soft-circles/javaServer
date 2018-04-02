package http.server;
import http.controllers.CatFormController;
import http.controllers.IController;
import http.IO.ClientInput;
import http.IO.ClientOutput;
import http.IO.file.FileIO;
import http.IO.file.InvalidPathException;
import http.controllers.DirectoryController;
import http.controllers.EditFileController;
import http.controllers.PostFileContentsController;
import http.controllers.RedirectController;
import http.controllers.TeaPotController;
import http.method.httpMethod;
import http.request.HttpRequest;
import http.request.error.InvalidRequestException;
import http.response.HttpResponse;
import http.response.HttpResponseWriter;
import http.router.Router;
import http.socket.IClient;
import http.socket.Client;
import http.status.InvalidStatusCodeException;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Arrays;

public class Server {
    private Router router;

    public Server(int portNum, String directory) throws IOException, InvalidRequestException, InvalidPathException, InvalidStatusCodeException {
        router = configServer(new FileIO(directory));
        System.out.println("Server starting on port: " + String.valueOf(portNum) + " " + "with public directory: " + directory);
        ServerSocket socket = new ServerSocket(portNum);
        while (true) {
        System.out.println("Waiting for client...");
        IClient client = new Client(socket);
        System.out.println("Client connected");
        ClientInput clientInput = new ClientInput(client.getInputStream());
        String rawRequest = clientInput.getRawRequestString();
        HttpRequest httpRequest = new HttpRequest(rawRequest);
        if (httpRequest.getContentLength() > 0) {
            httpRequest.setBody(clientInput.getBytes(httpRequest.getContentLength()));
        }
        System.out.println(rawRequest);
        IController handler = router.getController(httpRequest.path(), httpRequest.method());
        HttpResponse httpResponse = handler.generateResponse(httpRequest);
        ClientOutput clientOutput = new ClientOutput(client.getOutputStream());
        byte[] byteResponse = new HttpResponseWriter().sendHttpResponse(httpResponse);
        client.getOutputStream().write(byteResponse);
        client.closeConnection();
        System.out.println(httpResponse.fullResponse());
        }
    }

    private Router configServer(FileIO fileIO) {
        Router router = new Router();
        DirectoryController dirHandler = new DirectoryController(fileIO);
        PostFileContentsController postFileHandler = new PostFileContentsController(router, fileIO);
        EditFileController editFileController = new EditFileController(fileIO);

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
        return router;
    }
}




