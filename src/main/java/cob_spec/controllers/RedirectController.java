package cob_spec.controllers;

import http.request.HttpRequest;
import http.response.HttpResponse;
import http.router.Redirects;
import http.status.Status;

import java.util.Set;


public class RedirectController implements IController {
    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus(Status.Found);
        httpResponse.addHeader("Location", getRedirectLocation(httpRequest.path()));
        httpResponse.setEmptyBody();
        return httpResponse;
    }

    private String getRedirectLocation(String path) {
        Set<String> strings = Redirects.VALID_REDIRECTS.keySet();
        for (String route : strings) {
            if (Redirects.isRedirect(path)) {
                return route;
            }
        }
        return "";
    }
}
