package by.belstu.servlet;

import by.belstu.helpers.RandomHelper;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SssJSON extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int n = Integer.parseInt(request.getHeader("XRand-N"));
            StringBuilder textResult = new StringBuilder();
            int count = RandomHelper.getRandomNumber(5, 10);

            textResult.append("[");
            for (int i = 0; i < count; i++) {
                Integer number = RandomHelper.getRandomNumber(-n, n);

                textResult.append(number).append(i < count - 1 ? "," : "");
            }
            textResult.append("]");

            Thread.sleep(1000);

            response.setContentType("application/json");
            response.getWriter().println(textResult);
        } catch (Exception e) {
            response.getWriter().println(e.getMessage());
        }
    }
}
