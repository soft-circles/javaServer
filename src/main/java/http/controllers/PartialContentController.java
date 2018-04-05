package http.controllers;

import http.IO.file.IFileIO;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.status.HttpStatus;
import http.status.InvalidStatusCodeException;
import http.utils.ContentReader;

import java.io.IOException;

public class PartialContentController implements IController {
    private final IFileIO IFileIO;

    public PartialContentController(IFileIO IFileIO) {
        this.IFileIO = IFileIO;
    }

    @Override
    public HttpResponse generateResponse(HttpRequest httpRequest) throws IOException, InvalidStatusCodeException {
        int end;
        int start;
        HttpResponse httpResponse = new HttpResponse();
        byte[] bytes = IFileIO.readFile(httpRequest.path());
        int totalBytes = bytes.length;
        String[] partialRange = httpRequest.getPartialRange();
        if(partialRange.length == 1) {
            start = Integer.parseInt(partialRange[0]);
            end = totalBytes;
        } else if (partialRange[0].equals("")) {
            start = totalBytes - Integer.parseInt(partialRange[1]);
            end = totalBytes;
        } else {
            start = Integer.parseInt(partialRange[0]);
            end = Integer.parseInt(partialRange[1]) + 1;
        }
        if (start > totalBytes || end > totalBytes) {
            httpResponse.setStatus("416");
            httpResponse.addHeader("Content-Range", "bytes " + "*/" + String.valueOf(bytes.length));
            httpResponse.setBody("");
        } else {
            httpResponse.setStatus("206");
            httpResponse.setBody(IFileIO.readFile(httpRequest.path(), start, end));
            httpResponse.addHeader("Content-Range", "bytes " + String.valueOf(start) + "-" + String.valueOf(end - 1) + "/" + String.valueOf(bytes.length));
            httpResponse.addHeader("Content-Type", ContentReader.getFileType(httpRequest.path()));
        }
        httpResponse.setReasonPhrase(HttpStatus.message(httpResponse.getStatus()));
        return httpResponse;
    }
}
