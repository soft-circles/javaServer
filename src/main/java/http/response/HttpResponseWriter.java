package http.response;

import java.io.ByteArrayOutputStream;
import java.util.Map;


public class HttpResponseWriter {

    private final byte[] NEW_LINE = "\r\n".getBytes();

    public byte[] sendHttpResponse(HttpResponse httpResponse) {
        return responseAsBytes(httpResponse);
    }

    private byte[] responseAsBytes(HttpResponse httpResponse) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        writeToByteArrayOS(stream, httpResponse.statusLine());
        writeToByteArrayOS(stream, NEW_LINE);
        writeToByteArrayOS(stream, headers(httpResponse.getHeaders()));
        writeToByteArrayOS(stream, NEW_LINE);
        writeToByteArrayOS(stream, httpResponse.getBody());
        return stream.toByteArray();
    }

    private String headers(Map<String, String> headers) {
        StringBuilder builder = new StringBuilder();
        headers.forEach((key, value) -> builder.append(key).append(": ").append(value).append("\r\n"));
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
