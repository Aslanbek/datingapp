package kz.astana.dating.app.controller.filter;

import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import kz.astana.dating.app.model.Gender;
import kz.astana.dating.app.model.Status;
import lombok.Setter;

import static jakarta.servlet.DispatcherType.FORWARD;
import static jakarta.servlet.DispatcherType.REQUEST;
import static kz.astana.dating.app.utils.StringUtils.isBlank;

import java.io.IOException;
import java.util.Locale;

@WebFilter(value = "/*", dispatcherTypes = {FORWARD, REQUEST})
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

        if (request.getDispatcherType() != REQUEST && request instanceof HttpMethodRequestWrapper wrapper) {
            request = (HttpServletRequest) wrapper.getRequest();
        } else {
            String paramValue = request.getParameter(METHOD_PARAM);

            if ("POST".equals(request.getMethod()) && !isBlank(paramValue)) {
                String method = paramValue.toUpperCase(Locale.ENGLISH);
                request = new HttpMethodRequestWrapper(request, method);
            }
        }
        filterChain.doFilter(request, response);
    }


    /**
     * Simple {@link HttpServletRequest} wrapper that returns the supplied
     * method for {@link HttpServletRequest#getMethod()}.
     */
    private static class HttpMethodRequestWrapper extends HttpServletRequestWrapper {
        @Setter
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