package com.jty.myutils.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;

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
}
