package wxapp.filter;

import wxapp.util.RedisUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "SessionFilter",urlPatterns = {"/wxapp/*"})
public class SessionFilter implements Filter {
    public static String[] sessionPaths = {"/wxapp/getAccount", "/wxapp/addAccount", "/wxapp/joinAccount",
            "/wxapp/getBills", "/wxapp/addBill", "/wxapp/getPeers"};

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        String path = httpServletRequest.getServletPath();
        if (this.containPath(path)) {
            String sessionId = httpServletRequest.getSession().getId();
            RedisUtil redisUtil = new RedisUtil();
            if (!redisUtil.checkKey(sessionId)) {
                RequestDispatcher rd = req.getRequestDispatcher("/wxapp/sessionError");
                rd.forward(req, resp);
            }else {
                chain.doFilter(req, resp);
            }
        } else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

    private boolean containPath(String path) {
        for (String sessionPath : sessionPaths) {
            if (sessionPath.equals(path)) return true;
        }
        return false;
    }

}
