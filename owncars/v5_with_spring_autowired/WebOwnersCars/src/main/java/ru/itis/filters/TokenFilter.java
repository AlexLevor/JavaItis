package ru.itis.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by KFU-user on 11.11.2016.
 */
public class TokenFilter implements Filter {
    private String messageParam;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.messageParam = filterConfig.getInitParameter("message-param");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = servletRequest.getParameter("token");
        System.out.println(messageParam + " " + token);
        if (token == null) {
            token = ((HttpServletRequest)servletRequest).getCookies()[0].getValue();
        }

        if (token != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    public void destroy() {

    }
}