package http.connectionProcess;

import http.request.error.InvalidRequestException;
import http.router.NoAuthOnRouteException;

import java.io.IOException;

public interface IHttpConnectionToProcess {

    void execute() throws IOException, InvalidRequestException, NoAuthOnRouteException;
}
