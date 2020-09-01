package com.jty.myutils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
     *
     * @param context      Context
     * @param msg          Show Message default:loading...
     * @param isCancelable is CancelAble
     * @return loading dialog
     */
    public static Dialog loadingDialog1(Context context, String msg, boolean isCancelable) {
        View view = setLoadingView(context, false);
        // loading layout
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.dialog_loading_view);
        // tip text
        TextView tipTextView = (TextView) view.findViewById(R.id.tipTextView);
        if (msg != null) {
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
     *
     * @param context      Context
     * @param msg          Show Message
     * @param isCancelable isCancelable
     * @return
     */
    public static Dialog loadingDialog2(Context context, String msg, boolean isCancelable) {
        View v = setLoadingView(context, true);
        RelativeLayout layout = (RelativeLayout) v.findViewById(R.id.dialog_view);

        //提示文字
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);
        //使用默认值
        if (msg != null) {
            tipTextView.setText(msg);// set info
        }
        Dialog loadingDialog = new Dialog(context, R.style.MyDialogStyle2);
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

    public static View setLoadingView(View view) {
        return view;
    }

    public static View setLoadingView(Context context, int resource) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(resource, null);
    }

    private static View setLoadingView(Context context, boolean isHorizontal) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_loading_1, null);
        if (isHorizontal) view = inflater.inflate(R.layout.dialog_loading_2, null);
        return view;
    }


    /**
     * Close Dialog
     *
     * @param dialog dialog
     */
    public static void closeDialog(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

}
