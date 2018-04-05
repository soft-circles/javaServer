package http.IO.file;

import java.io.IOException;

public interface IFileIO {
    boolean exists(String path);

    boolean isFile(String path);

    boolean isDirectory(String path);

    String getWorkingDirectory();

    byte[] readFile(String fileName) throws IOException;

    byte[] readFile(String fileName, int start, int end) throws IOException;

    String[] getFilesInDirectory(String path);

    void createFile(String path, byte[] data) throws IOException;

    boolean deleteFile(String path) throws IOException;
}
