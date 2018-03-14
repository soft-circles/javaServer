package HttpResponse;

import Directory.FileFetcher;
import Directory.HTMLgenerator;
import FileIO.FileIO;
import HttpRequest.HttpRequest;
import HttpStatus.StatusMessages;

import java.nio.file.FileSystems;
import java.util.HashMap;

public class HttpResponseBuilder {
    private static HttpResponse httpResponse;
    private static FileIO fileIO;
    private HttpResponseBuilder(){
    }

    static public HttpResponse build(HttpRequest httpRequest) {
        httpResponse = new HttpResponse(new HashMap<>());
        assignAttributes(httpResponse, httpRequest);
        setStatus(httpResponse, httpRequest);
        setReasonPhrase(httpResponse);
        return httpResponse;
    }

    private static void setReasonPhrase(HttpResponse httpResponse) {
        httpResponse.reason_phrase = StatusMessages.STATUSES.get(Integer.parseInt(httpResponse.status)).toString();
    }

    private static void setStatus(HttpResponse httpResponse, HttpRequest httpRequest) {
        fileIO = new FileIO(FileSystems.getDefault().getPath(httpRequest.path()).toString());
        System.out.println(fileIO.workingDirectory);
        if (fileIO.isDirectory(httpRequest.path()) || fileIO.exists(httpRequest.path())) {
            httpResponse.setStatus("200");
            String html = HTMLgenerator.generate(FileFetcher.fetch(httpRequest.path()));
            httpResponse.addToBody(html);
        } else {
            httpResponse.setStatus("404");
        }
    }

    private static void assignAttributes(HttpResponse httpResponse, HttpRequest httpRequest) {
        httpResponse.http_version = "HTTP/2.0";
        httpResponse.request_uri = httpRequest.path();
        httpResponse.request_http_version = httpRequest.version();
        httpResponse.request_method = httpRequest.method();
    }
}
