package http.request;

import http.method.httpMethod;
import http.request.error.InvalidRequestException;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestParser {
    private int contentLength;
    private httpMethod method;
    private String requestLine;
    private String path;
    private String version;
    private HashMap<String, String> headers;

    public HttpRequestParser(String request) throws InvalidRequestException {
            parseRequestLine(getFirstLine(request));
            this.headers = parseHeaders(request);
            if (headers.containsKey("Content-Length"))
                setContentLength(Integer.parseInt(headers.get("Content-Length")));
    }

    private String getFirstLine(String rawRequest) {
         return rawRequest.split("\n")[0];
    }

    private HashMap<String, String> parseHeaders(String rawRequest) {
         HashMap<String, String> headers = new HashMap<>();
         try {
             String rawHeaders = rawRequest.split("\n", 2)[1];
             String[] headerLines = rawHeaders.split("\n");
             for (String line : headerLines) {
                 String[] lineSplit = line.split(": ", 2);
                 if (lineSplit.length == 2) {
                     headers.put(lineSplit[0], lineSplit[1]);
                 }
             }
         } catch (Exception e) {

         }
         return headers;
     }

    private void parseRequestLine(String requestLine) {
        setRequestLine(requestLine);
        try {
            setMethod(httpMethod.valueOf(requestLine.split(" ")[0]));
        } catch (Exception e) {
            setMethod(httpMethod.INVALID);
        }
        try {
            String[] pathAndParameters = requestLine.split(" ")[1].split("\\?", 2);
            setPath(pathAndParameters[0]);
            setVersion(requestLine.split(" ")[2]);
        } catch (Exception e) {
            setPath("/");
            setVersion("HTTP/1.1");
        }
    }

    public int getContentLength() {
        return contentLength;
    }

    private void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public httpMethod getMethod() {
        return method;
    }

    public void setMethod(httpMethod method) {
        this.method = method;
    }

    public String getRequestLine() {
        return requestLine;
    }

    private void setRequestLine(String requestLine) {
        this.requestLine = requestLine;
    }

    public String getPath() {
        return path;
    }

    private void setPath(String path) {
        this.path = path;
    }

    public String getVersion() {
        return version;
    }

    private void setVersion(String version) {
        this.version = version;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}