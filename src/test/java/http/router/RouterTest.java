package http.router;

import http.IO.file.FileIO;
import http.handlers.request.*;
import http.request.HttpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class RouterTest {

    private HttpRequest httpRequestGet,
                        httpRequestPost,
                        httpRequestDelete,
                        httpRequestOptions,
                        httpRequestPut,
                        httpRequestInvalid;
    private FileIO fileIO;

    @BeforeEach
    void setUp() {
        fileIO = new FileIO("./public");
        httpRequestGet = new HttpRequest(rawGetRequest());
        httpRequestPut = new HttpRequest(rawPutRequest());
        httpRequestPost = new HttpRequest(rawPostRequest());
        httpRequestDelete = new HttpRequest(rawDeleteRequest());
        httpRequestOptions = new HttpRequest(rawOptionsRequest());
        httpRequestInvalid = new HttpRequest(rawInvalidRequest());
    }



    @Test
    void returnsAppropriateHandler() {
        assertEquals(GetRequestHandler.class, Router.getHandler(httpRequestGet, fileIO).getClass());
        assertEquals(PutRequestHandler.class, Router.getHandler(httpRequestPut, fileIO).getClass());
        assertEquals(PostRequestHandler.class, Router.getHandler(httpRequestPost, fileIO).getClass());
        assertEquals(DeleteRequestHandler.class, Router.getHandler(httpRequestDelete, fileIO).getClass());
        assertEquals(OptionsRequestHandler.class, Router.getHandler(httpRequestOptions, fileIO).getClass());
        assertEquals(InvalidRequestHandler.class, Router.getHandler(httpRequestInvalid, fileIO).getClass());
    }


    private String rawGetRequest() {
        return "GET / HTTP/1.1\r\n";
    }

    private String rawPutRequest() {
        return "PUT / HTTP/1.1\r\n";
    }

    private String rawPostRequest() {
        return "POST / HTTP/1.1\r\n";

    }   private String rawOptionsRequest() {
        return "OPTIONS / HTTP/1.1\r\n";
    }

    private String rawDeleteRequest() {
        return "DELETE / HTTP/1.1\r\n";
    }

    private String rawInvalidRequest() {
        return "TEST / HTTP/2000\r\n";
    }
}