package http.handlers.directory;

import http.utils.HTMLgenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HTMLgeneratorTest {

    @Test
    void generate() {
        assertEquals(expectedOutput().getBytes().length, HTMLgenerator.generate(files()).length);
    }

    private List<String> files() {
        List<String> files = new ArrayList<>();
        files.add("text.txt");
        files.add("example.html");
        files.add("application.js");
        return files;
    }

    private String expectedOutput() {
        return "<DOCTYPE! html>" +
                "<html>" +
                "<head>" +
                "<meta charset=\"utf-8\">" +
                "<title></title>" +
                "</head>" +
                "<body>" +
                "<ul>"+
                "<li><a href=/text.txt>text.txt</a></li>" +
                "<li><a href=/example.html>example.html</a></li>" +
                "<li><a href=/application.js>application.js</a></li>" +
                "</ul>" +
                "</body>" +
                "</html>";
    }
}