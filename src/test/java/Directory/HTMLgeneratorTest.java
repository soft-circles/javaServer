package Directory;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HTMLgeneratorTest {

    @Test
    void generate() {
        assertEquals(expectedOutput(),HTMLgenerator.generate(files()));
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
                "<li>text.txt</li>" +
                "<li>example.html</li>" +
                "<li>application.js</li>" +
                "</ul>" +
                "</body>" +
                "</html>";
    }
}