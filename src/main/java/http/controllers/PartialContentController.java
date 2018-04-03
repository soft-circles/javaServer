package http.controllers;

import http.IO.file.FileIO;
import http.IO.file.InvalidPathException;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.HttpStatus;
import http.status.InvalidStatusCodeException;
import http.utils.ContentReader;

import java.io.IOException;

public class PartialContentController implements IController {
    private final FileIO fileIO;

    public PartialContentController(FileIO fileIO) {
        this.fileIO = fileIO;
    }

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) throws IOException, InvalidPathException, InvalidStatusCodeException {
        int last;
        int first;
        HttpResponse httpResponse = new HttpResponse();
        byte[] bytes = fileIO.readFile(httpRequest.path());
        first = httpRequest.getPartialRange()[0];
        if (httpRequest.getPartialRange()[1] < 0) {
            last = bytes.length;
        } else {
            last = httpRequest.getPartialRange()[1];
        }
        if (first > bytes.length || last > bytes.length) {
           httpResponse.setStatus("416");
           httpResponse.setBody("");
        } else {
            httpResponse.setStatus("206");
            int totalLength = last - first;
            byte[] body = new byte[totalLength];
            System.arraycopy(bytes, first, body, 0, totalLength);
            httpResponse.setBody(body);
            httpResponse.addHeader("Content-Range", "bytes " + String.valueOf(first) + "-" + String.valueOf(last - 1) + "/" + String.valueOf(bytes.length));
            httpResponse.addHeader("Content-Type", ContentReader.getFileType(httpRequest.path()));
        }
        httpResponse.setReasonPhrase(HttpStatus.message(httpResponse.getStatus()));
        return httpResponse;
    }
}
