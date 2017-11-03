package servlet;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.bcel.internal.generic.DDIV;

import webApp1.DbDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rdDispatcher = request.getRequestDispatcher("login.jsp");
		rdDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String errMsg="";
		RequestDispatcher rd;
		String username=request.getParameter("username");
		String pass=request.getParameter("pass");
		try {
			DbDao dbDao=new DbDao("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/login_test","root","root");
			ResultSet rs=dbDao.query("select pass from user_inf where name=?",username);
			if(rs.next()) {
				if(rs.getString("pass").equals(pass)) {
					HttpSession session=request.getSession(true);
					session.setAttribute("name", username);
					rd=request.getRequestDispatcher("/welcom.jsp");
					rd.forward(request, response);
				}else {
					errMsg+="您的用户名密码不符合，请重新输入";
				}
			}else {
				errMsg+="您的用户名不存在，请先注册";
			}
			dbDao.closeConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(errMsg!=null&&!errMsg.equals("")) {
			rd=request.getRequestDispatcher("/login.jsp");
			request.setAttribute("err", errMsg);
			rd.forward(request, response);
		}
		
	}

}
