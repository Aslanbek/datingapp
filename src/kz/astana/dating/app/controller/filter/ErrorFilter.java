package kz.astana.dating.app.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.astana.dating.app.service.WordBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;

import static jakarta.servlet.RequestDispatcher.ERROR_EXCEPTION;

@WebFilter(value = "/*", dispatcherTypes = DispatcherType.ERROR)
public class ErrorFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(ErrorFilter.class);

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        Throwable throwable = (Throwable) req.getAttribute(ERROR_EXCEPTION);
        if (res.getStatus() >= 500) {
            log.error("Error 500", throwable);
        } else {
            log.warn("Exception code: {}", res.getStatus());
        }

        filterChain.doFilter(req, res);
    }
}
