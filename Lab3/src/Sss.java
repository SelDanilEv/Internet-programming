import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Sss extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Sss Get");

// Task1
//         RequestDispatcher rd = req.getRequestDispatcher("/GGG");
//         rd.forward(req, resp);

// Task3
//         RequestDispatcher rd6 = req.getRequestDispatcher("/page.html");
//         rd6.forward(req, resp);

// Task4
//        RequestDispatcher rd = req.getRequestDispatcher("/GGG");
//        rd.forward(req, resp);

// Task5
//        RequestDispatcher rd = req.getRequestDispatcher("/GGG");
//        resp.getWriter().write("output Task5 from Sss 2");
//        rd.forward(req, resp);

// Task6
//        var param = req.getParameterNames().nextElement();
//        resp.sendRedirect("http://localhost:8080/Lab3/GGG?" + param + "=" + req.getParameter(param));

//Task7
        var param = req.getParameterNames().nextElement();
        var paramValue = req.getParameter(param);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/Lab3/GGG?" + param + "=" + paramValue))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            resp.getWriter().write("response Task 7:" + response.body());
        } catch (InterruptedException e) {
            resp.getWriter().write("Task 7: Bad Request");
        }

    }

//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("Sss " + req.getMethod());
//        // Task2
//        RequestDispatcher rd = req.getRequestDispatcher("/GGG");
//        rd.forward(req, resp);
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("POST Sss");
//         RequestDispatcher dispatcher = request.getRequestDispatcher("/GGG");
//         dispatcher.forward(request, response);
//Task6
//        response.setStatus(307);
//        response.setHeader("Location", "http://localhost:8080/Lab3/GGG");

//        response.sendRedirect("http://localhost:8080/Lab3/GGG");

//Task9
        var param = request.getParameterNames().nextElement();
        var paramValue = request.getParameter(param);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest requestB = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/Lab3/GGG"))
                .POST(HttpRequest.BodyPublishers.ofString(paramValue))
                .build();
        try {
            HttpResponse<String> responseB = client.send(requestB, HttpResponse.BodyHandlers.ofString());
            response.getWriter().write("response Task 9:" + responseB.body());
        } catch (
                InterruptedException e) {
            response.getWriter().write("Task 9: Bad Request");
        }
    }
}
