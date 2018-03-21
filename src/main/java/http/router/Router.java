package http.router;

import http.IO.file.FileIO;
import http.handlers.request.*;
import http.request.HttpRequest;

public class Router {

    private Router(){}

    public static IRequestHandler getHandler(HttpRequest httpRequest, FileIO fileIO) {
        return selectHandler(httpRequest, fileIO);
    }

    private static IRequestHandler selectHandler(HttpRequest httpRequest, FileIO fileIO) {
        IRequestHandler handler;
        switch (httpRequest.method()) {
            case GET: handler = new GetRequestHandler(httpRequest, fileIO);
                        break;
            case HEAD: handler = new HeadRequestHandler(httpRequest);
                        break;
            case PUT: handler = new PutRequestHandler(httpRequest, fileIO);
                        break;
            case POST: handler = new PostRequestHandler(httpRequest, fileIO);
                        break;
            case OPTIONS: handler = new OptionsRequestHandler(httpRequest);
                        break;
            case DELETE: handler = new DeleteRequestHandler(httpRequest, fileIO);
                        break;
            default: handler = new InvalidRequestHandler();
                         break;
        }
        return handler;
    }
}
