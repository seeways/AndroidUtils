package com.jty.myutils.utils;

import android.os.Build;
import android.text.Html;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author TaoYuan
 * @time 2017/3/20 0020
 * @desc 编码工具类
 */

public class EncodeUtils {

    /**
     * 默认URL编码
     * @param input
     * @return
     */
    public static String urlDecode(String input) {
        return urlDecode(input, "UTF-8");
    }

    /**
     * 自定义字符集编码
     * @param input
     * @param charset
     * @return
     */
    public static String urlDecode(String input, String charset) {
        try {
            return URLDecoder.decode(input, charset);
        } catch (UnsupportedEncodingException e) {
            return input;
        }
    }

    /** Base64系列 **/

    /**
     * Base64编码
     */
    public static byte[] base64Encode(byte[] input) {
        return Base64.encode(input, Base64.DEFAULT);
    }
    /**
     * Base64自定义flags编码
     */
    public static byte[] base64Encode(byte[] input,int flags) {
        return Base64.encode(input, flags);
    }

    /**
     * Base64解码
     */
    public static byte[] base64Decode(byte[] input) {
        return Base64.decode(input, Base64.DEFAULT);
    }
    /**
     * Base64自定义flags解码
     */
    public static byte[] base64Decode(byte[] input,int flags) {
        return Base64.decode(input, flags);
    }

    /**
     * Html编码
     *
     * @param input 要Html编码的字符串
     * @return Html编码后的字符串
     */
    public static String htmlEncode(CharSequence input) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            return Html.escapeHtml(input);
        } else {
            // 参照Html.escapeHtml()中代码
            StringBuilder out = new StringBuilder();
            for (int i = 0, len = input.length(); i < len; i++) {
                char c = input.charAt(i);
                if (c == '<') {
                    out.append("&lt;");
                } else if (c == '>') {
                    out.append("&gt;");
                } else if (c == '&') {
                    out.append("&amp;");
                } else if (c >= 0xD800 && c <= 0xDFFF) {
                    if (c < 0xDC00 && i + 1 < len) {
                        char d = input.charAt(i + 1);
                        if (d >= 0xDC00 && d <= 0xDFFF) {
                            i++;
                            int codepoint = 0x010000 | (int) c - 0xD800 << 10 | (int) d - 0xDC00;
                            out.append("&#").append(codepoint).append(";");
                        }
                    }
                } else if (c > 0x7E || c < ' ') {
                    out.append("&#").append((int) c).append(";");
                } else if (c == ' ') {
                    while (i + 1 < len && input.charAt(i + 1) == ' ') {
                        out.append("&nbsp;");
                        i++;
                    }
                    out.append(' ');
                } else {
                    out.append(c);
                }
            }
            return out.toString();
        }
    }

    /**
     * Html解码
     *
     * @param input 待解码的字符串
     * @return Html解码后的字符串
     */
    @SuppressWarnings("deprecation")
    public static CharSequence htmlDecode(String input) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(input, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(input);
        }
    }

}
