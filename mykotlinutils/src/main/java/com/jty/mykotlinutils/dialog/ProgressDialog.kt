package com.jty.mykotlinutils.dialog

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView

import com.jty.mykotlinutils.R

/**
 * Created by TaoYuan on 2017/9/20 0020.
 *
 * Dialog Of Progress
 */

object ProgressDialog {
    /**
     * Loading Dialog 1
     * @param context Context
     * @param msg Show Message default:loading...
     * @param isCancelable is CancelAble
     * @return loading dialog
     */
    fun loadingDialog1(context: Context, msg: String?, isCancelable: Boolean): Dialog {
        val inflater = LayoutInflater.from(context)
        //get loading view
        val view = inflater.inflate(R.layout.dialog_loading_1, null)
        // loading layout
        val layout = view.findViewById(R.id.dialog_loading_view) as LinearLayout
        // tip text
        val tipTextView = view.findViewById(R.id.tipTextView) as TextView
        if (msg != null) {
            tipTextView.text = msg// set info
        }

        /**
         * Set Dialog's Attributes
         */
        // Create Dialog Style
        val loadingDialog = Dialog(context, R.style.MyDialogStyle1)
        //is Cancelable
        loadingDialog.setCancelable(isCancelable)
        //is click outside the dialog
        loadingDialog.setCanceledOnTouchOutside(false)
        loadingDialog.setContentView(layout, LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT))
        /**
         * Show Dialog Method
         */
        val window = loadingDialog.window
        val lp = window!!.attributes
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.setGravity(Gravity.CENTER)
        window.attributes = lp
        window.setWindowAnimations(R.style.PopWindowAnimStyle)
        loadingDialog.show()

        return loadingDialog
    }

    /**
     * Loading Dialog 2
     * @param context  Context
     * @param msg  Show Message
     * @param isTransBg isTransBg
     * @param isCancelable isCancelable
     * @return
     */
    fun loadingDialog2(context: Context, msg: String?, isTransBg: Boolean, isCancelable: Boolean): Dialog {

        val inflater = LayoutInflater.from(context)
        val v = inflater.inflate(R.layout.dialog_loading_2, null)
        val layout = v.findViewById(R.id.dialog_view) as RelativeLayout

        //图片
        val spaceshipImage = v.findViewById(R.id.img) as ImageView
        //提示文字
        val tipTextView = v.findViewById(R.id.tipTextView) as TextView
        //旋转动画
        val hyperspaceJumpAnimation = AnimationUtils.loadAnimation(context, R.anim.rotate_animation)
        //显示动画
        spaceshipImage.startAnimation(hyperspaceJumpAnimation)
        //使用默认值
        if (msg != null) {
            tipTextView.text = msg// set info
        }
        val loadingDialog = Dialog(context, if (isTransBg) R.style.TransDialogStyle else R.style.MyDialogStyle2)    // 创建自定义样式dialog
        loadingDialog.setContentView(layout)
        loadingDialog.setCancelable(isCancelable)
        loadingDialog.setCanceledOnTouchOutside(false)

        val window = loadingDialog.window
        val lp = window!!.attributes
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.setGravity(Gravity.CENTER)
        window.attributes = lp
        window.setWindowAnimations(R.style.PopWindowAnimStyle)
        loadingDialog.show()
        return loadingDialog
    }


    /**
     * Close Dialog
     * @param dialog dialog
     */
    fun closeDialog(dialog: Dialog?) {
        if (dialog != null && dialog.isShowing) {
            dialog.dismiss()
        }
    }
}
