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

@WebServlet(name = "JoinAccountServlet",urlPatterns = {"/joinAccount"})
public class JoinAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RedisUtil redisUtil = new RedisUtil();
        String openid = redisUtil.queryString(request.getSession().getId());
        String account_id = request.getParameter("account_id");
        String account_code=request.getParameter("account_code");
        DbDao dbDao = (DbDao)getServletContext().getAttribute("dbDao");
        try {
            ResultSet resultSet = dbDao.query("select count(*) as num from account where id=? and code=?",account_id,account_code);
            if(resultSet.next()){
                if(resultSet.getInt("num")==1){
                    ResultSet resultSet1 = dbDao.query("select count(*) as num from user_account where user_openid=? and account_id=?",openid,account_id);
                    if(resultSet1.next()){
                        if(resultSet1.getInt("num")>0){
                            response.getWriter().write("530");
                        }
                    }
                    dbDao.insert("insert into user_account(user_openid,account_id) value(?,?)",openid,account_id);
                    response.getWriter().write("500");
                }else {
                    response.getWriter().write("510");
                }
            }else {
                response.getWriter().write("520");
            }
        } catch (Exception e) {
            response.getWriter().write("120");
            e.printStackTrace();
        }
    }
}
