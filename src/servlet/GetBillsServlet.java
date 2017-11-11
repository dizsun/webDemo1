package servlet;

import bean.BillBean;
import util.DbDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

@WebServlet(name = "GetBillsServlet",urlPatterns = {"/getBills"})
public class GetBillsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int account_id = Integer.parseInt(request.getParameter("account_id"));
        DbDao dbDao = (DbDao) getServletContext().getAttribute("dbDao");
        try {
            ResultSet resultSet = dbDao.query("select * from bill where account_id=?",account_id);
            ArrayList<BillBean> billBeans = new ArrayList<>();
            while (resultSet.next()){
                BillBean billBean = new BillBean();
                billBean.setId(resultSet.getInt("id"));
                billBean.setBrief_intro(resultSet.getString("brief_intro"));
                billBean.setCreator(resultSet.getString("creator"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
