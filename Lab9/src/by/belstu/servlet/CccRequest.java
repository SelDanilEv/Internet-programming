package by.belstu.servlet;

import by.belstu.Bean.CBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CccRequest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response(request, response);
    }

    //http://localhost:8080/lab9_war_exploded/cccrequest?cBean=new&f1=2&f2=2&f3=3
    protected void response(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String value1 = request.getParameter("f1");
        String value2 = request.getParameter("f2");
        String value3 = request.getParameter("f3");
        String cBeanName = request.getParameter("cBean");
        if (cBeanName == null) {
            writeErrorMessage(response, "add cBean parameter");
            return;
        } else {
            CBean cBean;
            if (cBeanName.equals("new")) {
                cBean = new CBean();

                cBean = this.setValues(cBean, value1, value2, value3);
                request.setAttribute("atrCBean", cBean);
            } else if (cBeanName.equals("old")) {
                cBean = (CBean) request.getAttribute("atrCBean");
                if (cBean == null) {
                    writeErrorMessage(response, "create new by.belstu.servlet.lib.CBean");
                    return;
                }
                cBean = this.setValues(cBean, value1, value2, value3);
                request.setAttribute("atrCBean", cBean);
            } else {
                writeErrorMessage(response, "wrong cBean parameter");
                return;
            }
        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/CccRequest.jsp");
        requestDispatcher.forward(request, response);
    }

    private CBean setValues(CBean cBean, String value1, String value2, String value3) {
        if (value1 != null) {
            cBean.setValue1(value1);
        }
        if (value2 != null) {
            cBean.setValue2(value2);
        }
        if (value3 != null) {
            cBean.setValue3(value3);
        }
        return cBean;
    }

    private void writeErrorMessage(HttpServletResponse response, String message) throws IOException {
        response.getWriter().write(message);
    }
}

