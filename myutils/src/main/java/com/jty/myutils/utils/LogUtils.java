package com.jty.myutils.utils;

import android.annotation.SuppressLint;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtils {

    public static void write(String file, String content) {
        write(file, content, true);
    }

    public static void write(String file, String content, boolean append) {
        write(file, content, append, true);
    }

    public static void write(String file, String content, boolean append, boolean needTime) {
        BufferedWriter out = null;
        try {
            FileUtils.createOrExistsFile(file);
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append)));
            if (needTime) out.write(TimeUtils.getTime() + "\r " + content + "\n");
            else out.write(content + "\n");
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String readFile(String filePath) {
        StringBuilder content = new StringBuilder(); //文件内容字符串
        //打开文件

        //如果path是传递过来的参数，可以做一个非目录的判断
        if (FileUtils.isFileExists(filePath) && FileUtils.isFile(filePath)) {
            try {
                InputStream instream = new FileInputStream(filePath);
                InputStreamReader inputreader = new InputStreamReader(instream);
                BufferedReader buffreader = new BufferedReader(inputreader);
                String line;
                //分行读取
                while ((line = buffreader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                instream.close();
            } catch (java.io.FileNotFoundException e) {
                L.e("The File doesn't not exist.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content.toString();
    }

    //获得当日时间
    @SuppressLint("SimpleDateFormat")
    public static String getDay() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

}
