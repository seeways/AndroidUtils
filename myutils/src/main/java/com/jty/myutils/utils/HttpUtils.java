package com.jty.myutils.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * http请求工具类,附带网络工具
 *
 * @author JTY
 */

public class HttpUtils {
    private static final int TIMEOUT_IN_MILLIONS = 5 * 1000;

    public interface CallBack {
        void onRequestComplete(String result);
    }

    /**
     * 异步的Get请求
     *
     * @param url
     * @param callBack
     */
    public static void doGetAsyn(final String url, final CallBack callBack) {
        new Thread() {
            public void run() {
                try {
                    String result = doGet(url);
                    if (callBack != null) {
                        callBack.onRequestComplete(result);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 异步的Post请求
     *
     * @param url
     * @param params
     * @param callBack
     * @throws Exception
     */
    public static void doPostAsyn(final String url,
                                  final String params, final CallBack callBack) {
        new Thread() {
            public void run() {
                try {
                    String result = doPost(url, params);
                    if (callBack != null) {
                        callBack.onRequestComplete(result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * Get请求，获得返回数据
     *
     * @param urlStr
     * @return
     * @throws Exception
     */
    public static String doGet(String urlStr) throws Exception {
        URL url = null;
        HttpURLConnection connection = null;
        InputStream in = null;
        ByteArrayOutputStream out = null;
//		try{
        url = new URL(urlStr);
        connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(TIMEOUT_IN_MILLIONS);
        connection.setConnectTimeout(TIMEOUT_IN_MILLIONS);
        connection.setRequestMethod("GET");
//			connection.setRequestProperty("accept", "*/*");
//			connection.setRequestProperty("connection", "Keep-Alive");
        connection.connect();
        if (connection.getResponseCode() == 200) {

            in = connection.getInputStream();
            out = new ByteArrayOutputStream();
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.close();
            in.close();

            return out.toString();
        }

//		}catch(Exception e){
//			e.printStackTrace();
//		}
        return null;
    }

    /**
     * 有响应结果的Post
     *
     * @param params 发送请求的 URL
     * @param urlStr 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     * @throws Exception
     */
    public static String doPost(String urlStr, String params) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = null;

        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setUseCaches(false);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setReadTimeout(TIMEOUT_IN_MILLIONS);
            conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);
            if (params != null && !params.trim().equals("")) {
                // 获取URLConnection对象对应的输出流
                out = new PrintWriter(conn.getOutputStream());
                // 发送请求参数
                out.print(params);
                // flush输出流的缓冲
                out.flush();
            }
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                result += line;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }


}
