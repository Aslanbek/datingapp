package kz.astana.dating.app.controller.filter;

import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import kz.astana.dating.app.model.Gender;
import kz.astana.dating.app.model.Status;

import java.io.IOException;
import java.util.Locale;

@WebFilter("/*")
public class HiddenHttpMethodFilter implements Filter {

    public static final String METHOD_PARAM = "_method";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        if (servletContext.getAttribute("genders") == null) {
            servletContext.setAttribute("genders", Gender.values());
        }
        if (servletContext.getAttribute("statuses") == null) {
            servletContext.setAttribute("statuses", Status.values());
        }
    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String paramValue = request.getParameter(METHOD_PARAM);

        if ("POST".equals(request.getMethod()) && paramValue != null && !paramValue.isBlank()) {
            String method = paramValue.toUpperCase(Locale.ENGLISH);
            HttpServletRequest wrapper = new HttpMethodRequestWrapper(request, method);
            filterChain.doFilter(wrapper, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }



    @Override
    public void destroy() {

    }

    /**
     * Simple {@link HttpServletRequest} wrapper that returns the supplied
     * method for {@link HttpServletRequest#getMethod()}.
     */
    private static class HttpMethodRequestWrapper extends HttpServletRequestWrapper {

        private final String method;

        public HttpMethodRequestWrapper(HttpServletRequest request, String method) {
            super(request);
            this.method = method;
        }

        @Override
        public String getMethod() {
            return this.method;
        }
    }

}