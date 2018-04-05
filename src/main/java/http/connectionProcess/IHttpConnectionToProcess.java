package http.connectionProcess;

import http.IO.file.InvalidPathException;
import http.request.error.InvalidRequestException;
import http.router.NoAuthOnRouteException;
import http.status.InvalidStatusCodeException;

import java.io.IOException;

public interface IHttpConnectionToProcess {

    void execute() throws IOException, InvalidRequestException, NoAuthOnRouteException, InvalidStatusCodeException, InvalidPathException;
}
