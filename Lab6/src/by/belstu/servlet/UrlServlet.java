package by.belstu.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UrlServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String param = req.getParameter("urln");

        PrintWriter printWriter = resp.getWriter();

        if (param == null) {
            printWriter.println("parameter URLn not found");
        } else {
            if (param.equals("1")) {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
//                        .uri(URI.create(getInitParameter("URL1")))
                        .uri(URI.create(getServletContext().getInitParameter("URL1")))
                        .build();
                try {
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    resp.getWriter().write("response URL1:" + response.body());
                } catch (InterruptedException e) {
                    resp.getWriter().write("URL1: Bad Request");
                }
                //resp.sendRedirect(getServletContext().getInitParameter("URL1"));
            } else if (param.equals("2")) {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
//                        .uri(URI.create(getInitParameter("URL2")))
                        .uri(URI.create(getServletContext().getInitParameter("URL2")))
                        .build();
                try {
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    resp.getWriter().write("response URL2:" + response.body());
                } catch (InterruptedException e) {
                    resp.getWriter().write("URL2: Bad Request");
                }
                //resp.sendRedirect(getServletContext().getInitParameter("URL2"));
            } else {
                printWriter.println("parameter URL" + param + " not found");
            }
        }
    }
}
