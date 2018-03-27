package http.response;


import http.socket.IClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;


public class HttpResponseWriter {

    private final byte[] NEWLINE = "\r\n".getBytes();

    public void sendHttpResponse(IClient client, HttpResponse httpResponse) throws IOException {
        byte[] responseBytes = responseAsBytes(httpResponse);
        client.getOutputStream().write(responseBytes);
    }

    private byte[] responseAsBytes(HttpResponse httpResponse) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        writeToByteArrayOS(stream, httpResponse.statusLine());
        writeToByteArrayOS(stream, NEWLINE);
        writeToByteArrayOS(stream, headers(httpResponse.getHeaders()));
        writeToByteArrayOS(stream, NEWLINE);
        writeToByteArrayOS(stream, httpResponse.getBody());
        return stream.toByteArray();
    }

    private String headers(Map<String, String> headers) {
        StringBuilder builder = new StringBuilder();
        headers.forEach((key, value) -> builder.append(key + ": " + value + "\r\n"));
        return builder.toString();
    }

    private void writeToByteArrayOS(ByteArrayOutputStream stream, String string) {
        byte[] bytesToWrite = string.getBytes();
        writeToByteArrayOS(stream, bytesToWrite);
    }

    private void writeToByteArrayOS(ByteArrayOutputStream stream, byte[] bytesToWrite) {
        stream.write(bytesToWrite, 0, bytesToWrite.length);
    }
}
