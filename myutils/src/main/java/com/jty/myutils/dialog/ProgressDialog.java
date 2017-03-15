package com.jty.myutils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jty.myutils.R;


/**
 * Created by JTY on 2017/3/9 0009
 * Loading帮助类
 */

public class ProgressDialog {
    /**
     * Loading Dialog 1
     * @param context Context
     * @param msg Show Message default:loading...
     * @param isCancelable is CancelAble
     * @return loading dialog
     */
    public static Dialog loadingDialog1(Context context, String msg,boolean isCancelable) {
        LayoutInflater inflater = LayoutInflater.from(context);
        //get loading view
        View view = inflater.inflate(R.layout.dialog_loading_1, null);
        // loading layout
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.dialog_loading_view);
        // tip text
        TextView tipTextView = (TextView) view.findViewById(R.id.tipTextView);
        if(msg!=null){
            tipTextView.setText(msg);// set info
        }

        /**
         * Set Dialog's Attributes
         */
        // Create Dialog Style
        Dialog loadingDialog = new Dialog(context, R.style.MyDialogStyle1);
        //is Cancelable
        loadingDialog.setCancelable(isCancelable);
        //is click outside the dialog
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        /**
         * Show Dialog Method
         */
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        loadingDialog.show();

        return loadingDialog;
    }

    /**
     * Loading Dialog 2
     * @param context  Context
     * @param msg  Show Message
     * @param isTransBg isTransBg
     * @param isCancelable isCancelable
     * @return
     */
    public static Dialog loadingDialog2(Context context, String msg, boolean isTransBg, boolean isCancelable) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_loading_2, null);
        RelativeLayout layout = (RelativeLayout) v.findViewById(R.id.dialog_view);

        //图片
        ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
        //提示文字
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);
        //旋转动画
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(context, R.anim.rotate_animation);
        //显示动画
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);
        //使用默认值
        if(msg!=null){
            tipTextView.setText(msg);// set info
        }
        Dialog loadingDialog = new Dialog(context, isTransBg ? R.style.TransDialogStyle : R.style.MyDialogStyle2);    // 创建自定义样式dialog
        loadingDialog.setContentView(layout);
        loadingDialog.setCancelable(isCancelable);
        loadingDialog.setCanceledOnTouchOutside(false);

        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        loadingDialog.show();
        return loadingDialog;
    }


    /**
     * Close Dialog
     * @param dialog dialog
     */
    public static void closeDialog(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    }
