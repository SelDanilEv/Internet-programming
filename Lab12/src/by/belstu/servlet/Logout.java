package by.belstu.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Logout extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("Servlet:Logout");
        if (request.getSession() != null) {
            request.getSession().invalidate();
        }
        request.logout();

        response.sendError(401, "velosiped (logout for basic)");

        response.sendRedirect("http://localhost:8080/Lab12/");
    }
}