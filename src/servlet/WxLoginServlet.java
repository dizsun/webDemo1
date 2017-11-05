package servlet;

import util.DataBean;
import util.ResponseJsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "WxLoginServlet",urlPatterns = "/wxLogin")
public class WxLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String appid = "wx2ba5834caba16490";

        String appsecret = "6dd9a0d6d2da385980b9f388400eeaa8";
        String js_code = request.getParameter("js_code");
        String requestUrl="https://api.weixin.qq.com/sns/jscode2session";
        String outputStr ="appid=wx2ba5834caba16490&secret=6dd9a0d6d2da385980b9f388400eeaa8&js_code=" + js_code + "&grant_type=authorization_code";
        String httpResult = ResponseJsonUtils.httpRequest(requestUrl,outputStr);
        DataBean data = ResponseJsonUtils.getData(httpResult);
//        Map<String,Object> data=new HashMap<>();
//        data.put("data",httpResult);
//        ResponseJsonUtils.jsonp(response,data);
        if(data.getErrcode()==null) {
            String sessionId = request.getSession(true).getId();
            response.getWriter().write("sessionId:" + sessionId);
        }else {
            response.getWriter().write("error");
        }
    }
}
