package by.belstu.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

public class JspFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("JspFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("do JspFilter");

        String value = servletRequest.getParameter("jsp");
        if (value == null) {
            servletResponse.getWriter().println("Ccc blocked on JSP");
        } else {
            System.out.printf("do JspFilter good: %s \n", value);
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        System.out.println("JspFilter destroy");
    }
}
