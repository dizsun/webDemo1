package wxapp.servlet;

import wxapp.util.DbDao;
import wxapp.util.RedisUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/wxapp/register"})
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
        RedisUtil redisUtil = new RedisUtil();
        String user_openid = redisUtil.queryString(redisUtil.queryString("register_session"));
//        String user_nickname=new String(request.getParameter("nickName").getBytes("ISO-8859-1"),"utf-8");
        String user_nickname = request.getParameter("nickName");
        String user_avatarUrl = request.getParameter("avatarUrl");
        if (user_openid != null && !user_openid.equals("")) {
            DbDao dbDao = (DbDao) getServletContext().getAttribute("dbDao");
            try {
                //注册成功
                if (dbDao.insert("insert into user_info(user_nickname,user_avatarUrl,user_openid) value(?,?,?)", user_nickname, user_avatarUrl, user_openid)) {
//                    response.setContentType("text/json;charset=UTF-8");
//                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("{\"code\":\"200\",\"sessionId\":\""+request.getSession().getId()+"\"}");
                } else {//注册失败
                    response.getWriter().write("210");
                }
            } catch (Exception e) {//数据库出错
                response.getWriter().write("120");
            }
        } else {//openid不存在
            response.getWriter().write("220");
        }


    }
}
