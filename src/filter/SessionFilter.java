package filter;

import util.RedisUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "SessionFilter")
public class SessionFilter implements Filter {
    public static String[] sessionPaths={"/getAccount","/addAccount","/register","/joinAccount","/getBills","/addBill","/getPeers"};
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        String path = httpServletRequest.getServletPath();
        if(this.containPath(path)){
            String sessionId = httpServletRequest.getSession().getId();
            RedisUtil redisUtil = new RedisUtil();
            if(!redisUtil.checkKey(sessionId)) {
                RequestDispatcher rd = req.getRequestDispatcher("/sessionError");
                rd.forward(req,resp);
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }
    private boolean containPath(String path){
        for (String sessionPath : sessionPaths) {
            if(sessionPath.equals(path))return true;
        }
        return false;
    }

}
