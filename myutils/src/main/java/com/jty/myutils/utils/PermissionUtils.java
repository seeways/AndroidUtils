package com.jty.myutils.utils;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态申请权限
 * 可以在activity中增加如果权限被拒绝后的弹窗，在onRequestPermissionsResult中判断状态
 *
 * @author lixiao
 * @since 2017-10-25 00:00
 */
public class PermissionUtils {

    private static List<String> missingPermission = new ArrayList<>();
    public static final int REQUEST_PERMISSION_CODE = 12345;
    private static final String[] REQUIRED_PERMISSION_LIST = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    public static boolean checkPermission(AppCompatActivity activity) {
        return checkPermission(activity, REQUIRED_PERMISSION_LIST, REQUEST_PERMISSION_CODE);
    }

    public static boolean checkPermission(AppCompatActivity activity, String[] REQUIRED_PERMISSION_LIST, int REQUEST_PERMISSION_CODE) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Check for permissions
            for (String eachPermission : REQUIRED_PERMISSION_LIST) {
                if (ContextCompat.checkSelfPermission(activity, eachPermission) != PackageManager.PERMISSION_GRANTED) {
                    missingPermission.add(eachPermission);
                }
            }

            // Request for missing permissions
            if (!missingPermission.isEmpty()) {
                ActivityCompat.requestPermissions(activity,
                        missingPermission.toArray(new String[missingPermission.size()]),
                        REQUEST_PERMISSION_CODE);
                return false;
            } else {
                return true;
            }
        }

        return true;
    }


    public static boolean HavecheckPermission(AppCompatActivity activity, String[] permissions, int[] grantResults) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (int i = grantResults.length - 1; i >= 0; i--) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    missingPermission.remove(permissions[i]);
                }
            }
            return missingPermission.isEmpty();
        }
        return true;
    }
}
