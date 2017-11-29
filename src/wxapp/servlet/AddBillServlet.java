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

@WebServlet(name = "AddBillServlet",urlPatterns = {"/wxapp/addBill"})
public class AddBillServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RedisUtil redisUtil = new RedisUtil();
        String openid = redisUtil.queryString(request.getSession().getId());
        int account_id = Integer.parseInt(request.getParameter("account_id"));
        String money = request.getParameter("money");
        String brief_intro = request.getParameter("brief_intro");
        DbDao dbDao = (DbDao) getServletContext().getAttribute("dbDao");
        try {
            ResultSet resultSet = dbDao.query("select user_nickname from user_info where user_openid=?",openid);
            if(resultSet.next()){
                String creator=resultSet.getString("user_nickname");
                if(dbDao.insert("insert into bill(brief_intro,creator,money,account_id) value(?,?,?,?)",brief_intro,creator,money,account_id)){
                    ResultSet resultSet1 = dbDao.query("select total from account where id=?",account_id);
                    resultSet1.next();
                    Double total = Double.parseDouble(resultSet1.getString("total"));
                    Double bill_money = Double.parseDouble(money);
                    dbDao.update("update account set total=? where id=?",String.valueOf(total+bill_money),account_id);
                    response.getWriter().write("600");
                }else {
                    response.getWriter().write("610");
                }
            }else {
                response.getWriter().write("300");
            }
        } catch (Exception e) {
            response.getWriter().write("120");
            e.printStackTrace();
        }
    }
}
