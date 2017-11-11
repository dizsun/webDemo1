package servlet;

import bean.PeerBean;
import com.alibaba.fastjson.JSON;
import util.DbDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

@WebServlet(name = "GetPeersServlet",urlPatterns = {"/getPeers"})
public class GetPeersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int account_id = Integer.parseInt(request.getParameter("account_id"));
        DbDao dbDao = (DbDao) getServletContext().getAttribute("dbDao");
        try {
            ResultSet resultSet = dbDao.query("select * from user_account where account_id=?",account_id);
            ArrayList<PeerBean> peerBeans = new ArrayList<>();
            while (resultSet.next()){
                PeerBean peerBean = new PeerBean();
                peerBean.setUser_nickname(resultSet.getString("user_nickname"));
                peerBean.setUser_avatarUrl(resultSet.getString("user_avatarUrl"));
                peerBeans.add(peerBean);
            }
            String peersStr = JSON.toJSONString(peerBeans);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(peersStr);
        } catch (Exception e) {
            response.getWriter().write("120");
            e.printStackTrace();
        }
    }
}
