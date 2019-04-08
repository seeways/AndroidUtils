package com.jty.myutils.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.jty.myutils.R;
import com.jty.myutils.ui.StatusBarCompat;


/**
 * Created by TaoYuan on 2018/3/26 0026.
 *
 * 可继承后自行修改
 */

public abstract class BaseActivity extends Activity {
    /**
     * 标记标题左右两边的类型:文字
     */
    protected final int TITLE_TYPE_TEXT = 0;
    /**
     * 标记标题左右两边的类型:图片
     */
    protected final int TITLE_TYPE_IMG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // 把actvity放到application栈中管理
        AppManager.getAppManager().addActivity(this);
        // 无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 默认着色状态栏
        SetStatusBarColor();

        setContentView(getLayoutId());

        this.initPresenter();
        this.initView();

    }


    /*********************子类实现*****************************/
    //获取布局文件
    public abstract int getLayoutId();

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();

    //初始化view
    public abstract void initView();


    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor() {
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.colorPrimary));
    }






    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);

    }


    /**
     * 左上角的back点击事件
     */
    protected void registerBack() {
        ImageView backImg = (ImageView) findViewById(R.id.toolbar_back);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseActivity.this.finish();
            }
        });
    }

    /**
     * 左上角显示图片
     */
    private ImageView showImg;
    protected void left_show(int resId) {
        showImg = (ImageView) findViewById(R.id.toolbar_left_logo);
        ImageView backImg = (ImageView) findViewById(R.id.toolbar_back);
        hideView(backImg);
        showImg.setImageResource(resId);
    }
    protected void left_show(int resId, int maxHeight, int maxWidth) {
        left_show(resId);
        //调整showImg大小
        showImg.setAdjustViewBounds(true);
        showImg.setMaxWidth(maxWidth);
        showImg.setMaxHeight(maxHeight);
    }

    /**
     * 右上角的点击事件.
     */
    protected void rightDo() {
        RelativeLayout rlRight = (RelativeLayout) findViewById(R.id.toolbar_layout_menu);
        rlRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rightDoWhat();
            }
        });
    }

    protected void rightDoWhat() {
        //toolbar_iv_menu image
        //toolbar_tv_menu text

    }

    /**
     * 显示某控件
     * @param view
     */
    public void showView(View view){
        view.setVisibility(View.VISIBLE);
    }
    /**
     * 隐藏某控件
     * @param view
     */
    public void hideView(View view){
        view.setVisibility(View.GONE);
    }

    // 封装跳转
    public void openActivity(Class<?> c) {
        openActivity(c, null);
    }

    // 跳转 传递数据 bundel
    public void openActivity(Class<?> c, Bundle b) {
        openActivity(c, b, null);
    }

    public void openActivity(Class<?> c, Bundle b, Uri uri) {
        Intent intent = new Intent(this, c);
        if (b != null) {
            intent.putExtras(b);
        }
        if (uri != null) {
            intent.setData(uri);
        }

        startActivity(intent);
    }


}
