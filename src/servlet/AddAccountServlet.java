package servlet;

import util.DbDao;
import util.RedisUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Map;

@WebServlet(name = "AddAccountServlet", urlPatterns = {"/addAccount"})
public class AddAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RedisUtil redisUtil = new RedisUtil();
        String openid = redisUtil.queryString(request.getSession().getId());
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String creator = request.getParameter("creator");
        String brief_introduction = request.getParameter("brief_introduction");
        String dateStr = request.getParameter("date");
        Timestamp date = Timestamp.valueOf(dateStr);
        DbDao dbDao = (DbDao) getServletContext().getAttribute("dbDao");
        try {
            dbDao.insert("insert into account(id,name,code,creator,brief_intro,date) value(?,?,?,?,?,?)",
                    id,name,code,creator,brief_introduction,date);
            dbDao.insert("insert into user_account(user_openid,account_id) value(?,?)",openid,id);
            response.getWriter().write("400");
        } catch (Exception e) {
            response.getWriter().write("120");
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
