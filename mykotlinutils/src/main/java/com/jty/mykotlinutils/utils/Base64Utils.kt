package com.jty.mykotlinutils.utils

import android.util.Base64

/**
 * Created by TaoYuan on 2017/9/20 0020.
 */

object Base64Utils {

    /**
     * Base64 Encode
     */
    fun base64Encode(input: ByteArray): ByteArray {
        return Base64.encode(input, Base64.DEFAULT)
    }

    /**
     * Custom Base64 Flags Encode
     */
    fun base64Encode(input: ByteArray, flags: Int): ByteArray {
        return Base64.encode(input, flags)
    }

    /**
     * Base64 Decode
     */
    fun base64Decode(input: ByteArray): ByteArray {
        return Base64.decode(input, Base64.DEFAULT)
    }

    /**
     * Custom Base64 Flags Decode
     */
    fun base64Decode(input: ByteArray, flags: Int): ByteArray {
        return Base64.decode(input, flags)
    }
}
