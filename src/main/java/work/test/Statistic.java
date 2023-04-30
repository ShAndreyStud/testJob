package work.test;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Statistic implements Filter {

    @Autowired
    private StatisticService statisticService;

    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws java.io.IOException, ServletException {
        HttpServletRequest httpRequest = ((HttpServletRequest) request);
        String name = httpRequest.getParameter("name");
        if (httpRequest.getRequestURI().equals("/test/findAge"))
            statisticService.increaseCount(name);
        chain.doFilter(request, response);
    }
}