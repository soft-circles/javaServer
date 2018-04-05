package http.IO.file;

import java.io.IOException;

public class MockFileIO implements IFileIO {
    @Override
    public boolean exists(String path) {
        return false;
    }

    @Override
    public boolean isFile(String path) {
        if (path.equals("/cat-form"))
            return true;
        else {
            return false;
        }
    }

    @Override
    public boolean isDirectory(String path) {
        if (path.equals("/directory")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getWorkingDirectory() {
        return null;
    }

    @Override
    public byte[] readFile(String fileName) {
        return new byte[] {0, 74, 53, 85};
    }

    @Override
    public byte[] readFile(String fileName, int start, int end) {
        return new byte[0];
    }

    @Override
    public String[] getFilesInDirectory(String path) {
        return new String[0];
    }

    @Override
    public void createFile(String path, byte[] data) {

    }

    @Override
    public boolean deleteFile(String path) {
        return false;
    }
}
