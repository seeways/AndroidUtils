package com.jty.myutils.permission

import androidx.fragment.app.FragmentActivity

/**
 * created by Taoyuan on 2021/6/13
 * 对外接口
 */
object PermissionX {
    private const val TAG = "InvisibleFragment"

    /**
     * 对外请求接口
     */
    fun request(
        activity: FragmentActivity,
        vararg permissions: String,
        callback: PermissionCallback
    ) {
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if (existedFragment != null) {
            existedFragment as InvisibleFragment
        } else {
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            // 返回
            invisibleFragment
        }
        fragment.requestNow(callback, *permissions)
    }
}