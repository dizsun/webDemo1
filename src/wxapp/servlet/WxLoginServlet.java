package wxapp.servlet;

import wxapp.bean.DataBean;
import wxapp.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.ResultSet;

@WebServlet(name = "WxLoginServlet",urlPatterns = "/wxapp/wxLogin")
public class WxLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String appid = "wx2ba5834caba16490";
        String appsecret = "6dd9a0d6d2da385980b9f388400eeaa8";
        String js_code = request.getParameter("js_code");
        String requestUrl="https://api.weixin.qq.com/sns/jscode2session";
        String outputStr ="appid="+appid+"&secret="+appsecret+"&js_code=" + js_code + "&grant_type=authorization_code";
        String httpResult = ResponseJsonUtils.httpRequest(requestUrl,outputStr);
        DataBean data = ResponseJsonUtils.getData(httpResult);
        if(data.getErrcode()==null) {
            RedisUtil redisUtil = new RedisUtil();
            redisUtil.addString(request.getSession().getId(),data.getOpenid());
            DbDao dbDao = (DbDao)getServletContext().getAttribute("dbDao");
            try {
                ResultSet rs = dbDao.query("select * from user_info where user_openid=?",data.getOpenid());
                if(!rs.next()){
                    //未注册
                    redisUtil.addString("register_session",request.getSession().getId());
                    response.getWriter().write("110");
                }else {
                    //登陆成功
                    response.getWriter().write("{\"code\":\"130\",\"sessionId\":\""+request.getSession().getId()+"\"}");
                }
            } catch (Exception e) {
                //数据库出错
                response.getWriter().write("120");
            }
        }else {
            //微信账号登陆错误
            response.getWriter().write("100");
        }
    }
}
