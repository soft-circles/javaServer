package http.controllers;

import http.IO.file.FileIO;
import http.method.httpMethod;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.HttpStatus;
import http.status.InvalidStatusCodeException;
import http.utils.ContentReader;
import http.utils.HTMLgenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DirectoryController implements IController {
    private HttpRequest httpRequest;
    private HttpResponse httpResponse;
    private FileIO fileIO;

    public DirectoryController(FileIO fileIO) {
        httpResponse = new HttpResponse();
        this.fileIO = fileIO;
    }

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) throws IOException, InvalidStatusCodeException {
        this.httpRequest = httpRequest;
        if (httpRequest.method() == httpMethod.HEAD) {
            return new HeadController().generateResponse(httpRequest);
        }
        return buildResponse();
    }

    private HttpResponse buildResponse() throws IOException, InvalidStatusCodeException {
        buildResponseBody();
        buildResponseHeaders();
        return httpResponse;
    }

    private void buildResponseHeaders() {
        httpResponse.addHeader("Content-Type", ContentReader.getFileType(httpRequest.path()));
    }

    private void buildResponseBody() throws IOException, InvalidStatusCodeException {
        if (fileIO.isDirectory(httpRequest.path())) {
            httpResponse.setBody(generateDirectoryList());
        } else if (fileIO.isFile(httpRequest.path())){
            httpResponse.setBody(fileIO.readFile(httpRequest.path()));
            httpResponse.addHeader("Content-Length", String.valueOf(httpResponse.getBody().length));
            httpResponse.addHeader("Content-Type", ContentReader.getFileType(httpRequest.path()));
        }
        httpResponse.setReasonPhrase(HttpStatus.message(httpResponse.getStatus()));
    }

    private byte[] generateDirectoryList() {
        return HTMLgenerator.generate(listDirectoryContents(fileIO.getFilesInDirectory(httpRequest.path())));
    }

    private static List<String> listDirectoryContents(String[] fileNames) {
        List<String> results = new ArrayList<>();
        for (String fileName : fileNames) {
            results.add(fileName);
        }
        return results;
    }
}
