package http.connectionProcess;

import http.IO.ClientInput;
import http.IO.file.InvalidPathException;
import http.controllers.IController;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.response.HttpResponseWriter;
import http.router.NoAuthOnRouteException;
import http.router.Router;
import http.socket.IClient;
import http.status.InvalidStatusCodeException;

import java.io.IOException;

public class HttpConnectionToProcess implements IHttpConnectionToProcess {
    private final HttpResponseWriter httpResponseWriter;
    private final IClient client;
    private final Router router;

    public HttpConnectionToProcess(IClient client, Router router, HttpResponseWriter httpResponseWriter) {
        this.client = client;
        this.router = router;
        this.httpResponseWriter = httpResponseWriter;
    }

    public void execute() throws IOException, NoAuthOnRouteException, InvalidStatusCodeException, InvalidPathException {
        ClientInput clientInput = new ClientInput(client.getInputStream());
        String rawRequestString = clientInput.getRawRequestString();
        HttpRequest httpRequest = new HttpRequest(rawRequestString);
        if (httpRequest.getContentLength() > 0) {
            httpRequest.setBody(clientInput.getBytes(httpRequest.getContentLength()));
        }
        HttpResponse httpResponse = null;
        if (router.hasAuth(httpRequest.path())) {
            httpResponse = getHttpResponseWithAuth(httpRequest);
        } else  {
            httpResponse = getHttpResponse(httpRequest);
        }
        byte[] byteResponse = httpResponseWriter.sendHttpResponse(httpResponse);
        client.getOutputStream().write(byteResponse);
        client.closeConnection();
    }

    private HttpResponse getHttpResponse(HttpRequest httpRequest) throws IOException, InvalidPathException, InvalidStatusCodeException, NoAuthOnRouteException {
        IController handler = router.getController(httpRequest.path(), httpRequest.method());
        return handler.generateResponse(httpRequest);
    }

    private HttpResponse getHttpResponseWithAuth(HttpRequest httpRequest) throws InvalidPathException, InvalidStatusCodeException, IOException, NoAuthOnRouteException {
        IController handler = router.getControllerWithAuth(httpRequest);
        return handler.generateResponse(httpRequest);
    }
}
