package servlet;

import util.DbDao;
import util.RedisUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

@WebServlet(name = "RegisterServlet",urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RedisUtil redisUtil = new RedisUtil();
        String user_openid = redisUtil.queryString(request.getSession().getId());
        String user_nickname=new String(request.getParameter("nickName").getBytes("ISO-8859-1"),"utf-8");
        String user_avatarUrl=request.getParameter("avatarUrl");
        if(user_openid!=null && !user_openid.equals("")){
            DbDao dbDao = (DbDao)getServletContext().getAttribute("dbDao");
            try {
                if(dbDao.insert("insert into user_info(user_nickname,user_avatarUrl,user_openid) value(?,?,?)",user_nickname,user_avatarUrl,user_openid)){
                    response.getWriter().write("200");
                    System.out.println("200");
                }else {
                    response.getWriter().write("210");
                    System.out.println("210");
                }
            } catch (Exception e) {
                response.getWriter().write("120");
                System.out.println("120");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
