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
            case GET: handler = new GetRequestHandler(fileIO);
                        break;
            case HEAD: handler = new HeadRequestHandler(fileIO);
                        break;
            case PUT: handler = new PutRequestHandler(fileIO);
                        break;
            case POST: handler = new PostRequestHandler(fileIO);
                        break;
            case OPTIONS: handler = new OptionsRequestHandler();
                        break;
            case DELETE: handler = new DeleteRequestHandler(fileIO);
                        break;
            default: handler = new InvalidRequestHandler();
                         break;
        }
        return handler;
    }
}
