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

@WebServlet(name = "DeleteAccountServlet",urlPatterns = {"/wxapp/deleteAccount"})
public class DeleteAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RedisUtil redisUtil = new RedisUtil();
        String openid = redisUtil.queryString(request.getSession().getId());
        int account_id=Integer.parseInt(request.getParameter("account_id"));
        DbDao dbDao = (DbDao) getServletContext().getAttribute("dbDao");
        try {
            ResultSet resultSet = dbDao.query("select creator from account where id=?",account_id);
            String creator = resultSet.getString("creator");
            if(creator.equals(openid)){
                dbDao.update("delete from account where id=?",account_id);
                dbDao.update("delete from user_account where account_id=?",account_id);
                dbDao.update("delete from bill where account_id=?",account_id);
                response.getWriter().write("700");
            }else {
                response.getWriter().write("710");
            }
        } catch (Exception e) {
            response.getWriter().write("120");
            e.printStackTrace();
        }
    }
}
