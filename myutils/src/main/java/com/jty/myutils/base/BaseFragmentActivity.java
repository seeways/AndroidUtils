package com.jty.myutils.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import java.util.Stack;

import static java.lang.System.currentTimeMillis;

/**
 * @author TaoYuan
 * @time 2017/7/27 0027
 * @desc
 */

public abstract class BaseFragmentActivity extends FragmentActivity {

    private static Stack<Activity> saveActivitys = new Stack<>();

    private long lastClickTime;

    public static final int clickIntervalTime = 500;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //create and into stack
        saveActivitys.push(this);

        initView();

        initData();

        initListener();
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();


    /**
     * Fragment Replace
     */
    private Fragment currentFragment;
    public void fragmentReplace(int target, Fragment toFragment, boolean backStack) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        String toClassName = toFragment.getClass().getSimpleName();
        if (manager.findFragmentByTag(toClassName) == null) {
            transaction.replace(target, toFragment, toClassName);
            if (backStack) {
                transaction.addToBackStack(toClassName);
            }
            transaction.commit();
        }
    }

    public void FragmentShow(int target, Fragment toFragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (currentFragment != null) {
            transaction.hide(currentFragment);
        }
        String toClassName = toFragment.getClass().getSimpleName();
        // toFragment之前添加使用过->显示出来
        if (manager.findFragmentByTag(toClassName) != null) {
            transaction.show(toFragment);
        } else {// toFragment还没添加使用过->添加上去
            transaction.add(target, toFragment, toClassName);
        }
        transaction.commit();
        // toFragment更新为当前的
        currentFragment = toFragment;
    }

    /**
     * on back
     */
    public void onBack(View view) {
        finish();
    }

    /**
     * Activity跳转
     */
    public void openActivity(Class<?> targetActivity, Bundle bundle, boolean isClose) {
        Intent intent = new Intent(this, targetActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        this.startActivity(intent);
        if (isClose) {
            this.finish();
        }
    }


    /**
     * prevent duplicate click
     */
    public boolean verifyClickTime() {
        long currentTime = currentTimeMillis();
        if (currentTime - lastClickTime < clickIntervalTime) {
            return false;
        }
        lastClickTime = currentTime;
        return true;
    }

    /**
     * Close All Activity
     */
    public static void finishAllActivity() {
        for (int i = 0; i < saveActivitys.size(); i++) {
            //out stack and close
            Activity saveActivity = saveActivitys.pop();
            saveActivity.finish();
        }
    }

    /**
     * double-click to the close
     */
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long currentTime = System.currentTimeMillis();
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (currentTime - exitTime > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = currentTime;
            } else {
                finishAllActivity();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (saveActivitys.contains(this)) {
            saveActivitys.remove(this);
        }

    }
}
