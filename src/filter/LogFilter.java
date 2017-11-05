package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "LogFilter",urlPatterns = {"/*"})
public class LogFilter implements Filter {
    private FilterConfig config;

    public void destroy() {
        this.config = null;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        ServletContext context = this.config.getServletContext();
        long before = System.currentTimeMillis();
//        System.out.println("开始过滤");
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        System.out.println("Filter已经截获到用户的请求地址：" + httpServletRequest.getServletPath());
        chain.doFilter(req, resp);
        long after = System.currentTimeMillis();
//        System.out.println("过滤结束");
        System.out.println("请求被定位到" + httpServletRequest.getRequestURI() + "  所花的时间为：" + (after - before));
    }

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

}
