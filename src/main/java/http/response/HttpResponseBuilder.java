package http.response;

import http.handlers.directory.FileFetcher;
import http.handlers.directory.HTMLgenerator;
import http.IO.file.FileIO;
import http.request.HttpRequest;
import http.status.StatusMessages;

import java.nio.file.FileSystems;
import java.util.HashMap;

public class HttpResponseBuilder {
    private HttpResponseBuilder(){
    }

    public static HttpResponse build(HttpRequest httpRequest) {
        HttpResponse httpResponse = new HttpResponse(new HashMap<>());
        assignAttributes(httpResponse, httpRequest);
        setStatus(httpResponse, httpRequest);
        setReasonPhrase(httpResponse);
        return httpResponse;
    }

    private static void setReasonPhrase(HttpResponse httpResponse) {
        httpResponse.setReasonPhrase(StatusMessages.STATUSES.get(Integer.parseInt(httpResponse.getStatus())).toString());
    }

    private static void setStatus(HttpResponse httpResponse, HttpRequest httpRequest) {
        FileIO fileIO = new FileIO(FileSystems.getDefault().getPath(httpRequest.path()).toString());
        if (fileIO.isDirectory(httpRequest.path()) || fileIO.exists(httpRequest.path())) {
            httpResponse.setStatus("200");
            String html = HTMLgenerator.generate(FileFetcher.fetch(httpRequest.path()));
            httpResponse.addToBody(html);
        } else {
            httpResponse.setStatus("404");
        }
    }

    private static void assignAttributes(HttpResponse httpResponse, HttpRequest httpRequest) {
        httpResponse.setRequestHttpVersion("HTTP/2.0");
        httpResponse.setRequestUri(httpRequest.path());
        httpResponse.setRequestHttpVersion(httpRequest.version());
        httpResponse.setRequestMethod(httpRequest.method());
    }
}
