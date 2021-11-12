package by.belstu.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Aaa extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpResponse<String> responseB = postB("http://localhost:8080/lab8_war_exploded/Bbb",
                "param1",
                "param2",
                "param3",
                "header1",
                "header2",
                "header3");

        var headers = responseB.headers().map();
        String res = "response body:" + responseB.body()
                + "</br>"
                + "header from Bbb 1" + headers.get("header_from_Bbb_1")
                + "header from Bbb 1" + headers.get("header_from_Bbb_2");

        response.setContentType("text/html");
        response.getWriter().println("<h1>Body:</h1>" + res);
    }

    private static HttpResponse<String> postB(
            String path,
            String param1,
            String param2,
            String param3,
            String header1,
            String header2,
            String header3) {
        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create(String.format("%s?param1=%s&param2=%s&param3=%s", path, param1, param2, param3));
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(""))
                .setHeader("header1", header1)
                .setHeader("header2", header2)
                .setHeader("header3", header3)
                .build();
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            return null;
        }
    }
}
