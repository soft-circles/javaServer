package http.connectionProcess;

import http.client.ClientInput;
import http.client.IClient;
import http.controllers.IController;
import http.controllers.InternalServerErrorController;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.response.HttpResponseWriter;
import http.router.NoAuthOnRouteException;
import http.router.Router;
import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class HttpConnectionToProcess implements IHttpConnectionToProcess {
    private final HttpResponseWriter httpResponseWriter;
    private final IClient client;
    private final Router router;

    public HttpConnectionToProcess(IClient client, Router router, HttpResponseWriter httpResponseWriter) {
        this.client = client;
        this.router = router;
        this.httpResponseWriter = httpResponseWriter;
    }

    public void execute() {
        ClientInput clientInput = getClientInput();
        String rawRequestString = clientInput.getRawRequestString();
        HttpRequest httpRequest = new HttpRequest(rawRequestString);
        setContentLength(clientInput, httpRequest);
        HttpResponse httpResponse = getHttpResponse(httpRequest);
        byte[] byteResponse = httpResponseWriter.sendHttpResponse(httpResponse);
        writeToClient(byteResponse);
    }

    private void writeToClient(byte[] byteResponse) {
        try {
            client.write(byteResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            client.closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HttpResponse getHttpResponse(HttpRequest httpRequest) {
        HttpResponse httpResponse = null;
        try {
            if (router.hasAuth(httpRequest.path())) {
                httpResponse = getHttpResponseWithAuth(httpRequest);
            } else  {
                httpResponse = getHttpResponseOnRoute(httpRequest);
            }
        } catch (NoAuthOnRouteException e) {
            e.printStackTrace();
            httpResponse = new InternalServerErrorController().generateResponse(httpRequest);

        } catch (IOException e) {
            e.printStackTrace();
            httpResponse = new InternalServerErrorController().generateResponse(httpRequest);
        }
        return httpResponse;
    }

    private void setContentLength(ClientInput clientInput, HttpRequest httpRequest) {
        if (httpRequest.getContentLength() > 0) {
            httpRequest.setBody(clientInput.getBytes(httpRequest.getContentLength()));
        }
    }

    private ClientInput getClientInput() {
        ClientInput clientInput = null;
        try {
            clientInput = new ClientInput(client.getInputStream());
        } catch (IOException e) {
            try {
                clientInput = new ClientInput(IOUtils.toInputStream("Error", "UTF-8"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return clientInput;
    }

    private HttpResponse getHttpResponseOnRoute(HttpRequest httpRequest) throws IOException {
        IController handler = router.getController(httpRequest.path(), httpRequest.method());
        return handler.generateResponse(httpRequest);
    }

    private HttpResponse getHttpResponseWithAuth(HttpRequest httpRequest) throws IOException, NoAuthOnRouteException {
        IController handler = router.getControllerWithAuth(httpRequest);
        return handler.generateResponse(httpRequest);
    }
}
