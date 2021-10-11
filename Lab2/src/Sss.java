import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class Sss extends HttpServlet {
    public void init(ServletConfig sc) throws ServletException {
        System.out.println("init");
    }

    public void destroy() {
        System.out.println("destroy");
    }

    protected void service(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        System.out.println("service " + rq.getMethod());
        PrintWriter pw = rs.getWriter();
        pw.println("Service " + rq.getMethod());
        pw.println("ServerName: " + rq.getServerName());
        pw.println("Local Address: " + rq.getLocalAddr());
        pw.println("FirstName: " + rq.getParameter("firstname"));
        pw.println("LastName: " + rq.getParameter("lastname"));
        pw.close();
    }

    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        PrintWriter pw = rs.getWriter();
        pw.println("Sss: " + rq.getMethod());
        pw.println("FirstName: " + rq.getParameter("firstname"));
        pw.println("LastName: " + rq.getParameter("lastname"));
        pw.close();
    }

    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        PrintWriter pw = rs.getWriter();
        pw.println("Sss: " + rq.getMethod());
        pw.println("FirstName: " + rq.getParameter("firstname"));
        pw.println("LastName: " + rq.getParameter("lastname"));
        pw.println("getRemoteHost: " + rq.getQueryString());
        pw.close();
    }
}
