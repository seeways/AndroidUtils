package com.jty.myutils.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * SharedPreferences管理类
 *
 * @author jty
 */

public class SPUtils {

    /**
     * 定义一个存在手机里的文件名
     */
    public static final String FILE_NAME = "my_share_data";

    /**
     * 保存数据
     * @param context
     * @param key
     * @param object
     */
    public static void put(Context context, String key, Object object) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
//		editor.commit();
        SharedPreferencesCompat.apply(editor);


    }

    /**
     * 得到数据
     * @param context
     * @param key
     * @param defaultobject
     * @return
     */
    public static Object get(Context context, String key, Object defaultobject) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        if (defaultobject instanceof String) {
            String s = sp.getString(key, (String) defaultobject);
            return s;
        } else if (defaultobject instanceof Integer) {
            int i = sp.getInt(key, (Integer) defaultobject);
            return i;
        } else if (defaultobject instanceof Float) {
            float f = sp.getFloat(key, (Float) defaultobject);
            return f;
        } else if (defaultobject instanceof Long) {
            long l = sp.getLong(key, (Long) defaultobject);
            return l;
        } else if (defaultobject instanceof Boolean) {
            boolean flag = sp.getBoolean(key, (Boolean) defaultobject);
            return flag;
        }

        return null;
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param context
     * @param key
     */
    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     *
     * @param context
     */
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean contains(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.contains(key);

    }

    /**
     * 返回所有的键值对
     *
     * @param context
     * @return
     */
    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getAll();

    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     *
     * @author jty zhy
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找Apply的方法
         *
         * @return
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = Editor.class;

                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        public static void apply(Editor editor) {
            // TODO Auto-generated method stub
            if (sApplyMethod != null) {
                try {
                    sApplyMethod.invoke(editor);
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            editor.commit();
        }
    }


}
