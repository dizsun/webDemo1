package servlet;

import bean.AccountBean;
import com.alibaba.fastjson.JSON;
import util.DbDao;
import util.RedisUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

@WebServlet(name = "GetAccountServlet",urlPatterns = {"/getAccount"})
public class GetAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sessionId = request.getSession().getId();
        RedisUtil redisUtil = new RedisUtil();
        String openid = redisUtil.queryString(sessionId);
//        String openid = request.getParameter("openid");
        DbDao dbDao = (DbDao) getServletContext().getAttribute("dbDao");
        try {
            ResultSet resultSet = dbDao.query("select * from account where creator=?",openid);
            ResultSet userSet = dbDao.query("select user_nickname from user_info where user_openid=?",openid);
            if(userSet.next()){
                String userName = userSet.getString("user_nickname");
                ArrayList<AccountBean> accounts = new ArrayList<>();
                while (resultSet.next()){
                    AccountBean accountBean = new AccountBean();
                    accountBean.setId(resultSet.getInt("id"));
                    accountBean.setName(resultSet.getString("name"));
                    accountBean.setBrief_introduction(resultSet.getString("brief_intro"));
                    accountBean.setDate(resultSet.getTimestamp("date"));
                    accountBean.setCode(resultSet.getInt("code"));
                    accountBean.setCreator(userName);
                    ResultSet user_accounts = dbDao.query("select count(*) as num from user_account where account_id=?",accountBean.getId());
                    user_accounts.next();
                    accountBean.setPeers(user_accounts.getInt("num"));
                    ResultSet account_bills = dbDao.query("select count(*) as num from bill where account_id=?",accountBean.getId());
                    account_bills.next();
                    accountBean.setBills(account_bills.getInt("num"));
                    accounts.add(accountBean);
                }
                String accountsStr = JSON.toJSONString(accounts);
                response.getWriter().write(accountsStr);
            }else {
                response.getWriter().write("300");
            }
        } catch (Exception e) {
            response.getWriter().write("120");
            e.printStackTrace();
        }
    }
}
