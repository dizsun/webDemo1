package listener;

import util.DbDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

@WebListener()
public class GetConnListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    // Public constructor is required by servlet spec
    public GetConnListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        String driver = application.getInitParameter("driver");
        String url = application.getInitParameter("url")+"/wxdb?useUnicode=true&characterEncoding=UTF-8";
        String user = application.getInitParameter("username");
        String pass = application.getInitParameter("password");
        DbDao dbDao = new DbDao(driver, url, user, pass);
        application.setAttribute("dbDao", dbDao);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                try {
                    dbDao.query("use wxdb");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0 , 3600000);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        DbDao dbDao = (DbDao) application.getAttribute("dbDao");
        try {
            dbDao.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
      /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
      /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
