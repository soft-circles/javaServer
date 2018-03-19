package http.router;

import http.handlers.request.*;
import http.request.HttpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RouterTest {

    private HttpRequest httpRequestGet,
                        httpRequestPost,
                        httpRequestDelete,
                        httpRequestOptions,
                        httpRequestPut,
                        httpRequestInvalid;

    @BeforeEach
    void setUp() {
        httpRequestGet = new HttpRequest(rawGetRequest());
        httpRequestPut = new HttpRequest(rawPutRequest());
        httpRequestPost = new HttpRequest(rawPostRequest());
        httpRequestDelete = new HttpRequest(rawDeleteRequest());
        httpRequestOptions = new HttpRequest(rawOptionsRequest());
        httpRequestInvalid = new HttpRequest(rawInvalidRequest());
    }



    @Test
    void getResponse() {
        assertEquals(DirectoryHandler.class, Router.getHandler(httpRequestGet).getClass());
        assertEquals(PutRequestHandler.class, Router.getHandler(httpRequestPut).getClass());
        assertEquals(PostRequestHandler.class, Router.getHandler(httpRequestPost).getClass());
        assertEquals(DeleteRequestHandler.class, Router.getHandler(httpRequestDelete).getClass());
        assertEquals(OptionsRequestHandler.class, Router.getHandler(httpRequestOptions).getClass());
        assertEquals(InvalidRequestHandler.class, Router.getHandler(httpRequestInvalid).getClass());
    }


    private String rawGetRequest() {
        return "GET / HTTP/1.1\r\n" +
                "Host: www.nowhere123.com\r\n" +
                "Accept: image/gif, image/jpeg, */*\r\n" +
                "Accept-Language: en-us\r\n" +
                "Accept-Encoding: gzip, deflate\r\n" +
                "User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)\r\n\r\n";
    }

    private String rawPutRequest() {
        return "PUT / HTTP/1.1\r\n" +
                "Host: www.nowhere123.com\r\n" +
                "Accept: image/gif, image/jpeg, */*\r\n" +
                "Accept-Language: en-us\r\n" +
                "Accept-Encoding: gzip, deflate\r\n" +
                "User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)\r\n\r\n";
    }

    private String rawPostRequest() {
        return "POST / HTTP/1.1\r\n" +
                "Host: www.nowhere123.com\r\n" +
                "Accept: image/gif, image/jpeg, */*\r\n" +
                "Accept-Language: en-us\r\n" +
                "Accept-Encoding: gzip, deflate\r\n" +
                "User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)\r\n\r\n";

    }   private String rawOptionsRequest() {
        return "OPTIONS / HTTP/1.1\r\n" +
                "Host: www.nowhere123.com\r\n" +
                "Accept: image/gif, image/jpeg, */*\r\n" +
                "Accept-Language: en-us\r\n" +
                "Accept-Encoding: gzip, deflate\r\n" +
                "User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)\r\n\r\n";
    }

    private String rawDeleteRequest() {
        return "DELETE / HTTP/1.1\r\n" +
                "Host: www.nowhere123.com\r\n" +
                "Accept: image/gif, image/jpeg, */*\r\n" +
                "Accept-Language: en-us\r\n" +
                "Accept-Encoding: gzip, deflate\r\n" +
                "User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)\r\n\r\n";
    }

    private String rawInvalidRequest() {
        return "TEST / HTTP/2000\r\n";
    }
}