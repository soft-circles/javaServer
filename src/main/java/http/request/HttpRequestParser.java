package http.request;

import http.handlers.cookie.Cookie;
import http.method.httpMethod;
import http.request.error.InvalidRequestException;
import http.router.Router;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestParser {
    private int contentLength;
    private httpMethod method;
    private String requestLine;
    private String path;
    private String version;
    private HashMap<String, String> headers;
    private Map<String, String> parameters;
    private ArrayList<Cookie> cookies;

    public HttpRequestParser(String request) throws InvalidRequestException {
        this.cookies = new ArrayList<>();
        parseRequestLine(getFirstLine(request));
        this.headers = parseHeaders(request);
        setCookies();
        if (headers.containsKey("Content-Length")) {
            setContentLength(Integer.parseInt(headers.get("Content-Length")));
        }

    }

    private void setCookies() {
        if (this.headers.containsKey("Cookie")) {
            try {
                String[] cookie = this.headers.get("Cookie").split(";");
                for (String cookieString : cookie) {
                    String[] nameAndValue = cookieString.split("=");
                    cookies.add(new Cookie(nameAndValue[0], nameAndValue[1]));
                }
            } catch (Exception e) {
                String cookie = this.headers.get("Cookie");
                String[] nameAndValue = cookie.split("=");
                cookies.add(new Cookie(nameAndValue[0], nameAndValue[1]));
            }
        }
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
        assignMethod(requestLine);
        assignPathAndParameters(requestLine);
        setVersion(requestLine.split(" ")[2]);
    }

    private void assignPathAndParameters(String requestLine) {
        try {
            String[] pathAndParameters = requestLine.split(" ")[1].split("\\?", 2);
            setPath(pathAndParameters[0]);
            if (pathAndParameters.length > 1) {
                setParameters(parseParameters(pathAndParameters[1]));
            }
        } catch (Exception e) {
            setPath("/");
            setVersion("HTTP/1.1");
        }
    }

    private void assignMethod(String requestLine) {
        try {
            setMethod(httpMethod.valueOf(requestLine.split(" ")[0]));
        } catch (Exception e) {
            setMethod(httpMethod.INVALID);
        }
    }

    private Map<String, String>parseParameters(String parameterString) throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<>();
        String[] split = parameterString.split("&");
        for (String pair : split) {
            String[] split1 = pair.split("=");
            params.put(split1[0], URLDecoder.decode(split1[1], "UTF-8"));
        }
        return params;
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

    public void setParameters(Map<String,String> parameters) {
        this.parameters = parameters;
    }

    public Map<String, String> getParameters() {
        return this.parameters;
    }

    public ArrayList<Cookie> getCookies() {
        return this.cookies;
    }
}