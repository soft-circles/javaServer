package http.status;

import java.util.HashMap;
import java.util.Map;

public class StatusMessages {

    private StatusMessages(){}

    private static Map<Integer, String> statuses() {
        Map<Integer, String> statuses = new HashMap<>();
        statuses.put(100, "Continue");
        statuses.put(101, "Switching Protocols");
        statuses.put(102, "Processing");
        statuses.put(103, "Early Hints");
        statuses.put(200, "OK");
        statuses.put(201, "Created");
        statuses.put(202, "Accepted");
        statuses.put(203, "Non-Authoritative Information");
        statuses.put(204, "No Content");
        statuses.put(205, "Reset Content");
        statuses.put(206, "Partial Content");
        statuses.put(207, "Multi-Status");
        statuses.put(208, "Already Reported");
        statuses.put(226, "IM Used");
        statuses.put(300, "Multiple Choices");
        statuses.put(301, "Moved Permanently");
        statuses.put(302, "Found");
        statuses.put(303, "See Other");
        statuses.put(304, "Not Modified");
        statuses.put(305, "Use Proxy");
        statuses.put(306, "Switch Proxy");
        statuses.put(307, "Temporary Redirect");
        statuses.put(308, "Permanent Redirect");
        statuses.put(400, "Bad Request");
        statuses.put(401, "Unauthorized");
        statuses.put(402, "Payment Required");
        statuses.put(403, "Forbidden");
        statuses.put(404, "Not Found");
        statuses.put(405, "Method Not Allowed");
        statuses.put(406, "Not Acceptable");
        statuses.put(407, "Proxy Authentication Required");
        statuses.put(408, "Request Timeout");
        statuses.put(409, "Conflict");
        statuses.put(410, "Gone");
        statuses.put(411, "Length Required");
        statuses.put(412, "Precondition Failed");
        statuses.put(413, "Payload Too Large");
        statuses.put(414, "URI Too Long");
        statuses.put(415, "Unsupported Media Type");
        statuses.put(416, "Range Not Satisfiable");
        statuses.put(417, "Expectation Failed");
        statuses.put(418, "I'm a teapot");
        statuses.put(421, "Misdirected Request");
        statuses.put(422, "Unprocessable Entity");
        statuses.put(423, "Locked");
        statuses.put(424, "Failed Dependency");
        statuses.put(426, "Upgrade Required");
        statuses.put(428, "Precondition Required");
        statuses.put(429, "Too Many Requests");
        statuses.put(431, "Request Header Fields Too Large");
        statuses.put(451, "Unavailable For Legal Reasons");
        statuses.put(500, "Internal Server Error");
        statuses.put(501, "Not Implemented");
        statuses.put(502, "Bad Gateway");
        statuses.put(503, "Service Unavailable");
        statuses.put(504, "Gateway Timeout");
        statuses.put(505, "HTTP Version Not Supported");
        statuses.put(506, "Variant Also Negotiates");
        statuses.put(507, "Insufficient Storage");
        statuses.put(508, "Loop Detected");
        statuses.put(510, "Not Extended");
        statuses.put(511, "Network Authentication Required");
        return statuses;
    }
    public static final Map STATUSES = statuses();
}
