package com.jty.myutils.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.storage.StorageManager;

import com.jty.myutils.constant.MemoryUtils;
import com.jty.myutils.constant.RegexUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author TaoYuan
 * @time 2017/3/21 0021
 * @desc 文件帮助类
 */

public class FileUtils {
    /**
     * 根据文件路径获取文件
     *
     * @param filePath 文件路径
     * @return 文件
     */
    public static File getFileByPath(String filePath) {
        return StringUtils.isEmpty(filePath) ? null : new File(filePath);
    }

    /**
     * 判断文件是否存在
     *
     * @param filePath 文件路径
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public static boolean isFileExists(String filePath) {
        return isFileExists(getFileByPath(filePath));
    }

    public static boolean isFileExists(File file) {
        return file != null && file.exists();
    }


    /**
     * 判断文件是否存在，不存在则判断是否创建成功
     *
     * @param filePath 文件路径
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsFile(String filePath) {
        return createOrExistsFile(getFileByPath(filePath));
    }
    public static boolean createOrExistsFile(File file) {
        if (file == null) return false;
        // 如果存在，是文件则返回true，是目录则返回false
        if (file.exists()) return file.isFile();
        if (!createOrExistsDir(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**

    /**
     * 重命名文件
     *
     * @param filePath 文件路径
     * @param newName  新名称
     * @return {@code true}: 重命名成功<br>{@code false}: 重命名失败
     */
    public static boolean renameFile(String filePath, String newName) {
        return rename(getFileByPath(filePath), newName);
    }

    public static boolean rename(File file, String newName) {
        // 文件为空返回false
        if (file == null) return false;
        // 文件不存在返回false
        if (!file.exists()) return false;
        // 新的文件名为空返回false
        if (StringUtils.isEmpty(newName)) return false;
        // 如果文件名没有改变返回true
        if (newName.equals(file.getName())) return true;
        File newFile = new File(file.getParent() + File.separator + newName);
        // 如果重命名的文件已存在返回false
        return !newFile.exists()
                && file.renameTo(newFile);
    }

    /**
     * 判断是否是目录
     *
     * @param dirPath 目录路径
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isDir(String dirPath) {
        return isDir(getFileByPath(dirPath));
    }

    public static boolean isDir(File file) {
        return isFileExists(file) && file.isDirectory();
    }

    /**
     * 判断是否是文件
     *
     * @param filePath 文件路径
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isFile(String filePath) {
        return isFile(getFileByPath(filePath));
    }

    public static boolean isFile(File file) {
        return isFileExists(file) && file.isFile();
    }

    /**
     * 如果目录不存在则创建目录
     *
     * @param dirPath 目录路径
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsDir(String dirPath) {
        return createOrExistsDir(getFileByPath(dirPath));
    }

    public static boolean createOrExistsDir(File file) {
        // 如果存在，是目录则返回true，是文件则返回false，不存在则返回是否创建成功
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }


    /**
     * 获取目录下制定后缀名的文件(忽略大小写)
     *
     * @param dirPath     路径
     * @param suffix      后缀名
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     */
    public static List<File> listFilesInDirWithFilter(String dirPath, String suffix, boolean isRecursive) {
        File dir = getFileByPath(dirPath);
        if (isRecursive) return listFilesInDirWithFilter(dir, suffix);
        if (dir == null || !isDir(dir)) return null;
        List<File> list = new ArrayList<>();
        File[] files = dir.listFiles();
        if (files != null && files.length != 0) {
            for (File file : files) {
                if (file.getName().toUpperCase().endsWith(suffix.toUpperCase())) {
                    list.add(file);
                }
            }
        }
        return list;
    }

    private static List<File> listFilesInDirWithFilter(File dir, String suffix) {
        if (dir == null || !isDir(dir)) return null;
        List<File> list = new ArrayList<>();
        File[] files = dir.listFiles();
        if (files != null && files.length != 0) {
            for (File file : files) {
                if (file.getName().toUpperCase().endsWith(suffix.toUpperCase())) {
                    list.add(file);
                }
                if (file.isDirectory()) {
                    list.addAll(listFilesInDirWithFilter(file, suffix));
                }
            }
        }
        return list;
    }


    /**
     * 文件转bytes
     *
     * @param filePath 文件路径
     * @return 字符数组
     */
    public static byte[] File2Bytes(String filePath) {
        return File2Bytes(getFileByPath(filePath));
    }

    public static byte[] File2Bytes(File file) {
        if (file == null) return null;
        try {
            return input2OutputStream((new FileInputStream(file))).toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * inputStream转outputStream
     *
     * @param is 输入流
     * @return outputStream子类
     */
    private static ByteArrayOutputStream input2OutputStream(InputStream is) {
        if (is == null) return null;
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] b = new byte[MemoryUtils.KB];
            int len;
            while ((len = is.read(b, 0, MemoryUtils.KB)) != -1) {
                os.write(b, 0, len);
            }
            return os;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取文件大小
     *
     * @param PathOrURL 文件路径或URL
     * @return 文件大小
     */
    public static String getFileSize(String PathOrURL) {
        boolean isURL = PathOrURL.matches(RegexUtils.REGEX_URL);
        if (isURL) {
            // 创建连接
            HttpURLConnection conn = null;
            try {
                conn = (HttpURLConnection) new URL(PathOrURL).openConnection();
                //处理下载读取长度为-1 问题
                conn.setRequestProperty("Accept-Encoding", "identity");
                conn.connect();
                if (conn.getResponseCode() == 200) {
                    // 获取文件大小
                    int len = conn.getContentLength();
                    return len == -1 ? "" : byte2FitMemorySize(len);
                }
                return "connect error";

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return getFileSize(getFileByPath(PathOrURL));
    }

    public static String getFileSize(File file) {
        long len = getFileLength(file);
        return len == -1 ? "" : byte2FitMemorySize(len);
    }


    /**
     * 获取文件长度
     *
     * @param filePath 文件路径
     * @return 文件大小
     */
    public static long getFileLength(String filePath) {
        return getFileLength(getFileByPath(filePath));
    }

    /**
     * 获取文件长度
     *
     * @param file 文件
     * @return 文件大小
     */
    public static long getFileLength(File file) {
        if (!isFile(file)) return -1;
        return file.length();
    }


    /**
     * 获取全路径中的文件名
     *
     * @param file 文件
     * @return 文件名
     */
    public static String getFileName(File file) {
        if (file == null) return null;
        return getFileName(file.getPath());
    }

    /**
     * 获取文件名
     *
     * @param filePath 文件路径
     * @return 文件名
     */
    public static String getFileName(String filePath) {
        if (StringUtils.isEmpty(filePath)) return filePath;
        int lastSep = filePath.lastIndexOf(File.separator);
        return lastSep == -1 ? filePath : filePath.substring(lastSep + 1);
    }


    /**
     * 获取文件拓展名
     *
     * @param filePath 文件路径
     * @return 文件拓展名
     */
    public static String getFileExtension(String filePath) {
        if (getFileByPath(filePath) == null) return null;
        if (StringUtils.isEmpty(filePath)) return filePath;
        int lastPoi = filePath.lastIndexOf('.');
        int lastSep = filePath.lastIndexOf(File.separator);
        if (lastPoi == -1 || lastSep >= lastPoi) return "";
        return filePath.substring(lastPoi + 1);
    }


    private static final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * byteArr转hexString
     * <p>例如：</p>
     * bytes2HexString(new byte[] { 0, (byte) 0xa8 }) returns 00A8
     *
     * @param bytes 字节数组
     * @return 16进制大写字符串
     */
    private static String bytes2HexString(byte[] bytes) {
        if (bytes == null) return null;
        int len = bytes.length;
        if (len <= 0) return null;
        char[] ret = new char[len << 1];
        for (int i = 0, j = 0; i < len; i++) {
            ret[j++] = hexDigits[bytes[i] >>> 4 & 0x0f];
            ret[j++] = hexDigits[bytes[i] & 0x0f];
        }
        return new String(ret);
    }

    /**
     * 字节数转合适内存大小
     * <p>保留3位小数</p>
     *
     * @param byteNum 字节数
     * @return 合适内存大小
     */
    @SuppressLint("DefaultLocale")
    private static String byte2FitMemorySize(long byteNum) {
        if (byteNum < 0) {
            return "shouldn't be less than zero!";
        } else if (byteNum < MemoryUtils.KB) {
            return String.format("%.3fB", (double) byteNum + 0.0005);
        } else if (byteNum < MemoryUtils.MB) {
            return String.format("%.3fKB", (double) byteNum / MemoryUtils.KB + 0.0005);
        } else if (byteNum < MemoryUtils.GB) {
            return String.format("%.3fMB", (double) byteNum / MemoryUtils.MB + 0.0005);
        } else {
            return String.format("%.3fGB", (double) byteNum / MemoryUtils.GB + 0.0005);
        }
    }


    public String getStoragePath23(Context context, boolean isUsb) {
        String path = "";

        if (Build.VERSION.SDK_INT < 23) return path;


        StorageManager mStorageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
        Class<?> volumeInfoClazz = null;
        Class<?> diskInfoClaszz = null;

        try {
            volumeInfoClazz = Class.forName("android.os.storage.VolumeInfo");
            diskInfoClaszz = Class.forName("android.os.storage.DiskInfo");

            Method StorageManager_getVolumes = Class.forName("android.os.storage.StorageManager").getMethod("getVolumes");
            Method VolumeInfo_GetDisk = volumeInfoClazz.getMethod("getDisk");
            Method VolumeInfo_GetPath = volumeInfoClazz.getMethod("getPath");
            Method DiskInfo_IsUsb = diskInfoClaszz.getMethod("isUsb");
            Method DiskInfo_IsSd = diskInfoClaszz.getMethod("isSd");

            List<Object> List_VolumeInfo = (List<Object>) StorageManager_getVolumes.invoke(mStorageManager);
            for (int i = 0; i < List_VolumeInfo.size(); i++) {
                Object volumeInfo = List_VolumeInfo.get(i);
                Object diskInfo = VolumeInfo_GetDisk.invoke(volumeInfo);


                if (diskInfo == null) continue;

                boolean sd = (boolean) DiskInfo_IsSd.invoke(diskInfo);
                boolean usb = (boolean) DiskInfo_IsUsb.invoke(diskInfo);

                File file = (File) VolumeInfo_GetPath.invoke(volumeInfo);
                // Logger.d("diskinfo="+file.getAbsolutePath()+"; is_usb="+usb+";  is_sd="+sd);

                if (isUsb == usb) {//usb
                    path = file.getAbsolutePath();
                    break;
                } else if (!isUsb == sd) {//sd
                    path = file.getAbsolutePath();
                    break;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

}
