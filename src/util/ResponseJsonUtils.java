package util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Web服务端返回JSON工具类
 * 工具类依赖FastJSON
 * 工具类支持返回JSON和JSONP格式数据
 *
 * @author accountwcx@qq.com
 */
public class ResponseJsonUtils {
    /**
     * 默认字符编码
     */
    private static String encoding = "UTF-8";

    /**
     * JSONP默认的回调函数
     */
    private static String callback = "callback";

    /**
     * FastJSON的序列化设置
     */
    private static SerializerFeature[] features = new SerializerFeature[]{
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
    private static String toJSONString(Object obj) {
        return JSON.toJSONString(obj, features);
    }

    /**
     * 返回JSON格式数据
     */
    public static void json(HttpServletResponse response, Object data, String encoding) {
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
    public static void json(HttpServletResponse response, Object data) {
        json(response, data, encoding);
    }

    /**
     * 返回JSONP数据，使用默认编码和默认回调函数
     */
    public static void jsonp(HttpServletResponse response, Object data) {
        jsonp(response, callback, data, encoding);
    }

    /**
     * 返回JSONP数据，使用默认编码
     */
    public static void jsonp(HttpServletResponse response, String callback, Object data) {
        jsonp(response, callback, data, encoding);
    }

    /**
     * 返回JSONP数据
     */
    public static void jsonp(HttpServletResponse response, String callback, Object data, String encoding) {
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

    public static String getEncoding() {
        return encoding;
    }

    public static void setEncoding(String encoding) {
        ResponseJsonUtils.encoding = encoding;
    }

    public static String getCallback() {
        return callback;
    }

    public static void setCallback(String callback) {
        ResponseJsonUtils.callback = callback;
    }

    public static String httpRequest(String requestUrl, String outputStr) {
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