package http.controllers;

import http.controllers.IController;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.router.Redirects;
import http.status.StatusMessages;

import java.util.Iterator;
import java.util.Set;


public class RedirectController implements IController {
    public RedirectController() {
    }

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus("302");
        httpResponse.setReasonPhrase(StatusMessages.STATUSES.get(302).toString());
        httpResponse.addHeader("Location", getRedirectLocation(httpRequest.path()));
        httpResponse.addToBody("");
        return httpResponse;
    }

    private String getRedirectLocation(String path) {
        Set<String> strings = Redirects.VALID_REDIRECTS.keySet();
        Iterator<String> iterator = strings.iterator();
        while(iterator.hasNext()) {
            String route = iterator.next();
            if (Redirects.isRedirect(path)) {
                return route;
            }
        }
        return "";
    }
}
