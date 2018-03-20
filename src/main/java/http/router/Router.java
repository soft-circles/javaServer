package http.router;

import http.handlers.request.*;
import http.request.HttpRequest;

public class Router {

    private Router(){}

    public static IRequestHandler getHandler(HttpRequest httpRequest) {
        return selectHandler(httpRequest);
    }

    private static IRequestHandler selectHandler(HttpRequest httpRequest) {
        IRequestHandler handler;
        switch (httpRequest.method()) {
            case GET: handler = new GetRequestHandler(httpRequest);
                        break;
            case HEAD: handler = new HeadRequestHandler(httpRequest);
                        break;
            case PUT: handler = new PutRequestHandler(httpRequest);
                        break;
            case POST: handler = new PostRequestHandler(httpRequest);
                        break;
            case OPTIONS: handler = new OptionsRequestHandler(httpRequest);
                        break;
            case DELETE: handler = new DeleteRequestHandler(httpRequest);
                        break;
            default: handler = new InvalidRequestHandler(httpRequest);
                         break;
        }
        return handler;
    }
}
