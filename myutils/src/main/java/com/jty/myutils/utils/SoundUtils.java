package com.jty.myutils.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import java.io.IOException;

/**
 * created by Taoyuan on 2020/7/3
 */
public class SoundUtils {

    private MediaPlayer player;
    private AssetManager assetManager;

    public SoundUtils(Context context, String fileName) {
        player = new MediaPlayer();
        assetManager = context.getResources().getAssets();
        setDataSource(fileName);
    }

    public void setDataSource(String fileName) {
        try {
            AssetFileDescriptor fileDescriptor = assetManager.openFd(fileName);
            player.setDataSource(
                    fileDescriptor.getFileDescriptor(),
                    fileDescriptor.getStartOffset(),
                    fileDescriptor.getLength());
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (!player.isPlaying())
            player.start();
    }

    public void stop() {
        if (player != null && player.isPlaying()) {
            player.stop();
            player.reset();
        }
    }

    public void destroy() {
        if (player != null) {
            player.release();
            player = null;
        }
        if (assetManager != null) {
            assetManager.close();
            assetManager = null;
        }
    }


    // 获取系统铃声
    public static Ringtone getSystemSound(Context context) {
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        Ringtone mRingtone = RingtoneManager.getRingtone(context, uri);//通过Uri 来获取提示音的实例对象
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            mRingtone.setLooping(false);
        }
        return mRingtone;
    }

    public static Ringtone getSystemSound(Context context, int type) {
        Uri uri = RingtoneManager.getDefaultUri(type);
        Ringtone mRingtone = RingtoneManager.getRingtone(context, uri);//通过Uri 来获取提示音的实例对象
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            mRingtone.setLooping(false);
        }
        return mRingtone;
    }

//    //设置震动
//    public static void setVibrator(Context context) {
//        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
//        try {
//            vibrator.vibrate(500);//震动时长 ms
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            L.e("MissingPermission");
//        }
//
//    }


}
