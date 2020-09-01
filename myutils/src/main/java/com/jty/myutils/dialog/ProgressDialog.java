package com.jty.myutils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jty.myutils.R;


/**
 * Created by JTY on 2017/3/9 0009
 * Loading帮助类
 */

public class ProgressDialog {


    /**
     * Loading Dialog
     *
     * @param context context
     * @return loading dialog
     */
    public static Dialog loadingDialog(Context context) {
        return loadingDialog(context, true);
    }

    /**
     * Loading Dialog
     *
     * @param context      Context
     * @param isCancelable is CancelAble
     * @return loading dialog
     */
    public static Dialog loadingDialog(Context context, boolean isCancelable) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_loading, null);
        return loadingDialog(context, view, isCancelable);
    }


    /**
     * Loading Dialog
     *
     * @param context      Context
     * @param msg          Show Message default:loading...
     * @param isCancelable is CancelAble
     * @return loading dialog
     */
    public static Dialog loadingDialog(Context context, String msg, boolean isCancelable) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_loading, null);
        // tip text
        TextView tipTextView = view.findViewById(R.id.tipTextView);
        tipTextView.setText(msg);
        return loadingDialog(context, view, isCancelable);
    }


    /**
     * Loading Dialog
     *
     * @param context      Context
     * @param view         view
     * @param isCancelable is Cancelable
     * @return progress dialog
     */
    public static Dialog loadingDialog(Context context, View view, boolean isCancelable) {

        /**
         * Set Dialog's Attributes
         */
        // Create Dialog Style
        Dialog loadingDialog = new Dialog(context, R.style.MyDialogStyle);
        //is Cancelable
        loadingDialog.setCancelable(isCancelable);
        //is click outside the dialog
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setContentView(view, new LinearLayout.LayoutParams(
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
