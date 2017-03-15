package com.jty.myutils.utils;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * 内存管理类
 *
 * @author JTY
 */
public class MemoryInfo {
/*@Override
protected void onCreate(Bundle savedInstanceState) {
    // TODO Auto-generated method stub
    super.onCreate(savedInstanceState);
    //getAvailaleSize();
    //getAllSize();
    Log.i("info","内部可用存储空间 是："+Long.toString(getAvailableInternalMemorySize()/(1024*1024)));
    Log.i("info","内部总共存储空间是："+Long.toString(getTotalInternalMemorySize()/(1024*1024)));
    Log.i("info","外部可用存储空间是："+Long.toString(getAvailableExternalMemorySize()/(1024*1024)));
    Log.i("info","外部总共存储空间是："+Long.toString(getTotalExternalMemorySize()/(1024*1024)));
}*/

    /**
     * 获得可用内存
     *
     * @return
     */
    public long getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();  //获取数据目录
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }

    /**
     * 获得总内存
     *
     * @return
     */
    public long getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }

    //是否可用
    public boolean externalMemoryAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }


    /**
     * 获得可用外存
     *
     * @return
     */
    public long getAvailableExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            return availableBlocks * blockSize;
        } else {
            return -1;
        }
    }

    /**
     * 获得总外存
     *
     * @return
     */
    public long getTotalExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();
            return totalBlocks * blockSize;
        } else {
            return -1;
        }
    }

}
