package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import util.ResponseJsonUtils;

@WebServlet(name = "WxLoginServlet",urlPatterns = "/wxLogin")
public class WxLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String appid = "wx2ba5834caba16490";
        String appsecret = "6dd9a0d6d2da385980b9f388400eeaa8";
        String js_code = request.getParameter("js_code");
        String requestUrl="https://api.weixin.qq.com/sns/jscode2session";
        String outputStr ="appid=wx2ba5834caba16490&secret=6dd9a0d6d2da385980b9f388400eeaa8&js_code=" + js_code + "&grant_type=authorization_code";
        String httpResult=httpRequest(requestUrl,outputStr);
        Map<String,Object> data=new HashMap<>();
        data.put("data",httpResult);
        ResponseJsonUtils.jsonp(response,data);
    }

    private String httpRequest(String requestUrl, String outputStr) {
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            if (null != outputStr) {
                OutputStream os = conn.getOutputStream();
                os.write(outputStr.getBytes("utf-8"));
                os.close();
            }
            // 读取服务器端返回的内容
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }
}
