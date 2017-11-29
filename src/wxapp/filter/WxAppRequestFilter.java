package wxapp.filter;

import wxapp.util.DbDao;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.ResultSet;

@WebFilter(filterName = "WxAppRequestFilter",urlPatterns = {"/wxapp/*"})
public class WxAppRequestFilter implements Filter {
    private ServletContext context;
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        DbDao dbDao = (DbDao) context.getAttribute("dbDao");
        try {
            dbDao.insert("insert into site_info() values()");
        } catch (Exception e) {
            e.printStackTrace();
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        context = config.getServletContext();
    }

}
