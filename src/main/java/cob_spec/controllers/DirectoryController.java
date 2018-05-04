package cob_spec.controllers;

import http.IO.IFileIO;
import http.method.HttpMethod;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.utils.ContentReader;
import http.utils.HTMLgenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DirectoryController implements IController {
    private HttpRequest httpRequest;
    private HttpResponse httpResponse;
    private IFileIO IFileIO;

    public DirectoryController(IFileIO IFileIO) {
        httpResponse = new HttpResponse();
        this.IFileIO = IFileIO;
    }

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) throws IOException{
        this.httpRequest = httpRequest;
        if (httpRequest.method() == HttpMethod.HEAD) {
            return new HeadController().generateResponse(httpRequest);
        }
        return buildResponse();
    }

    private HttpResponse buildResponse() throws IOException {
        buildResponseBody();
        buildResponseHeaders();
        return httpResponse;
    }

    private void buildResponseHeaders() {
        httpResponse.addHeader("Content-Type", ContentReader.getFileType(httpRequest.path()));
    }

    private void buildResponseBody() throws IOException {
        if (IFileIO.isDirectory(httpRequest.path())) {
            httpResponse.setBody(generateDirectoryList());
        } else if (IFileIO.isFile(httpRequest.path())){
            httpResponse.setBody(IFileIO.readFile(httpRequest.path()));
        }
        httpResponse.addHeader("Content-Length", String.valueOf(httpResponse.getBody().length));
        httpResponse.addHeader("Content-Type", ContentReader.getFileType(httpRequest.path()));
    }

    private byte[] generateDirectoryList() {
        return HTMLgenerator.generate(listDirectoryContents(IFileIO.getFilesInDirectory(httpRequest.path())));
    }

    private static List<String> listDirectoryContents(String[] fileNames) {
        List<String> results = new ArrayList<>();
        for (String fileName : fileNames) {
            results.add(fileName);
        }
        return results;
    }
}
