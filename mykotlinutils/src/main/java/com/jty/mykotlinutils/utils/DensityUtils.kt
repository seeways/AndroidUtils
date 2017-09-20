package com.jty.mykotlinutils.utils

import android.content.Context
import android.util.TypedValue

/**
 * Created by TaoYuan on 2017/9/20 0020.
 *
 * Screen Density Convert
 *
 * This Class Cannot be Instantiated,
 * Direct Use Static Method Call It In Java.
 * Just Like This : DensityUtils.Companion.dp2px(context,dp);
 */

class DensityUtils private constructor() {

    init {
        //cannot be instantiated
        throw UnsupportedOperationException("cannot be instantiated")
    }

    companion object {

        /**
         * dp转px
         *
         * @param context
         * @param dp
         * @return px
         */
        fun dp2px(context: Context, dp: Float): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, context.resources.displayMetrics).toInt()

        /**
         * sp转px
         *
         * @param context
         * @param sp
         * @return px
         */
        fun sp2px(context: Context, sp: Float): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                sp, context.resources.displayMetrics).toInt()

        /**
         * px转dp
         *
         * @param context
         * @param px
         * @return dp
         */
        fun px2dp(context: Context, px: Float): Float =
                px / context.resources.displayMetrics.density

        /**
         * px转sp
         *
         * @param context
         * @param px
         * @return sp
         */
        fun px2sp(context: Context, px: Float): Float =
                px / context.resources.displayMetrics.scaledDensity
    }


}
