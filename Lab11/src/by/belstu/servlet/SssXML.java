package by.belstu.servlet;

import by.belstu.helpers.RandomHelper;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SssXML extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int n = Integer.parseInt(request.getHeader("XRand-N"));
            StringBuilder result = new StringBuilder("<?xml version=\"1.0\"  encoding = \"utf-8\" ?>");

            int count = RandomHelper.getRandomNumber(5, 10);

            result.append("<rand>");
            for (int i = 0; i < count; i++) {
                Integer number = RandomHelper.getRandomNumber(-n, n);
                result.append("<num>").append(number).append("</num>");
            }
            result.append("</rand>");

            Thread.sleep(5000);

            response.setContentType("text/xml");
            response.getWriter().println(result);

        } catch (Exception e) {
            response.getWriter().println(e.getMessage());
        }
    }
}
