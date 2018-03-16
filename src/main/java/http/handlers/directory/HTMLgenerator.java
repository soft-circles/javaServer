package http.handlers.directory;

import java.util.List;

public class HTMLgenerator {

    private HTMLgenerator() { }

    public static String generate(List<String> files) {
        return "<DOCTYPE! html>" +
                "<html>" +
                "<head>" +
                "<meta charset=\"utf-8\">" +
                "<title></title>" +
                "</head>" +
                "<body>" +
                generateListItems(files) +
                "</body>" +
                "</html>";
    }

    private static String generateListItems(List<String> files) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<ul>");
        for (String file : files) {
            stringBuilder.append("<li>" + file + "</li>");
        }
        stringBuilder.append("</ul>");
        return stringBuilder.toString();
    }
}
