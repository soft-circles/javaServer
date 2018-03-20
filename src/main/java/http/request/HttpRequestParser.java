package http.request;

import http.method.httpMethod;

import java.util.HashMap;

public class HttpRequestParser {
    public int contentLength;
    public httpMethod method;
    public String requestLine, path, version;
    public HashMap<String, String> headers;

    public HttpRequestParser(String request) {
        parseRequestLine(getFirstLine(request));
        this.headers = parseHeaders(request);
        if (headers.containsKey("Content-Length")) {
            this.contentLength = Integer.parseInt(headers.get("Content-Length"));
        }
    }

    private String getFirstLine(String raw_request) {
         return raw_request.split("\n")[0];
    }

    private HashMap<String, String> parseHeaders(String raw_request) {
         HashMap<String, String> headers = new HashMap<>();
         String rawHeaders = raw_request.split("\n", 2)[1];
         String[] headerLines = rawHeaders.split("\n");
         for (String line : headerLines) {
             String lineSplit[] = line.split(": ", 2);
             if (lineSplit.length == 2) {
                 headers.put(lineSplit[0], lineSplit[1]);
             }
         }
         return headers;
     }

    private void parseRequestLine(String requestLine) {
        this.requestLine = requestLine;
        try {
            this.method = httpMethod.valueOf(requestLine.split(" ")[0]);
        } catch(IllegalArgumentException e) {
            this.method = httpMethod.INVALID;
        }
        String pathAndParameters[] = requestLine.split(" ")[1].split("\\?", 2);
        this.path = pathAndParameters[0];
        this.version = requestLine.split(" ")[2];
    }
}