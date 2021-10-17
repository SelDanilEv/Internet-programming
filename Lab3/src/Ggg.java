import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.stream.Collectors;

public class Ggg extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Ggg Get");

// Task1
//        var param = req.getParameterNames().nextElement();
//        resp.getWriter().write(param + ": " + req.getParameter(param));

// Task4
//        RequestDispatcher rd4 = req.getRequestDispatcher("/page.html");
//        rd4.forward(req, resp);

// Task5
//        resp.getWriter().write("output Task5 from GGG");

// Task6
//        resp.getWriter().write("output Task 6:" + req.getQueryString());

// Task 7
//        resp.getWriter().write("query: " + req.getQueryString());

    }

//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("Ggg " + req.getMethod());
//
//// Task2
//        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//        resp.getWriter().write("output Task5 from Ggg: " + body);
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("POST Ggg");
//        var param = request.getParameterNames().nextElement();
//        response.getWriter().write(param + "(post): " + request.getParameter(param));
        
        //Task9
//        String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//        response.getWriter().write(body);
    }
}
