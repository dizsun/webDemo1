package mysite.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import mysite.util.HttpRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "QueryExpressServlet",urlPatterns = {"/queryExpress"})
public class QueryExpressServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String postid = request.getParameter("postid");
        String url="http://www.kuaidi100.com/autonumber/autoComNum";
        HashMap<String ,String> param=new HashMap<>();
        param.put("resultv2","1");
        param.put("text","426425825111");
        HashMap<String ,String> requestProperty=new HashMap<>();
        requestProperty.put("Accept","application/json, text/javascript, */*; q=0.01");
        requestProperty.put("Accept-Encoding","gzip, deflate");
        requestProperty.put("Accept-Language","zh-CN,zh;q=0.8,en;q=0.6");
        requestProperty.put("Connection","keep-alive");
        requestProperty.put("Host","www.kuaidi100.com");
        requestProperty.put("Origin","http://www.kuaidi100.com");
        requestProperty.put("Referer","http://www.kuaidi100.com/?from=openv");
        requestProperty.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
        requestProperty.put("X-Requested-With","XMLHttpRequest");
        StringBuffer result = HttpRequest.sendGet(url,param,requestProperty);
        JSONObject jsonObject = JSON.parseObject(result.toString());
        JSONArray jsonArray = jsonObject.getJSONArray("auto");
        JSONObject jsonObject1 = jsonArray.getJSONObject(0);
        String comCode = jsonObject1.getString("comCode");
        System.out.println(comCode);
        String url1 = "http://www.kuaidi100.com/query";
        HashMap<String,String> param1 = new HashMap<>();
        param1.put("type",comCode);
        param1.put("postid",postid);
        HashMap<String ,String> requestProperty1 = new HashMap<>();
        requestProperty1.put("Accept","application/json, text/javascript, */*; q=0.01");
        requestProperty1.put("Accept-Encoding","gzip, deflate");
        requestProperty1.put("Accept-Language","zh-CN,zh;q=0.8,en;q=0.6");
        requestProperty1.put("Connection","keep-alive");
        requestProperty1.put("Host","www.kuaidi100.com");
        requestProperty.put("Referer","http://www.kuaidi100.com/?from=openv");
        requestProperty.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
        requestProperty.put("X-Requested-With","XMLHttpRequest");
        StringBuffer result1 = HttpRequest.sendGet(url1,param1,requestProperty1);
        System.out.println(result1);
//        System.out.println(new String(result1.getBytes(),"gb2312"));
//        System.out.println(new String(result1.getBytes("iso-8859-1"), "utf-8"));
//        System.out.println(new String(result1.getBytes(),"big5"));
//        System.out.println(new String(result1.getBytes("gbk"),"utf8"));
//        System.out.println(new String(result1.getBytes("utf8"),"gbk"));
        System.out.println(result1);
//        response.getWriter().write(result1);
    }
}
