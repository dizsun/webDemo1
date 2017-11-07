package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;

@WebServlet(name = "generateAccountIdServlet", urlPatterns = {"/generateAccountId"})
public class generateAccountIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = (int) getServletContext().getAttribute("generateAccountId");
        getServletContext().setAttribute("generateAccountId", id + 1);
        response.getWriter().write(String.valueOf(id));
    }
}
