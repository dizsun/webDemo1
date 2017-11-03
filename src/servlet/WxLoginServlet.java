package servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

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
        ResponseJsonUtils rju = new ResponseJsonUtils();
        String httpResult=rju.httpRequest(requestUrl,outputStr);
        Map<String,Object> data=new HashMap<>();
        data.put("data",httpResult);
        rju.jsonp(response,data);
    }

    public class ResponseJsonUtils {
        /**
         * 默认字符编码
         */
        private  String encoding = "UTF-8";

        /**
         * JSONP默认的回调函数
         */
        private  String callback = "callback";

        /**
         * FastJSON的序列化设置
         */
        private  SerializerFeature[] features = new SerializerFeature[]{
                //输出Map中为Null的值
                SerializerFeature.WriteMapNullValue,

                //假设Boolean对象为Null。则输出为false
                SerializerFeature.WriteNullBooleanAsFalse,

                //假设List为Null。则输出为[]
                SerializerFeature.WriteNullListAsEmpty,

                //假设Number为Null。则输出为0
                SerializerFeature.WriteNullNumberAsZero,

                //输出Null字符串
                SerializerFeature.WriteNullStringAsEmpty,

                //格式化输出日期
                SerializerFeature.WriteDateUseDateFormat
        };

        /**
         * 把Java对象JSON序列化
         *
         * @param obj 须要JSON序列化的Java对象
         * @return JSON字符串
         */
        private  String toJSONString(Object obj) {
            return JSON.toJSONString(obj, features);
        }

        /**
         * 返回JSON格式数据
         */
        public  void json(HttpServletResponse response, Object data, String encoding) {
            //设置编码格式
            response.setContentType("text/plain;charset=" + encoding);
            response.setCharacterEncoding(encoding);

            PrintWriter out = null;
            try {
                out = response.getWriter();
                out.write(toJSONString(data));
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 返回JSON格式数据，使用默认编码
         */
        public  void json(HttpServletResponse response, Object data) {
            json(response, data, encoding);
        }

        /**
         * 返回JSONP数据，使用默认编码和默认回调函数
         */
        public  void jsonp(HttpServletResponse response, Object data) {
            jsonp(response, callback, data, encoding);
        }

        /**
         * 返回JSONP数据，使用默认编码
         */
        public  void jsonp(HttpServletResponse response, String callback, Object data) {
            jsonp(response, callback, data, encoding);
        }

        /**
         * 返回JSONP数据
         */
        public  void jsonp(HttpServletResponse response, String callback, Object data, String encoding) {
            StringBuffer sb = new StringBuffer(callback);
            sb.append("(");
            sb.append(toJSONString(data));
            sb.append(");");

            // 设置编码格式
            response.setContentType("text/plain;charset=" + encoding);
            response.setCharacterEncoding(encoding);

            PrintWriter out = null;
            try {
                out = response.getWriter();
                out.write(sb.toString());
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public  String getEncoding() {
            return encoding;
        }

        public  void setEncoding(String encoding) {
            this.encoding = encoding;
        }

        public  String getCallback() {
            return callback;
        }

        public  void setCallback(String callback) {
            this.callback = callback;
        }

        public  String httpRequest(String requestUrl, String outputStr) {
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

}
