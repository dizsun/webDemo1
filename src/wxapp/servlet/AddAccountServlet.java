package wxapp.servlet;

import wxapp.util.DbDao;
import wxapp.util.RedisUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Timestamp;

@WebServlet(name = "AddAccountServlet",urlPatterns = {"/wxapp/addAccount"})
public class AddAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RedisUtil redisUtil = new RedisUtil();
        String openid = redisUtil.queryString(request.getSession().getId());
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String brief_introduction = request.getParameter("brief_introduction");
        DbDao dbDao = (DbDao) getServletContext().getAttribute("dbDao");
        try {
            dbDao.insert("insert into account(name,code,creator,brief_intro) value(?,?,?,?)",
                    name,code,openid,brief_introduction);
            ResultSet resultSet = dbDao.query("select id from account where creator=? order by id desc",openid);
            if(resultSet.next()){
                int id = resultSet.getInt("id");
                dbDao.insert("insert into user_account(user_openid,account_id) value(?,?)",openid,id);
                response.getWriter().write("400");
            }else {
                response.getWriter().write("410");
            }
        } catch (Exception e) {
            response.getWriter().write("120");
            e.printStackTrace();
        }
    }
}
