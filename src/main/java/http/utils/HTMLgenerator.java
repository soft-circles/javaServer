package http.utils;

import java.util.List;

public class HTMLgenerator {

    private HTMLgenerator() { }

    public static byte[] generate(List<String> files) {
        String template = "<DOCTYPE! html>" +
                "<html>" +
                "<head>" +
                "<meta charset=\"utf-8\">" +
                "<title></title>" +
                "</head>" +
                "<body>" +
                generateListItems(files) +
                "</body>" +
                "</html>";

        return template.getBytes();
    }

    private static String generateListItems(List<String> files) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<ul>");
        for (String file : files) {
            stringBuilder.append("<li><a href=/").append(file).append(">").append(file).append("</a></li>");
        }
        stringBuilder.append("</ul>");
        return stringBuilder.toString();
    }
}
