package com.jty.myutils.permission

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment
import java.util.*

/**
 * created by Taoyuan on 2021/6/13
 * 用来请求运行时权限的隐藏的Fragment
 */
class InvisibleFragment : Fragment() {

    /**
     * 运行时权限申请结果回调
     */
    private var callback: (PermissionCallback)? = null

    /**
     * 请求函数
     * @param1:结果回调
     * @param2:可变长的申请权限列表
     */
    fun requestNow(cb: PermissionCallback, vararg permissions: String) {
        callback = cb
        requestPermissions(permissions, 10086)
    }

    /**
     * 回调请求结果
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 10086) {
            /**
             * 权限拒绝列表
             */
            val deniedList = ArrayList<String>()
            for ((index, result) in grantResults.withIndex()) {
                if (result != PackageManager.PERMISSION_GRANTED)
                    deniedList.add(permissions[index])
            }
            callback?.let {
                it(deniedList.isEmpty(), deniedList)
            }
        }
    }
}
// 类型别名
typealias PermissionCallback = (Boolean, List<String>) -> Unit